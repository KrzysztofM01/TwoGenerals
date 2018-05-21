package network;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import variables.VariablesGraphics;

public class NetworkConnectPanel {

    private static final String STYLESHEET = "connectPanel.css";

    private TextField ipField = new TextField("127.0.0.1");
    private TextField portField = new TextField("22222");
    private Button hostButton = new Button("Host");
    private Button connectButton = new Button("Connect");
    private Button cancelButton = new Button("Cancel");
    private Text connectionStatus = new Text();

    public NetworkConnectPanel(Stage primaryStage) {

        Pane connectMainPane = new Pane();

        Text ip = new Text("IP:");
        ip.setId("IP");
        Text port = new Text("Port:");
        port.setId("port");
        ip.setTranslateY(VariablesGraphics.screenHeight*0.22);
        ip.setTranslateX(VariablesGraphics.screenWidth*0.175);
        port.setTranslateY(VariablesGraphics.screenHeight*0.25);
        port.setTranslateX(VariablesGraphics.screenWidth*0.158);
        this.connectionStatus.setId("connectionStatus");
        this.ipField.setLayoutX(VariablesGraphics.screenWidth*0.2);
        this.ipField.setLayoutY(VariablesGraphics.screenHeight*0.2);
        this.portField.setLayoutX(VariablesGraphics.screenWidth*0.2);
        this.portField.setLayoutY(VariablesGraphics.screenHeight*0.23);
        this.connectButton.setLayoutX(VariablesGraphics.screenWidth*0.268);
        this.connectButton.setLayoutY(VariablesGraphics.screenHeight*0.26);
        this.hostButton.setLayoutX(VariablesGraphics.screenWidth*0.23);
        this.cancelButton.setLayoutX(VariablesGraphics.screenWidth*0.22);
        this.hostButton.setLayoutY(VariablesGraphics.screenHeight*0.26);
        this.cancelButton.setLayoutY(VariablesGraphics.screenHeight*0.26);
        this.connectionStatus.setTranslateX(VariablesGraphics.screenWidth*0.2);
        this.connectionStatus.setTranslateY(VariablesGraphics.screenHeight*0.31);
        this.connectionStatus.setWrappingWidth(VariablesGraphics.screenWidth*0.12);
        this.cancelButton.setVisible(false);
        this.cancelButton.setManaged(false);

        Scene scene = new Scene(connectMainPane, VariablesGraphics.screenWidth/2, VariablesGraphics.screenHeight/2);
        scene.getStylesheets().addAll(STYLESHEET);

        connectMainPane.getChildren().addAll(cancelButton, ip, port, ipField, portField, hostButton, connectButton, connectionStatus);


        primaryStage.setTitle("Two Generals");
        primaryStage.getIcons().add(new Image("images/2GIcon.png"));

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
