package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class AboutCmd extends Command{
	
	private GameWorld gw;
	
	public AboutCmd(GameWorld gw) {
		super("About");
        this.gw = gw;
	}
	
	//shows information about the game. Called when About button is pushed.
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
		 String about = "OnTargetGame\n "
				 +"Course: CSC 133\n"
		 		+ "Developer: Mariya Cherednichenko\n"
				+ "Version: 1.0";
		 Dialog.show("About", about, "OK", null );
	    
	 }

}
