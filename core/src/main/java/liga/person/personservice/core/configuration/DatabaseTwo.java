package liga.person.personservice.core.configuration;

import liga.person.personservice.core.dto.DatabaseProperty;
import org.springframework.beans.factory.annotation.Qualifier;
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

import static liga.person.personservice.core.configuration.DatabaseOne.*;

@EnableJpaRepositories(
        entityManagerFactoryRef = DatabaseTwo.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = DatabaseTwo.TRANSACTION_MANAGER,
        basePackages = DatabaseTwo.JPA_REPOSITORY_PACKAGE
)
@Configuration
public class DatabaseTwo {

    public static final String PROPERTY_PREFIX = "app-dbtwo-datasource";
    public static final String JPA_REPOSITORY_PACKAGE = "liga.medical.common.core";
    public static final String ENTITY_PACKAGE = "liga.medical.common.core.model";
    public static final String ENTITY_MANAGER_FACTORY = "twoEntityManagerFactory";
    public static final String DATA_SOURCE = "twoDataSource";
    public static final String DATABASE_PROPERTY = "twoDatabaseProperty";
    public static final String TRANSACTION_MANAGER = "twoTransactionManager";

    private String url = "jdbc:postgresql://localhost:5432/db_log";
    private String username = "postgres";
    private String password = "postgres";
    private String driver = "org.postgresql.Driver";

    @Bean(DATABASE_PROPERTY)
    @ConfigurationProperties(prefix = PROPERTY_PREFIX)
    public DatabaseProperty appDatabaseProperty() {
        return new DatabaseProperty();
    }

    @Bean(DATA_SOURCE)
    public DataSource appDataSource(
            @Qualifier(DATABASE_PROPERTY) DatabaseProperty databaseProperty
    ) {
        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(url)
                .driverClassName(driver)
                .build();
    }

    @Bean(ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean appEntityManager(
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPersistenceUnitName(ENTITY_MANAGER_FACTORY);
        em.setPackagesToScan(ENTITY_PACKAGE);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(TRANSACTION_MANAGER)
    public PlatformTransactionManager sqlSessionTemplate(
            @Qualifier(ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManager,
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager.getObject());
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
