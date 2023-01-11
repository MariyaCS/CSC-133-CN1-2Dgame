package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class HelpCmd extends Command{
	
	private GameWorld gw;
	
	public HelpCmd(GameWorld gw) {
		super("Help");
        this.gw = gw;
	}
	
	//Invoked when Help button is pushed. It display information about key commands.
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
		 String helpInfo = "a - accelerate\n"
		 		+ "b - break\n"
		 		+ "f - feed the Ant\n"
		 		+ "g - collide with Spider\n"
		 		+ "l - turn left\n"
		 		+ "r - turn right\n"
		 		+ "m - show map\n"
		 		+ "x - exit the Game";
				 
		 Dialog.show("Key commands:", helpInfo, "OK", null);
	    System.out.println("Help displayed");
	 }

}
