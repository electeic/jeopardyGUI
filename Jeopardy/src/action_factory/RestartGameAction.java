package action_factory;

import frames.MainGUINetworked;
import game_logic.ServerGameData;
import messages.Message;
import messages.RestartGameMessage;
import server.Client;

public class RestartGameAction extends Action{

	@Override
	public void executeAction(MainGUINetworked mainGUI, ServerGameData gameData, Message message, Client client) {
		RestartGameMessage rgm = (RestartGameMessage) message;
		//set the current turn to the new first team, passed in the message
		gameData.setNextTurn(rgm.getFirstTeam());
		//reset all questions and teams
		mainGUI.resetGame();
		client.setElimindated(false);
		//check whether we need to disable game board buttons
		if (rgm.getFirstTeam() != client.getTeamIndex()){
			mainGUI.disableAllButtons();
		}
		
		if(mainGUI.myTimeQuestion.timer!=null){
			mainGUI.myTimeQuestion.stopTimer();
		}
		if(mainGUI.myTimer.timer!=null){
			mainGUI.myTimer.stopTimer();
		}
		if(mainGUI.buzzInTimer.timer!=null){
			mainGUI.buzzInTimer.stopTimer();
		}
		
		mainGUI.myClock.startAnimation();
		mainGUI.myTimer.startTime(mainGUI.getJeopardyLabel());
	}

}
