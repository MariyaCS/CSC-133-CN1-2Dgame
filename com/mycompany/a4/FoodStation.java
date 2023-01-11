package com.mycompany.a4;



import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;


public class FoodStation extends Fixed implements IDrawable {
	
	private int capacity = size;
	private GameWorld gw;
	private boolean selected = false;
	
	private Transform myTranslate, myScale, myRotate;
	private Point topLeft, topRight, bottomLeft, bottomRight;
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public FoodStation(int size, int color) {
		
		super(size, color);
		topLeft = new Point(-(size/2), size/2);
		topRight = new Point(size/2, size/2);
		bottomLeft = new Point(-(size/2), -(size/2));
		bottomRight = new Point(size/2, -(size/2));
		myTranslate = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		myRotate = Transform.makeIdentity();
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
	
	public int getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public void draw(Graphics g, Point pPtrRelPrnt) {
		 
//		int px = (int) pPtrRelPrnt.getX();
//		int py = (int) pPtrRelPrnt.getY();
//		int x = (int) (this.getLocation().getX() + px)+size/2;
//		int y = (int) (this.getLocation().getY() + py)+size/2;
//		 if(selected) {
//			 g.drawRect(x, y, this.getSize(), this.getSize());
//		  }
//		 else {
//			 g.setColor(this.getColor());
//			 g.fillRect(x, y, this.getSize(), this.getSize());
//			 g.setColor(ColorUtil.BLACK);
//			 g.drawString("" + this.capacity, x, y);
//		 }
		
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
		
		g.drawLine ((int) (topLeft.getX() + pPtrRelPrnt.getX()), (int)(topLeft.getY() + pPtrRelPrnt.getY()),
					(int)(topRight.getX() + pPtrRelPrnt.getX()), (int)(topRight.getY() + pPtrRelPrnt.getY()));
		g.drawLine ((int)(topLeft.getX() + pPtrRelPrnt.getX()), (int)(topLeft.getY() + pPtrRelPrnt.getY()),
				    (int)(bottomLeft.getX()+ pPtrRelPrnt.getX()), (int)(bottomLeft.getY() + pPtrRelPrnt.getY()));
		g.drawLine ((int)(bottomLeft.getX()+ pPtrRelPrnt.getX()), (int)(bottomLeft.getY() + pPtrRelPrnt.getY()), 
				   (int)(bottomRight.getX() + pPtrRelPrnt.getX()), (int)(bottomRight.getY() + pPtrRelPrnt.getY()));
		g.drawLine ((int)(topRight.getX() + pPtrRelPrnt.getX()), (int)(topRight.getY() + pPtrRelPrnt.getY()),
				   (int)(bottomRight.getX() + pPtrRelPrnt.getX()), (int)(bottomRight.getY() + pPtrRelPrnt.getY()));
		 g.fillRect((int)(bottomLeft.getX()+ pPtrRelPrnt.getX()), (int)(bottomLeft.getY() + pPtrRelPrnt.getY()), this.getSize(), this.getSize());
		 g.setColor(ColorUtil.BLACK);
		 g.drawString("" + this.capacity,(int)pPtrRelPrnt.getX(), (int)pPtrRelPrnt.getY());
		g.setTransform(gCopy);
	}
	
	@Override
	public void setSelected(boolean b) {
		selected = b;
		
	}
	@Override
	public boolean isSelected() {
		return selected;
	}	
}
