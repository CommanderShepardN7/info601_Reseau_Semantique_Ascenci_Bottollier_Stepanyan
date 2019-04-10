package modele;

import java.util.ArrayList;

import constantes.Constantes;
import types.TAttribute;
import types.TRelation;

/**********************************************************
 * 
 * Attributs du graphe de connaissances:
 * - un nom
 * - une liste de noeuds
 * - une mémoire
 * 
 *********************************************************/

public class KnowledgeGraph extends OrientedGraph<KnowledgeNode> {
	
	/**********************************************************
	 * 
	 * Attributs du graphe:
	 * - un nom
	 * - une liste de noeuds
	 * - une mémoire
	 * 
	 *********************************************************/
	
	private ArrayList<KnowledgeNode> nodes;
	private Path<KnowledgeNode> path;
	
	/**********************************************************
	 * 
	 * Attributs du graphe:
	 * - un nom
	 * - une liste de noeuds
	 * - une mémoire
	 * 
	 *********************************************************/

	public KnowledgeGraph(String graphName) {
		this(graphName, new ArrayList<KnowledgeNode>());
	}
	
	public KnowledgeGraph(String graphName, ArrayList<KnowledgeNode> nodes) {
		setName(graphName);
		setNodes(nodes);
	}
	
	/**********************************************************
	 * 
	 * Attributs du graphe:
	 * - un nom
	 * - une liste de noeuds
	 * - une mémoire
	 * 
	 *********************************************************/
	
