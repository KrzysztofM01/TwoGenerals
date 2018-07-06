package database;

import database.Entities.CardDB;
import database.Entities.CardSuggest;
import database.Entities.User;
import game.logic.cards.CardCreator;
import game.logic.cards.CardLogic;
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

    public static void closeSessionFactory() {
        sessionFactory.close();
    }

    public static String insertUser(String login, String password) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(login, password));
            transaction.commit();
            return "Registration was successful";
        } catch (HibernateException e) {
            if (e.getCause().getMessage().contains("Duplicate entry")) {
                return "This login is already taken";
            }
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return "Error: cannot register.";
    }

    public static User checkPasswordForLogin(String login, String password) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), login));
            TypedQuery<User> typedQuery = session.createQuery(criteriaQuery);
            User user = typedQuery.getSingleResult();
            transaction.commit();
            session.close();
            if (user.getPassword().equals(password)) {
                return user;
            }
        } catch (javax.persistence.NoResultException ignored) {
        }
        return null;
    }

    public static CardLogic getCardLogic(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CardDB> criteriaQuery = criteriaBuilder.createQuery(CardDB.class);
        Root<CardDB> root = criteriaQuery.from(CardDB.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        TypedQuery<CardDB> typedQuery = session.createQuery(criteriaQuery);
        CardDB cardDB = typedQuery.getSingleResult();
        CardLogic cardLogic = CardCreator.newCardFromDB(cardDB);
        transaction.commit();
        session.close();
        return cardLogic;
    }

    public static ArrayList<CardLogic> getAllCards() {
        ArrayList<CardLogic> cardDeck = new ArrayList<>();
        List<CardDB> cardDeckDB;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CardDB> criteriaQuery = criteriaBuilder.createQuery(CardDB.class);
        Root<CardDB> root = criteriaQuery.from(CardDB.class);
        criteriaQuery.select(root);
        TypedQuery<CardDB> typedQuery = session.createQuery(criteriaQuery);
        cardDeckDB = typedQuery.getResultList();
        for (CardDB cardDB : cardDeckDB) {
            cardDeck.add(CardCreator.newCardFromDB(cardDB));
        }
        transaction.commit();
        session.close();
        return cardDeck;
    }

    public static ArrayList<CardLogic> getAllCardSuggestions() {
        ArrayList<CardLogic> cardDeck = new ArrayList<>();
        List<CardSuggest> cardSuggestList;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CardSuggest> criteriaQuery = criteriaBuilder.createQuery(CardSuggest.class);
        Root<CardSuggest> root = criteriaQuery.from(CardSuggest.class);
        criteriaQuery.select(root);
        TypedQuery<CardSuggest> typedQuery = session.createQuery(criteriaQuery);
        cardSuggestList = typedQuery.getResultList();
        for (CardSuggest cardSuggest : cardSuggestList) {
            cardDeck.add(CardCreator.newCardFromSuggest(cardSuggest));
        }
        transaction.commit();
        session.close();
        return cardDeck;
    }

    public static void saveCardList(User user) {
        StringBuilder sb = new StringBuilder();
        for (CardLogic cardLogic : user.getCardDeck()) {
            sb.append(cardLogic.getCardID());
            sb.append(", ");
        }
        user.setCardListString(sb.toString());
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static void removeCardSuggest(CardLogic cardLogic) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(new CardSuggest(cardLogic));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    public static void insertCardSuggest(CardLogic cardLogic) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new CardSuggest(cardLogic));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
