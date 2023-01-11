package com.mycompany.view;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.mycompany.a4.GameWorld;

public class ScoreView extends Container implements Observer{
	
	private GameWorld gw;
	private Label timeLabel;
	private Label lifesLeftLabel;
	private Label flagReachedLabel;
	private Label foodLevelLabel;
	private Label soundValueLabel;
	private Label healthLevelLabel;
	
	//sets labels in the North container
	public ScoreView() {
		
		getAllStyles().setPadding(Component.LEFT, 160);
		
		Label clockLabel = new Label("Time:");
		add(styleLabel(clockLabel));
		
		timeLabel = new Label("0");
		this.add(styleLabel(timeLabel));
		
		Label lifeLabel = new Label("Lives Left:");
		this.add(styleLabel(lifeLabel));
		lifesLeftLabel = new Label("0");
		this.add(styleLabel(lifesLeftLabel));
		
		Label flagLabel = new Label("Flag Reached:");
		this.add(styleLabel(flagLabel));
		flagReachedLabel = new Label("0");
		this.add(styleLabel(flagReachedLabel));
		
		Label foodLabel = new Label("Food Level:");
		this.add(styleLabel(foodLabel));
		foodLevelLabel = new Label("0");
		this.add(styleLabel(foodLevelLabel));
		
		Label healthLabel = new Label("Health Level: ");
		this.add(styleLabel(healthLabel));
		healthLevelLabel = new Label("0");
		this.add(styleLabel(healthLevelLabel));
		
		Label soundLabel = new Label("Sound: ");
		this.add(styleLabel(soundLabel));
		soundValueLabel = new Label("OFF");
		this.add(styleLabel(soundValueLabel));
	}
	
	
	public Label styleLabel(Label label) {
		
		label.getAllStyles().setPadding(2,2,1,1);
		label.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		return label;
		
	}
	
	//outputs the current game/ant state information. This method is invoked when Observable(GameWorld) notifies this class of changes. 
	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorld) data ;
		this.lifesLeftLabel.setText(""+gw.getLives());
		this.timeLabel.setText(""+gw.getClock());
		this.flagReachedLabel.setText(""+gw.getFlagReached());
		this.foodLevelLabel.setText(""+gw.getFoodLevel());
		this.healthLevelLabel.setText(""+gw.getHealthLevel());
		if(gw.getSound()) {
				soundValueLabel.setText("ON");
			}else 
				this.soundValueLabel.setText("OFF");
		this.revalidate();
	}

}
