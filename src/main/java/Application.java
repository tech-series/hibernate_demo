import entities.AccountType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import java.time.LocalDate;
import java.util.Properties;

public class Application {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(AccountType.class);
        configuration.setProperties(new Properties() {
            {
                put("hibernate.connection.username", "chk");
                put("hibernate.connection.password", "chk");
                put("hibernate.connection.driver_class",
                        "com.mysql.cj.jdbc.Driver");
                put("hibernate.connection.url", "jdbc:mysql://localhost:3306/db");
                put("hibernate.hbm2ddl.auto", "update");
            }
        });

        SessionFactory sessionFactory = configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
        );
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        AccountType type = new AccountType();
        type.setName("Savings");
        type.setCreatedDate(LocalDate.now());
        type.setLastUpdatedDate(LocalDate.now());
        type.setCreatedBy("Morgan");
        type.setLastUpdatedBy("Morgan");
        session.persist(type);
        session.getTransaction().commit();
        session.close();


    }
}

