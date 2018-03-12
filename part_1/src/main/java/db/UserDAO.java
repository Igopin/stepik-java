package db;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) {
        return session.get(UsersDataSet.class, id);
    }

    public UsersDataSet getUserByLogin(String login) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<UsersDataSet> criteria = builder.createQuery( UsersDataSet.class );
        Root<UsersDataSet> root = criteria.from( UsersDataSet.class );
        criteria.select( root );
        criteria.where( builder.equal( root.get("login"), login));

        return  session.createQuery( criteria ).getSingleResult();
    }

    public long insertUser(String login, String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login, password));
    }
}
