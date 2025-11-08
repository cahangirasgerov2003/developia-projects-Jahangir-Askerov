package az.developia.librarian_jahangir_askerov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.request.AuthRequest;
import az.developia.librarian_jahangir_askerov.request.TokenRequest;
import az.developia.librarian_jahangir_askerov.response.AuthResponse;
import az.developia.librarian_jahangir_askerov.service.CustomUserDetailsService;
import az.developia.librarian_jahangir_askerov.util.jwt.JwtUtil;
import az.developia.librarian_jahangir_askerov.util.jwt.RefreshTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final JwtUtil jwtUtil;

	private final CustomUserDetailsService customUserDetailsService;

	private final RefreshTokenUtil refreshTokenUtil;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			CustomUserDetailsService customUserDetailsService, RefreshTokenUtil refreshTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.customUserDetailsService = customUserDetailsService;
		this.refreshTokenUtil = refreshTokenUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		final String refreshToken = refreshTokenUtil.generateRefreshToken(userDetails.getUsername());

		return ResponseEntity.ok(new AuthResponse(jwt, refreshToken));
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody TokenRequest tokenRequest) {
		String refreshToken = tokenRequest.getRefreshToken();

		String username = refreshTokenUtil.extractUsername(refreshToken);
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

		if (refreshTokenUtil.validateToken(refreshToken, userDetails.getUsername())) {
			final String newAccessToken = jwtUtil.generateToken(userDetails.getUsername());
			return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
		} else {
			return ResponseEntity.status(403).body("Invalid refresh token");
		}
	}
}
