package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ente database table.
 * 
 */
@Entity
@Table(name="ente")
public class Ente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ente", unique=true, nullable=false)
	private Integer idEnte;

	@Column(name="asia_asip", length=11)
	private String asiaAsip;

	@Column(name="codice_fiscale", length=16)
	private String codiceFiscale;

	@Column(length=255)
	private String denominazione;

	@Column(name="partita_iva", length=11)
	private String partitaIva;

	//uni-directional many-to-one association to EnteObiettivo
    @ManyToOne
	@JoinColumn(name="id_ente_obiettivo", nullable=false)
	private EnteObiettivo enteObiettivo;

	//uni-directional many-to-one association to EnteTipologiaAmministrativa
    @ManyToOne
	@JoinColumn(name="id_ente_tipologia_amministrativa", nullable=false)
	private EnteTipologiaAmministrativa enteTipologiaAmministrativa;

	//uni-directional many-to-one association to Stato
    @ManyToOne
	@JoinColumn(name="id_stato", nullable=false)
	private Stato stato;

    public Ente() {
    }

	public Integer getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getAsiaAsip() {
		return this.asiaAsip;
	}

	public void setAsiaAsip(String asiaAsip) {
		this.asiaAsip = asiaAsip;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public EnteObiettivo getEnteObiettivo() {
		return this.enteObiettivo;
	}

	public void setEnteObiettivo(EnteObiettivo enteObiettivo) {
		this.enteObiettivo = enteObiettivo;
	}
	
	public EnteTipologiaAmministrativa getEnteTipologiaAmministrativa() {
		return this.enteTipologiaAmministrativa;
	}

	public void setEnteTipologiaAmministrativa(EnteTipologiaAmministrativa enteTipologiaAmministrativa) {
		this.enteTipologiaAmministrativa = enteTipologiaAmministrativa;
	}
	
	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	
}