	public ArrayList<KnowledgeNode> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<KnowledgeNode> nodes) {
		this.nodes = nodes;
	}
	
	/**********************************************************
	 * 
	 * Méthodes privées
	 * 
	 *********************************************************/
	
	/*
	 * 
	 * 
	 * 
	 */
	
	protected boolean isALoopGeneratedRec (KnowledgeNode node) {
		
		// si le noeud actuel est dans la mémoire, alors il y a boucle
		if (isInMemory(node)) {
			return true;
		}
		
		else {
			addMemory(node);
			
			boolean loop = false;
			// on ne parcourt que les relation "is a"
			ArrayList<KnowledgeNode> nodesToVisit = node.getRelation(TRelation.IS_A);
			// on parcourt les voisins tant qu'une boucle n'a pas été trouvée et qu'il reste des voisins
			int i = 0;
			while (i<nodesToVisit.size() && !loop) {
				loop = isALoopGeneratedRec(node.getRelation(TRelation.IS_A).get(i));
				i++;
			}
			return loop;
		}
	}
	
	protected KnowledgeNode firstNodeWithRelationRec (KnowledgeNode node, TRelation relation) {
		
		// si le noeud actuel a la relation donnée
		if (node.hasRelation(relation)) {
			return node;
		}
		
		// toutes les relations associées au noeud courant
		ArrayList<KnowledgeNode> nodesToVisit = new ArrayList<KnowledgeNode>();
		nodesToVisit.addAll(node.getRelation(TRelation.IS_A));
		nodesToVisit.addAll(node.getRelation(TRelation.IS_MODEL_OF));
		nodesToVisit.addAll(node.getRelation(TRelation.IS_QUAD_CORE_OF));
		
		// si le noeud n'a plus aucun voisin non parcouru
		if (this.filterMem(nodesToVisit).size() == 0) {
			return (KnowledgeNode) Constantes.NOT_SET_OBJECT;
		}
		
		else {
			TRelation rel;
			
			/* on l'ajoute à la mémoire */
			addMemory(node);
			
			KnowledgeNode hasRelation = (KnowledgeNode) Constantes.NOT_SET_OBJECT;
				
			/* on parcourt l'ensemble des relations */
			int i = 0;
			while (i<TRelation.values().length && (hasRelation == Constantes.NOT_SET_OBJECT)) {
				
				/* on récupère une relation */
				rel = TRelation.values()[i];
				
				/* parcours de la relation */
				int j = 0;
				while (j<node.getRelation(rel).size() && (hasRelation == Constantes.NOT_SET_OBJECT)) {
					
					/* on appelle la méthode récursivement, pour chaque élément résultant de la relation */
					hasRelation = firstNodeWithRelationRec (node.getRelation(rel).get(j), relation);
					
					/* une relation isA parcourue */
					j++;
					
				}
				
				/* on passe à la relation suivante */
				i++;
			}
			
			return hasRelation;
		}
	}
	
	protected KnowledgeNode firstNodeWithAttributeRec (KnowledgeNode node, TAttribute attr) {
		// si le noeud actuel a la relation donnée
		if (node.hasAttribute(attr)) {
			return node;
		}
		
		// toutes les relations associées au noeud courant
		ArrayList<KnowledgeNode> nodesToVisit = new ArrayList<KnowledgeNode>();
		nodesToVisit.addAll(node.getRelation(TRelation.IS_A));
		nodesToVisit.addAll(node.getRelation(TRelation.IS_MODEL_OF));
		nodesToVisit.addAll(node.getRelation(TRelation.IS_QUAD_CORE_OF));
		
		// si le noeud n'a plus aucun voisin non parcouru
		if (this.filterMem(nodesToVisit).size() == 0) {
			return (KnowledgeNode) Constantes.NOT_SET_OBJECT;
		}
		
		else {
			TRelation rel;
			
			/* on l'ajoute à la mémoire */
			addMemory(node);
			
			KnowledgeNode hasAttribute = (KnowledgeNode) Constantes.NOT_SET_OBJECT;
				
			/* on parcourt l'ensemble des relations */
			int i = 0;
			while (i<TRelation.values().length && (hasAttribute == Constantes.NOT_SET_OBJECT)) {
				
				/* on récupère une relation */
				rel = TRelation.values()[i];
				
				/* parcours de la relation */
				int j = 0;
				while (j<node.getRelation(rel).size() && (hasAttribute == Constantes.NOT_SET_OBJECT)) {
					
					/* on appelle la méthode récursivement, pour chaque élément résultant de la relation */
					hasAttribute = firstNodeWithAttributeRec (node.getRelation(rel).get(j), attr);
					
					/* une relation isA parcourue */
					j++;
					
				}
				
				/* on passe à la relation suivante */
				i++;
			}
			
			return hasAttribute;
		}
	}
	
	/**********************************************************
	 * 
	 * Méthodes publiques
	 * 
	 *********************************************************/

	/*
	 * 
	 * 
	 * 
	 */
	
	@Override
	public boolean addNode(KnowledgeNode node) {
		/* on empêche l'utilisateur de générer deux noeuds de même nom */
		if (nodeExists(node.getName()) == -1) {
			return getNodes().add(node);
		}
		else {
			return false;
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */

	@Override
	public boolean removeNode(String nodeName) {
		int pos = nodeExists(nodeName);
		
		/* si l'élément cherché existe */
		if (pos != -1) {
			
			TRelation rel; // une relation
			int n; // un entier contenant la taille d'un tableau
			KnowledgeNode nodeInter;
			
			/* on récupère ce noeud */
			KnowledgeNode actualNode = getNodes().get(pos);
			
			/* on parcourt l'ensemble des relations */
			int j = 0;
			while (j<TRelation.values().length) {
				/* on récupère une relation */
				rel = TRelation.values()[j];
				
				n = actualNode.getRelation(rel).size();
				
				/* on supprime toutes les relations du type donné liées à ce noeud */
				for(int i=0; i<n; i++) {
					nodeInter = actualNode.getRelation(rel).get(0);
					actualNode.removeRelation(nodeInter, rel); // 0 => ArrayList supprime au fur et à mesure
				}
				
				
				/* on supprime toutes les relations où ce noeud était un successeur */
				n = actualNode.getPred().size();
				
				for(int i=0; i<n; i++) {
					nodeInter = actualNode.getPred().get(i);
					nodeInter.removeRelation(actualNode, rel);
				}
				
				/* on passe au type de relation suivant */
				j++;
			}
			
			/* on supprime le noeud */
			getNodes().remove(pos);
		}
		
		return pos != -1;
	}
	
	/*
	 * 
	 * On ajoute la relation donnée au noeud donné et on ajoute ce noeud en prédécesseur du successeur.
	 * 
	 */
	
	public boolean addRelation (String nodeName, String nodeRelatedName, TRelation relation) {
		return getANode(nodeName).addRelation(getANode(nodeRelatedName), relation) && getANode(nodeRelatedName).addPred(getANode(nodeName));
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean removeRelation (String nodeName, String nodeRelatedName, TRelation relation) {
		return getANode(nodeName).removeRelation(getANode(nodeRelatedName), relation);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public void setAttribute (String nodeName, TAttribute attr, String value) {
		getANode(nodeName).setAttribute(attr, value);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public void removeAttribute (String nodeName, TAttribute attr) {
		getANode(nodeName).setAttribute(attr, (String) Constantes.NOT_SET_OBJECT);
	}
	
	/*
	 * 
	 * 
	 * 
	 */

	@Override
	public int nbNodes () {
		return getNodes().size();
	}
	
	/*
	 * 
	 * Vérifie s'il existe un noeud de nom donné.
	 * 
	 * - nomNoeud: Le nom cherché.
	 * 
	 * Retourne la position du noeud si trouvé, sinon -1.
	 * 
	 */
	
	@Override
	public int nodeExists (String nodeName) {
		int finded = -1;
		int i = 0;
		while(i < getNodes().size() && finded == -1) {
			if(getNodes().get(i).getName().equals(nodeName)) {
				finded = i;
			}
			i++;
		}
		return finded;
	}
	
	/*
	 * 
	 * Vérifie si une boucle de relations isA est généré à l'ajout
	 * d'une relation isA. 
	 * 
	 */
	
	public boolean isALoopGenerated (String nodeName) {
		clearMemory();
		KnowledgeNode node = getANode(nodeName);
		return isALoopGeneratedRec (node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public KnowledgeNode getANode (String nodeName) {
		return getNodes().get(nodeExists(nodeName));
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public KnowledgeNode firstNodeWithRelation (String firstNodeName, TRelation relation) {
		clearMemory();
		KnowledgeNode node = getANode(firstNodeName);
		return firstNodeWithRelationRec (node, relation);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public KnowledgeNode firstNodeWithAttribute (String firstNodeName, TAttribute attr) {
		clearMemory();
		KnowledgeNode node = getANode(firstNodeName);
		return firstNodeWithAttributeRec (node, attr);
	}

	/*
	 * 
	 * 
	 * 
	 */
	
	@Override
	public String toString() {
		KnowledgeNode actualNode;
		
		String s = Constantes.CONSOLE_SEPARATOR + "\n";
		s += "Nom du graphe: " + getName() + "\n";
		s += Constantes.CONSOLE_SEPARATOR + "\n";
		
		// on parcourt la liste des noeuds du graphe
		for(int i=0; i < nbNodes(); i++) {
			
			actualNode = getNodes().get(i);
			
			s += actualNode.toString();
		}
		s += Constantes.CONSOLE_SEPARATOR + "\n";
		return s;
	}

}
