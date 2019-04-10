package modele;

import java.util.ArrayList;

import constantes.Constantes;
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
			ArrayList<KnowledgeNode> nodesToVisit = node.getIsARelation();
			// on parcourt les voisins tant qu'une boucle n'a pas été trouvée et qu'il reste des voisins
			int i = 0;
			while (i<nodesToVisit.size() && !loop) {
				loop = isALoopGeneratedRec(node.getIsARelation().get(i));
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
		
		else {
			addMemory(node);
			
			KnowledgeNode hasRelation = (KnowledgeNode) Constantes.NOT_SET_OBJECT;
			
			// on parcourt toutes les relations
			ArrayList<KnowledgeNode> nodesToVisit = new ArrayList<KnowledgeNode>();
			nodesToVisit.addAll(node.getIsARelation());
			nodesToVisit.addAll(node.getIsModelOfRelation());
			nodesToVisit.addAll(node.getIsQuadCoreOfRelation());
			
			int i = 0;
			while (i<nodesToVisit.size() && hasRelation.equals(Constantes.NOT_SET_OBJECT)) {
				//hasRelation = firstNodeWithRelationRec (node.get)
			}
			
			return hasRelation;
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
			
			/* on récupère ce noeud */
			KnowledgeNode actualNode = getNodes().get(pos);
			
			int nbIsA = actualNode.getIsARelation().size();
			int nbIsModelOf = actualNode.getIsModelOfRelation().size();
			int nbIsQuadCore = actualNode.getIsQuadCoreOfRelation().size();
			
			/* on supprime toutes les relations "is a" liées à ce noeud */
			for(int i=0; i<nbIsA; i++) {
				actualNode.getIsARelation().remove(0); // 0 => ArrayList supprime au fur et à mesure
			}
			
			/* on supprime toutes les relations "is model of" liées à ce noeud */
			for(int i=0; i<nbIsModelOf; i++) {
				actualNode.getIsModelOfRelation().remove(0); // 0 => ArrayList supprime au fur et à mesure
			}
			
			/* on supprime toutes les relations "is quad core" liées à ce noeud */
			for(int i=0; i<nbIsQuadCore; i++) {
				actualNode.getIsQuadCoreOfRelation().remove(0); // 0 => ArrayList supprime au fur et à mesure
			}
			
			/* on supprime le noeud */
			getNodes().remove(pos);
		}
		
		return pos != -1;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean addIsARelation(String nodeName, String nodeRelatedName) {
		 boolean isAdded = getANode(nodeName).addIsARelation(getANode(nodeRelatedName));
		
		/* si une boucle est générée, on supprime la relation */
		if (isALoopGenerated(nodeName)) {
			getANode(nodeName).removeIsARelation(getANode(nodeRelatedName));
			isAdded = false;
		}
		return isAdded;
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
	
	@Override
	public String toString() {
		KnowledgeNode actualNode;
		
		String s = Constantes.CONSOLE_SEPARATOR + "\n";
		s += "Nom du graphe: " + getName() + "\n";
		s += Constantes.CONSOLE_SEPARATOR + "\n";
		
		// on parcourt la liste des noeuds du graphe
		for(int i=0; i < nbNodes(); i++) {
			
			actualNode = getNodes().get(i);
			
			// attributes
			
			s += "(Nom:         " + actualNode.getName();
			s += (actualNode.hasSocket()? ",\n Socket:      " + actualNode.getSocket() : "");
			s += (actualNode.hasRam()? ",\n Ram:         " + actualNode.getRam() : "");
			s += (actualNode.hasPrice()? ",\n Price:       " + actualNode.getPrice() + "€" : "");
			s += (actualNode.hasReleaseDate()? ",\n ReleaseDate: " + actualNode.getReleaseDate() : "");
			s += "):\n";
			
			int nbIsA = actualNode.getIsARelation().size();
			int nbIsModelOf = actualNode.getIsModelOfRelation().size();
			int nbIsQuadCore = actualNode.getIsQuadCoreOfRelation().size();
			
			// relations
			
			if(nbIsA > 0 || nbIsModelOf > 0 || nbIsQuadCore > 0) {
				if(nbIsA > 0) {
					s += "    Is A => (";
					for(int j=0; j < nbIsA; j++) {
						s += "(" + actualNode.getIsARelation().get(j).getName() + ")";
					}
					s += ")\n";
				}
				if(nbIsModelOf > 0) {
					s += "    Is Model Of => (";
					for(int j=0; j < nbIsModelOf; j++) {
						s += "(" + actualNode.getIsModelOfRelation().get(j).getName() + ")";
					}
					s += ")\n";
				}
				if(nbIsQuadCore > 0) {
					s += "    Is Quad Core Of => (";
					for(int j=0; j < nbIsA; j++) {
						s += "(" + actualNode.getIsQuadCoreOfRelation().get(j).getName() + ")";
					}
					s += ")\n";
				}
			} else {
				s += "    Aucune relation n'a encore été créée.\n";
			}
		}
		s += Constantes.CONSOLE_SEPARATOR + "\n";
		return s;
	}

}
