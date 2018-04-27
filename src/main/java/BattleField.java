import cards.Card;

public class BattleField {

    public static void addCardToBattleFront(Card card, Player player, BattleFront battleFront){
        battleFront.addCard(card);
        player.getDeck().remove(card);
    }

    public static void attackOnBattleFront(BattleFront battleFront1, BattleFront battleFront2){
        int difference = battleFront1.getSummedPower() - battleFront2.getSummedPower();
        if (difference > 0 ){
            battleFront2.setHP(battleFront2.getHP() - difference);
        } else {
            battleFront1.setHP(battleFront1.getHP() - difference*(-1));
        }
    }
}
