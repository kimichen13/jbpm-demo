package com.qqviaja.jbpm.config;

import org.kie.internal.identity.IdentityProvider;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Create on 2022/1/17.</p>
 *
 * @author Kimi Chen
 */
public class DefaultIdentityProvider implements IdentityProvider {
    @Override
    public String getName() {
        return "admin";
    }

    @Override
    public List<String> getRoles() {
        return Arrays.asList("Admin", "PM");
    }

    @Override
    public boolean hasRole(String role) {
        return true;
    }
}
