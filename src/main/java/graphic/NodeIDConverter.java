package graphic;

import logic.battleFields.LineType;

public class NodeIDConverter {

    public static LineType toLineType(String nodeID){
        // Based on what lies in nodeID, this method will return proper line type
        if (nodeID.contains("left")) {
            return LineType.left;
        } else if (nodeID.contains("center")) {
            return LineType.center;
        } else {
            return LineType.right;
        }
    }

    public static boolean isItBattleFieldID(String nodeID){
        // This method ensures that you can only click on battlefield or on card (which has the battlefield ID) to
        // play the card. Clicking on battlefield box or card numbers will return true, ensuring that
        // the card won't be played.
        return nodeID.contains("BattleField") || nodeID.contains("FrontTextBoxGUI") || nodeID.contains("cardNumbers");

    }
}
