package it.inera.abi.dao.mapping;

import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.CataloghiCollettiviZonaTipo;
import it.inera.abi.persistence.CataloghiSupportoDigitaleTipo;
import it.inera.abi.persistence.CatalogoGeneraleTipo;
import it.inera.abi.persistence.ContattiTipo;
import it.inera.abi.persistence.DepositiLegaliTipo;
import it.inera.abi.persistence.DestinazioniSocialiTipo;
import it.inera.abi.persistence.Dewey;
import it.inera.abi.persistence.EnteObiettivo;
import it.inera.abi.persistence.EnteTipologiaAmministrativa;
import it.inera.abi.persistence.FondiSpecialiCatalogazioneInventario;
import it.inera.abi.persistence.IndicizzazioneClassificata;
import it.inera.abi.persistence.IndicizzazioneSoggetto;
import it.inera.abi.persistence.NormeCatalogazione;
import it.inera.abi.persistence.PatrimonioSpecializzazioneCategoria;
import it.inera.abi.persistence.PrestitoInterbibliotecarioModo;
import it.inera.abi.persistence.PrestitoLocaleMaterialeEscluso;
import it.inera.abi.persistence.PrestitoLocaleUtentiAmmessi;
import it.inera.abi.persistence.RiproduzioniTipo;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;
import it.inera.abi.persistence.StatoCatalogazioneTipo;
import it.inera.abi.persistence.Thesaurus;
import it.inera.abi.persistence.TipologiaFunzionale;

import java.util.Hashtable;

public class DtoJpaMapping {

	private static Hashtable<Integer, Class<?>> dynaClasses = null;

