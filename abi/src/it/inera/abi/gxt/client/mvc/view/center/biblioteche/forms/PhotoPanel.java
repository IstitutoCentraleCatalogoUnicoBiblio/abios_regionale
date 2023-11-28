/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPhotoPanel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RadioButton;

public class PhotoPanel extends ContentPanelForTabItem {

	private Button addPhoto;
	private Button removePhoto;
	private ListaPhotoPanel listaPhotoPanel;

	private String codiceIsil;

	private BibliotecheServiceAsync bibliotecheService;

	public PhotoPanel() {
		super();
		setMonitorWindowResize(true);

		setHeaderVisible(false);
		setBodyBorder(false);

		bibliotecheService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);	

		FieldSet photoSet = new FieldSet();
		Utils.setFieldSetProperties(photoSet, "Foto");

		addPhoto = new Button("Inserisci una nuova foto");
		Utils.setStylesButton(addPhoto);
		addPhoto.setStyleAttribute("marginBottom", "20px");
		addPhoto.setSize(165, 30);
		addPhoto.setIcon(Resources.ICONS.add());
		addPhoto.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Window window = new Window();

				window.setSize(400, 200);
				window.setModal(true);
				window.setHeading("Inserimento nuova foto");
				window.setLayout(new FitLayout());
				window.setClosable(false);

				final FormPanel photoFormPanel = new FormPanel();
				photoFormPanel.setBodyBorder(false);
				photoFormPanel.setBorders(false);
				photoFormPanel.setHeaderVisible(false);
				photoFormPanel.setAction(GWT.getModuleBaseURL() + "photouploadservlet");
				photoFormPanel.setEncoding(Encoding.MULTIPART);
				photoFormPanel.setMethod(Method.POST);

				/* Campo nascosto per il passaggio del codice isil */
				TextField<String> codiceIsilField = new TextField<String>();
				codiceIsilField.setAllowBlank(false);
				codiceIsilField.setName("codiceIsil");
				codiceIsilField.setVisible(false);

				if (codiceIsil != null && codiceIsil.length() > 0) {
					codiceIsilField.setValue(codiceIsil);
				}

				photoFormPanel.add(codiceIsilField);

				/* Foto locale -> INIZIO */
				final Text uriFotoLabel = new Text("File:");
				uriFotoLabel.setStyleAttribute("fontSize", "12px");
				uriFotoLabel.setWidth(72);

				final FileUploadField file = new FileUploadField() {
					/* this is to resolve the "fakepath" issue */
					@Override
					protected void onChange(ComponentEvent ce) {
						final String fullPath = getFileInput().getValue();
						final int lastIndex = fullPath.lastIndexOf('\\');
						final String fileName = fullPath.substring(lastIndex + 1);
						setValue(fileName);
					}
				}; 
				file.setAllowBlank(false);
				file.setName("uploadedfile");
				file.setHideLabel(true);
				file.setWidth(200);
				/* Foto locale -> FINE*/

				/* Foto remota -> INIZIO */
				final Text urlFotoLabel = new Text("Url:");
				urlFotoLabel.setStyleAttribute("fontSize", "12px");
				urlFotoLabel.setWidth(72);
				urlFotoLabel.hide();

				final TextField<String> url = new TextField<String>();
				url.setAllowBlank(true);
				url.setStyleAttribute("fontSize", "12px");
				url.setName("url");
				url.setWidth(200);
				url.hide();
				/* Foto remota -> FINE */ 

				LayoutContainer radioContainer = new LayoutContainer(new TableLayout(4));
				LayoutContainer captionContainer = new LayoutContainer(new TableLayout(2));
				final LayoutContainer pathContainer = new LayoutContainer(new TableLayout(2));
				final LayoutContainer uriContainer = new LayoutContainer(new TableLayout(2));

				TableData d = new TableData();
				d.setWidth("15%");
				d.setMargin(5);
				d.setPadding(10);

				TableData d2 = new TableData();
				d2.setWidth("85%");
				d2.setMargin(5);
				d2.setPadding(10);

				Text fotoLocaleLabel = new Text("Foto locale:");
				fotoLocaleLabel.setStyleAttribute("fontSize", "12px");
				radioContainer.add(fotoLocaleLabel, d);

				final RadioButton fotoLocaleRadio = new RadioButton("pathFoto");
				fotoLocaleRadio.setValue(true);
				fotoLocaleRadio.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						photoFormPanel.remove(uriContainer);
						photoFormPanel.add(pathContainer);
						uriFotoLabel.show();
						file.setAllowBlank(false);
						file.show();
						urlFotoLabel.hide();
						url.setAllowBlank(true);
						url.hide();

