package logic.battleFields;

import graphic.PlayerType;

import java.io.Serializable;

public enum LineType implements Serializable{
    left, center, right;

    public String toOpponentBattleFrontNodeID (){
        switch (this){
            case left:
                return "leftOpponentBattleFront";
            case center:
                return "centerOpponentBattleFront";
            case right:
                return "rightOpponentBattleFront";
        }
        return "";
    }
}

