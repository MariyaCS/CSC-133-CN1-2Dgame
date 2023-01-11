package com.mycompany.a4;

import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.geom.Dimension;
import com.mycompany.a4.Flag;


public class GameWorld extends Observable{
	Game game;
	private boolean isPaused;
	private boolean positionPressed;
	private int height;
	private int width;
	private int lives;
	private int clock;
	private boolean gameOver;
	private boolean soundOn;
	private int flagReached;
	private int foodLevel;
	private int healthLevel;
	private GameCollection collection;
	private Ant ant;
	private Flag flag1, flag2, flag3, flag4;
	private FoodStation foodStation1, foodStation2, foodStation3;
	private Spider spider1, spider2;
	private BGSound background;
	private Sound flag;
	private Sound food;
	private Sound spider;
	
	Random rand = new Random();
	//Point antPoint = new Point(10.0f, 10.0f);
	Point flag1Point = new Point(100.0f, 100.0f);
	Point flag2Point = new Point(900.0f, 300.0f);
	Point flag3Point = new Point(500.0f, 700.0f);
	Point flag4Point = new Point(1000.0f, 800.0f);
	Point food1Point = new Point(1000*rand.nextFloat(), 1000*rand.nextFloat());
	Point food2Point = new Point(1000*rand.nextFloat(), 1000*rand.nextFloat());
	Point spider1Point = new Point(1000*rand.nextFloat(), 1000*rand.nextFloat());
	Point spider2Point = new Point(1000*rand.nextFloat(), 1000*rand.nextFloat());
	int colorGreen = ColorUtil.rgb(0, 179, 89);
	int colorBlack = ColorUtil.rgb(0, 0, 0);
	int colorRed = ColorUtil.rgb(225, 0, 0);
	int colorBlue = ColorUtil.rgb(128, 149, 225);
	
