package action_factory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import frames.MainGUINetworked;
import game_logic.QuestionAnswer;	
import game_logic.ServerGameData;
import messages.Message;
import messages.QuestionAnsweredMessage;
import messages.UnableToAnswerMessage;
import other_gui.QuestionGUIElement;
import other_gui.QuestionGUIElementNetworked;
import other_gui.TeamGUIComponents;
import server.Client;
import timers.BuzzInTimer;
import timers.ClockSpin;
import timers.PlayersClock;

public class UnableToAnswerAction extends Action{

	private ServerGameData gameData;
	private MainGUINetworked mainGUI;
	private QuestionGUIElementNetworked currentQuestion;
	private TeamGUIComponents currentTeam;
	private String answer;
	private Client client;
	private PlayersClock myGif;
	public ClockSpin myClock;
//	public BuzzInTimer bit;

	public void executeAction(MainGUINetworked mainGUI, ServerGameData gameData, Message message,
			Client client) {
		//set variables
		if(mainGUI.myTimeQuestion.timer!=null){
			mainGUI.myTimeQuestion.stopTimer();
		}
		if(mainGUI.myTimer.timer!=null){
			mainGUI.myTimer.stopTimer();
		}
		if(mainGUI.buzzInTimer.timer!=null){
			mainGUI.buzzInTimer.stopTimer();
		}
		
		for(QuestionGUIElement qge: gameData.getQuestions()){
				qge.myFISH.setVisible(true);			
		}
		
		UnableToAnswerMessage qAnsweredMessage = (UnableToAnswerMessage) message;
		this.gameData = gameData;
		this.client = client;
		this.mainGUI = mainGUI;
		currentQuestion = client.getCurrentQuestion();
		currentTeam = currentQuestion.getCurrentTeam();
		
//		mainGUI.myTimeQuestion.stopTimer();
		
		validFormat();
		mainGUI.buzzInTimer.startTime(client.getCurrentQuestion().countdown);
//		mainGUI.myClock.startAnimation();
		
		for (QuestionGUIElement question : gameData.getQuestions()){
			question.teamLabel.removeAll();
			question.teamLabel.setIcon(new ImageIcon(createImage(mainGUI.myClock)));
//			question.teamLabel.add(mainGUI.myClock);
		}
	}
	
	public BufferedImage createImage(JPanel panel) {

		int w = panel.getWidth();
		int h = panel.getHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		panel.print(g);
		return bi;
	}
	
	private void validFormat(){
			mainGUI.pointsPanel.removeAll();
			PlayersClock myGif = new PlayersClock(mainGUI.pointsPanel,gameData);
			myGif.normal();
			currentTeam.deductPoints(currentQuestion.getPointValue());
			mainGUI.addUpdate(currentTeam.getTeamName()+" answered incorrectly. $"+currentQuestion.getPointValue()+" will be deducted from their total. ");
			//check if all teams have gotten a chance to answer
			currentQuestion.setBuzzInPeriod();
			mainGUI.addUpdate("Buzz in to answer!");
			//TODO 
	}
	
	
}
