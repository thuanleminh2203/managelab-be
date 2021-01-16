package com.example.demo.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class AssignPrincipalHandshakeHandler extends DefaultHandshakeHandler {
    private static final String ATTR_PRINCIPAL = "__principal__";

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        final String name;
        System.out.println("====request======"+request.getHeaders());
//        if (!attributes.containsKey(ATTR_PRINCIPAL)) {
//            name = generateRandomUsername();
//            attributes.put(ATTR_PRINCIPAL, name);
//        } else {
//            name = (String) attributes.get(ATTR_PRINCIPAL);
//        }
//        return new Principal() {
//            @Override
//            public String getName() {
//                return name;
//            }
//        };
        return new Principal() {
            @Override
            public boolean equals(Object another) {
                return false;
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String getName() {
                return "thuan";
            }
        };
    }

//    private String generateRandomUsername() {
//        RandomStringGenerator randomStringGenerator =
//                new RandomStringGenerator.Builder()
//                        .withinRange('0', 'z')
//                        .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();
//        return randomStringGenerator.generate(32);
//    }
}