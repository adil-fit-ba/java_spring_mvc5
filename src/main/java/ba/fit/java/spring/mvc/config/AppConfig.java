package ba.fit.java.spring.mvc.config;

import ba.fit.java.spring.mvc.entitymodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")

@ComponentScans(value = { @ComponentScan("ba.fit.java.spring.mvc.dao"),
        @ComponentScan("ba.fit.java.spring.mvc.service")
})
public class AppConfig {


    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties props = new Properties();

        // Setting JDBC properties
            props.put(DRIVER,   env.getProperty("myDb.driver"));
        props.put(URL,          env.getProperty("myDb.jdbcUrl"));
        props.put(USER,         env.getProperty("myDb.username"));
        props.put(PASS,         env.getProperty("myDb.password"));

        // Setting Hibernate properties
        props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

        // Setting C3P0 properties
        props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
        props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
        props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
        props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
        props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(
                AutorizacijskiToken.class,
                DodjeljenPredmet.class,
                KorisnickiNalog.class,
                Nastavnik.class,
                Odjeljenje.class,
                OdjeljenjeStavka.class,
                Predmet.class,
                Ucenik.class
        );

        return factoryBean;
    }



}
