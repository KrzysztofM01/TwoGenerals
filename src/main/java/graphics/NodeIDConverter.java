package graphics;

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
}
