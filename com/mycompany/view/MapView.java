package com.mycompany.view;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a4.GameObject;
import com.mycompany.a4.GameWorld;
import com.mycompany.a4.IDrawable;
import com.mycompany.a4.IIterator;



public class MapView extends Container implements Observer{
	
	private GameWorld gw;
	Transform worldToND, ndToDisplay, theVTM ;
	private float winLeft, winBottom, winRight, winTop;
	
	//sets style of the map view container. 
	public MapView() {
	
        setLayout(new BorderLayout());
		getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(ColorUtil.WHITE);
		getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.MAGENTA));
		
		winLeft = 0;
		winBottom = 0;
		winRight = this.getWidth()/2; 
		winTop = this.getHeight()/2; 

	}

	
	//outputs the map of the game in the console window. This method is invoked when Observable(GameWorld) map has changed.
	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorld) observable;
		gw.showMap();
		this.repaint();
		
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		winLeft = 0;
		winBottom = 0;
		winRight = this.getWidth()/2; 
		winTop = this.getHeight()/2; 
		float winWidth = winRight - winLeft;
		float winHeight = winTop - winBottom;
	
		worldToND = buildWorldToNDXform(winWidth, winHeight, winLeft, winBottom);
		ndToDisplay = buildNDToDisplayXform(this.getWidth(), this.getHeight());
		theVTM = ndToDisplay.copy();
		theVTM.concatenate(worldToND);
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		gXform.translate(getAbsoluteX(),getAbsoluteY()); 
		gXform.concatenate(theVTM);  
		gXform.translate(-getAbsoluteX(),-getAbsoluteY()); 
		g.setTransform(gXform);
		
		IIterator gameCollection = gw.getIterator();
		Point pCmpRelPrnt = new Point(getX(), getY());
		Point pCmpRelScreen = new Point(getAbsoluteX(),getAbsoluteY());
		while(gameCollection.hasNext()) {
			GameObject gameObj = gameCollection.getNext();
			if(gameObj instanceof IDrawable) {
				((IDrawable) gameObj).draw(g, pCmpRelPrnt, pCmpRelScreen);
			}	
		}
		g.resetAffine() ;
	 }
	
	private Transform buildWorldToNDXform(float winWidth, float winHeight, float
			winLeft, float winBottom){
			Transform tmpXfrom = Transform.makeIdentity();
			tmpXfrom.scale( (1/winWidth) , (1/winHeight) );
			tmpXfrom.translate(-winLeft,-winBottom);
			return tmpXfrom;
	}
	private Transform buildNDToDisplayXform (float displayWidth, float displayHeight){
			Transform tmpXfrom = Transform.makeIdentity();
			tmpXfrom.translate(0, displayHeight);
			tmpXfrom.scale(displayWidth, -displayHeight);
			return tmpXfrom;
	}
	
//	public void pointerPressed(int x, int y) {
//		
//		 x = x - getParent().getAbsoluteX();
//		 y = y - getParent().getAbsoluteY();
//		
//		if(gw.getPositionPressed()) {
//			
//			IIterator itr = gw.getIterator();
//			
//			while(itr.hasNext()) {
//				GameObject currObj = itr.getNext();
//					if(currObj instanceof Flag) {
//						if(((Flag)currObj).isSelected()){
//							int newX = x - getX();
//							int newY = y - getY();
//							Point newPoint = new Point(newX, newY);
//							((Flag)currObj).setLocation(newPoint);
//							((Flag)currObj).setSelected(false);
//							gw.setPositionPressed(!gw.getPositionPressed());
//						}
//					}
//					else if(currObj instanceof FoodStation) {
//						if(((FoodStation)currObj).isSelected()) {
//							int newX = x - getX();
//							int newY = y - getY();
//							Point newPoint = new Point(newX, newY);
//							((FoodStation)currObj).setLocation(newPoint);
//							((FoodStation)currObj).setSelected(false);
//							gw.setPositionPressed(!gw.getPositionPressed());
//						}
//					}	
//			}	
//		}
//		else {
//			Point pPtrRelPrnt = new Point(x, y);
//			Point pCmpRelPrnt = new Point(getX(), getY());
//			
//			IIterator itr = gw.getIterator();
//			while(itr.hasNext()) {
//				GameObject currObj = itr.getNext();
//					if(currObj instanceof Flag) {
//						if(((Flag) currObj).contains(pPtrRelPrnt, pCmpRelPrnt)) {
//							((Flag) currObj).setSelected(true);
//						}
//						else {
//							((Flag) currObj).setSelected(false);
//						}
//					}
//					else if(currObj instanceof FoodStation) {
//						if(((FoodStation) currObj).contains(pPtrRelPrnt, pCmpRelPrnt)) {
//							((FoodStation) currObj).setSelected(true);
//						}
//						else {
//							((FoodStation) currObj).setSelected(false);
//						}
//					}
//			  }	
//		}
//		repaint();
//	}

}
