package game.variables;

public class VariablesLogic {

    private static VariablesLogic instance;

    private VariablesLogic() {
    }

    // Create instance when someone wants to getInstance for the first time
    public static synchronized VariablesLogic getInstance() {
        if (instance == null) {
            instance = new VariablesLogic();
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
}