	//sets initial objects and settings of the game
	public void init() {
		collection = new GameCollection();
		lives = 3;
		clock = 0;
		gameOver = false;
		soundOn = false;
		isPaused = false;
		positionPressed = false;
		
		ant = new Ant(20, colorRed, 0, 50);
		ant.translate(100,100);
		spider1 = new Spider(10+rand.nextInt(30), colorBlack, rand.nextInt(359), 10+rand.nextInt(20));
		spider1.translate(300, 500); //this.getHeight()*rand.nextFloat() //this.getWidth()*rand.nextFloat()
		spider1.rotate(0+rand.nextInt(180));
		spider2 = new Spider(10+rand.nextInt(30), colorBlack, rand.nextInt(359), 10+rand.nextInt(20));
		spider2.translate(500, 400); //900*rand.nextFloat() //900*rand.nextFloat()
		spider2.rotate(0+rand.nextInt(180));
		flag1 = new Flag(30, colorBlue);
//		flag1.rotate(180);
		flag1.translate(200,200);
		flag2 = new Flag(60, colorBlue);
		flag2.rotate(180);
		flag2.translate(500,500);
		flag3 = new Flag(60, colorBlue);
		flag3.rotate(180);
		flag3.translate(900,100);
		flag4 = new Flag(60, colorBlue);
		flag4.rotate(180);
		flag4.translate(700,700);
		flag1.setSeqNum(1);
		flag2.setSeqNum(2);
		flag3.setSeqNum(3);
		flag4.setSeqNum(4);	
		foodStation1 = new FoodStation(30+rand.nextInt(50), colorGreen);
		foodStation1.translate(900*rand.nextFloat(), 900*rand.nextFloat());
		foodStation1.rotate(180);
		foodStation2 = new FoodStation(30+rand.nextInt(50), colorGreen);
		foodStation2.translate(100*rand.nextFloat(), 100*rand.nextFloat());
		foodStation2.rotate(180);
		collection.add(ant);
		collection.add(spider1);
		collection.add(spider2);
		collection.add(flag1);
		collection.add(flag2);
		collection.add(flag3);
		collection.add(flag4);
		collection.add(foodStation1);
		collection.add(foodStation2);
		setChanged();
		notifyObservers(this);
	}
	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;

	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
		this.setChanged();
		this.notifyObservers();
	}
	

	public int getClock() {
		return clock;
	}

	public void setClock(int clock) {
		this.clock = clock;
		this.setChanged();
		this.notifyObservers();
	}

	public int getFlagReached() {
		return ant.getLastFlagReached();
	}


	public int getFoodLevel() {
		return ant.getFoodLevel();
	}
	
	public void setFoodLevel() {
		this.foodLevel = ant.getFoodLevel();
		this.setChanged();
		this.notifyObservers();
	}

	public int getHealthLevel() {
		return ant.getHealthLevel();
	}
	
	public void setHealthLevel() {
		this.healthLevel = ant.getHealthLevel();
		this.setChanged();
		this.notifyObservers();
	}
	public boolean getSound() {
		return soundOn;
	}

	public void setSound(boolean sound) {
		this.soundOn = sound;
		this.setChanged();
		this.notifyObservers(this);
	}


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public void createSounds() {
		background = new BGSound("background.wav");
		flag = new Sound("flag.wav");
		food = new Sound("food.wav");
		spider = new Sound("spider.wav");
		setChanged();
		notifyObservers(this);
	
	}
	public void setPositionPressed(boolean isPressed) {
		this.positionPressed = isPressed;
		setChanged();
		notifyObservers(this);
	}
	
	public boolean getPositionPressed() {
		return this.positionPressed;
	}

	//method collide() is called when "g" key is pressed or Collide with Spider button is pushed.
	//The method updates ant's health level by reducing it by 1 and updates speed of the ant.
	public void collideWithSpider() {
		if(ant.getHealthLevel()>0) {
			int newHealthLvl= (ant.getHealthLevel())-1;
			ant.setHealthLevel(newHealthLvl);
			if(soundOn) {
				spider.play();
			}
			ant.setColor(ColorUtil.rgb(255, 77, 77));	
				if(newHealthLvl==0 && this.lives>0) {
					this.lives-=1;
					System.out.println("You lost a life. You have "+this.lives+" lives remaind.");
			
					ant.setHealthLevel(10);		
				}
		}
			
	
		if(this.lives==0){
			System.out.println("Game over");
			
			gameOver = true;
			game = new Game();
		}
		if(ant.getSpeed()>0) {
			ant.accelerate();
			
		}
		
		setChanged();
		notifyObservers(this);
	}
	//collideWithFlag() is called when collide with flag button is pushed. It prompts user to enter a number between 1 and 4.
	// The method updates last flag reached by the ant. 
	//It checks whether the number entered is exactly one greater than the flag indicated by lastFlagReached field
    //of the ant and if so, update the lastFlagReached field of the ant by increasing it by one.
	public void collideWithFlag(int flagNum){
		int lastFlag = ant.getLastFlagReached();
		int newLastFlag = lastFlag+1;
		if(flagNum==newLastFlag) {
			if(soundOn) {
				flag.play();
			}
			ant.setLastFlagReached(newLastFlag);
			if(newLastFlag == 4) {
				System.out.println("You won! Congratulation you reached the last flag!");
				Dialog.show("You won!", "Congratulation you reached the last flag!", "OK", null);
				setSound(false);
				game = new Game();
			}
		}
		setChanged();
		notifyObservers(this);
	}
	// feed() is called when "f" key is pressed or when collideWithFood button is pushed.
	// The method increases the food level of the ant by the capacity of food station ant collided with. 
	//It sets capacity of the food station to zero and creates new food station of a random size. 
	public void collideWithFood(GameObject gameObj) {	
		
		if(((FoodStation)gameObj).getCapacity()!=0) {
			if(soundOn) {
				food.play();
			}
			ant.setFoodLevel(ant.getFoodLevel()+((FoodStation)gameObj).getCapacity());
			Point food3Point = new Point(1000*rand.nextFloat(), 1000*rand.nextFloat());
			collection.add(new FoodStation(30+rand.nextInt(50), colorGreen));
			((FoodStation)gameObj).setCapacity(0);
			((FoodStation)gameObj).setColor(ColorUtil.rgb(161,230,172));
		}
	
		setChanged();
		notifyObservers(this);
	} 
	//returns collection of the game objects
	public IIterator getIterator() {
		return collection.getIterator();
	}

	//reduceSpeed() method is called when "b" key is pressed or
	// when Break button is pushed.This method calls decelerate() method in Ant's class and decreases speed of the ant. 
	public void reduceSpeed() {
		ant.decelerate();
		setChanged();
		notifyObservers(this);
	}
	

	
	// changeAntHeadingLeft() is called when "l" key is pressed or Left button is pushed.
	//calls method changeHeadingLeft() in Ant class. 
	public void changeAntHeadingLeft() {
		IIterator itr = collection.getIterator();
		while(itr.hasNext()) {
			GameObject tempItr = itr.getNext();
			if (tempItr instanceof Ant) {
				((Ant)tempItr).changeHeadingLeft();
			}
		}
		setChanged();
		notifyObservers(this);
	}
	
	// changeAntHeadingLeft() is called when "r" key is pressed or Right button is pushed.
		//calls method changeHeadingLeft() in Ant class. 
	public void changeAntHeadingRight() {
		IIterator itr = collection.getIterator();
		while(itr.hasNext()) {
			GameObject tempItr = itr.getNext();
			if (tempItr instanceof Ant) {
				((Ant)tempItr).changeHeadingRight();
			}
		}
		setChanged();
		notifyObservers(this);
	}
	
	//decreaseFoodLevel() is called when "t" key is pressed or when Tick button is pushed.
	//It reduces ant's food level by the food level consumption rate.
	public void decreaseFoodLevel() {

		int newFoodLvl=ant.decreaseFoodLvl();
		if(newFoodLvl==0 && this.lives>0) {
			this.lives-=1;
			System.out.println("You lost a life. You have "+this.lives+" lives remaind.");
			ant.setFoodLevel(10);		
		}
		if(this.lives==0){
			System.out.println("Game over");
			Dialog.show("Game Over!", "", "OK", null);
			setSound(false);
			gameOver = true;
			game = new Game();
		}
		
	}
	
	//updatePosition() is called when "t" key is pressed or Tick button is pushed.
	//This method calls ant and spiders move() methods to update positions of the movable objects  
	public void updatePosition(int timeElapsed, Dimension dCmpSize){
//		Point size = new Point(this.width, this.height);
		IIterator itr = collection.getIterator();
		while(itr.hasNext()) {
			GameObject tempItr = itr.getNext();
			if (tempItr instanceof Spider) {
				((Spider)tempItr).move(timeElapsed, dCmpSize);
			}
			if (tempItr instanceof Ant) {
				((Ant)tempItr).move(timeElapsed);
			}
		}	

	}
	
	// changeSpiderHeading() is called when "t" key is pressed of Tick button is pushed.
		//The method updates spiders' heading by 5 degree. 
		public void changeSpiderHeading() {
			IIterator itr = collection.getIterator();
			while(itr.hasNext()) {
				GameObject tempItr = itr.getNext();
				if (tempItr instanceof Spider) {
					((Spider)tempItr).setHeading(((Spider)tempItr).getHeading()+5);
				}
			}	

		}

	public void clockTick(int time, Dimension dCmpSize) {
		
		updatePosition(time, dCmpSize);
		decreaseFoodLevel();
		changeSpiderHeading();
		clock++;
		
		 IIterator iter = collection.getIterator();
		 while(iter.hasNext()) {
			 GameObject obj = iter.getNext();
			 if(obj instanceof ICollider) {
					ICollider currObj = (ICollider)obj;
					IIterator iter2 = collection.getIterator();						
			 }	
		 }
			if(soundOn) {
				background.play();
			}
	}
	
	
	// accelerate() is called when "a" key is pressed of Accelerate button is pushed
	//The method calls accelerate method in Ant's class
	public void accelerate() {
		IIterator itr = collection.getIterator();
		while(itr.hasNext()) {
			GameObject tempItr = itr.getNext();
			if (tempItr instanceof Ant) {
				((Ant)tempItr).accelerate();
			}
		}	
		setChanged();
		notifyObservers(this);
	}
	
	
	// showMap() is called when "m" key is pressed
	//This method display information about the object in the console window.
	//It shows location, color, speed, heading, size etc of the objects. 
	public void showMap(){
		IIterator itr = collection.getIterator();
		while(itr.hasNext()){
			GameObject tempItr = itr.getNext();
			System.out.println(tempItr.toString());
		}
	}


}
