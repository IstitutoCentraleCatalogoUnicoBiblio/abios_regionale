package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the destinazioni_sociali database table.
 * 
 */
@Entity
@Table(name="destinazioni_sociali")
public class DestinazioniSociali implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DestinazioniSocialiPK id;

	@Column(length=255)
	private String note;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to DestinazioniSocialiTipo
    @ManyToOne
	@JoinColumn(name="id_destinazioni_sociali_tipo", nullable=false, insertable=false, updatable=false)
	private DestinazioniSocialiTipo destinazioniSocialiTipo;

    public DestinazioniSociali() {
    }

	public DestinazioniSocialiPK getId() {
		return this.id;
	}

	public void setId(DestinazioniSocialiPK id) {
		this.id = id;
	}
	
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public DestinazioniSocialiTipo getDestinazioniSocialiTipo() {
		return this.destinazioniSocialiTipo;
	}

	public void setDestinazioniSocialiTipo(DestinazioniSocialiTipo destinazioniSocialiTipo) {
		this.destinazioniSocialiTipo = destinazioniSocialiTipo;
	}
	
}