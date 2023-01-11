package com.mycompany.commands;


import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.Game;
import com.mycompany.a4.GameWorld;


	
	
public class PauseCmd extends Command{

	private GameWorld gw;
	private Game game;
	
	public PauseCmd(GameWorld gw, Game game) {
		super("Pause");
		this.game = game;
		this.gw = gw;
	}	
	@Override 
	public void actionPerformed(ActionEvent e) {
		if(!gw.isPaused()) {	
			game.pauseGame();
			gw.setPaused(!gw.isPaused());
		}
		else {
			game.resumeGame();
			gw.setPaused(!gw.isPaused());
		}
	
	}

}
