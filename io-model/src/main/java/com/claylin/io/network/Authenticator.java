package com.claylin.io.network;

import com.claylin.io.common.security.auth.PrincipalBuilder;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

public interface Authenticator extends Cloneable {

    void configure(TransportLayer transportLayer, PrincipalBuilder principalBuilder, Map<String, ?> configs);

    void authenticate() throws IOException;

    /**
     * @return Principal using PrincipalBuilder
     */
    Principal principal();

    /**
     * @return true if authentication is complete otherwise return false
     */
    boolean complete();
}
