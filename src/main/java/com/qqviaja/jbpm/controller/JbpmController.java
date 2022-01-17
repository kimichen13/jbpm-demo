package com.qqviaja.jbpm.controller;

import com.qqviaja.jbpm.config.JbpmEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * <p>Create on 2022/1/17.</p>
 *
 * @author Kimi Chen
 */
@RestController
public class JbpmController {

    @Autowired
    private JbpmEngine jbpmEngine;

    @GetMapping("/startProcess")
    public long startProcess() {
        return jbpmEngine.startProcess("test", Collections.singletonMap("name", "kimi")).getId();
    }

}
