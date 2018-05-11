package graphic;

public class PlayerTypeConverter {
    public static PlayerType toPlayerType (int playerID){
        if (playerID == 0){
            return PlayerType.player;
        } else {
            return PlayerType.opponent;
        }
    }
}
