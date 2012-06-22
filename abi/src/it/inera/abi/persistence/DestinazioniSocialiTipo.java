package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the destinazioni_sociali_tipo database table.
 * 
 */
@Entity
@Table(name="destinazioni_sociali_tipo")
public class DestinazioniSocialiTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_destinazioni_sociali", unique=true, nullable=false)
	private Integer idDestinazioniSociali;

	@Column(nullable=false, length=255)
	private String descrizione;

    public DestinazioniSocialiTipo() {
    }

	public Integer getIdDestinazioniSociali() {
		return this.idDestinazioniSociali;
	}

	public void setIdDestinazioniSociali(Integer idDestinazioniSociali) {
		this.idDestinazioniSociali = idDestinazioniSociali;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}