	public static Object dto2DynaRecord(DynaTabDTO dto,boolean modifica) {

		if (dto.getIdTabella() == /*4*/PATRIMONIO_LIBRARIO_GRANDI_VOCI_INDEX) {
			PatrimonioSpecializzazioneCategoria psc = new PatrimonioSpecializzazioneCategoria();
			if(modifica)
				psc.setIdPatrimonioSpecializzazioneCategoria(dto.getId());
			if(dto.getValue()!=null){
				psc.setDescrizione(dto.getValue());
			}
			return psc;
		}

		if (dto.getIdTabella() == /*7*/DESTINAZIONI_SOCIALI_TIPOLOGIE_INDEX) {
			DestinazioniSocialiTipo dst = new DestinazioniSocialiTipo();
			if(modifica)
				dst.setIdDestinazioniSociali(dto.getId());
			if(dto.getValue()!=null){
				dst.setDescrizione(dto.getValue());
			}
			return dst;
		}

		if (dto.getIdTabella() == /*9*/ACCESSO_MODALITA_INDEX) {
			AccessoModalita am = new AccessoModalita();
			if(modifica)
				am.setIdAccessoModalita(dto.getId());
			if(dto.getValue()!=null){
				am.setDescrizione(dto.getValue());
			}
			return am;
		}

		if (dto.getIdTabella() == /*10*/ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX) {
			EnteTipologiaAmministrativa eta = new EnteTipologiaAmministrativa();
			if(modifica)
				eta.setIdEnteTipologiaAmministrativa(dto.getId());
			if(dto.getValue()!=null){
				eta.setDescrizione(dto.getValue());
			}
			return eta;
		}

		if (dto.getIdTabella() == /*11*/ENTI_FUNZIONI_OBIETTIVI_INDEX) {
			EnteObiettivo efo = new EnteObiettivo();
			if(modifica)
				efo.setIdEnteObiettivo(dto.getId());
			if(dto.getValue()!=null){
				efo.setDescrizione(dto.getValue());
			}
			return efo;
		}



		if (dto.getIdTabella() == /*12*/TIPOLOGIE_FUNZIONALI_INDEX) {
			TipologiaFunzionale tpf = new TipologiaFunzionale();
			if(modifica)
				tpf.setIdTipologiaFunzionale(dto.getId());
			if(dto.getValue()!=null){
				tpf.setDescrizione(dto.getValue());
			}
			return tpf;
		}


		if (dto.getIdTabella() == /*13*/CATALOGAZIONE_STATI_BIBLIOTECHE_INDEX) {
			StatoCatalogazioneTipo stato = new StatoCatalogazioneTipo();

			if(modifica)
				stato.setIdStatoCatalogazioneTipo(dto.getId());
			if(dto.getValue()!=null){
				stato.setDescrizione(dto.getValue());			
			}
			return stato;
		}	

		if (dto.getIdTabella() == /*14*/THESAURUS_INDEX) {
			Thesaurus t = new Thesaurus();
			if(modifica)
				t.setIdThesaurus(dto.getId());
			if(dto.getValue()!=null){
				t.setDescrizione(dto.getValue());
			}
			return t;
		}

		if (dto.getIdTabella() == /*16*/CONTATTI_TIPI_INDEX) {
			ContattiTipo ct = new ContattiTipo();
			if(modifica)
				ct.setIdContattiTipo(dto.getId());
			if(dto.getValue()!=null){
				ct.setDescrizione(dto.getValue());
			}
			return ct;
		}

		if (dto.getIdTabella() == /*17*/RIPRODUZIONI_TIPI_INDEX) {
			RiproduzioniTipo rt = new RiproduzioniTipo();
			if(modifica)
				rt.setIdRiproduzioniTipo(dto.getId());
			if(dto.getValue()!=null){
				rt.setDescrizione(dto.getValue());
			}
			return rt;
		}

		if (dto.getIdTabella() == /*19*/ SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE_INDEX) {
			ServiziInformazioniBibliograficheModalita sibm = new ServiziInformazioniBibliograficheModalita();
			if(modifica)
				sibm.setIdServiziInformazioniBibliograficheModalita(dto.getId());
			if(dto.getValue()!=null){
				sibm.setDescrizione(dto.getValue());
			}
			return sibm;
		}

		if (dto.getIdTabella() == /*21*/FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO_INDEX) {
			FondiSpecialiCatalogazioneInventario fsci = new FondiSpecialiCatalogazioneInventario();
			if(modifica)
				fsci.setIdFondiSpecialiCatalogazioneInventario(dto.getId());
			if(dto.getValue()!=null){
				fsci.setDescrizione(dto.getValue());
			}
			return fsci;
		}

		if (dto.getIdTabella() == /*21*/CATALOGHI_TIPI_SUPPORTO_INVENTARIO_INDEX) {
			CataloghiSupportoDigitaleTipo csdt = new CataloghiSupportoDigitaleTipo();
			if(modifica)
				csdt.setIdCataloghiSupportoDigitaleTipo(dto.getId());
			if(dto.getValue()!=null){
				csdt.setDescrizione(dto.getValue());
			}
			return csdt;
		}

		if (dto.getIdTabella() == /*23*/PRESTITO_LOCALE_UTENTI_AMMESSI_INDEX) {
			PrestitoLocaleUtentiAmmessi plua = new PrestitoLocaleUtentiAmmessi();
			if(modifica)
				plua.setIdPrestitoLocaleUtentiAmmessi(dto.getId());
			if(dto.getValue()!=null){
				plua.setDescrizione(dto.getValue());
			}
			return plua;
		}


		if (dto.getIdTabella() == /*24*/PRESTITO_LOCALE_MATERIALE_ESCLUSO_INDEX) {
			PrestitoLocaleMaterialeEscluso plme = new PrestitoLocaleMaterialeEscluso();
			if(modifica)
				plme.setIdPrestitoLocaleMaterialeEscluso(dto.getId());
			if(dto.getValue()!=null){
				plme.setDescrizione(dto.getValue());
			}
			return plme;
		}

		if (dto.getIdTabella() == /*25*/PRESTITO_INTERBIBLIOTECARIO_MODO_INDEX) {
			PrestitoInterbibliotecarioModo pim = new PrestitoInterbibliotecarioModo();
			if(modifica)
				pim.setIdPrestitoInterbibliotecarioModo(dto.getId());
			if(dto.getValue()!=null){
				pim.setDescrizione(dto.getValue());
			}
			return pim;
		}
		if (dto.getIdTabella() == /*26*/PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX) {
			SistemiPrestitoInterbibliotecario spi = new SistemiPrestitoInterbibliotecario();
			if(modifica)
				spi.setIdSistemiPrestitoInterbibliotecario(dto.getId());
			if(dto.getValue()!=null){
				spi.setDescrizione(dto.getValue());
			}
			return spi;
		}



		if (dto.getIdTabella() == /*28*/SEZIONI_SPECIALI_INDEX) {
			SezioniSpeciali ss = new SezioniSpeciali();
			if(modifica)
				ss.setIdSezioniSpeciali(dto.getId());
			if(dto.getValue()!=null){
				ss.setDescrizione(dto.getValue());	
			}
			return ss;

		}

		if (dto.getIdTabella() == /*29*/DEPOSITI_LEGALI_TIPOLOGIE_INDEX) {
			DepositiLegaliTipo dlt = new DepositiLegaliTipo();
			if(modifica)
				dlt.setIdDepositiLegaliTipo(dto.getId());
			if(dto.getValue()!=null){
				dlt.setDescrizione(dto.getValue());		
			}
			return dlt;

		}

		if (dto.getIdTabella() == /*30*/CATALOGAZIONE_NORME_INDEX) {
			NormeCatalogazione nc = new NormeCatalogazione();
			if(modifica)
				nc.setIdNormeCatalogazione(dto.getId());
			if(dto.getValue()!=null){
				nc.setDescrizione(dto.getValue());
			}
			return nc;
		}

		if (dto.getIdTabella() == /*31*/INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX) {
			IndicizzazioneClassificata ic = new IndicizzazioneClassificata();
			if(modifica)
				ic.setIdIndicizzazioneClassificata(dto.getId());
			if(dto.getValue()!=null){
				ic.setDescrizione(dto.getValue());
			}
			return ic;
		}

		if (dto.getIdTabella() == /*32*/INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX) {
			IndicizzazioneSoggetto is = new IndicizzazioneSoggetto();
			if(modifica)
				is.setIdIndicizzazioneSoggetto(dto.getId());
			if(dto.getValue()!=null){
				is.setDescrizione(dto.getValue());
			}
			return is;
		}

		if (dto.getIdTabella() == /*33*/SISTEMI_RETI_BIBLITOECHE_INDEX) {
			SistemiBiblioteche sb = new SistemiBiblioteche();
			if(modifica)
				sb.setIdSistemiBiblioteche(dto.getId());
			if(dto.getValue()!=null){
				sb.setDescrizione(dto.getValue());
			}
			return sb;
		}

		if (dto.getIdTabella() == /*34*/CATALOGO_GENERALE_TIPO_INDEX) {
			CatalogoGeneraleTipo cgt = new CatalogoGeneraleTipo();
			if(modifica)
				cgt.setIdCatalogoGeneraleTipo(dto.getId());
			if(dto.getValue()!=null){
				cgt.setDescrizione(dto.getValue());
			}
			return cgt;
		}

		if (dto.getIdTabella() == /*35*/CATALOGHI_COLLETTIVI_ZONA_TIPO_INDEX) {
			CataloghiCollettiviZonaTipo cczt = new CataloghiCollettiviZonaTipo();
			if(modifica)
				cczt.setIdCataloghiCollettiviZonaTipo(dto.getId());
			if(dto.getValue()!=null){
				cczt.setDescrizione(dto.getValue());
			}
			return cczt;
		}
		//RIMOSSO DOPPIONE TABELLA DINAMICA
//		if (dto.getIdTabella() == /*36*/STATO_CATALOGAZIONE_BIBLIOTECHE_INDEX) {
//			StatoCatalogazioneTipo sct = new StatoCatalogazioneTipo();
//			if(modifica)
//				sct.setIdStatoCatalogazioneTipo(dto.getId());
//			if(dto.getValue()!=null){
//				sct.setDescrizione(dto.getValue());
//			}
//			return sct;
//		}

		return null;
	}

