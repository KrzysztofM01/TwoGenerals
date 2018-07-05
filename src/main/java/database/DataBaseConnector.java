package database;

import game.logic.cards.CardCreator;
import game.logic.cards.CardLogic;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainPanel.MainMenuPanel;
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

    public static void checkPasswordForLogin(String login, String password, Text systemResponse, Stage primaryStage, Button loginButton, Button registerButton) {

        try {
            loginButton.setDisable(true);
            registerButton.setDisable(true);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), login));
            TypedQuery<User> typedQuery = session.createQuery(criteriaQuery);
            User user = typedQuery.getSingleResult();
            if (user.getPassword().equals(password)) {
                systemResponse.setText("Login successful");
                launchMainMenuPanel(primaryStage, user, systemResponse);
            } else {
                systemResponse.setText("Invalid password");
                loginButton.setDisable(false);
                registerButton.setDisable(false);
            }
            transaction.commit();
            session.close();
        } catch (javax.persistence.NoResultException e) {
            loginButton.setDisable(false);
            registerButton.setDisable(false);
            if (e.getMessage().equals("No entity found for query")) {
                systemResponse.setText("This user is not in database");
            }
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static CardLogic getCardLogic(int id) {
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

    private static void launchMainMenuPanel(Stage primaryStage, User user, Text systemResponse) {
        ArrayList<CardLogic> cardDeck = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();

        for (String field : user.getCardListString().split(", "))
            intList.add(Integer.parseInt(field));

        Task task = new Task<Void>() {
            @Override
            public Void call() {
                for (Integer integer : intList) {
                    cardDeck.add(getCardLogic(integer));
                    systemResponse.setText("Loading data from database " + cardDeck.size() * 2 + "%...");
                }
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            user.setCardDeck(cardDeck);
            new MainMenuPanel(primaryStage, user);
        });
        new Thread(task).start();

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
        for (CardDB cardDB: cardDeckDB) {
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
        for (CardSuggest cardSuggest: cardSuggestList) {
            cardDeck.add(CardCreator.newCardFromSuggest(cardSuggest));
        }
        transaction.commit();
        session.close();
        return cardDeck;
    }

    public static void saveCardList (User user) {
        StringBuilder sb = new StringBuilder();
        for (CardLogic cardLogic: user.getCardDeck()) {
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

    public static void removeCardSuggest (CardLogic cardLogic) {
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
}
