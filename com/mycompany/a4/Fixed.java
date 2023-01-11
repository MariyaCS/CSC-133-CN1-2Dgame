package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class Fixed extends GameObject implements ISelectable{
	 
	// constructor of abstract class Fixed. It sets size, location and color of fixed objects
	public Fixed(int size, int color) {
		super(size, color); 
	
	}
	public Fixed() {
		super(); 
	
	}
	// toString() method converts color of the objects to specific text format. 
	public String ColorToString() {
		String myDesc = "color=" + "[" + ColorUtil.red(this.getColor()) + ","
				+ ColorUtil.green(this.getColor()) + ","
				+ ColorUtil.blue(this.getColor()) + "]";
		
		return myDesc;
	}
	// round() method rounds numbers to 1 decimal point
	public String round() {
		double roundLocX = Math.round(this.getLocation().getX()*10.0)/10.0;
		double roundLocY = Math.round(this.getLocation().getY()*10.0)/10.0;
		String roundNum = roundLocX + ", " + roundLocY;
		return roundNum;
	}

}
