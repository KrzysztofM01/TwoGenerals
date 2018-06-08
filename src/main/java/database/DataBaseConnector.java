package database;

import javafx.scene.text.Text;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnector {

    private static SessionFactory sessionFactory;

    public static void startSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void insertUser(String login, String password, Text systemResponse) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(login, password));
            transaction.commit();
            systemResponse.setText("Registration was successful");
        } catch (HibernateException e) {
            if (e.getCause().getMessage().contains("Duplicate entry")) {
                systemResponse.setText("This login is already taken");
            }
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public static ArrayList<User> getUserList() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        ArrayList<User> arrayList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            List queryList = session.createQuery("FROM database.User WHERE id = 5").list();
            for (Object aQueryList : queryList) {
                User c = (User) aQueryList;
                arrayList.add(c);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return arrayList;
    }

    public static User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), login));
        TypedQuery<User> typedQuery = session.createQuery(criteriaQuery);
        User User = typedQuery.getSingleResult();
        transaction.commit();
        session.close();
        return User;
    }

    public static void checkPasswordForLogin(String login, String password, Text systemResponse) {

        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), login));
            TypedQuery<User> typedQuery = session.createQuery(criteriaQuery);
            if (typedQuery.getSingleResult().getPassword().equals(password)) {
                systemResponse.setText("Login successful");
            } else {
                systemResponse.setText("Invalid password");
            }
            transaction.commit();
            session.close();
        } catch (javax.persistence.NoResultException e) {
            if (e.getMessage().equals("No entity found for query")) {
                systemResponse.setText("This user is not in database");
            }
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
