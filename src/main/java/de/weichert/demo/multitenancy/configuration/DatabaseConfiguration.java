package de.weichert.demo.multitenancy.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Created by Jannik on 02.07.15.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"de.weichert.demo.multitenancy.repository"}, entityManagerFactoryRef = "entityManagerFactory")
public class DatabaseConfiguration implements EnvironmentAware{

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
    private RelaxedPropertyResolver jpaProps;



    @Bean(name = "metaDataSource")
    @ConfigurationProperties(prefix="spring.datasource.meta")
    public DataSource metaDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("meta_db")
                .addScript("classpath:sql/meta/schema.sql")
                .addScript("classpath:sql/meta/data.sql")
                .build();
    }

    //TODO: Replace schema-init with liquibase
    @Bean(name = "tenantOneDataSource")
    @ConfigurationProperties(prefix="spring.datasource.tenant1")
    public DataSource tenantOneDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("tenant_2_db")
                .addScript("classpath:sql/tenant/schema.sql")
                .build();
    }

    //TODO: Replace schema-init with liquibase
    @Bean(name = "tenantTwoDataSource")
    @ConfigurationProperties(prefix="spring.datasource.tenant2")
    public DataSource tenantTwoDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("tenant_1_db")
                .addScript("classpath:sql/tenant/schema.sql")
                .build();
    }

    public void setEnvironment(Environment environment) {
        this.jpaProps = new RelaxedPropertyResolver(environment, "spring.jpa.");
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       DataSource dataSource) {
        log.info("Configuring EntityManagerFactory for Tenants");
        LocalContainerEntityManagerFactoryBean factory = builder.dataSource(dataSource)
                .persistenceUnit("tenantPU")
                .jta(true)
                .packages("de.weichert.demo.multitenancy")
                .build();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaDialect(new HibernateJpaDialect());

        return factory;
    }

}

