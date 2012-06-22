package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the utenti database table.
 * 
 */
@Entity
@Table(name="utenti")
public class Utenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_utenti", unique=true, nullable=false)
	private Integer idUtenti;

	@Column(length=255)
	private String cognome;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="data_modifica_password")
	private Date dataModificaPassword;

	@Column(length=50)
	private String email;

	@Column(nullable=false)
	private Boolean enabled;

	@Column(length=50)
	private String fax;

	@Column(length=50)
	private String incarico;

	@Column(nullable=false, length=20)
	private String login;

	@Column(length=255)
	private String nome;

	@Column(length=255)
	private String note;

	@Column(nullable=false, length=50)
	private String password;

	@Column(length=50)
	private String tel;

	//bi-directional many-to-one association to NuovaBiblioteca
	@OneToMany(mappedBy="utenti_gestore")
	private List<NuovaBiblioteca> nuovaBibliotecas_gestore;

	//bi-directional many-to-one association to NuovaBiblioteca
	@OneToMany(mappedBy="utenti_creatore")
	private List<NuovaBiblioteca> nuovaBibliotecas_creatore;

	//uni-directional many-to-many association to Biblioteca
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_utenti"
		, joinColumns={
			@JoinColumn(name="id_utenti", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		)
	private List<Biblioteca> bibliotecasGestite;

	//uni-directional many-to-many association to Profili
    @ManyToMany
	@JoinTable(
		name="utenti_has_profili"
		, joinColumns={
			@JoinColumn(name="id_utenti", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_profili", nullable=false)
			}
		)
	private List<Profili> profilis;
    
    @Column(name="codice_validazione", length=50)
    private String codiceValidazione;

    public Utenti() {
    }

	public Integer getIdUtenti() {
		return this.idUtenti;
	}

	public void setIdUtenti(Integer idUtenti) {
		this.idUtenti = idUtenti;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataModificaPassword() {
		return this.dataModificaPassword;
	}

	public void setDataModificaPassword(Date dataModificaPassword) {
		this.dataModificaPassword = dataModificaPassword;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIncarico() {
		return this.incarico;
	}

	public void setIncarico(String incarico) {
		this.incarico = incarico;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<NuovaBiblioteca> getNuovaBibliotecas_gestore() {
		return this.nuovaBibliotecas_gestore;
	}

	public void setNuovaBibliotecas_gestore(List<NuovaBiblioteca> nuovaBibliotecas_gestore) {
		this.nuovaBibliotecas_gestore = nuovaBibliotecas_gestore;
	}
	
	public List<NuovaBiblioteca> getNuovaBibliotecas_creatore() {
		return this.nuovaBibliotecas_creatore;
	}

	public void setNuovaBibliotecas_creatore(List<NuovaBiblioteca> nuovaBibliotecas_creatore) {
		this.nuovaBibliotecas_creatore = nuovaBibliotecas_creatore;
	}
	
	public List<Biblioteca> getBibliotecasGestite() {
		return this.bibliotecasGestite;
	}

	public void setBibliotecasGestite(List<Biblioteca> bibliotecasGestite) {
		this.bibliotecasGestite = bibliotecasGestite;
	}
	
	public List<Profili> getProfilis() {
		return this.profilis;
	}

	public void setProfilis(List<Profili> profilis) {
		this.profilis = profilis;
	}
	
	public String getCodiceValidazione() {
		return this.codiceValidazione;
	}
	
	public void setCodiceValidazione(String codiceValidazione) {
		this.codiceValidazione = codiceValidazione;
	}
	
}