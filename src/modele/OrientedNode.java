package modele;

import java.util.ArrayList;

/**********************************************************
 * 
 * 
 * 
 *********************************************************/

public abstract class OrientedNode<T extends OrientedNode<?>> extends Node {
	
	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/

	protected ArrayList<T> pred = new ArrayList<T>();
	
	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/
	
	public ArrayList<T> getPred () {
		return this.pred;
	}
	
	public void setPred (ArrayList<T> pred) {
		this.pred = pred;
	}
	
	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean addPred (T pred) {
		return getPred().add(pred);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public void removePred (T pred) {
		getPred().remove(pred);
	}
	
}
