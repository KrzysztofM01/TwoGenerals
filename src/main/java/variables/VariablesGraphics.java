package variables;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class VariablesGraphics {
    private static Screen screen = Screen.getPrimary();
    private static Rectangle2D screenBounds = screen.getVisualBounds();
    public static final double screenHeight = screenBounds.getHeight()*0.98;
    public static final double screenWidth = screenBounds.getWidth()*0.98;
    public static final double cardHeight = screenBounds.getWidth()*0.06;
    public static final double cardPadding = 2;
    public static final double battleFieldWidth = screenBounds.getWidth() * 0.7 / 3;
    public static final double battleFieldBreakWidth = screenBounds.getWidth() * 0.01;
    public static final double battleFieldHeight = screenBounds.getHeight() * 0.495;
    public static final double battleFrontTextBoxWidth = battleFieldWidth / 2;
    public static final double battleFrontTextBoxHeight = battleFieldHeight / 7;
    public static final double battleFieldPositionY = screenHeight / 2 - battleFieldHeight / 1.8;
    public static final double cardWidth = cardHeight/3*2;
}
