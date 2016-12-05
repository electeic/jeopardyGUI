package timers;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import frames.MainGUINetworked;
import game_logic.ServerGameData;
import messages.UnableToAnswerMessage;
import server.Client;

public class TimeQuestionChosen {
	public Timer timer;
	ServerGameData gameData;
	Client client;
	MainGUINetworked mainGUI;
//	JLabel myL;
//	public TimeQuestionChosen(JLabel myLabel, MainGUINetworked mainGUI, ServerGameData gameData, Client client){
	public TimeQuestionChosen(MainGUINetworked mainGUI, ServerGameData gameData, Client client){
		if(mainGUI.myTimer != null){
			mainGUI.myTimer.stopTimer();
		}

		this.gameData = gameData;
		this.client = client;
		this.mainGUI = mainGUI;
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
					System.out.println("THIS IS ISH:OSD:l");
					client.sendMessage(new UnableToAnswerMessage());
					stopTimer();
//					timer.cancel();
				}
			}
		}, 1000, 1000);
	}
	
	public void stopTimer(){
		timer.cancel();
		timer.purge();
	}
}