package variables;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class VariablesGraphics {
    private static Screen screen = Screen.getPrimary();
    private static Rectangle2D screenBounds = screen.getBounds();
    public static final double screenHeight = screenBounds.getHeight();
    public static final double screenWidth = screenBounds.getWidth();
    public static final double cardHeight = screenBounds.getHeight()*0.1;
    public static final double cardPadding = 2;
    public static final double battleFieldWidth = screenBounds.getWidth() * 0.71 / 3;
    public static final double battleFieldBreakWidth = screenBounds.getWidth() * 0.01;
    public static final double battleFieldHeight = screenBounds.getHeight() * 0.495;
    public static final double battleFrontTextBoxWidth = battleFieldWidth / 2;
    public static final double battleFrontTextBoxHeight = battleFieldHeight / 7;
    public static final double battleFieldPositionY = screenHeight / 2 - battleFieldHeight / 1.62;
    public static final double cardWidth = cardHeight*340/475;
    public static final double cardPrevieWidth = cardWidth * 2.5;
    public static final double cardPreviewHeigth = cardHeight * 2.5;
    public static final double playerCardPositionY = screenHeight*0.985- 2*cardHeight-2*cardPadding;
}
