package action_factory;

import frames.MainGUINetworked;
import game_logic.ServerGameData;
import messages.BuzzInMessage;
import messages.Message;
import other_gui.QuestionGUIElement;
import server.Client;
import timers.TimeQuestionChosen;

public class BuzzInAction extends Action{

	@Override
	public void executeAction(MainGUINetworked mainGUI, ServerGameData gameData,
			Message message, Client client) {
		BuzzInMessage buzzMessage = (BuzzInMessage) message;
		//update the team on the current question to be the one who buzzed in
		client.getCurrentQuestion().updateTeam(buzzMessage.getBuzzInTeam(), gameData);

		if(mainGUI.myTimeQuestion.timer!=null){
			mainGUI.myTimeQuestion.stopTimer();
		}
		if(mainGUI.myTimer.timer!=null){
			mainGUI.myTimer.stopTimer();
		}
		if(mainGUI.buzzInTimer.timer!=null){
			mainGUI.buzzInTimer.stopTimer();
		}
		
//		for(QuestionGUIElement qge: gameData.getQuestions()){
//				qge.myFISH.setVisible(false);
//		}

//		for(QuestionGUIElement qge: gameData.getQuestions()){
//			if(client.getTeamIndex() != gameData.getCurrentTeam().getTeamIndex()){
//				qge.myFISH.setVisible(true);
//			}else{
//				qge.myFISH.setVisible(false);
//			}
//		}
		
		mainGUI.myTimeQuestion.startTime(client.getCurrentQuestion().countdown);

	}

}
