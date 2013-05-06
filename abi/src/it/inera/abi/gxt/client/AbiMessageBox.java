package it.inera.abi.gxt.client;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.MessageBox.MessageBoxType;

/**
 * Classe di utilità per message box personalizzate in base all'operazione eseguita 
 *
 */
public class AbiMessageBox {

	public static final String CONFERMA_RIMOZIONE_VOCE_MESSAGE="La voce verrà rimossa definitivamente dal database. Continuare?";
	public static final String CONFERMA_RIMOZIONE_VOCE_TITLE="RIMOZIONE VOCE";

	public static final String CONFERMA_CREAZIONE_VOCE_MESSAGE="La nuova voce verrà salvata sul database. Continuare?";
	public static final String CONFERMA_CREAZIONE_VOCE_TITLE="CREAZIONE NUOVA VOCE";

	public static final String ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE="Salvataggio effettuato con successo!";
	public static final String ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE="Errore durante il salvataggio della voce!";
	public static final String ESITO_CREAZIONE_VOCE_TITLE="ESITO SALVATAGGIO";

	public static final String ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE="Rimozione effettuata con successo!";
	public static final String ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE="Errore durante la rimozione della voce!";
	public static final String ESITO_RIMOZIONE_FAILURE_COSTRAINT_KEY_VOCE_MESSAGE_GENERICO="Impossibile rimuovere la voce!";

	public static final String ESITO_RIMOZIONE_VOCE_TITLE="ESITO RIMOZIONE";

	public static final String INVALID_PARAMETER_TITLE="PARAMETRO NON VALIDO";
	public static final String INVALID_PARAMETER_MESSAGE="ATTENZIONE: selezionare un valore differente.<br>Il parametro non è valido per il campo: ";

	public static final String INFO_SELECTION_TITLE="INFORMAZIONI SELEZIONE";
	public static final String INFO_SELECTION_MESSAGE="Le voci principali sono relative ad una categoria. Selezionare una voce di secondo livello per continuare con l'operazione.";

	public static final String ESITO_VOCE_GIA_PRESENTE_TITLE="AVVISO VOCE INSERITA";
	public static final String ESITO_VOCE_GIA_PRESENTE_MESSAGE="ATTENZIONE: la voce inserita è già presente in lista.";

	public static final String INSERIMENTO_PARAMETRO_FAILURE_MESSAGE = "ATTENZIONE: parametro inserito non valido!";
	public static final String INSERIMENTO_PARAMETRO_TITLE = "INSERIMENTO PARAMETRO";
	
	public static final String MANCATA_SELEZIONE_TITLE = "AVVISO MANCATA SELEZIONE";
	public static final String MANCATA_SELEZIONE_TO_MODIFY_MESSAGE = "ATTENZIONE: selezionare una voce da modificare.";
	public static final String MANCATA_SELEZIONE_TO_REMOVE_MESSAGE = "ATTENZIONE: selezionare una voce da rimuovere.";
	
	public static final String SELEZIONE_MULTIPLA_TITLE = "AVVISO SELEZIONE MULTIPLA";
	public static final String SELEZIONE_MULTIPLA_TO_MODIFY_MESSAGE = "ATTENZIONE: selezionare SOLO una voce da modificare.";
	public static final String SELEZIONE_MULTIPLA_TO_REMOVE_MESSAGE = "ATTENZIONE: selezionare SOLO una voce da rimuovere.";
	
	public static final String UPLOAD_TITLE = "AVVISO UPLOAD";
	public static final String UPLOAD_MESSAGE = "Il file è stato caricato con successo!";
	public static final String UPLOAD_ERROR_MESSAGE = "ATTENZIONE: si è verificato un errore durante il caricamento del file.";
	
	public static final String CHECK_RICERCA_VIA_CODICE_TITLE = "AVVISO RICERCA VIA CODICE";
	public static final String CHECK_RICERCA_VIA_CODICE_MESSAGE = "ATTENZIONE: il codice inserito non raggiunge la lunghezza richiesta.";
	
	public static final int INFO_BOX_DELAY =5000;//Ritardo in millisecondi
	public static final int INFO_BOX_WIDTH=300;
	public static final int INFO_BOX_HEIGHT=150;
	public static final String MESSAGGIO_WARNING_DATA_MODIFICA_REVISIONE = "Attenzione: per riportare la biblioteca ad uno stato precedente annullando le modifiche, utilizzare la funzione \"Ripristina\" dal menu di catalogazione per non modificare il campo \"Data Modifica\" relativo alle \"Informazioni Catalogatore\". ";
	
	/**
	 * Per gli errori
	 * @param errorMessage
	 * @param title
	 */
	public static void messageErrorAlertBox(String errorMessage, String title) {
				MessageBox m = new MessageBox();
				m.setType(MessageBoxType.ALERT);
				m.setTitle(title);
				m.setMessage(errorMessage);
				m.setIcon(MessageBox.ERROR);
				m.show();
//
//		InfoConfigCustom errorConfig = new InfoConfigCustom(title, errorMessage);
//		errorConfig.setDysplay(INFO_BOX_DELAY);
//		errorConfig.setWidth(INFO_BOX_WIDTH);
//		errorConfig.setWidth(INFO_BOX_HEIGHT);
//		Info.display(errorConfig);
	}

	/**
	 * Caso successo operazione
	 * @param successMessage
	 * @param title
	 */
//	public static void messageSuccessAlertBox(String successMessage, String title) {
		//		MessageBox.info(title, successMessage, null).show();
//		InfoConfigCustom infoConfig = new InfoConfigCustom(title, successMessage);
//		infoConfig.setDysplay(INFO_BOX_DELAY);
//		infoConfig.setWidth(INFO_BOX_WIDTH);
//		infoConfig.setWidth(INFO_BOX_HEIGHT);
//		Info.display(infoConfig);

//	}
	
	public static void messageSuccessAlertBox(String successMessage, String title) {
				MessageBox.info(title, successMessage, null).show();
	}

	public static void messageSuccessAlertBox(String successMessage, String title, Listener<MessageBoxEvent> callback) {
		MessageBox.info(title, successMessage, callback).show();
	}

	/**
	 * MessageBox di richiesta conferma operazione
	 * @param confirmMessage  il testo del corpo della finestra
	 * @param title il testo della barra del titolo
	 * @param listenerCallback callback il listener invocato dopo la chiusura del message box  
	 */
	public static void messageConfirmOperationAlertBox(String confirmMessage, String title,Listener<MessageBoxEvent> listenerCallback) {
		MessageBox.confirm(title, confirmMessage, listenerCallback).show();
	}
	public static void messageConfirmOperationAlertBox(String confirmMessage, String title, int minWidth, Listener<MessageBoxEvent> listenerCallback) {
		MessageBox box = MessageBox.confirm(title, confirmMessage, listenerCallback);
		box.setMinWidth(minWidth);
		box.setMaxWidth(1000);
		box.show();
	}

	public static void messageAlertBox(String successMessage, String title) {
		MessageBox.alert(title, successMessage, null).show();
	}
	
	public static void messageAlertConfirmBox(String alertMessage, String title,Listener<MessageBoxEvent> listenerCallback) {
		
		MessageBox m = MessageBox.confirm(title, alertMessage, listenerCallback);
		m.show();
	}



}
