package com.claylin.io.common.security.auth;

import com.claylin.io.common.Configurable;
import com.claylin.io.network.Authenticator;
import com.claylin.io.network.TransportLayer;

import java.security.Principal;
import java.util.Map;

public interface PrincipalBuilder extends Configurable {

    void configure(Map<String, ?> configs);

    Principal buildPrincipal(TransportLayer transportLayer, Authenticator authenticator);

    void close();
}
