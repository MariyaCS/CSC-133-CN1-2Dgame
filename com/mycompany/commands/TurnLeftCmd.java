package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class TurnLeftCmd extends Command{

	private GameWorld gw;
	
	public TurnLeftCmd(GameWorld gw) {
		super("Turn Left");
        this.gw = gw;
	}
	
	//Invoked when "l" key is pressed or Left button is pushed.
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	    gw.changeAntHeadingLeft();
	    System.out.println("heading changed to left");
	 }

}
