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

public class MainScreenTimer{
	public Timer timer;
	ServerGameData gameData;
	Client client;
	MainGUINetworked mainGUI;
	JPanel pointsPanel;
	public PlayersClock myGif;
//	JLabel myL;
	
	public MainScreenTimer(JLabel myLabel, MainGUINetworked mainGUI, ServerGameData gameData, Client client, JPanel pointsPanel){
//		this.myL = myLabel;
		startTime(myLabel);
		this.gameData = gameData;
		this.client = client;
		this.mainGUI = mainGUI;
		this.pointsPanel = pointsPanel;
		myGif = new PlayersClock(pointsPanel, gameData);
		
//		for(QuestionGUIElement qge: gameData.getQuestions()){
//			if(client.getTeamIndex() != gameData.getCurrentTeam().getTeamIndex()){
//				qge.myFISH.setVisible(true);
//			}
//		}
		
	}

	public void startTime(JLabel myL){
	    timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			int i = 15;
			@Override
			public void run() {
				myL.setText("Jeopardy :" + i);
				i--;
				if (i < 0){
					mainGUI.addUpdate(gameData.getCurrentTeam().getTeamName()+"did not choose a question in time");
					gameData.nextTurn();
					i = 10;
					mainGUI.addUpdate("It is "+gameData.getCurrentTeam().getTeamName()+"'s turn to choose a question.");
					if (gameData.getCurrentTeam().getTeamIndex() != client.getTeamIndex()) mainGUI.disableAllButtons();
					stopTimer();
					mainGUI.showMainPanel();				
					myGif.addComponents();
					
				}

			}
		}, 1000, 1000);

	}
	public void stopTimer(){
		timer.cancel();
		timer.purge();
	}
}