	public static Class<?> getDynaClass(int idTabella) {
		if (dynaClasses == null) {
			dynaClasses = new Hashtable<Integer, Class<?>>();

			/* TABELLE DINAMICHE VOCE SINGOLA GIà MAPPATE */

			dynaClasses.put(/*4*/PATRIMONIO_LIBRARIO_GRANDI_VOCI_INDEX,  PatrimonioSpecializzazioneCategoria.class);
			dynaClasses.put(/*7*/DESTINAZIONI_SOCIALI_TIPOLOGIE_INDEX,  DestinazioniSocialiTipo.class);
			dynaClasses.put(/*9*/ACCESSO_MODALITA_INDEX,  AccessoModalita.class);
			dynaClasses.put(/*10*/ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX, EnteTipologiaAmministrativa.class);			
			dynaClasses.put(/*11*/ENTI_FUNZIONI_OBIETTIVI_INDEX, EnteObiettivo.class);			

			dynaClasses.put(/*12*/TIPOLOGIE_FUNZIONALI_INDEX, TipologiaFunzionale.class);
			dynaClasses.put(/*13*/CATALOGAZIONE_STATI_BIBLIOTECHE_INDEX, StatoCatalogazioneTipo.class);
			dynaClasses.put(/*14*/THESAURUS_INDEX, Thesaurus.class);
			dynaClasses.put(/*16*/CONTATTI_TIPI_INDEX, ContattiTipo.class);
			dynaClasses.put(/*17*/RIPRODUZIONI_TIPI_INDEX, RiproduzioniTipo.class);	
			dynaClasses.put(/*18*/SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE_INDEX, ServiziInformazioniBibliograficheModalita.class);	
			dynaClasses.put(/*20*/FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO_INDEX, FondiSpecialiCatalogazioneInventario.class);
			dynaClasses.put(/*21*/CATALOGHI_TIPI_SUPPORTO_INVENTARIO_INDEX, CataloghiSupportoDigitaleTipo.class);
			dynaClasses.put(/*23*/PRESTITO_LOCALE_UTENTI_AMMESSI_INDEX, PrestitoLocaleUtentiAmmessi.class);
			dynaClasses.put(/*24*/PRESTITO_LOCALE_MATERIALE_ESCLUSO_INDEX, PrestitoLocaleMaterialeEscluso.class);	
			dynaClasses.put(/*25*/PRESTITO_INTERBIBLIOTECARIO_MODO_INDEX, PrestitoInterbibliotecarioModo.class);	
			dynaClasses.put(/*26*/PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX,SistemiPrestitoInterbibliotecario.class);		
			dynaClasses.put(/*27*/SEZIONI_SPECIALI_INDEX, SezioniSpeciali.class);
			dynaClasses.put(/*28*/DEPOSITI_LEGALI_TIPOLOGIE_INDEX, DepositiLegaliTipo.class);
			dynaClasses.put(/*39*/CATALOGAZIONE_NORME_INDEX, NormeCatalogazione.class);
			dynaClasses.put(/*30*/INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX, IndicizzazioneClassificata.class);
			dynaClasses.put(/*31*/INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX, IndicizzazioneSoggetto.class);
			dynaClasses.put(/*32*/SISTEMI_RETI_BIBLITOECHE_INDEX, SistemiBiblioteche.class);
			dynaClasses.put(/*34*/CATALOGO_GENERALE_TIPO_INDEX, CatalogoGeneraleTipo.class);
			dynaClasses.put(/*35*/CATALOGHI_COLLETTIVI_ZONA_TIPO_INDEX, CataloghiCollettiviZonaTipo.class);
			//RIMOSSO DOPPIONE TABELLA DINAMICA
			//			dynaClasses.put(/*36*/STATO_CATALOGAZIONE_BIBLIOTECHE_INDEX, StatoCatalogazioneTipo.class);

			/* END----TABELLE DINAMICHE VOCE SINGOLA GIà MAPPATE */
		}

		return dynaClasses.get(idTabella);
	}

