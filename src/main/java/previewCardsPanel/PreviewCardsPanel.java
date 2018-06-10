package previewCardsPanel;

import database.DataBaseConnector;
import game.variables.VariablesGraphics;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PreviewCardsPanel {

    public PreviewCardsPanel(Stage primaryStage) {

        // To remove
        DataBaseConnector.startSessionFactory();
        //


        Pane previewCardsPane = new Pane();
        Scene scene = new Scene(previewCardsPane, VariablesGraphics.getInstance().getScreenWidth(), VariablesGraphics.getInstance().getScreenHeight());

        InfrastructurePane infrastructurePane = new InfrastructurePane();
        ScrollPane cardScrollPane = infrastructurePane.infrastructurePane();
        cardScrollPane.setId("scrollPane");
        scene.getStylesheets().addAll("cssFiles/previewCardsPanel.css");

        previewCardsPane.getChildren().addAll(cardScrollPane);

        primaryStage.close();
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
