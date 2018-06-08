package loginPanel;

import database.DataBaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.codec.digest.DigestUtils;

public class PaneController {

    @FXML
    private Text systemResponse;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;


    @FXML
    protected void handleRegisterButtonAction() {
        if (!loginField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            DataBaseConnector.insertUser(loginField.getText(), DigestUtils.sha256Hex(passwordField.getText()), systemResponse);
        } else {
            systemResponse.setText("Please write your login and password");
        }
    }

    @FXML
    protected void handleLoginButtonAction() {
        if (!loginField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            DataBaseConnector.checkPasswordForLogin(loginField.getText(), DigestUtils.sha256Hex(passwordField.getText()), systemResponse);
        } else {
            systemResponse.setText("Please write your login and password");
        }
    }
}