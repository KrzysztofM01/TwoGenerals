package loginPanel;

import database.DataBaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class LoginPanel extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        DataBaseConnector.startSessionFactory();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/loginMenu.fxml")));

        Scene scene = new Scene(root, 600, 550);
        primaryStage.setTitle("Two Generals");
        primaryStage.getIcons().add(new Image("images/2GIcon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

        /*

        primaryStage.show();
        primaryStage.setTitle("No hula hula");
        //flow pane zarzadca scen
        FlowPane flowPane = new FlowPane();
        // scena mozna do niej wrzucac


        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("FXML/loginMenu.fxml"));
        GridPane fp = fxmlLoader.load();
        Scene scene = new Scene(fp, 300, 300);
        primaryStage.setScene(scene);
        /*
        Button button = new Button("Click me babe...");
        button.setLayoutX(150);
        button.setLayoutX(150);
        flowPane.getChildren().add(button);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("wololo");
                }
            });
            */
    }

    @Override
    public void stop() {
        DataBaseConnector.getSessionFactory().close();
    }
}
