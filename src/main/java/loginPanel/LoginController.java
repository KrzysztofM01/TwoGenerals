package loginPanel;

import database.DataBaseConnector;
import database.Entities.User;
import game.logic.cards.CardLogic;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import menuPanel.MenuPanel;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;

public class LoginController {

    @FXML
    private Text systemResponse;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    private Stage primaryStage;

    @FXML
    private void handleRegisterButtonAction() {
        if (checkIfFieldsAreEmpty()) {
            systemResponse.setText(DataBaseConnector.insertUser(
                    loginField.getText(),
                    DigestUtils.sha256Hex(passwordField.getText())));
        }
    }

    @FXML
    private void handleLoginButtonAction() {
        turnOnButtons(false);
        if (checkIfFieldsAreEmpty()) {
            User user = DataBaseConnector.checkPasswordForLogin(loginField.getText(), DigestUtils.sha256Hex(passwordField.getText()));

            if (user != null) {
                systemResponse.setText("Login successful");
                ArrayList<CardLogic> cardDeck = new ArrayList<>();
                ArrayList<Integer> intList = new ArrayList<>();

                // Converts string of ints into int list
                for (String field : user.getCardListString().split(", "))
                    intList.add(Integer.parseInt(field));

                // Converts ints in the list to cards from database, to ensure that it doesn't block main
                // application this is done in task, upon success sets the card deck and starts menu panel
                Task task = new Task<Void>() {
                    @Override
                    public Void call() {
                        for (Integer integer : intList) {
                            cardDeck.add(DataBaseConnector.getCardLogic(integer));
                            systemResponse.setText("Loading data from database " + cardDeck.size() * 2 + "%...");
                        }
                        return null;
                    }
                };
                task.setOnSucceeded(e -> {
                    user.setCardDeck(cardDeck);
                    new MenuPanel(primaryStage, user);
                });
                new Thread(task).start();
            } else {
                systemResponse.setText("Can't login with your input");
            }
        }
        turnOnButtons(true);
    }

    public void turnOnButtons(boolean bool) {
        loginButton.setDisable(!bool);
        registerButton.setDisable(!bool);
    }

    private boolean checkIfFieldsAreEmpty() {
        if (!loginField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            return true;
        } else {
            systemResponse.setText("Please write your login and password");
            return false;
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setSystemResponse(String systemResponse) {
        this.systemResponse.setText(systemResponse);
    }
}