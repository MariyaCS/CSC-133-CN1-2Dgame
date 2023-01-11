package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;



public class Flag extends Fixed implements IDrawable{
	
	private int seqNum;
	private GameWorld gw;
	private boolean selected = false;
	private Transform myTranslate, myScale, myRotate;
	private Point top, bottomLeft, bottomRight;
	
	public Flag(int size, int color) {
		super(size, color);
		top = new Point(0, size/2);
		bottomLeft = new Point(-(size/2), -(size/2));
		bottomRight = new Point(size/2, -(size/2));
		myTranslate = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		myRotate = Transform.makeIdentity();		
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
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
	public int getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
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
	
	@Override
	public void draw (Graphics g, Point pPtrRelPrnt) {
		
//			g.setColor(this.getColor());
//		  int px = (int) pPtrRelPrnt.getX();
//		  int py = (int) pPtrRelPrnt.getY();
//		  int x = (int) (this.getLocation().getX() + px)+size/2;
//		  int y = (int) (this.getLocation().getY() + py)+size/2;
//		  int[] xPoints = { x, (x - size), (x + size), x };
//		  int[] yPoints = { (y + size), (y - size), (y - size), (y + size) };
//		  int nPoints = 3;
//		 
//		  if(selected) {
//			  g.drawPolygon(xPoints, yPoints, nPoints);
//		  }
//		  else {
//			   g.fillPolygon(xPoints, yPoints, nPoints);
//			   g.setColor(ColorUtil.BLACK);
//			   g.drawString("" + this.seqNum, x-5, y-30);	  
//		  }
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
		int[] xPoints = {(int) (top.getX() + pPtrRelPrnt.getX()), (int) (bottomLeft.getX()+ pPtrRelPrnt.getX()),
				(int) (bottomRight.getX() + pPtrRelPrnt.getX())};
		int[] yPoints = {(int) (top.getY() + pPtrRelPrnt.getY()), (int) (bottomLeft.getY() + pPtrRelPrnt.getY()),
				(int) (bottomRight.getY() + pPtrRelPrnt.getY())};
		
		g.fillPolygon(xPoints, yPoints, 3);
		
		 g.setColor(ColorUtil.BLACK);
		 g.drawString(Integer.toString(this.seqNum), (int)pPtrRelPrnt.getX(), (int)pPtrRelPrnt.getY());	
		g.setTransform(gCopy);
		g.resetAffine();
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
