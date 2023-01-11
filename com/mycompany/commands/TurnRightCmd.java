package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class TurnRightCmd extends Command{
	
	private GameWorld gw;
	
	public TurnRightCmd(GameWorld gw) {
		super("Turn Right");
        this.gw = gw;
	}
	
	//Invoked when "r" key is pressed or Right button is pushed.
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	    gw.changeAntHeadingRight();
	    System.out.println("heading changed to right");
	 }

}