	public static DynaTabDTO dynaRecord2DTO(Object dynaRecord) {
		DynaTabDTO dto = new DynaTabDTO();



		if (dynaRecord instanceof PatrimonioSpecializzazioneCategoria) {
			PatrimonioSpecializzazioneCategoria psc = (PatrimonioSpecializzazioneCategoria) dynaRecord;
			dto.setId(psc.getIdPatrimonioSpecializzazioneCategoria());
			dto.setValue(psc.getDescrizione());
			dto.setIdTabella(/*4*/PATRIMONIO_LIBRARIO_GRANDI_VOCI_INDEX);

			return dto;

		}

		if (dynaRecord instanceof DestinazioniSocialiTipo) {
			DestinazioniSocialiTipo dst = (DestinazioniSocialiTipo) dynaRecord;
			dto.setId(dst.getIdDestinazioniSociali());
			dto.setValue(dst.getDescrizione());
			dto.setIdTabella(/*7*/DESTINAZIONI_SOCIALI_TIPOLOGIE_INDEX);

			return dto;

		}

		if (dynaRecord instanceof AccessoModalita) {
			AccessoModalita sb = (AccessoModalita) dynaRecord;
			dto.setId(sb.getIdAccessoModalita());
			dto.setValue(sb.getDescrizione());
			dto.setIdTabella(/*9*/ACCESSO_MODALITA_INDEX);

			return dto;

		}

		if (dynaRecord instanceof EnteTipologiaAmministrativa) {
			EnteTipologiaAmministrativa eta = (EnteTipologiaAmministrativa) dynaRecord;
			dto.setId(eta.getIdEnteTipologiaAmministrativa());
			dto.setValue(eta.getDescrizione());
			dto.setIdTabella(/*10*/ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX);

			return dto;

		}

		if (dynaRecord instanceof EnteObiettivo) {
			EnteObiettivo eta = (EnteObiettivo) dynaRecord;
			dto.setId(eta.getIdEnteObiettivo());
			dto.setValue(eta.getDescrizione());
			dto.setIdTabella(/*11*/ENTI_FUNZIONI_OBIETTIVI_INDEX);

			return dto;

		}
		if (dynaRecord instanceof TipologiaFunzionale) {
			TipologiaFunzionale tpf = (TipologiaFunzionale) dynaRecord;
			dto.setId(tpf.getIdTipologiaFunzionale());
			dto.setValue(tpf.getDescrizione());
			dto.setIdTabella(/*12*/TIPOLOGIE_FUNZIONALI_INDEX);

			return dto;

		}		

		if (dynaRecord instanceof StatoCatalogazioneTipo) {

			StatoCatalogazioneTipo st = (StatoCatalogazioneTipo) dynaRecord;
			dto.setId(st.getIdStatoCatalogazioneTipo());
			dto.setValue(st.getDescrizione());
			dto.setIdTabella(/*13*/CATALOGAZIONE_STATI_BIBLIOTECHE_INDEX);

			return dto;

		}

		if (dynaRecord instanceof Thesaurus) {
			Thesaurus t = (Thesaurus) dynaRecord;
			dto.setId(t.getIdThesaurus());
			dto.setValue(t.getDescrizione());
			dto.setIdTabella(/*14*/THESAURUS_INDEX);
			return dto;

		}

		if (dynaRecord instanceof ContattiTipo) {
			ContattiTipo ct = (ContattiTipo) dynaRecord;
			dto.setId(ct.getIdContattiTipo());
			dto.setValue(ct.getDescrizione());
			dto.setIdTabella(/*16*/CONTATTI_TIPI_INDEX);
			return dto;

		}

		if (dynaRecord instanceof RiproduzioniTipo) {
			RiproduzioniTipo rt = (RiproduzioniTipo) dynaRecord;
			dto.setId(rt.getIdRiproduzioniTipo());
			dto.setValue(rt.getDescrizione());
			dto.setIdTabella(/*17*/RIPRODUZIONI_TIPI_INDEX);
			return dto;

		}

		if (dynaRecord instanceof ServiziInformazioniBibliograficheModalita) {
			ServiziInformazioniBibliograficheModalita sibm = (ServiziInformazioniBibliograficheModalita) dynaRecord;
			dto.setId(sibm.getIdServiziInformazioniBibliograficheModalita());
			dto.setValue(sibm.getDescrizione());
			dto.setIdTabella(/*19*/SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE_INDEX);
			return dto;

		}

		if (dynaRecord instanceof FondiSpecialiCatalogazioneInventario) {
			FondiSpecialiCatalogazioneInventario fsci =(FondiSpecialiCatalogazioneInventario) dynaRecord;
			dto.setId(fsci.getIdFondiSpecialiCatalogazioneInventario());
			dto.setValue(fsci.getDescrizione());
			dto.setIdTabella(/*21*/FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO_INDEX);

			return dto;
		}

		if (dynaRecord instanceof CataloghiSupportoDigitaleTipo) {
			CataloghiSupportoDigitaleTipo csdt =(CataloghiSupportoDigitaleTipo) dynaRecord;
			dto.setId(csdt.getIdCataloghiSupportoDigitaleTipo());
			dto.setValue(csdt.getDescrizione());
			dto.setIdTabella(/*21*/CATALOGHI_TIPI_SUPPORTO_INVENTARIO_INDEX);

			return dto;
		}



		if (dynaRecord instanceof PrestitoLocaleUtentiAmmessi) {
			PrestitoLocaleUtentiAmmessi plme =(PrestitoLocaleUtentiAmmessi) dynaRecord;
			dto.setId(plme.getIdPrestitoLocaleUtentiAmmessi());
			dto.setValue(plme.getDescrizione());
			dto.setIdTabella(/*23*/PRESTITO_LOCALE_UTENTI_AMMESSI_INDEX);

			return dto;
		}

		if (dynaRecord instanceof PrestitoLocaleMaterialeEscluso) {
			PrestitoLocaleMaterialeEscluso plme =(PrestitoLocaleMaterialeEscluso) dynaRecord;
			dto.setId(plme.getIdPrestitoLocaleMaterialeEscluso());
			dto.setValue(plme.getDescrizione());
			dto.setIdTabella(/*24*/PRESTITO_LOCALE_MATERIALE_ESCLUSO_INDEX);

			return dto;
		}

		if (dynaRecord instanceof PrestitoInterbibliotecarioModo) {
			PrestitoInterbibliotecarioModo pim =(PrestitoInterbibliotecarioModo) dynaRecord;
			dto.setId(pim.getIdPrestitoInterbibliotecarioModo());
			dto.setValue(pim.getDescrizione());
			dto.setIdTabella(/*25*/PRESTITO_INTERBIBLIOTECARIO_MODO_INDEX);

			return dto;
		}

		if (dynaRecord instanceof SistemiPrestitoInterbibliotecario) {
			SistemiPrestitoInterbibliotecario spi =(SistemiPrestitoInterbibliotecario) dynaRecord;
			dto.setId(spi.getIdSistemiPrestitoInterbibliotecario());
			dto.setValue(spi.getDescrizione());
			dto.setIdTabella(/*26*/PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX);

			return dto;
		}



		if (dynaRecord instanceof SezioniSpeciali) {
			SezioniSpeciali ss = (SezioniSpeciali) dynaRecord;
			dto.setId(ss.getIdSezioniSpeciali());
			dto.setValue(ss.getDescrizione());
			dto.setIdTabella(/*28*/SEZIONI_SPECIALI_INDEX);

			return dto;
		}

		if (dynaRecord instanceof DepositiLegaliTipo) {
			DepositiLegaliTipo dlt = (DepositiLegaliTipo) dynaRecord;
			dto.setId(dlt.getIdDepositiLegaliTipo());
			dto.setValue(dlt.getDescrizione());
			dto.setIdTabella(/*29*/DEPOSITI_LEGALI_TIPOLOGIE_INDEX);

			return dto;
		}

		if (dynaRecord instanceof NormeCatalogazione) {
			NormeCatalogazione nc  =(NormeCatalogazione) dynaRecord;
			dto.setId(nc.getIdNormeCatalogazione());
			dto.setValue(nc.getDescrizione());
			dto.setIdTabella(/*30*/CATALOGAZIONE_NORME_INDEX);

			return dto;
		}

		if (dynaRecord instanceof IndicizzazioneClassificata) {
			IndicizzazioneClassificata ic  =(IndicizzazioneClassificata ) dynaRecord;
			dto.setId(ic.getIdIndicizzazioneClassificata());
			dto.setValue(ic.getDescrizione());
			dto.setIdTabella(/*31*/INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX);

			return dto;
		}

		if (dynaRecord instanceof IndicizzazioneSoggetto) {
			IndicizzazioneSoggetto is  =(IndicizzazioneSoggetto ) dynaRecord;
			dto.setId(is.getIdIndicizzazioneSoggetto());
			dto.setValue(is.getDescrizione());
			dto.setIdTabella(/*32*/INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX);

			return dto;
		}

		if (dynaRecord instanceof SistemiBiblioteche) {
			SistemiBiblioteche sb = (SistemiBiblioteche) dynaRecord;
			dto.setId(sb.getIdSistemiBiblioteche());
			dto.setValue(sb.getDescrizione());
			dto.setIdTabella(/*33*/SISTEMI_RETI_BIBLITOECHE_INDEX);

			return dto;

		}


		if (dynaRecord instanceof CatalogoGeneraleTipo) {
			CatalogoGeneraleTipo cgt = (CatalogoGeneraleTipo) dynaRecord;
			dto.setId(cgt.getIdCatalogoGeneraleTipo());
			dto.setValue(cgt.getDescrizione());
			dto.setIdTabella(/*34*/CATALOGO_GENERALE_TIPO_INDEX);
			return dto;

		}

		if (dynaRecord instanceof CataloghiCollettiviZonaTipo) {
			CataloghiCollettiviZonaTipo cczt = (CataloghiCollettiviZonaTipo) dynaRecord;
			dto.setId(cczt.getIdCataloghiCollettiviZonaTipo());
			dto.setValue(cczt.getDescrizione());
			dto.setIdTabella(/*35*/CATALOGHI_COLLETTIVI_ZONA_TIPO_INDEX);

			return dto;

		}
		//RIMOSSO DOPPIONE TABELLA DINAMICA		
//		if (dynaRecord instanceof StatoCatalogazioneTipo) {
//			StatoCatalogazioneTipo sct = (StatoCatalogazioneTipo) dynaRecord;
//			dto.setId(sct.getIdStatoCatalogazioneTipo());
//			dto.setValue(sct.getDescrizione());
//			dto.setIdTabella(/*36*/STATO_CATALOGAZIONE_BIBLIOTECHE_INDEX);
//
//			return dto;
//
//		}

		return null;
	}

