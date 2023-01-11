package com.mycompany.a4;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public class Ant extends Movable implements ISteerable, IDrawable{

	private  int heading;
	private  int speed;
	private static Ant ant;
	private int foodConsumptionRate=1;
	private int lastFlagReached=1;
	private int maximumSpeed=100;
	private int healthLevel=10;
	private int foodLevel=100;
	private int radius;
	private Point lowerLeftInLocalSpace ;
	private Transform myTranslate, myScale, myRotate;
	
 //Constructor Ant() sets size, location, color, heading and speed of the object (ant).
	public Ant(int size, int color, int heading, int speed) {
		super(size, color, heading, speed);
		radius = size/2;
		lowerLeftInLocalSpace = new Point(-radius, -radius);
		myTranslate = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		myRotate = Transform.makeIdentity();
	}
	
	private Ant() {
		super();
	}
	
	public static Ant getAnt() {
		if(ant == null) 
			ant = new Ant();
			
		return ant;
	}
	
	
	public int getSize() {
		return size;
	}
	
	public int getHeading() {
		return heading;
	}
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}																						
	
	
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}
	public int getFoodLevel() {
		return foodLevel;
	}
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	public int getHealthLevel() {
		return healthLevel;
	}
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	public void translate(float x, float y) {
		myTranslate.translate(x, y);
	}
	
	public void scale(float x, float y) {
			myScale.scale(x, y);
	}
	
	public void rotate(float degrees) {
		myRotate.rotate((float) Math.toRadians(degrees), 0, 0);
	}
	
	public void resetTransform() {
		
		myRotate.setIdentity();
		myScale.setIdentity();
		myTranslate.setIdentity();
	}
	//move() causes the ant to update its location based on its current heading and speed
	public void move(int elapsedMilliSecs) {
		
//		if(foodLevel>0 && healthLevel>0) {
//			//translate((float)(Math.cos(Math.toRadians(90 - heading))), 0);
//		}	
		this.translate(10,10);
	
	}
	//accelerate() updates object's speed based on its current health level and food level
	public void accelerate() {
		if((this.healthLevel==0) ||(this.foodLevel==0)) {
			super.setSpeed(0);
		}
		else if((this.healthLevel<10) && (this.healthLevel>0)) {
			super.setSpeed(this.maximumSpeed*this.healthLevel/10);
		}
		else{
			super.setSpeed(this.maximumSpeed);
		}
	}
	
	// decelerate() decreases object's speed by one when brake command is applied
	public void decelerate() {
		
		if(this.speed>0) {
			super.setSpeed(this.speed - 10);
			//this.speed = this.speed-10;
		}
	}
	public int decreaseFoodLvl() {
		
		int newFoodLvl=this.foodLevel-this.foodConsumptionRate; 
		if(newFoodLvl>=0) {
			this.setFoodLevel(newFoodLvl);
		}
		return newFoodLvl;
	}
	
	// changeHeading() changes heading of the ant by 5 degrees to the left or to the right. It accepts char parameter and if parameter is 'l' 
	// the heading is changed to the left, if parameter is 'r' the heading is changed to the right. 
	public void changeHeadingLeft() {
		
			super.heading=super.heading-2;
			this.rotate(-2);
			
	}
	
	public void changeHeadingRight() {	

		super.heading=super.heading+2;
		this.rotate(2);
	}
	

//	@Override
	public void draw(Graphics g, Point pPtrRelPrnt) {
//		  g.setColor(this.getColor());
//		  int px = (int) pPtrRelPrnt.getX();
//		  int py = (int) pPtrRelPrnt.getY();
//		  int x = (int) (this.getLocation().getX() + px) + size/2;
//		  int y = (int) (this.getLocation().getY() + py) + size/2;
//
//	        g.setColor(this.getColor());
//	        g.fillArc(x, y, this.size, this.size, 360, 360);

	}
	
	public void draw(Graphics g, Point pPtrRelPrnt, Point pCmpRelScreen) {
		 g.setColor(this.getColor());
		 Transform gTransform = Transform.makeIdentity();
			g.getTransform(gTransform);
			Transform gCopy = gTransform.copy();				
			gTransform.translate(pCmpRelScreen.getX(), pCmpRelScreen.getY());
			gTransform.concatenate(myRotate);
			gTransform.translate(myTranslate.getTranslateX(), myTranslate.getTranslateY());
			gTransform.scale(myScale.getScaleX(), myScale.getScaleY());
			gTransform.translate(-pCmpRelScreen.getX(), -pCmpRelScreen.getY());
			g.setTransform(gTransform);
			g.fillArc((int) (pPtrRelPrnt.getX() + lowerLeftInLocalSpace.getX()),
					 (int)(pPtrRelPrnt.getY() + lowerLeftInLocalSpace.getY()), 2*radius, 2*radius, 0, 360);
			
			g.setTransform(gCopy);
		
	}
		
}