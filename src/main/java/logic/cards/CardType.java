package logic.cards;

import java.io.Serializable;
import java.util.Arrays;

public enum CardType implements Serializable{
    BattleCard, BoostArmyMorale, MagicCatalyst, HawkMaster, MagicNullification, UncontrolledPower, ShadowDancer, Assassination, Trickster;

    public String toStringSeparated() {
        return this.toString().replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
    }
}
