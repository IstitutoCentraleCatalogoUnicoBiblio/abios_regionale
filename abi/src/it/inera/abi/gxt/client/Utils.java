package it.inera.abi.gxt.client;

import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.CheckBoxSelectionModelCustom;

import java.util.List;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.KeyCodes;

/**
 * Classi di utilità per la UI
 * @author reschini
 *
 */
/**
 * @author davide
 *
 */
public class Utils {

	private SimpleComboBox<String> combo;

	/**
	 * Seleziona la prima entry del tree menu.
	 * Usato nella init del menu (in modo che appena il menu viene creato ALMENO il primo elemento sia selezionato),
	 * e nella exapand
	 * @param tree
	 */
	public static void selectFirstMenuEntry(final TreePanel<MenuItem> tree) {
		MenuItem mi = tree.getStore().getChild(0);
		tree.getSelectionModel().select(mi, false);
	}

	/**
	 * Crea un tooltip
	 * @param title
	 * @param text
	 * @param anchor
	 * @return
	 */
	public static ToolTipConfig getTooltip(String title, String text, String anchor) {
		ToolTipConfig config = new ToolTipConfig();  
		config.setTitle(title);  
		config.setText(text);  
		config.setMouseOffset(new int[] {0, 0});  
		config.setAnchor(anchor);
		return config;
	}


	/**
	 * Inserisce il tag html <br> nel primo spazio disponibile dopo ogni 
	 * intervallo di caratteri di lunghezza intervalloBr
	 * @param value
	 * @param intervalloBr
	 * @return encoded - la stringa codificata
	 */
	public static String insertBRtag(String value, int intervalloBr){
		if(value!=null){
		int lunghezzaTot=value.length();

		if(lunghezzaTot>intervalloBr){
			String encoded="";
			int avanzamenti=0;
			int tolleranza=4;
			int tollerati=0;
			for (int i = 0; i <lunghezzaTot; i++) {

				if(avanzamenti>=intervalloBr){
					if(value.charAt(i)==' '){
						encoded+="<br/> ";
						avanzamenti=0;
					}else{
						tollerati++;
						if(tollerati>tolleranza){
							encoded+="-<br/> ";
							avanzamenti=0;
							tollerati=0;
						}
					}
				}
				encoded+=value.charAt(i);

				avanzamenti++;
			}
			return encoded;
		}
		return value;
		}
		return "";
	}
	
	/**
	 * Inserisce il tag html <br> nel primo spazio disponibile dopo ogni 
	 * intervallo di caratteri di lunghezza intervalloBr
	 * Utlizzato nei MenuItem
	 * @param value
	 * @param intervalloBr
	 * @return encoded - la stringa codificata
	 */
	public static String insertBRtagMenuItems(String value, int intervalloBr){
		int lunghezzaTot=value.length();

		if(lunghezzaTot>intervalloBr){
			String encoded="";
			int avanzamenti=0;
			int tolleranza=2;
			int tollerati=0;
			for (int i = 0; i <lunghezzaTot; i++) {

				if(avanzamenti>=intervalloBr){
					if(value.charAt(i)==' '){
						encoded+="<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						avanzamenti=0;
					}else{
						tollerati++;
						if(tollerati>tolleranza){
							encoded+="-<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							avanzamenti=0;
							tollerati=0;
						}
					}
				}
				encoded+=value.charAt(i);

				avanzamenti++;
			}
			return encoded;
		}
		return value;
	}


	/**
	 * Rimuove il tag hmtl <br/> dalla stringa value
	 * @param value
	 * @return
	 */
	public static String removeBRtag(String value){
		String	decoded=value.replace("<br/>", " ");
		return decoded;
	}

	/**Codifica i tag  html " " < > in &#"tag";
	 * @param s
	 * @return
	 */
	public static String encodeHTML(String s)	{
		StringBuffer out = new StringBuffer();
		for(int i=0; i<s.length(); i++){
			char c = s.charAt(i);
			if(c > 127 || c=='"' || c=='<' || c=='>')
			{
				out.append("&#"+(int)c+";");
			}
			else
			{
				out.append(c);
			}
		}
		return out.toString();
	}

