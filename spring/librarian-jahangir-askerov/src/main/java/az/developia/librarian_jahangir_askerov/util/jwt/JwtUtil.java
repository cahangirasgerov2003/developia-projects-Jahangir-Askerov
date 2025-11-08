package az.developia.librarian_jahangir_askerov.util.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

//	openssl rand -base64 32 ------------- yeni 256 bitlik base64 ( string ) formatinda random secret ver
	@Value("${jwt.secret}")
	private String SECRET_KEY_STRING;

	private static final long EXPIRATION_TIME = 1000 * 60 * 60;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username) // payload-a username qoyulur
				.setIssuedAt(new Date()) // token yaradılma tarixi
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // bitmə vaxtı
				.signWith(getSignInKey(), SignatureAlgorithm.HS256) // imza + secret
				.compact();
	}

	public boolean validateToken(String token, String username) {
		final String tokenUsername = extractUsername(token);
		return (tokenUsername.equals(username) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY_STRING);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
