package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class BreakCmd extends Command{

		private GameWorld gw;
		
		public BreakCmd(GameWorld gw) {
			super("Break");
	        this.gw = gw;
		}
		
		//Called when "b" key is pressed or Break button is pushed.
		 @Override
		 public void actionPerformed(ActionEvent e)
		 {
		    gw.reduceSpeed();
		    System.out.println("speed changed");
		 }
}
