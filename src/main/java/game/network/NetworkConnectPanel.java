package game.network;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import game.variables.VariablesGraphics;

public class NetworkConnectPanel {

    private static final String STYLESHEET = "cssFiles/networkConnectPanel.css";

    private TextField ipField = new TextField("127.0.0.1");
    private TextField portField = new TextField("22222");
    private Button hostButton = new Button("Host");
    private Button connectButton = new Button("Connect");
    private Button cancelButton = new Button("Cancel");
    private Text connectionStatus = new Text();

    NetworkConnectPanel(Stage primaryStage) {

        Pane connectMainPane = new Pane();

        Text ip = new Text("IP:");
        ip.setId("IP");

        Text port = new Text("Port:");
        port.setId("port");

        ip.setTranslateX(135);
        ip.setTranslateY(185);

        port.setTranslateX(113);
        port.setTranslateY(225);

        ipField.setLayoutX(170);
        ipField.setLayoutY(165);

        portField.setLayoutX(170);
        portField.setLayoutY(205);

        connectButton.setLayoutX(205);
        connectButton.setLayoutY(245);

        hostButton.setLayoutX(277);
        hostButton.setLayoutY(245);

        cancelButton.setLayoutX(265);
        cancelButton.setLayoutY(245);
        cancelButton.setVisible(false);
        cancelButton.setManaged(false);

        connectionStatus.setId("connectionStatus");
        connectionStatus.setTranslateX(170);
        connectionStatus.setTranslateY(305);
        connectionStatus.setWrappingWidth(150);

        StackPane logoPane = new StackPane();
        logoPane.setId("logoPane");
        logoPane.setPrefSize(325, 100);
        logoPane.setLayoutX(62.5);
        logoPane.setLayoutY(50);

        Scene scene = new Scene(connectMainPane, 450, 400);
        scene.getStylesheets().addAll(STYLESHEET);

        connectMainPane.getChildren().addAll(cancelButton, ip, port, ipField, portField, hostButton,
                connectButton, connectionStatus, logoPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getIp() {
        return ipField.getText();
    }

    public int getPort() {
        return Integer.valueOf(portField.getText());
    }

    public Button getHostButton() {
        return hostButton;
    }

    public Button getConnectButton() {
        return connectButton;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus.setText(connectionStatus);
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void showCancelButton() {
        operateCancelButton(false, true);
    }

    private void operateCancelButton(boolean b, boolean b2) {
        this.connectButton.setVisible(b);
        this.connectButton.setManaged(b);
        this.hostButton.setVisible(b);
        this.hostButton.setManaged(b);
        this.cancelButton.setVisible(b2);
        this.cancelButton.setManaged(b2);
    }

    public void hideCancelButton() {
        operateCancelButton(true, false);
    }
}
