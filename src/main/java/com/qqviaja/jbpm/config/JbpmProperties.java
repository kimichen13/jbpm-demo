package com.qqviaja.jbpm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>Create on 2022/1/17.</p>
 *
 * @author Kimi Chen
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jbpm")
public class JbpmProperties {

    private String deploymentId;
    private List<String> processLocations;

}
