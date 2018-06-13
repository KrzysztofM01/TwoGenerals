package game.variables;

public class VLogic {

    private static VLogic instance;

    private VLogic() {
    }

    // Create instance when someone wants to getInstance for the first time
    public static synchronized VLogic getInstance() {
        if (instance == null) {
            instance = new VLogic();
        }
        return instance;
    }


    // Getters for game.logic game.variables
    public int getBattleFieldHitPoints() {
        return 100;
    }

    public int getPlayerHitPoints() {
        return 75;
    }

    public int getPlayerActionPoints() {
        return 12;
    }

    public double getFrontLineAttackFactor() {
        return 1.0;
    }

    public double getPlayerAttackFactor() {
        return 1.0;
    }

    public int getMessageShowUpTimeInMillis() {
        return 2500;
    }

    public int getMaxOfCertainCardType() {
        return 3;
    }

    public int getMaxOfCards() {
        return 50;
    }
}
