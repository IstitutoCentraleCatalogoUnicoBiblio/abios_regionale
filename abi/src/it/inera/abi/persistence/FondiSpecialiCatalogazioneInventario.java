package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fondi_speciali_catalogazione_inventario database table.
 * 
 */
@Entity
@Table(name="fondi_speciali_catalogazione_inventario")
public class FondiSpecialiCatalogazioneInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fondi_speciali_catalogazione_inventario", unique=true, nullable=false)
	private Integer idFondiSpecialiCatalogazioneInventario;

	@Column(nullable=false, length=255)
	private String descrizione;

	//bi-directional many-to-one association to FondiSpeciali
	@OneToMany(mappedBy="fondiSpecialiCatalogazioneInventario")
	private List<FondiSpeciali> fondiSpecialis;

    public FondiSpecialiCatalogazioneInventario() {
    }

	public Integer getIdFondiSpecialiCatalogazioneInventario() {
		return this.idFondiSpecialiCatalogazioneInventario;
	}

	public void setIdFondiSpecialiCatalogazioneInventario(Integer idFondiSpecialiCatalogazioneInventario) {
		this.idFondiSpecialiCatalogazioneInventario = idFondiSpecialiCatalogazioneInventario;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<FondiSpeciali> getFondiSpecialis() {
		return this.fondiSpecialis;
	}

	public void setFondiSpecialis(List<FondiSpeciali> fondiSpecialis) {
		this.fondiSpecialis = fondiSpecialis;
	}
	
}