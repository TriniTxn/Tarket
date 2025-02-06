package br.com.devannis.webmarket.configuration;

import br.com.devannis.webmarket.model.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenVerification {

    @Value("${my.secret.key}")
    private String token;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(token);

            
        }
    }
}
