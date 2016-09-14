package photohost.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DAO {
    private static final Logger log = Logger.getAnonymousLogger();
    private static final ThreadLocal session = new ThreadLocal();
    private static final SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

    protected DAO() {}
    /*Создание сессии*/
    public static Session getSession() {
        Session session = (Session) DAO.session.get();
        if (session == null) {
            session = sessionFactory.openSession();
            DAO.session.set(session);
        }
        return session;
    }
    /*Начало транзакции*/
    protected void begin() {
        getSession().beginTransaction();
    }

    /*Коммит транзакции*/
    protected void commit() {
        getSession().getTransaction().commit();
    }

    /*Откат транзакции*/
    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        DAO.session.set(null);
    }

    /*Закрытие транзакции*/
    public static void close() {
        getSession().close();
        DAO.session.set(null);
    }
}
