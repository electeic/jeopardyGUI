package timers;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frames.MainGUINetworked;
import game_logic.ServerGameData;
import messages.Message;
import other_gui.QuestionGUIElement;
import server.Client;

public class BuzzInTimer{
	public Timer timer;
	ServerGameData gameData;
	Client client;
	MainGUINetworked mainGUI;
	JPanel pointsPanel;
	public PlayersClock myGif;
//	JLabel myL;
	
//	public BuzzInTimer(JLabel myLabel, MainGUINetworked mainGUI, ServerGameData gameData, Client client, JPanel pointsPanel){
	public BuzzInTimer(MainGUINetworked mainGUI, ServerGameData gameData, Client client, JPanel pointsPanel){

		myGif = new PlayersClock(pointsPanel, gameData);
//		this.myL = myLabel;
		this.gameData = gameData;
		this.client = client;
		this.mainGUI = mainGUI;
		this.pointsPanel = pointsPanel;
	}

	public void startTime(JLabel myL){
	    timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 20;
			@Override
			public void run() {
				myL.setText(":" + i);
				i--;
				if (i < 0){
//					checkReadyForFJ()
					mainGUI.addUpdate("No one choose a question in time");
					gameData.nextTurn();
					mainGUI.addUpdate("It is "+gameData.getCurrentTeam().getTeamName()+"'s turn to choose a question.");
					if (gameData.getCurrentTeam().getTeamIndex() != client.getTeamIndex()) mainGUI.disableAllButtons();
					myGif.addComponents();
					mainGUI.showMainPanel();
					timer.cancel();
				}

			}
		}, 1000, 1000);

	}
	public void stopTimer(){
		timer.cancel();
	}
}