package variables;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class VariablesGraphics {

    private static VariablesGraphics instance;

    private VariablesGraphics() {
    }

    // Create instance when someone wants to getInstance for the first time
    public static synchronized VariablesGraphics getInstance() {
        if (instance == null) {
            instance = new VariablesGraphics();
        }
        return instance;
    }

    // Graphic variables
    private Screen screen = Screen.getPrimary();
    private Rectangle2D screenBounds = screen.getBounds();
    private final double screenHeight = screenBounds.getHeight();
    private final double screenWidth = screenBounds.getWidth();
    private final double cardHeight = screenBounds.getHeight()*0.1;
    private  final double cardPadding = 2;
    private final double battleFieldWidth = screenBounds.getWidth() * 0.71 / 3;
    private final double battleFieldBreakWidth = screenBounds.getWidth() * 0.01;
    private final double battleFieldHeight = screenBounds.getHeight() * 0.495;
    private final double battleFrontTextBoxWidth = battleFieldWidth / 2;
    private final double battleFrontTextBoxHeight = battleFieldHeight / 7;
    private final double battleFieldPositionY = screenHeight / 2 - battleFieldHeight / 1.62;
    private final double cardWidth = cardHeight*340/475;
    private final double cardPrevieWidth = cardWidth * 2.5;
    private final double cardPreviewHeigth = cardHeight * 2.5;
    private final double playerCardPositionY = screenHeight*0.985- 2*cardHeight-2*cardPadding;

    public double getScreenHeight() {
        return screenHeight;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getCardHeight() {
        return cardHeight;
    }

    public double getCardPadding() {
        return cardPadding;
    }

    public double getBattleFieldWidth() {
        return battleFieldWidth;
    }

    public double getBattleFieldBreakWidth() {
        return battleFieldBreakWidth;
    }

    public double getBattleFieldHeight() {
        return battleFieldHeight;
    }

    public double getBattleFrontTextBoxWidth() {
        return battleFrontTextBoxWidth;
    }

    public double getBattleFrontTextBoxHeight() {
        return battleFrontTextBoxHeight;
    }

    public double getBattleFieldPositionY() {
        return battleFieldPositionY;
    }

    public double getCardWidth() {
        return cardWidth;
    }

    public double getCardPrevieWidth() {
        return cardPrevieWidth;
    }

    public double getCardPreviewHeigth() {
        return cardPreviewHeigth;
    }

    public double getPlayerCardPositionY() {
        return playerCardPositionY;
    }
}
