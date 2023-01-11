package com.mycompany.a4;



import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Dimension;

public class Spider extends Movable implements IDrawable{
	
	private Transform myTranslate, myScale, myRotate;
	private Point top, bottomLeft, bottomRight;
	
	// constructor Spider sets size, location, color, heading and speed of the spiders. 
	public Spider(int size, int color, int heading, int speed) {
		super(size, color, heading, speed);
		top = new Point(0, size/2);
		bottomLeft = new Point(-(size/2), -(size/2));
		bottomRight = new Point(size/2, -(size/2));
		myTranslate = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		myRotate = Transform.makeIdentity();	
		
	}
	public int getSize() {
		return size;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		
	}
	
	public int getHeading() {
		return super.getHeading();
	}
	public void setHeading(int heading) {
		super.heading = heading;
	}
	public int getSpeed() {
		return super.getSpeed();
	}
	public void setSpeed(int speed) {
		super.speed = speed;
	}
	
	public void translate(float dx, float dy){
		myTranslate.translate(dx, dy);
	 }
	
	public void rotate(float degrees){
		myRotate.rotate((float)Math.toRadians(degrees),0,0);
	 }
	public void scale (float sx, float sy) {

		myScale.scale (sx, sy);
	}
	public void resetTransform() {
		
		myRotate.setIdentity();
		myScale.setIdentity();
		myTranslate.setIdentity();
	}
	
	// move() method updates location of the spiders based of heading and speed. 
	public void move(int elapsedMilliSecs, Dimension dCmpSize) {
		
		this.translate(10,10);
		this.rotate(10);
	
	}

	
	public void draw (Graphics g, Point pPtrRelPrnt, Point pCmpRelScreen) {
		
		g.setColor(this.getColor());
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		Transform gCopy = gXform.copy();
		
		gXform.translate(pCmpRelScreen.getX(),pCmpRelScreen.getY());
		gXform.translate(myTranslate.getTranslateX(), myTranslate.getTranslateY());
		gXform.concatenate(myRotate);
		gXform.scale(myScale.getScaleX(), myScale.getScaleY());
		gXform.translate(-pCmpRelScreen.getX(),-pCmpRelScreen.getY());
		g.setTransform(gXform);
		
		g.drawLine((int)(pPtrRelPrnt.getX()+top.getX()),(int)(pPtrRelPrnt.getY()+top.getY()),
				   (int)(pPtrRelPrnt.getX() + bottomLeft.getX()), (int)(pPtrRelPrnt.getY() + bottomLeft.getY()));
		g.drawLine((int) (top.getX() + pPtrRelPrnt.getX()), (int)(top.getY() + pPtrRelPrnt.getY()), 
				   (int)( bottomRight.getX() + pPtrRelPrnt.getX()), (int) (bottomRight.getY() + pPtrRelPrnt.getY()));
		g.drawLine((int) (bottomLeft.getX()+ pPtrRelPrnt.getX()), (int) (bottomLeft.getY() + pPtrRelPrnt.getY()), 
				   (int) (bottomRight.getX() + pPtrRelPrnt.getX()), (int)(bottomRight.getY() + pPtrRelPrnt.getY()));
		
	
		g.setTransform(gCopy);
		
	}

}