						photoFormPanel.layout();
					}
				});
				radioContainer.add(fotoLocaleRadio, d);

				Text fotoRemotaLabel = new Text("Foto remota:");
				fotoRemotaLabel.setStyleAttribute("fontSize", "12px");
				radioContainer.add(fotoRemotaLabel, d);

				final RadioButton fotoRemotaRadio = new RadioButton("pathFoto");

				fotoRemotaRadio.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						photoFormPanel.remove(pathContainer);
						photoFormPanel.add(uriContainer);
						uriFotoLabel.hide();
						file.setAllowBlank(true);
						file.hide();
						urlFotoLabel.show();
						url.setAllowBlank(false);
						url.show();

						photoFormPanel.layout();
					}
				});
				radioContainer.add(fotoRemotaRadio, d);

				/* Foto: caption -> INIZIO */
				Text descrFotoLabel = new Text("Descrizione:");
				descrFotoLabel.setStyleAttribute("fontSize", "12px");
				captionContainer.add(descrFotoLabel, d);

				final TextField<String> descrFotoField = new TextField<String>();
				descrFotoField.setAllowBlank(false);
				descrFotoField.setStyleAttribute("fontSize", "12px");
				descrFotoField.setName("caption");
				descrFotoField.setWidth(200);
				captionContainer.add(descrFotoField, d2);
				/* Foto: caption -> FINE */


				/* FOTO: path -> INIZIO */
				pathContainer.add(uriFotoLabel, d);

				pathContainer.add(file, d2);
				/* FOTO: path -> FINE */


				/* FOTO: url -> INIZIO */
				uriContainer.add(urlFotoLabel, d);

				uriContainer.add(url, d2);
				/* FOTO: url -> FINE */

				photoFormPanel.add(radioContainer);
				photoFormPanel.add(captionContainer);
				photoFormPanel.add(pathContainer);

				window.add(photoFormPanel);

				final Button save = new Button("Inserisci");
				save.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {

						if (!photoFormPanel.isValid()) {
							return;
						}

						if (fotoLocaleRadio.getValue()) {
							photoFormPanel.submit();

						} else {
							String caption = descrFotoField.getValue();

							String uri2 = url.getValue();
							if (!(uri2.contains("http://")) && !(uri2.contains("https://"))) {
								AbiMessageBox.messageErrorAlertBox("ATTENZIONE: l'url inserita non è corretta.", AbiMessageBox.UPLOAD_TITLE);

							} else {
								bibliotecheService.addPhoto(biblioteca.getIdBiblio(), caption, uri2, new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										window.close();
										listaPhotoPanel.getLoader().load();

									}

									@Override
									public void onFailure(Throwable caught) {
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.UPLOAD_ERROR_MESSAGE, AbiMessageBox.UPLOAD_TITLE);
										window.close();

									}
								});
							}
						}

					}
				});

				photoFormPanel.addListener(Events.Submit, new Listener<BaseEvent>() {
					public void handleEvent(BaseEvent be) {
						FormEvent formEvent = (FormEvent) be;
						if (formEvent.getResultHtml() != null && formEvent.getResultHtml().substring(0, 2).equalsIgnoreCase("ok")) {
							String newUri = null;
							if (formEvent.getResultHtml().length() > 2) {
								newUri = formEvent.getResultHtml().substring(5);
							}
							String caption = descrFotoField.getValue();
							bibliotecheService.addPhoto(biblioteca.getIdBiblio(), caption, newUri != null ? newUri : file.getValue(), new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									window.close();
									listaPhotoPanel.getLoader().load();

								}

								@Override
								public void onFailure(Throwable caught) {
									AbiMessageBox.messageErrorAlertBox(AbiMessageBox.UPLOAD_ERROR_MESSAGE, AbiMessageBox.UPLOAD_TITLE);
									window.close();

								}
							});

						} else if (formEvent.getResultHtml() != null && formEvent.getResultHtml().equalsIgnoreCase("error")) {
							AbiMessageBox.messageErrorAlertBox(AbiMessageBox.UPLOAD_ERROR_MESSAGE, AbiMessageBox.UPLOAD_TITLE);
							window.close();
						}
					};
				});

				final Button cancel = new Button("Annulla");
				cancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						window.close();
					}
				});

				FormButtonBinding buttonBinding = new FormButtonBinding(photoFormPanel);
				buttonBinding.addButton(save);

				window.addButton(save);
				window.addButton(cancel);
				window.show();
			}
		});

		LayoutContainer buttonContainer = new LayoutContainer(new TableLayout(2));

		TableData d = new TableData();
		d.setMargin(5);
		d.setPadding(10);

		buttonContainer.add(addPhoto, d);

		removePhoto = new Button("Rimuovi foto");
		Utils.setStylesButton(removePhoto);
		removePhoto.setStyleAttribute("marginBottom", "20px");
		removePhoto.setSize(165, 30);
		removePhoto.setIcon(Resources.ICONS.delete());
		removePhoto.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final int idPhoto;
				if (listaPhotoPanel.getView().getSelectionModel().getSelectedItem() != null) {
					idPhoto = listaPhotoPanel.getView().getSelectionModel().getSelectedItem().getIdPhoto();
					bibliotecheService.removePhoto(biblioteca.getIdBiblio(), idPhoto, new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
							listaPhotoPanel.getLoader().load();
							removePhoto.disable();
						}

						@Override
						public void onFailure(Throwable caught) {
							AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
							removePhoto.disable();
						}
					});	
				}

			}
		});
		removePhoto.disable();
		buttonContainer.add(removePhoto, d);

		photoSet.add(buttonContainer);

		listaPhotoPanel = new ListaPhotoPanel();
		listaPhotoPanel.setList();

		listaPhotoPanel.getView().addListener(Events.OnClick, new Listener<ListViewEvent<ModelData>>() {

			public void handleEvent(ListViewEvent<ModelData> be) {  
				if (removePhoto != null) {
					removePhoto.enable();
				}
			}  

		});  

		photoSet.add(listaPhotoPanel);

		add(photoSet);
	}

	public void setFieldsValues() {

		UIWorkflow.hideView(addPhoto);
		UIWorkflow.hideView(removePhoto);

		listaPhotoPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaPhotoPanel.setRemoveButton(removePhoto);
		listaPhotoPanel.getLoader().load();

		UIWorkflow.listViewEnableEvent(listaPhotoPanel.getView());

		if (biblioteca.getCodice() != null && biblioteca.getCodice().length() > 0) {
			codiceIsil = biblioteca.getCodice();
		}
	}

}
