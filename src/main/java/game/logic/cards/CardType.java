package game.logic.cards;

import java.io.Serializable;
import java.util.Arrays;

public enum CardType implements Serializable{
    BattleCard, BoostArmyMorale, MagicCatalyst, Tracker, MagicNullification, UncontrolledPower, Infiltrator, Assassination, Trickster;

    public String toStringSeparated() {
        return this.toString().replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
    }
}
