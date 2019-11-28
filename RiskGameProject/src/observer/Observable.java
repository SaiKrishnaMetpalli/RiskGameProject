package observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {

	/**
	 * This private variable contains the list of observers
	 */
	private List<Observer> observers = new ArrayList<Observer>();

	/**
	 * This method is used for attaching the observer to this class
	 * 
	 * @param o this variable contains Observer object
	 * @author Sai Krishna
	 */
	public void attach(Observer o) {
		this.observers.add(o);
	}

	/**
	 * This method is used for detaching the observer to this class
	 * 
	 * @param o this variable contains Observer object
	 * @author Sai Krishna
	 */
	public void detach(Observer o) {
		if (!observers.isEmpty()) {
			observers.remove(o);
		}
	}

	/**
	 * This method notifies the observer that state has changed
	 * 
	 * @param observable this variable contains the observable object state
	 * @author Ashish
	 */
	public void notifyObservers(Observable observable, Object x) {
		for (Observer observer : observers) {
			observer.update(observable, x);
		}
	}
}
