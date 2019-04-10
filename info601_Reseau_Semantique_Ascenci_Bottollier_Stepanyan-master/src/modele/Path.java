package modele;

import java.util.ArrayList;

/**********************************************************
 * 
 * Attributs de MainConsole:
 * - un graphe
 * - un booléen pour permettre à l'utilisateur de sortir du programme
 * 
 *********************************************************/

public class Path<T extends Node> {

	/**********************************************************
	 * 
	 * Attributs de MainConsole:
	 * - un graphe
	 * - un booléen pour permettre à l'utilisateur de sortir du programme
	 * 
	 *********************************************************/
	
	private ArrayList<T> nodes;
	
	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/
	
	public Path() {
		this.nodes = new ArrayList<T>();
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
	
	public boolean addNode(T node) {
		return this.nodes.add(node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public ArrayList<T> getPath() {
		return this.nodes;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public void clear() {
		this.nodes.clear();
	}
	
}
