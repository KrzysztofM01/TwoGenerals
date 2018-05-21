package graphic;

public class PlayerTypeConverter {

    private static int yourPlayerID;

    public static PlayerType toPlayerType (int playerID){
        if (playerID == yourPlayerID){
            return PlayerType.player;
        } else {
            return PlayerType.opponent;
        }
    }

    public static void setYourPlayerID(int yourPlayerID) {
        PlayerTypeConverter.yourPlayerID = yourPlayerID;
    }
}
