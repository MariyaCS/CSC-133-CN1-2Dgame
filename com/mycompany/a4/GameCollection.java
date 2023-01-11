package com.mycompany.a4;

import java.util.ArrayList;

public class GameCollection implements ICollection{
	
	private ArrayList<GameObject> theCollection;
	
	public GameCollection() {
		theCollection = new ArrayList<GameObject>();
	}
	//adds objects to the collection. This method is an implementation of ICollection add() method.
	@Override
	public void add(GameObject newObject) {
		theCollection.add(newObject);
		
	}
	//Obtains iterator over the collection. This method is an implementation of ICollection getIterator() method.
	@Override
	public IIterator getIterator(){
		return new GameListIterator();
	}
	
	private class GameListIterator implements IIterator{
		private int currElementIndex;
		
		public GameListIterator() {
			currElementIndex = -1;
		}
		//hasNext() checks whether there are more elements to be processed in the collection.This method is an implementation
		//of IIterator hasNext() method. 
		@Override
		public boolean hasNext() {
			if(theCollection.size()<=0) {
				return false;
			}
			if(currElementIndex == theCollection.size()-1) {
				return false;
			}
			return true;	
		}
		
		//getNext() returns the next element to be processed form the collection. This method is an implementation of
		//IIterator getNext() method.
		@Override
		public GameObject getNext() {
			currElementIndex++;
			return theCollection.get(currElementIndex);
		}
		
	}

}
