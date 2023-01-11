package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class ShowMapCmd extends Command{

		private GameWorld gw;
	    public ShowMapCmd(GameWorld gw)
	    {
	        super("Show Map");
	        this.gw = gw;
	    }
	    
	    //Invoked when "m" key is pressed. 
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        gw.showMap();
	        System.out.println("map displayed");
	    }
}
