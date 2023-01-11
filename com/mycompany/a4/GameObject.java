package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public abstract class GameObject implements ICollider{
	
	 int size;
	 Point location;
	 int color;
	 
	 private Transform myTranslate;
	private Transform myRotate;
	 private Transform myScale;
	 
	 protected GameWorld gw = new GameWorld();
	//Constructor GameObjects sets size, location and color of objects.
	public GameObject(int size, int color) {
	
		this.size = size;
		//this.location = location;
		this.color = color;
	}
	protected GameObject() {
		
	}
	
	public void setColor(int color) {

	}
	protected int getColor() {
		return color;
	}
	protected Point getLocation() {
		return location;
	};
//	public abstract String toString();
	public abstract String ColorToString();
	public int getSize() {
		return size;
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
	
	public boolean collidesWith(ICollider otherObj) {
		
		boolean result = false;
		float thisRightCorner = getLocation().getX()+(getSize()/2f);
		float thisLeftCorner = getLocation().getX()-(getSize()/2f);
		float thisTopCorner = getLocation().getY()+(getSize()/2f);
		float thisBottomCorner = getLocation().getY()-(getSize()/2f);
		
		GameObject other = ((GameObject)otherObj);
		
		float otherRightCorner = other.getLocation().getX()+(other.getSize()/2f);
		float otherLeftCorner = other.getLocation().getX()-(other.getSize()/2f);
		float otherTopCorner = other.getLocation().getY()+(other.getSize()/2f);
		float otherBottomCorner = other.getLocation().getY()-(other.getSize()/2f);
		
		if(thisRightCorner < otherLeftCorner || thisLeftCorner > otherRightCorner ) {
			result = false;
		}
		else if(thisBottomCorner > otherTopCorner || thisTopCorner < otherBottomCorner) {
			result = false;
		}
		else {
			result = true;
		}
		return result;
	}
	
	public void handleCollision(ICollider otherObj) {
//		if(otherObj instanceof Flag) {
//			int flagNum = ((Flag)otherObj).getSeqNum();
//			gw.collideWithFlag(flagNum);
//		}
//		if(otherObj instanceof FoodStation) {
//			GameObject foodStation = ((GameObject)otherObj);
//			gw.collideWithFood(foodStation);
//		}
//		if(otherObj instanceof Spider) {
//			gw.collideWithSpider();
//		}
	}
//	public void draw(Graphics g, Point pPtrRelPrnt ) {
//		// TODO Auto-generated method stub
//		//Point pCmpRelScrn
//	}
	public void draw(Graphics g, Point pPtrRelPrnt, Point pCmpRelScreen) {
		// TODO Auto-generated method stub
		
	}
	
	
 }