package com.qqviaja.jbpm.config;

import lombok.SneakyThrows;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.task.UserGroupCallback;
import org.kie.internal.identity.IdentityProvider;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.factorybeans.RuntimeEnvironmentFactoryBean;
import org.kie.spring.factorybeans.RuntimeManagerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.kie.spring.factorybeans.RuntimeEnvironmentFactoryBean.TYPE_DEFAULT;

/**
 * <p>Create on 2022/1/17.</p>
 *
 * @author Kimi Chen
 */
@Configuration
@EnableConfigurationProperties(JbpmProperties.class)
public class JbpmAutoConfiguration {

    @Autowired
    private JbpmProperties jbpmProperties;

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private PlatformTransactionManager ptm;

    @Bean
    public Map<Resource, ResourceType> resources() {
        return jbpmProperties.getProcessLocations().stream().map(ResourceFactory::newClassPathResource)
                .collect(Collectors.toMap(Function.identity(), Resource::getResourceType, (v1, v2) -> v1));
    }

    @SneakyThrows
    @Bean
    public RuntimeEnvironment runtimeEnvironment() {
        RuntimeEnvironmentFactoryBean bean = new RuntimeEnvironmentFactoryBean();
        bean.setType(TYPE_DEFAULT);
        bean.setEntityManagerFactory(emf);
        bean.setTransactionManager(ptm);
        bean.setAssets(resources());
        return (RuntimeEnvironment) bean.getObject();
    }

    @Bean(destroyMethod = "close")
    public RuntimeManagerFactoryBean runtimeManagerFactoryBean() {
        RuntimeManagerFactoryBean bean = new RuntimeManagerFactoryBean();
        bean.setIdentifier(jbpmProperties.getDeploymentId());
        bean.setRuntimeEnvironment(runtimeEnvironment());
        return bean;
    }

    @Bean
    public JbpmEngine jbpmEngine() {
        return new JbpmEngine();
    }

    @Bean
    public UserGroupCallback userGroupCallback() {
        return new DefaultUserGroupCallback();
    }

    @Bean
    public IdentityProvider identityProvider() {
        return new DefaultIdentityProvider();
    }


}
