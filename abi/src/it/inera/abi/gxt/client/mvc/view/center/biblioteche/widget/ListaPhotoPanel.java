package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.mvc.model.PhotoModel;
import it.inera.abi.gxt.client.mvc.view.ListViewDropTargetCustom;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.dnd.DND.Feedback;
import com.extjs.gxt.ui.client.dnd.ListViewDragSource;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.event.DNDListener;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ListaPhotoPanel extends ContentPanel {

	private int id_biblio;
	private boolean modifica;
	
	private BaseListLoader<ListLoadResult<PhotoModel>> loader;
	
	private BibliotecheServiceAsync bibliotecheService;
	
	private ListView<PhotoModel> view;
	private Button removeFromPhotoPanel;
	
	public ListaPhotoPanel() {

		setHeaderVisible(false);
		setAnimCollapse(false);  
		setId("images-view");  
		setWidth(535);  
		setAutoHeight(true);  
		setBodyBorder(false);
		modifica = false;

	}

	public void setList() {
		
		bibliotecheService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);  
		  
		final Text avviso = new Text("Non sono presenti foto per la biblioteca selezionata");
		avviso.setStyleAttribute("fontSize", "12px");
		avviso.hide();
		
		RpcProxy<List<PhotoModel>> proxy = new RpcProxy<List<PhotoModel>>() {
			
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<PhotoModel>> callback) {
				bibliotecheService.getPhotos(id_biblio, callback);
				
			}
		};
		
	    loader = new BaseListLoader<ListLoadResult<PhotoModel>>(proxy, new ModelReader());  
	    
	    final ListStore<PhotoModel> store = new ListStore<PhotoModel>(loader);  

		view = new ListView<PhotoModel>() {  
			@Override  
			protected PhotoModel prepareData(PhotoModel model) {  
				String s = model.get("caption");  
				model.set("shortName", Format.ellipse(s, 15));
				return model;  
			}  

		};  
		
		loader.addListener(Loader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (store.getCount() == 0) {
					avviso.show();
					
				} else {
					avviso.hide();
				}
				
			}
		});
		view.setTemplate(getTemplate());  
		view.setStore(store);
		view.setItemSelector("div.thumb-wrap");
		view.setBorders(false);

		view.addListener(Events.OnDoubleClick, new Listener<ListViewEvent<ModelData>>() {

            @Override
            public void handleEvent(ListViewEvent<ModelData> be) {
            	removeFromPhotoPanel.disable();
                final String imgurl = be.getModel().get("caption");
                final int idPhotoSelected = be.getModel().get("idPhoto");
                
                final Window window = new Window();

				window.setSize(300, 150);
				window.setModal(true);
				window.setHeading("Modifica caption");
				window.setLayout(new FitLayout());
				window.setClosable(false);
				
				/* Modifica: caption -> INIZIO */
				LayoutContainer modifCaptionContainer = new LayoutContainer(new TableLayout(2));
				
				TableData d = new TableData();
				d.setWidth("15%");
				d.setMargin(5);
				d.setPadding(10);

				TableData d2 = new TableData();
				d2.setWidth("60%");
				d2.setMargin(5);
				d2.setPadding(10);
				
				Text captionLabel = new Text("Descrizione:");
				captionLabel.setStyleAttribute("fontSize", "12px");
				modifCaptionContainer.add(captionLabel, d);

				final TextField<String> captionField = new TextField<String>();
				captionField.setAllowBlank(false);
				captionField.setStyleAttribute("fontSize", "12px");
				captionField.setName("caption");
				captionField.setValue(imgurl);
				modifCaptionContainer.add(captionField, d2);
				/* Modifica: caption -> FINE */
				
				window.add(modifCaptionContainer);
				
				final Button save = new Button("Salva");
				save.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						if (captionField.getValue() != null && captionField.getValue().equalsIgnoreCase(imgurl)) {
							/* Il campo non è stato modificato... */
							AbiMessageBox.messageAlertBox("Il campo descrizione non è stato modificato.", "AVVISO");
							
						} else if (captionField.getValue() != null && !(captionField.getValue().equalsIgnoreCase(imgurl))) {
							bibliotecheService.updatePhotoCaption(idPhotoSelected, captionField.getValue(), new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									window.close();
									loader.load();
								}

								@Override
								public void onFailure(Throwable caught) {
									window.close();
								}
							});
						}
					}
				});
				
				final Button cancel = new Button("Annulla");
				cancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
					
					@Override
					public void componentSelected(ButtonEvent ce) {
						window.close();
						view.getSelectionModel().deselectAll();
						
					}
				});
				
				window.addButton(save);
				window.addButton(cancel);
				window.show();

            }
        });
		
		ListViewDragSource source = new ListViewDragSource(view);
		source.setStatusText("{0} foto");

		ListViewDropTargetCustom target = new ListViewDropTargetCustom(view);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.INSERT);
		target.addDNDListener(new DNDListener() {
			@Override
			public void dragDrop(DNDEvent e) {
				super.dragDrop(e);
				System.out.println("Reordered");
				bibliotecheService.updatePhotoOrder(store.getModels(), new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						view.getSelectionModel().deselectAll();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						loader.load();						
					}
				});
			}
		});
		
		add(avviso);
		add(view);  

	}
	
	public BaseListLoader<ListLoadResult<PhotoModel>> getLoader() {
		return this.loader;
	}

	public void setIdBiblioteca(int id_biblio) {
		this.id_biblio = id_biblio;
	}
	
	public ListView<PhotoModel> getView() {
		return this.view;
	}
	
	public void setRemoveButton(Button remove) {
		this.removeFromPhotoPanel = remove;
	}
	
	private native String getTemplate() /*-{ 
    return ['<tpl for=".">', 
    '<div class="thumb-wrap" id="{idPhoto}">', 
    '<div class="thumb"><img src="{uri}" title="{caption}"></div>', 
    '<span class="x-editable">{shortName}</span></div>', 
    '</tpl>', 
    '<div class="x-clear"></div>'].join(""); 
     
    }-*/;  
}
