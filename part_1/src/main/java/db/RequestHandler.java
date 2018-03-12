package db;
import org.hibernate.Session;

interface RequestHandler<T>{
    T handle(Session session);
}
