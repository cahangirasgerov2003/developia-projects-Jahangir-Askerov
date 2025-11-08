package az.developia.librarian_jahangir_askerov.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import az.developia.librarian_jahangir_askerov.service.CustomUserDetailsService;
import az.developia.librarian_jahangir_askerov.util.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;

	public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Authorization header oxu
		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String username;

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response); // header yoxdursa, növbəti filter-ə keç
			return;
		}

		// Token-i ayır
		jwtToken = authHeader.substring(7); // "Bearer " prefix-i çıxar
		username = jwtUtil.extractUsername(jwtToken); // username çıxar

		// SecurityContext-də yoxla
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			// Token doğrula
			if (jwtUtil.validateToken(jwtToken, userDetails.getUsername())) {

				// Authentication yaradıb SecurityContext-ə əlavə et
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		// Növbəti filter-ə davam et
		filterChain.doFilter(request, response);
	}
}