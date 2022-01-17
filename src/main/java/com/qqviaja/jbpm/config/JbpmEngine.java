package com.qqviaja.jbpm.config;

import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * <p>Create on 2022/1/17.</p>
 *
 * @author Kimi Chen
 */
public class JbpmEngine {

    @Autowired
    private RuntimeManager runtimeManager;

    public ProcessInstance startProcess(String processId, Map<String, Object> params) {
        final RuntimeEngine runtimeEngine = runtimeManager.getRuntimeEngine(EmptyContext.get());
        return runtimeEngine.getKieSession().startProcess(processId, params);
    }

}