	/**Setta ad un testo 'text' gli attributi custom:fontSize , fontWeigth e fontColorEx passati come parametro;
	 * @param text
	 * @param fontSize
	 * @param fontWeigth
	 * @param fontColorEx
	 */
	public static void setFontSizeWeightStylesCustom(BoxComponent text, String fontSize, String fontWeigth, String fontColorEx){
		text.setStyleAttribute("fontSize", fontSize);
		text.setStyleAttribute("fontWeight", fontWeigth);
		text.setStyleAttribute("color", fontColorEx);
	}
	/**Setta un testo di colore nero, dimensione testo 12px e stile del font normal
	 * @param text
	 */
	public static void setFontColorStyleRed(BoxComponent text){
//		setFontSizeWeightStylesCustom(text, "12px", "normal", "#FF0000");
		setFontSizeWeightStylesCustom(text, "14px", "bold", "#FF0000");
	}
	/**Setta la un testo di colore rosso, dimensione testo 12px e stile del font normal
	 * @param text
	 */
	public static void setFontColorStyleBlack(BoxComponent text){
//		setFontSizeWeightStylesCustom(text, "12px", "normal", "#000000");
		setFontSizeWeightStylesCustom(text, "14px", "bold", "#000000");

	}

	/**Setta le caratteristiche grafiche per i form panel della catalogazione contenuti nei fieldset
	 * quali bordo, header non visibile, e margine inferiore
	 * @param formPanel
	 */
	public static void setFormStyleProperties(FormPanel formPanel) {
		formPanel.setHeaderVisible(false);
		formPanel.setBorders(true);
		formPanel.setBodyBorder(true);
		formPanel.setStyleAttribute("marginBottom", "10");
		formPanel.setStyleAttribute("font-weight", "bold");
		formPanel.setWidth(750);
	}
	/**Setta le caratteristiche grafiche per i fieldset della catalogazione
	 * @param fieldSet
	 * @param header
	 */
	public static void setFieldSetProperties(FieldSet fieldSet, String header) {
		fieldSet.setHeading("<span style=\"font-size: 18px;color:#4172D3; \">"+header+"</span>");
		fieldSet.setCollapsible(true);
		fieldSet.setStyleAttribute("border-width", "3px");

	}
	/**Aggiunge un listener ad un field di tipo testo, e se modificato ne cambia il colore
	 * della textLabel (rosso), se il valore modificato ritorna uguale al valore originale la label
	 * torna al colore di non modificato (nero)
	 * @param field
	 * @param textLabel
	 */
	public static void addListenerToChangeLabelColorIfModifiedTextField(final Field<String> field, final BoxComponent textLabel ) {
		field.addListener(Events.KeyUp,new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (field.getValue() != null && field.getOriginalValue() != null) {
					if (field.getValue().equalsIgnoreCase(field.getOriginalValue())) {
						setFontColorStyleBlack(textLabel);
					}
					else {
						setFontColorStyleRed(textLabel);
					}
				}
				else if (field.getValue() == null && field.getOriginalValue() == null) {
					setFontColorStyleBlack(textLabel);
				}
				else {/* Casi (field.getValue() != null && field.getOriginalValue() == null)
				 		e (field.getValue() == null && field.getOriginalValue() != null) */
					setFontColorStyleRed(textLabel);
				}
			}
		});
	}

	/**Aggiunge un listener ad un TextField di tipo double, e se modificato ne cambia il colore
	 * della textLabel (rosso), se il valore modificato ritorna uguale al valore originale la label
	 * torna al colore di non modificato (nero)
	 * @param field
	 * @param textLabel
	 */
	public static void addListenerToChangeLabelColorIfModifiedNumberFieldDouble(final NumberField field, final BoxComponent textLabel ) {
		field.addListener(Events.KeyUp,new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (field.getValue().doubleValue() == field.getOriginalValue().doubleValue()) {
					setFontColorStyleBlack(textLabel);
				}
				else {
					setFontColorStyleRed(textLabel);
				}
			}
		});
	}

	/**Aggiunge un listener ad un NumberField di tipo int, e se modificato ne cambia il colore
	 * della textLabel (rosso), se il valore modificato ritorna uguale al valore originale la label
	 * torna al colore di non modificato (nero)
	 * @param field
	 * @param textLabel
	 */
	public static void addListenerToChangeLabelColorIfModifiedNumberFieldInt(final NumberField field, final BoxComponent textLabel ) {
		field.addListener(Events.KeyUp,new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (field.getValue() != null && field.getOriginalValue() != null) {
					if (field.getValue().intValue() == field.getOriginalValue().intValue()) {
						setFontColorStyleBlack(textLabel);
					}
					else {
						setFontColorStyleRed(textLabel);
					}
				}
				else if (field.getValue() == null && field.getOriginalValue() == null) {
					setFontColorStyleBlack(textLabel);
				}
				else {/* Casi (field.getValue() != null && field.getOriginalValue() == null)
			 		e (field.getValue() == null && field.getOriginalValue() != null) */
					setFontColorStyleRed(textLabel);
				}
			}
		});
	}

	/**Aggiunge un listener ad un RadioGroup, e se modificato ne cambia il colore
	 * della textLabel (rosso), se il valore modificato ritorna uguale al valore originale la label
	 * torna al colore di non modificato (nero)
	 * @param group
	 * @param textLabel
	 */
	public static void addListenerToChangeLabelColorIfModifiedRadioGroup(final RadioGroup group, final BoxComponent textLabel) {
		group.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (group.getValue() != null && group.getOriginalValue() != null) {
					if(((String)group.getValue().getBoxLabel()).equalsIgnoreCase((String)group.getOriginalValue().getBoxLabel())) {
						Utils.setFontColorStyleBlack(textLabel);
					}
					else {
						Utils.setFontColorStyleRed(textLabel);
					}
				}
			}
		});
	}
	
	/**
	 * Setta la label di un oggetto TextArea di colore rosso
	 * @param t
	 * @param label
	 */
	
	public static void setFontColorStyleBlackTextArea(TextArea t, String label) {
		t.setFieldLabel("<span style=\"font-size: 14px;font-weigth:bold; color: #000000; \">"+label+"</span>");
	}
	/**
	 * Setta la label di un oggetto TextArea di colore nero
	 * @param t
	 * @param label
	 */
	public static void setFontColorStyleRedTextArea(TextArea t, String label) {
		t.setFieldLabel("<span style=\"font-size: 14px;font-weigth:bold; color: #FF0000; \">"+label+"</span>");
	}
	/**Aggiunge un listener ad un TextArea, e se modificato ne cambia il colore
	 * della textLabel (rosso), se il valore modificato ritorna uguale al valore originale la label
	 * torna al colore di non modificato (nero)
	 * @param bibliografiaBox
	 * @param label
	 */
	public static void addListenerToChangeLabelColorIfModifiedTextArea(final TextArea bibliografiaBox, final String label) {
		bibliografiaBox.addListener(Events.KeyUp, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (bibliografiaBox.getValue() != null && bibliografiaBox.getOriginalValue() != null) {
					if (((String) bibliografiaBox.getValue()).equalsIgnoreCase((String) bibliografiaBox.getOriginalValue())) {
						Utils.setFontColorStyleBlackTextArea(bibliografiaBox, label);
					}
					else {
						Utils.setFontColorStyleRedTextArea(bibliografiaBox, label);
					}
				}
				else if (bibliografiaBox.getValue() == null && bibliografiaBox.getOriginalValue() == null) {
					Utils.setFontColorStyleBlackTextArea(bibliografiaBox, label);
				}
				else {
					Utils.setFontColorStyleRedTextArea(bibliografiaBox, label);
				}
			}
		});
	}
	/**Aggiunge un listener ad un SimpleComboBox<String>, e se modificato ne cambia il colore
	 * della textLabel (rosso), se il valore modificato ritorna uguale al valore originale la label
	 * torna al colore di non modificato (nero)
	 * 
	 * @param combo
	 * @param comboLabel
	 */
	public static void addListenerToChangeLabelColorIfModifiedSimpleComboboxString(final SimpleComboBox<String> combo, final Text comboLabel ) {
		
		combo.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				if(se.getSelectedItem()!=null && combo.getOriginalValue()!=null){
					if((se.getSelectedItem().getValue()).equalsIgnoreCase(combo.getOriginalValue().getValue())){
						Utils.setFontColorStyleBlack(comboLabel);
					}else{
						Utils.setFontColorStyleRed(comboLabel);
					}
				}
			}
		});
	}
	
	
	/**
	 * Controlla che il numero degli elementi del plugin CheckBoxSelectionModel sia
	 * lo stesso di quelli contenuti nello store e cambia la label del bottone seleziona tutto di conseguenza
	 */
	public static void changeSelectAllText(CheckBoxSelectionModelCustom<?> checkPlugin, ListStore<?> store, Button selectAll) {
		if (checkPlugin.getSelectedItems().size() == store.getCount() && store.getCount()>0){
			selectAll.setText("Deseleziona tutto");
		}else {
			selectAll.setText("Seleziona tutto");
		}
	}
	
	/**
	 * Controlla che il numero degli elementi del plugin CheckBoxSelectionModel sia
	 * lo stesso di quelli contenuti nello store e visualizza/nasconde i bottoni di conseguenza
	 * Metodo utilizzao dove si sono verificati problemi di allineamento dell'icona del
	 * bottone nella modifica dinamica del testo nel metodo changeSelectAllText(...)
	 */
	public static void changeSelectDeselectAllButton(CheckBoxSelectionModelCustom<?> checkPlugin, ListStore<?> store, Button selectAll, Button deselectAll) {
		if (checkPlugin.getSelectedItems().size() == store.getCount()){
//			selectAll.setText("Deseleziona tutto");
			selectAll.hide();
			deselectAll.show();
		}else {
//			selectAll.setText("Seleziona tutto");
			selectAll.show();
			deselectAll.hide();
		}
	}
	/**
	 * Rimuove  gli id dele biblioteche selezionate solo se correntemente caricate nello store
	 * @param bibliotecheSelectedList
	 * @param store
	 */
	public static void removeIdsOfBibliosInAuctualListOnDeselectAll(List<Integer> bibliotecheSelectedList,ListStore<BiblioModel> store){
		if (bibliotecheSelectedList.size() != 0) {			
			Integer foundIdx=null;
			List<BiblioModel> tmplistuser = (List<BiblioModel>) store.getModels();
			for (BiblioModel biblioModel : tmplistuser) {

				for (Integer idEntry : bibliotecheSelectedList) {
					if (biblioModel.getIdBiblio() == idEntry.intValue()){
						foundIdx=bibliotecheSelectedList.indexOf(idEntry);
					}
				}
				if(foundIdx!=null){
					bibliotecheSelectedList.remove(foundIdx.intValue());
					foundIdx=null;
				}
			}
		}
	}
	/**
	 * Rimuove  gli id dei comuni selezionati solo se correntemente caricati nello store
	 * @param comuniSelectedList
	 * @param store
	 */
	public static void removeIdsOfComunisInAuctualListOnDeselectAll(List<Integer> comuniSelectedList,ListStore<ComuniModel> store){
		if (comuniSelectedList.size() != 0) {			
			Integer foundIdx=null;
			List<ComuniModel> tmplistuser = (List<ComuniModel>) store.getModels();
			for (ComuniModel comuniModel : tmplistuser) {

				for (Integer idEntry : comuniSelectedList) {
					if (comuniModel.getIdComune() == idEntry.intValue()){
						foundIdx=comuniSelectedList.indexOf(idEntry);
					}
				}
				if(foundIdx!=null){
					comuniSelectedList.remove(foundIdx.intValue());
					foundIdx=null;
				}
			}
		}
	}

	/**
	 * Aggiunge il un listener per l'evento della pressione del tasto "Invio"
	 * @param formPanel panel dal quale ricavare la lista dei fields sui quali si aggiunge il listener
	 * @param submitButton il pulsante su cui lanciare l'evento di submit  (Events.Select)
	 */
	public static void addKeyListenerForEnter(final Button submitButton,final FormPanel formPanel ) {
		KeyListener listener = new KeyListener() {
			@Override
			public void componentKeyPress(ComponentEvent event) {
				if(event.getKeyCode()==KeyCodes.KEY_ENTER){
					if(formPanel.isValid()){
						submitButton.fireEvent(Events.Select);
					}
				}
				super.componentKeyPress(event);
			}
		};

		for(Field<?> tmpField:formPanel.getFields()){
			//Se è un field di tipo RadioGroup
			if(tmpField instanceof RadioGroup){
				RadioGroup tmpRadioGroup = (RadioGroup) tmpField;
				//Scorro la lista di Radio contenuta e assofio il listener ad ognuno
				//Non è necessario per i Checkbox
				for(Field<?>  tmpRadio:tmpRadioGroup.getAll()){
					if((tmpRadio.hasListeners(Events.KeyPress))==false){
					tmpRadio.addKeyListener(listener);
					}
				}
			}
			//Altrimenti associo semplicemente il listener al field
			else if((tmpField instanceof TextArea)==false){
				if((tmpField.hasListeners(Events.KeyPress))==false){
					tmpField.addKeyListener(listener);
				}
			}
		}
	}
	
	
	/**
	 * Rimuove il  listener per l'evento della pressione del tasto "Invio" in modo
	 * che non sia attivo nel caso del readOnly
	 * @param formPanel panel dal quale ricavare la lista dei fields sui quali rimuovere il listener
	 */
	public static void removeKeyListenerForEnter(FormPanel formPanel) {

		for(Field<?> tmpField:formPanel.getFields()){
			KeyListener tmpKeyListener= null;
			//Se è un field di tipo RadioGroup
			if(tmpField instanceof RadioGroup){
				RadioGroup tmpRadioGroup = (RadioGroup) tmpField;
				//Scorro la lista di Radio contenuta nel RadioGroup
				//Non è necessario per i Checkbox
				for(Field<?>  tmpRadios:tmpRadioGroup.getAll()){
					//Se il keylistener è già stato associato
					if(tmpRadios.hasListeners(Events.KeyPress)){
						//rimuovo il listener di ognuni Radio
						tmpKeyListener= (KeyListener) tmpRadios.getListeners(Events.KeyPress).get(0);
						tmpRadios.removeKeyListener(tmpKeyListener);
					}
				}
			}
			//Altrimenti rimuovo semplicemente il listener al field
			else if((tmpField instanceof TextArea)==false){
				//Se il keylistener è già stato associato
				if(tmpField.hasListeners(Events.KeyPress)){
					//rimuovo il listener
					tmpKeyListener= (KeyListener) tmpField.getListeners(Events.KeyPress).get(0);
					tmpField.removeKeyListener(tmpKeyListener);
				}
			}
		}
	}
	
	/**
	 * 
	 * Setta il bordo dei pulsanti
	 * @param button 
	 * 
	 */
	public static void setStylesButton(Button button) {
		button.setStyleAttribute("border", "2px solid #8FC0FC");
		button.setStyleAttribute("-moz-border-radius", "3px");
		button.setStyleAttribute("border-radius", "3px");
		button.setStyleAttribute("background-color", "#8FC0FC");
	}
	
}
