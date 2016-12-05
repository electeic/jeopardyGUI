package timers;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frames.MainGUINetworked;
import game_logic.ServerGameData;
import other_gui.TeamGUIComponents;
import server.Client;

public class PlayersClock {
	public Timer timer;
	ServerGameData gameData;
	Client client;
	MainGUINetworked mainGUI;
	String name;
	JLabel myLabel;
	JPanel pointsPanel;
	public PlayersClock( JPanel pointsPanel, ServerGameData gameData){
//		startGif();
		this.gameData = gameData;
//		this.client = client;
//		this.mainGUI = mainGUI;
//		this.name = name;
//		this.myLabel = myLabel;
		this.pointsPanel = pointsPanel;
//		addComponents();
	}
	
	public void addComponents(){
		pointsPanel.removeAll();
		for (int i = 0; i < gameData.getNumberOfTeams(); i++) {
			TeamGUIComponents team = gameData.getTeamDataList().get(i);
//			System.out.println(gameData.getCurrentTeam().getTeamName());
//			System.out.println(team.getMainTeamNameLabel());
			if(gameData.getCurrentTeam().getTeamName().equals(team.getMainTeamNameLabel().getText())){
				JPanel bothNameTime = new JPanel();
//				bothNameTime.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				bothNameTime.add(team.getMainTeamNameLabel());
				bothNameTime.add(new ClockSpin());
				pointsPanel.add(bothNameTime);
			}
			else{
				pointsPanel.add(team.getMainTeamNameLabel());
			}
			pointsPanel.add(team.getTotalPointsLabel());
			//TODO add the animation of clocks
		}
	}
	
	public void normal(){
		for (int i = 0; i < gameData.getNumberOfTeams(); i++) {
			TeamGUIComponents team = gameData.getTeamDataList().get(i);
//			System.out.println(gameData.getCurrentTeam().getTeamName());
//			System.out.println(team.getMainTeamNameLabel());
			//TODO compare their index instead of their name
			if(gameData.getCurrentTeam().getTeamName().equals(team.getMainTeamNameLabel().getText())){
				JPanel bothNameTime = new JPanel();
//				bothNameTime.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				bothNameTime.add(team.getMainTeamNameLabel());
				pointsPanel.add(bothNameTime);
			}
			else{
				pointsPanel.add(team.getMainTeamNameLabel());
			}
			pointsPanel.add(team.getTotalPointsLabel());
		}
	}
}