	/**/	/* 00 */public static final int STATI_INDEX = 0;
	/**/	/* 01 */public static final int REGIONI_INDEX = 1;
	/**/	/* 02 */public static final int PROVINCE_INDEX = 2;
	/**/	/* 03 */public static final int COMUNI_INDEX = 3;
	/*2*/	/* 04 */public static final int PATRIMONIO_LIBRARIO_GRANDI_VOCI_INDEX = 4;
	/*2*/	/* 05 */public static final int PATRIMONIO_LIBRARIO_PICCOLE_VOCI_INDEX = 5;
	/*3*/	/* 06 */public static final int CATALOGHI_COLLETTIVI_INDEX = 6;
	/*1*/    /*07 */public static final int DESTINAZIONI_SOCIALI_TIPOLOGIE_INDEX  = 7;
	/*1*/	/* 08 */public static final int ACCESSO_UTENTI_AMMESSI_INDEX =8;
	/*1*/	/* 09 */public static final int ACCESSO_MODALITA_INDEX = 9;
	/*1*/	/* 10 */public static final int ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX = 10;
	/*1*/	/* 11 */public static final int ENTI_FUNZIONI_OBIETTIVI_INDEX = 11;
	/*1*/	/* 12 */public static final int TIPOLOGIE_FUNZIONALI_INDEX =12;
	/*1*/	/* 13 */public static final int CATALOGAZIONE_STATI_BIBLIOTECHE_INDEX = 13;
	/*1*/	/* 14 */public static final int THESAURUS_INDEX = 14;
	/*1*/	/* 15 */public static final int DEWEY_INDEX = 15;
	/*1*/	/* 16 */public static final int CONTATTI_TIPI_INDEX = 16;
	/*1*/	/* 17 */public static final int RIPRODUZIONI_TIPI_INDEX =17;
	/*1*/	/* 18 */public static final int SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE_INDEX = 18;
	/*1*/	/* 19 */public static final int SERVIZI_BIBLIOTECARI_CARTA_SERVIZI_INDEX = 19;
	/*1*/	/* 20 */public static final int FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO_INDEX = 20;
	/*1*/	/* 21 */public static final int CATALOGHI_TIPI_SUPPORTO_INVENTARIO_INDEX = 21;
	/*1*/	/* 22 */public static final int CATALOGHI_COLLETTIVI_TIPI_ZONE_DI_ESPANSIONE_INDEX =22;
	/*1*/	/* 23 */public static final int PRESTITO_LOCALE_UTENTI_AMMESSI_INDEX = 23;
	/*1*/	/* 24 */public static final int PRESTITO_LOCALE_MATERIALE_ESCLUSO_INDEX = 24;
	/*1*/	/* 25 */public static final int PRESTITO_INTERBIBLIOTECARIO_MODO_INDEX =25;
	/*1*/	/* 26 */public static final int PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX = 26;
	/*1*/	/* 27 */public static final int SEZIONI_SPECIALI_INDEX = 27;
	/*1*/	/* 28 */public static final int DEPOSITI_LEGALI_TIPOLOGIE_INDEX = 28;
	/*1*/	/* 29 */public static final int CATALOGAZIONE_NORME_INDEX = 29;
	/*1*/	/* 30 */public static final int INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX = 30;
	/*1*/	/* 31 */public static final int INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX = 31;
	/*1*/	/* 32 */public static final int SISTEMI_RETI_BIBLITOECHE_INDEX = 32;
	/*1*/	/* 33 */public static final int FONDI_ANTICHI_CONSISTENZA_INDEX = 33;
	/*1*/	/* 34 */public static final int CATALOGO_GENERALE_TIPO_INDEX = 34;
	/*1*/	/* 35 */public static final int CATALOGHI_COLLETTIVI_ZONA_TIPO_INDEX = 35;
	//RIMOSSO DOPPIONE TABELLA DINAMICA
	//	/*1*/	/* 36 */public static final int STATO_CATALOGAZIONE_BIBLIOTECHE_INDEX = 36;
	
	public static Hashtable<Integer, Class<?>> getDynaClasses() {
		return dynaClasses;
	}
}
