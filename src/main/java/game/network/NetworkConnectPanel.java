package game.network;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
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

        ip.setTranslateY(VariablesGraphics.getInstance().getScreenHeight() * 0.22);
        ip.setTranslateX(VariablesGraphics.getInstance().getScreenWidth() * 0.175);

        port.setTranslateY(VariablesGraphics.getInstance().getScreenHeight() * 0.25);
        port.setTranslateX(VariablesGraphics.getInstance().getScreenWidth() * 0.158);

        ipField.setLayoutX(VariablesGraphics.getInstance().getScreenWidth() * 0.2);
        ipField.setLayoutY(VariablesGraphics.getInstance().getScreenHeight() * 0.2);

        portField.setLayoutX(VariablesGraphics.getInstance().getScreenWidth() * 0.2);
        portField.setLayoutY(VariablesGraphics.getInstance().getScreenHeight() * 0.23);

        connectButton.setLayoutX(VariablesGraphics.getInstance().getScreenWidth() * 0.268);
        connectButton.setLayoutY(VariablesGraphics.getInstance().getScreenHeight() * 0.26);

        hostButton.setLayoutX(VariablesGraphics.getInstance().getScreenWidth() * 0.23);
        hostButton.setLayoutY(VariablesGraphics.getInstance().getScreenHeight() * 0.26);

        cancelButton.setLayoutX(VariablesGraphics.getInstance().getScreenWidth() * 0.22);
        cancelButton.setLayoutY(VariablesGraphics.getInstance().getScreenHeight() * 0.26);
        cancelButton.setVisible(false);
        cancelButton.setManaged(false);

        connectionStatus.setId("connectionStatus");
        connectionStatus.setTranslateX(VariablesGraphics.getInstance().getScreenWidth() * 0.2);
        connectionStatus.setTranslateY(VariablesGraphics.getInstance().getScreenHeight() * 0.31);
        connectionStatus.setWrappingWidth(VariablesGraphics.getInstance().getScreenWidth() * 0.12);

        Scene scene = new Scene(connectMainPane, VariablesGraphics.getInstance().getScreenWidth() / 2, VariablesGraphics.getInstance().getScreenHeight() / 2);
        scene.getStylesheets().addAll(STYLESHEET);

        connectMainPane.getChildren().addAll(cancelButton, ip, port, ipField, portField, hostButton,
                connectButton, connectionStatus);

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
