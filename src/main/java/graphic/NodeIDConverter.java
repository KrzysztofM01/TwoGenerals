package graphic;

import logic.battleFields.LineType;

public class NodeIDConverter {
    public static LineType toLineType(String nodeID){
        if (nodeID.contains("left")) {
            return LineType.left;
        } else if (nodeID.contains("center")) {
            return LineType.center;
        } else {
            return LineType.right;
        }
    }

    public static boolean isItBattleFieldID(String nodeID){
        return nodeID.contains("BattleField") || nodeID.contains("FrontTextBoxGUI");

    }
}
