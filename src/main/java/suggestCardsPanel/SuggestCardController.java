package suggestCardsPanel;

import database.DataBaseConnector;
import game.graphic.cards.cardPreview.CardPreview;
import game.graphic.cards.cardPreview.CardPreviewTextNumbers;
import game.graphic.panes.CardPreviewPane;
import game.logic.cards.CardCreator;
import game.logic.cards.CardLogic;
import game.logic.cards.CardType;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SuggestCardController {

    @FXML
    private TextField cardNameField;

    @FXML
    private TextField powerField;

    @FXML
    private TextField costField;

    @FXML
    private TextField imageURLField;

    @FXML
    private CheckBox battleCardCheckBox;

    @FXML
    private CheckBox specialCardCheckBox;

    @FXML
    private TextArea cardDescription;

    @FXML
    private Button sendCardButton;

    private ImageView borderImage;
    private CardPreviewPane cardPreviewPane;
    private CardPreviewTextNumbers powerNumber;
    private CardPreviewTextNumbers costNumber;

    @FXML
    protected void battleCardSelected() {
        battleCardCheckBox.setSelected(true);
        specialCardCheckBox.setSelected(false);
        if (battleCardCheckBox.isSelected()) {
            borderImage.setImage(new Image("cardImages/BorderBattleCard.png"));

        }
    }

    @FXML
    protected void specialCardSelected() {
        specialCardCheckBox.setSelected(true);
        battleCardCheckBox.setSelected(false);
        if (specialCardCheckBox.isSelected()) {
            borderImage.setImage(new Image("cardImages/BorderSpecialCard.png"));

        }
    }

    @FXML
    protected void loadGraphics() {
        if (checkIfAllFieldsAreFilled()) {
            CardType cardType;
            if (specialCardCheckBox.isSelected()) {
                cardType = CardType.SpecialCard;
            } else {
                cardType = CardType.BattleCard;
            }
            CardLogic cardLogic = CardCreator.newCard(cardNameField.getText(), cardType, Integer.valueOf(powerField.getText()), Integer.valueOf(costField.getText()), imageURLField.getText(), 0);
            assert cardLogic != null && powerNumber != null && costNumber != null;
            cardPreviewPane.getChildren().removeAll(cardPreviewPane.getTempCardPreview(), powerNumber, costNumber);
            cardLogic.setCardDescription(cardDescription.getText());

            try {
                CardPreview cardPreview = new CardPreview(cardLogic);
                cardPreviewPane.setTempCardPreview(cardPreview);
                powerNumber = new CardPreviewTextNumbers(cardLogic.getPower(), true);
                powerNumber.setViewOrder(-50);
                powerNumber.setTranslateY(cardPreview.getTranslateY() * 2);
                costNumber = new CardPreviewTextNumbers(cardLogic.getCost(), false);
                costNumber.setViewOrder(-50);
                costNumber.setTranslateY(cardPreview.getTranslateY() * 0.18);
                cardPreviewPane.getChildren().addAll(cardPreview, powerNumber, costNumber);
            } catch (IllegalArgumentException e) {
                imageURLField.setText("Wrong URL address!");
            }
        }
    }

    @FXML
    public void initialize() {
        powerField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                powerField.setText(oldValue);
            }
        });
        costField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                costField.setText(oldValue);
            }
        });
    }

    @FXML
    protected void suggestCard() {
        if (checkIfAllFieldsAreFilled()) {
            sendCardButton.setDisable(true);
            sendCardButton.setText("Card Sent");
            Task task = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        Image testImage = new Image(imageURLField.getText());
                        CardType cardType;
                        if (specialCardCheckBox.isSelected()) {
                            cardType = CardType.SpecialCard;
                        } else {
                            cardType = CardType.BattleCard;
                        }
                        CardLogic cardLogic = CardCreator.newCard(cardNameField.getText(), cardType, Integer.valueOf(powerField.getText()), Integer.valueOf(costField.getText()), imageURLField.getText(), 0);
                        DataBaseConnector.insertCardSuggest(cardLogic);
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        Platform.runLater(() -> sendCardButton.setText("Invalid Image"));

                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            task.setOnSucceeded(e -> {
                sendCardButton.setText("Suggest Card");
                sendCardButton.setDisable(false);
            });
            new Thread(task).start();
        }
    }

    public void setBorderImage(ImageView borderImage) {
        this.borderImage = borderImage;
    }

    public void setCardPreviewPane(CardPreviewPane cardPreviewPane) {
        this.cardPreviewPane = cardPreviewPane;
    }

    private boolean checkIfAllFieldsAreFilled() {
        if (cardNameField.getText().isEmpty()) {
            cardNameField.setText("Please fill this field.");
            return false;
        } else if (powerField.getText().isEmpty()) {
            powerField.setText("0");
            return false;
        } else if (costField.getText().isEmpty()) {
            costField.setText("0");
            return false;
        } else if (imageURLField.getText().isEmpty()) {
            imageURLField.setText("Please fill this field.");
            return false;
        } else {
            return true;
        }

    }

}