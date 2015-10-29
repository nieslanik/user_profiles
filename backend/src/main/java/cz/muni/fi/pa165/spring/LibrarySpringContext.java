package cz.muni.fi.pa165.spring;

import javax.inject.Singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application Spring configuration using in memory database
 *
 * @author Michael Simacek
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("cz.muni.fi.pa165")
public class LibrarySpringContext {
    /**
     * Creates entityManagerFactory for this configuration
     *
     * @return Sprint EMF bean
     * */
    @Bean
    @Singleton
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("cz.muni.fi.pa165.test");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    /**
     * Creates transaction manager
     * 
     * @return transaction manager
     * */
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
}