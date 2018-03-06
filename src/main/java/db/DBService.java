package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {
    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    public UsersDataSet getUser(long id) throws Exception {
        return doRequestToBase((Session session) -> new UserDAO(session).get(id));
    }

    public UsersDataSet getUserByLogin(String login) throws Exception {
        return doRequestToBase((Session session) -> new UserDAO(session).getUserByLogin(login));
    }

    public long addUser(String name, String password) throws Exception {
        return doRequestToBase((Session session) -> new UserDAO(session).insertUser(name, password));
    }

    private <T> T doRequestToBase(RequestHandler<T> handler) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            T value = handler.handle(session);
            session.close();
            return value;
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
    }

    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/stepik");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "1234");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
