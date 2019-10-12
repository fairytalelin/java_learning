package com.claylin.io.common.security.auth;

import javax.security.auth.Subject;
import javax.swing.plaf.PanelUI;
import java.security.Principal;

public class SelfPrincipal implements Principal {
    public static final String SEPERATOR = ":";
    public static final String USER_TYPE = "User";
    //public static final SelfPrincipal ANONYMOUS = new SelfPrincipal();

    private String principalType;
    private String name;

    public SelfPrincipal(String principalType, String name) {
        if (principalType == null || name == null) {
            throw new RuntimeException();
        }
        this.principalType = principalType;
        this.name = name;
    }

    public static SelfPrincipal fromString(String str) {
        if (str == null || str.isEmpty()) {
            throw new RuntimeException();
        }

        String[] split = str.split(SEPERATOR, 2);
        if (split == null || split.length != 2) {
            throw new RuntimeException();
        }
        return new SelfPrincipal(split[0], split[1]);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
