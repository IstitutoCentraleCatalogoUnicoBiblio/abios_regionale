package it.inera.abi.logic.auth;

import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assembler")
public class Assembler {

	@Transactional(readOnly = true)
	User buildUserFromUserEntity(Utenti utenti) {

		String username = utenti.getLogin();
		String password = utenti.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = utenti.getEnabled();
//		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Profili profili : utenti.getProfilis()) {
			authorities.add(new GrantedAuthorityImpl(profili.getDenominazione()));
		}

		User user = new User(username, password, enabled,  accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
}