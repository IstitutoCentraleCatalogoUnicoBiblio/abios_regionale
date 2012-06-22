package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the codici_tipo database table.
 * 
 */
@Entity
@Table(name="codici_tipo")
public class CodiciTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_codici", unique=true, nullable=false)
	private Integer idCodici;

	@Column(nullable=false, length=255)
	private String descrizione;

	//bi-directional many-to-one association to Codici
	@OneToMany(mappedBy="codiciTipo")
	private List<Codici> codicis;

    public CodiciTipo() {
    }

	public Integer getIdCodici() {
		return this.idCodici;
	}

	public void setIdCodici(Integer idCodici) {
		this.idCodici = idCodici;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Codici> getCodicis() {
		return this.codicis;
	}

	public void setCodicis(List<Codici> codicis) {
		this.codicis = codicis;
	}
	
}