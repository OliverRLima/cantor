package br.com.musicall.api.config.security;

import br.com.musicall.api.dominios.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${musicall.jwt.expiration}")
    private String expiration;

    @Value("${musicall.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication){
        Usuario logado = (Usuario) authentication.getPrincipal();

        Date hoje = new Date();
        Date dataExpiracao= new Date(hoje.getTime() + Long.parseLong(expiration));


        return Jwts.builder().setIssuer("API do Musicall").setSubject(logado.getIdUsuario().toString()).
                setIssuedAt(hoje).setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean isTokenValido(String token) {
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public Integer getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
}
