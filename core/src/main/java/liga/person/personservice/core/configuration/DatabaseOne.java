package liga.person.personservice.core.configuration;

import liga.person.personservice.core.dto.DatabaseProperty;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@EnableJpaRepositories(
        entityManagerFactoryRef = DatabaseOne.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = DatabaseOne.TRANSACTION_MANAGER,
        basePackages = DatabaseOne.JPA_REPOSITORY_PACKAGE)
@Configuration
public class DatabaseOne {

    public static final String PROPERTY_PREFIX = "app-dbone-datasource";
    public static final String JPA_REPOSITORY_PACKAGE = "liga.person.personservice.core";
    public static final String ENTITY_PACKAGE = "liga.person.personservice.core.model";
    public static final String ENTITY_MANAGER_FACTORY = "oneEntityManagerFactory";
    public static final String DATA_SOURCE = "oneDataSource";
    public static final String DATABASE_PROPERTY = "oneDatabaseProperty";
    public static final String TRANSACTION_MANAGER = "oneTransactionManager";

    @Bean(DATABASE_PROPERTY)
    @ConfigurationProperties(prefix = PROPERTY_PREFIX)
    public DatabaseProperty appDatabaseProperty() {
        return new DatabaseProperty();
    }

    @Bean(DATA_SOURCE)
    public DataSource appDataSource() {
        String url = "jdbc:postgresql://localhost:5432/medicine";
        String username = "postgres";
        String password = "postgres";
        String driver = "org.postgresql.Driver";
        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(url)
                .driverClassName(driver)
                .build();
    }

    @Bean(ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean appEntityManager(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPersistenceUnitName(ENTITY_MANAGER_FACTORY);
        em.setPackagesToScan(ENTITY_PACKAGE);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(TRANSACTION_MANAGER)
    public PlatformTransactionManager sqlSessionTemplate(
            @Qualifier(ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManager,
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager.getObject());
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource-secondary-liquibase")
    public LiquibaseProperties secondaryLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase secondaryLiquibase() {
        return springLiquibase(appDataSource(), secondaryLiquibaseProperties());
    }

    private SpringLiquibase springLiquibase(@Qualifier(DATA_SOURCE) DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }

}
