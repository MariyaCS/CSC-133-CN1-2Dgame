package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.commands.*;
import com.mycompany.view.MapView;
import com.mycompany.view.ScoreView;

public class Game extends Form implements Runnable{
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;

	
	private AccelerateCmd accelerateCmd;
	private ShowMapCmd showmap;
	private TurnLeftCmd turnLeft;
	private TurnRightCmd turnRight;
	private ClosingAppCmd exitGame;
	private BreakCmd breakCmd;
	private SoundCmd sound;
	private AboutCmd aboutCmd;
	private HelpCmd help;
	private PauseCmd pauseCmd;
	private PositionCmd positionCmd;
	UITimer timer;
	private Button accelerateBtn;
	private Button leftBtn;
	private Button breakBtn;
	private Button rightBtn;
	private CheckBox soundCheckBox;
	private Button pauseBtn;
	private Button positionBtn;
	
	
	public int time = 1000;
	
	// constructor Game() creates instance of a GameWorld class, calls method init() of GameWorld class and calls method play()
	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		
		setLayout(new BorderLayout());
		
		//Commands
		accelerateCmd = new AccelerateCmd(gw);
		showmap = new ShowMapCmd(gw);
		turnLeft = new TurnLeftCmd(gw);
		turnRight = new TurnRightCmd(gw);
		exitGame = new ClosingAppCmd();
		breakCmd = new BreakCmd(gw);
		sound = new SoundCmd(gw);
		aboutCmd = new AboutCmd(gw);
		help = new HelpCmd(gw);
		pauseCmd = new PauseCmd(gw, this);
		positionCmd = new PositionCmd(gw);
		
		//key listeners
		addKeyListener('a',accelerateCmd);
		addKeyListener('b', breakCmd);
		addKeyListener('m', showmap);
		addKeyListener('l', turnLeft);
		addKeyListener('r', turnRight);
		addKeyListener('x', exitGame);
		
		
		//Buttons
		accelerateBtn = styleBtn(new Button("Accelerate"));
		leftBtn = styleBtn(new Button("Left"));
		breakBtn = styleBtn(new Button("Break"));
		rightBtn = styleBtn(new Button("Right"));
		soundCheckBox = new CheckBox();
		pauseBtn = styleBtn(new Button("Pause"));
		positionBtn = styleBtn(new Button("Position"));
		
		//button commands

		accelerateBtn.setCommand(accelerateCmd);
		leftBtn.setCommand(turnLeft);
		rightBtn.setCommand(turnRight);
		breakBtn.setCommand(breakCmd);
		soundCheckBox.setCommand(sound);
		pauseBtn.setCommand(pauseCmd);
		positionBtn.setCommand(positionCmd);
	
		
		//south container
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		add(BorderLayout.SOUTH, southContainer);
		southContainer.add(positionBtn);
		southContainer.add(pauseBtn);
		
		
		//west container
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.getAllStyles().setPadding(Component.TOP, 100);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		add(BorderLayout.WEST, westContainer);
		westContainer.add(accelerateBtn);
		westContainer.add(leftBtn);
		
		//east container
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.getAllStyles().setPadding(Component.TOP, 100);
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		add(BorderLayout.EAST, eastContainer);
		eastContainer.add(breakBtn);
		eastContainer.add(rightBtn);
		
		//tool bar
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		myToolbar.setTitle("OnTargetGame");

		
		//tool bar commands
		myToolbar.addCommandToSideMenu(accelerateCmd);
		myToolbar.addComponentToSideMenu(soundCheckBox);
		myToolbar.addCommandToSideMenu(aboutCmd);
		myToolbar.addCommandToSideMenu(help);
		myToolbar.addCommandToSideMenu(exitGame);
		myToolbar.addCommandToRightBar(help);
		
		//style sound check box
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.rgb(157, 167, 224));
		soundCheckBox.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		
		add(BorderLayout.NORTH, sv);
		add(BorderLayout.CENTER, mv);
	
	
		show();
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		gw.init();
		gw.createSounds();
		revalidate();
		timer = new UITimer(this);
		timer.schedule(time, true, this);
		
	
	}
	
	
	public Button styleBtn(Button btn) {
		
		btn.getUnselectedStyle().setBgTransparency(255);
		btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		btn.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		btn.getAllStyles().setPadding(Component.TOP, 10);
		btn.getAllStyles().setPadding(Component.BOTTOM, 10);
		btn.getAllStyles().setPadding(Component.LEFT, 5);
		btn.getAllStyles().setPadding(Component.RIGHT, 5);
		return btn;
	}
	
	public void pauseGame() {
		
			timer.cancel();
			accelerateBtn.setEnabled(false);
			breakBtn.setEnabled(false);
			leftBtn.setEnabled(false);
			rightBtn.setEnabled(false);
		
			removeKeyListener('a', accelerateCmd);
			removeKeyListener('b', breakCmd);
			removeKeyListener('l', turnLeft);
			removeKeyListener('r', turnRight);
			if(gw.getSound()) {
				gw.setSound(false);
			}
			
			
			pauseBtn.setText("Play");
			revalidate();
			

	 }
	 public void resumeGame() {
		
			timer.schedule(time, true, this);	
			accelerateBtn.setEnabled(true);
			breakBtn.setEnabled(true);
			leftBtn.setEnabled(true);
			rightBtn.setEnabled(true);
				
			addKeyListener('a', accelerateCmd);
			addKeyListener('b', breakCmd);
			addKeyListener('l', turnLeft);
			addKeyListener('r', turnRight);
				
			pauseBtn.setText("Pause");
			revalidate();
	 }
		
	

	@Override
	public void run() {
		Dimension dCmpSize = new Dimension(mv.getWidth(), mv.getHeight());
			gw.clockTick(time, dCmpSize); 
			if(gw.isGameOver()) {
				Dialog.show("Game Over!", "", "OK", null);
				gw.setSound(false);
			}
			mv.repaint();	
	}
	
	

}
