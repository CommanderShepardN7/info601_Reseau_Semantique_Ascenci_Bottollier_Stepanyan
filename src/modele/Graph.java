package modele;

import java.util.ArrayList;

abstract class Graph<T extends Node> {

	/**********************************************************
	 * 
	 * Attributs du graphe:
	 * - un nom
	 * - une liste de noeuds
	 * - une mémoire
	 * 
	 *********************************************************/
	
	protected String name;
	
	public ArrayList<T> nodes;
	public ArrayList<T> memory = new ArrayList<T>();
	
	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String graphName) {
		this.name = graphName;
	}
	
	protected ArrayList<T> getMemory () {
		return this.memory;
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
	
	protected boolean addMemory (T node) {
		return getMemory().add(node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	protected void clearMemory () {
		getMemory().clear();
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	protected boolean isInMemory (T node) {
		return getMemory().contains(node);
	}
	
	/*
	 * 
	 * Retourne la liste de tous les noeuds qui ne sont pas dans
	 * la mémoire.
	 * 
	 */
	
	protected ArrayList<T> filterMem (ArrayList<T> nodeList) {
		ArrayList<T> res = new ArrayList<T>();
		
		for(int i=0; i<nodeList.size(); i++) {
			
			/* si le noeud n'est pas dans la mémoire */
			if(!isInMemory(nodeList.get(i))) {
				res.add(nodeList.get(i));
			}
			
		}
		
		return res;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public abstract boolean addNode (T node);
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public abstract boolean removeNode (String nodeName);

	/*
	 * 
	 * 
	 * 
	 */
	
	public abstract int nbNodes ();
	
	/*
	 * 
	 * Vérifie s'il existe un noeud de nom donné.
	 * 
	 * - nomNoeud: Le nom cherché.
	 * 
	 * Retourne la position du noeud si trouvé, sinon -1.
	 * 
	 */
	
	public abstract int nodeExists (String nomNoeud);
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public abstract String toString ();
	
}
