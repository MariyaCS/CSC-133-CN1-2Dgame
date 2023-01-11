package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class AccelerateCmd extends Command{
	private GameWorld gw;
    public AccelerateCmd(GameWorld gw)
    {
        super("Accelerate");
        this.gw = gw;
    }
    
    //Called when "a" key is pressed or Accelerate button is pushed.
    @Override
    public void actionPerformed(ActionEvent e)
    {
        gw.accelerate();
        System.out.println("speed changed");
    }

}
