package duan1.utils;

import duan1.models.*;
import duan1.models.user.UserModel;
import duan1.config.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.*;
import org.bson.Document;

public class AccessToken {
    public static String uid;
    public static String name;
    public static String perm;
    public static String uname;

    public static String generate(UserModel user) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(Config.JWT_SECRECT);
            String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("uname", user.uname)
                .withClaim("name", user.name)
                .withClaim("uid", user._id)
                .withClaim("perm", user.permission)
                .sign(algorithm);

            return token;
        }catch(Exception e) {
            throw e;
        }
    }

    public static Document verify(String token) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(Config.JWT_SECRECT);
            JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("auth0")
            .build();

            DecodedJWT decodedJWT;
            decodedJWT = verifier.verify(token);

            Document jwtResult = new Document();

            jwtResult.put("uid", decodedJWT.getClaim("uid").asString());
            jwtResult.put("name", decodedJWT.getClaim("name").asString());
            jwtResult.put("uname", decodedJWT.getClaim("uname").asString());
            jwtResult.put("perm", decodedJWT.getClaim("perm").asString());

            //Save login state
            uname = jwtResult.getString("uid");
            name = jwtResult.getString("name");
            uid = jwtResult.getString("uid");
            perm = jwtResult.getString("perm");

            return jwtResult;
        }catch(Exception e) {
            throw new Exception("INVALID_TOKEN");
        }
    }

    public static void clear() {
        //Clear temp data
        uid = null;
        name = null;
        perm = null;
        uname = null;
    }

}
