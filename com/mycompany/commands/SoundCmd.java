package com.mycompany.commands;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class SoundCmd extends Command{
	
	private GameWorld gw;
	
	public SoundCmd(GameWorld gw) {
		super("Sound ON/OFF");
        this.gw = gw;
	}
	//Invoked when check box is checked or unchecked. 
	 @Override
	 public void actionPerformed(ActionEvent e)
	 {
	    if(((CheckBox) e.getComponent()).isSelected()) {
	    	gw.setSound(true);
	    	System.out.println("sound ON");
	    }
	    else {
	    	gw.setSound(false);
	    	System.out.println("sound OFF");
	    }
	   
	 }

}
