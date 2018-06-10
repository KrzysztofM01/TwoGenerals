package loginPanel;

import database.DataBaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

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
        if (!loginField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            DataBaseConnector.insertUser(loginField.getText(), DigestUtils.sha256Hex(passwordField.getText()), systemResponse);
        } else {
            systemResponse.setText("Please write your login and password");
        }
    }

    @FXML
    private void handleLoginButtonAction() {
        if (!loginField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            DataBaseConnector.checkPasswordForLogin(loginField.getText(), DigestUtils.sha256Hex(passwordField.getText()), systemResponse, primaryStage, loginButton, registerButton);
        } else {
            systemResponse.setText("Please write your login and password");
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setSystemResponse(String systemResponse) {
        this.systemResponse.setText(systemResponse);
    }
}