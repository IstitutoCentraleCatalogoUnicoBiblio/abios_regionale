/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

package it.inera.abi.gxt.server;

import it.inera.abi.gxt.client.mvc.model.auth.ProfiliModel;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;
import it.inera.abi.gxt.client.services.AuthService;
import it.inera.abi.logic.auth.AuthLogic;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

/**
 * Implementazione dei servizi di autenticazione (lato server)
 * 
 */
@SuppressWarnings("serial")
public class AuthServiceImpl extends AutoinjectingRemoteServiceServlet implements AuthService {
	
	protected @Autowired AuthLogic authLogic;
	
	protected Log _log = LogFactory.getLog(AuthServiceImpl.class);
	
	@Override
	public UtentiAuthModel retrieveUser() {
		
		User user = authLogic.retrieveLoggedUser();
		if (user == null) {
			if (_log.isFatalEnabled()) _log.fatal("Not logged in");
			return null;
		} else {
			String username = user.getUsername();
			Utenti utente = authLogic.retrieveUtente(username);
			 
			List<Profili> profiliSet = utente.getProfilis();
			List<ProfiliModel> subjectModels = new ArrayList<ProfiliModel>(); 
			for (Profili profili : profiliSet) {
				subjectModels.add(new ProfiliModel(profili.getIdProfili(), profili.getDenominazione()));
			}
			
			UtentiAuthModel userModel = new UtentiAuthModel();
			userModel.setUserId(utente.getIdUtenti());
			userModel.setUserLogin(username);
			userModel.setProfili(subjectModels);
			
			userModel.setNome(utente.getNome());
			userModel.setCognome(utente.getCognome());
			userModel.setEmail(utente.getEmail());
			
			return userModel; 
		}
		
	}

	@Override
	public void checkLogin() {
		// nn fa nulla controlla solo se l'utente è loggato
	}
	
}
