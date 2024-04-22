package br.com.projetoBase.configuracoes;


import br.com.projetoBase.modelo.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService  {

    public String gerarToken(Usuario usuario){
        return JWT.create()
                .withIssuer("FERNANDO")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusHours(CostantesSeguranca.TEMPO_EXPIRAR)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(CostantesSeguranca.PALAVRA_SECRETA));
    }

    public String getSubject(String token){
        return JWT.require(Algorithm.HMAC256(CostantesSeguranca.PALAVRA_SECRETA))
                .withIssuer("FERNANDO")
                .build().verify(token).getSubject();

    }

}
