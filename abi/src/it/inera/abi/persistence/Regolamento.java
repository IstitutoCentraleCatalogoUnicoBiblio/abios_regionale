package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the regolamento database table.
 * 
 */
@Entity
@Table(name="regolamento")
public class Regolamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_regolamento", unique=true, nullable=false)
	private Integer idRegolamento;

	@Column(name="riferimento_normativa", length=255)
	private String riferimentoNormativa;

	@Column(length=255)
	private String url;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public Regolamento() {
    }

	public Integer getIdRegolamento() {
		return this.idRegolamento;
	}

	public void setIdRegolamento(Integer idRegolamento) {
		this.idRegolamento = idRegolamento;
	}

	public String getRiferimentoNormativa() {
		return this.riferimentoNormativa;
	}

	public void setRiferimentoNormativa(String riferimentoNormativa) {
		this.riferimentoNormativa = riferimentoNormativa;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
}