package action_factory;

import frames.MainGUINetworked;
import game_logic.ServerGameData;
import messages.Message;
import messages.QuestionClickedMessage;
import other_gui.QuestionGUIElement;
import other_gui.QuestionGUIElementNetworked;
import other_gui.TeamGUIComponents;
import server.Client;
import timers.TimeQuestionChosen;
import timers.WaitAnimation;

public class QuestionClickedAction extends Action{

	public void executeAction(MainGUINetworked mainGUI, ServerGameData gameData, Message message,
			Client client) {
		QuestionClickedMessage qClickedMessage = (QuestionClickedMessage) message;
		//set the current question on the client using the indices provided in the message
		client.setCurrentQuestion(qClickedMessage.getX(), qClickedMessage.getY());
		//get the new current question and set the original team that chose it
		QuestionGUIElementNetworked newCurrentQuestion = client.getCurrentQuestion();
		newCurrentQuestion.setOriginalTeam(gameData.getCurrentTeam().getTeamIndex(), gameData);
		//change to question panel, update number of chosen questions in game data, and set this question to asked
		
		if(mainGUI.myTimeQuestion.timer!=null){
			mainGUI.myTimeQuestion.stopTimer();
		}
		if(mainGUI.myTimer!=null){
			mainGUI.myTimer.stopTimer();
		}
		if(mainGUI.buzzInTimer.timer!=null){
			mainGUI.buzzInTimer.stopTimer();
		}
		
		mainGUI.changePanel(newCurrentQuestion.getQuestionPanel());
		gameData.updateNumberOfChosenQuestions();
		newCurrentQuestion.setAsked();
		
		for(QuestionGUIElement qge: gameData.getQuestions()){
			if(client.getTeamIndex() == gameData.getCurrentTeam().getTeamIndex()){
				qge.announcementsLabel.setText("Answer in 20 seconds! GO");		
			}
		}
		
//		for (QuestionGUIElement question : gameData.getQuestions()){
//			question.teamLabel.removeAll();
//			question.teamLabel.setText(gameData.getCurrentTeam().getTeamName());
		
//		}
		for(QuestionGUIElement qge: gameData.getQuestions()){
			if(client.getTeamIndex() != gameData.getCurrentTeam().getTeamIndex()){
				qge.showFishy();
			}
			else{
				qge.hideFishy();
			}
		}

		mainGUI.myTimeQuestion.startTime(newCurrentQuestion.countdown);
		//hnewCurrentQuestion.myFISH.add(new WaitAnimation());
		//myFISH.add(new WaitAnimation());
		//myFISH.setVisible(false);
		
		if(client.getTeamIndex() == gameData.getCurrentTeam().getTeamIndex()){
			mainGUI.addUpdate("Buzz in to answer!");
		}

	}

}