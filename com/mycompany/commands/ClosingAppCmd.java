package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ClosingAppCmd extends Command{

	public ClosingAppCmd() {
		super("Exit");
	}
	
	//Invoked when "x" key is pressed or when Exit button is pushed. Prompt user to confirm the action.
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	   Boolean isOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
	   		if(isOk) {
	   			Display.getInstance().exitApplication();
	   		}
	 }

}
