package game.eventHandlers;

import game.graphic.GraphicManager;
import game.graphic.PlayerType;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import game.logic.LogicManager;
import game.network.MethodWrapper;
import game.network.NetworkManager;
import game.variables.VariablesLogic;

import java.io.IOException;

public class EndTurnHandler implements EventHandler<MouseEvent> {

    private NetworkManager networkManager;
    private GraphicManager graphicManager;
    private LogicManager logicManager;

    public EndTurnHandler(NetworkManager networkManager, GraphicManager graphicManager, LogicManager logicManager) {
        this.networkManager = networkManager;
        this.graphicManager = graphicManager;
        this.logicManager = logicManager;
    }

    @Override
    public void handle(MouseEvent event) {
        if (networkManager.isYourTurn()){
            try {
                networkManager.setYourTurn(false);
                networkManager.getOos().writeObject(MethodWrapper.endTurn());
                logicManager.getPlayer(PlayerType.player).setActionPoints(VariablesLogic.getInstance().getPlayerActionPoints());
                graphicManager.endTurnGraphics();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
