package az.developia.librarian_jahangir_askerov.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import az.developia.librarian_jahangir_askerov.entity.UserEntity;
import az.developia.librarian_jahangir_askerov.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository repository;

	public CustomUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		boolean enabledFlag = user.getEnabled() != null && user.getEnabled() == 1;
		boolean activeFlag = user.getActive() != null && user.getActive() == 1;

		boolean canLogin = enabledFlag && activeFlag;

		String[] authorities = user.getRoles().stream().map(role -> {
			String name = role.getAuthority();

			return name.startsWith("ROLE_") ? name : "ROLE_" + name;
		}).toArray(String[]::new);

		return User.builder().username(user.getUsername()).password(user.getPassword()).authorities(authorities)
				.disabled(!canLogin).build();
	}

}
