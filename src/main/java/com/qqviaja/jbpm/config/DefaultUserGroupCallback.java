package com.qqviaja.jbpm.config;

import org.kie.api.task.UserGroupCallback;
import org.kie.internal.identity.IdentityProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>Create on 2022/1/17.</p>
 *
 * @author Kimi Chen
 */
public class DefaultUserGroupCallback implements UserGroupCallback {

    @Autowired
    private IdentityProvider identityProvider;

    @Override
    public boolean existsUser(String userId) {
        return true;
    }

    @Override
    public boolean existsGroup(String groupId) {
        return true;
    }

    @Override
    public List<String> getGroupsForUser(String userId) {
        return identityProvider.getRoles();
    }
}
