package main;

import java.util.Date;

import constantes.Constantes;
import lisibilite_code.ActionConsole;
import modele.KnowledgeGraph;
import modele.KnowledgeNode;


/**********************************************************
 * 
 * MainConsole:
 * 
 * Interface sur console.
 * L'utilisateur peut manipuler un graphe orienté.
 * 
 *********************************************************/

public class MainConsole extends ActionConsole {
	
	/**********************************************************
	 * 
	 * Attributs de MainConsole:
	 * - un graphe
	 * - un booléen pour permettre à l'utilisateur de sortir du programme
	 * 
	 *********************************************************/
	
	private static KnowledgeGraph g;
	private static boolean isInProgress;
	
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
	
	private static void progConfig() {
		isInProgress = true;
	}
	
	/*
	 * 
	 * Configuration du graphe.
	 * 
	 * Demande à l'utilisateur le nom du graphe.
	 * 
	 */
	
	private static void graphConfig() {
		ecrire_console(Constantes.CONSOLE_SEPARATOR);
		ecrire_console("Nom du graphe: ");
		
		g = new KnowledgeGraph(recupere_string());
		
		///////////////////////////////////////////
		
		/* Tests */
		
		KnowledgeNode a = new KnowledgeNode("a");
		KnowledgeNode b = new KnowledgeNode("b");
		KnowledgeNode c = new KnowledgeNode("c");
		KnowledgeNode d = new KnowledgeNode("d");
		KnowledgeNode e = new KnowledgeNode("e");
		
		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addNode(d);
		g.addNode(e);
		
//		g.getANode("a").addIsARelation(g.getANode("b"));
//		g.getANode("b").addIsARelation(g.getANode("d"));
//		g.getANode("d").addIsARelation(g.getANode("e"));
		
		g.addIsARelation("a", "b");
		g.addIsARelation("b", "d");
		g.addIsARelation("d", "e");
		
		ecrire_console(g.isALoopGenerated("a"));
		
//		g.getANode("e").addIsARelation(g.getANode("e"));
		
		g.addIsARelation("e", "e");
		
		ecrire_console(g.isALoopGenerated("a")); // on remarque que la vérification de boucle fonctionne!!!
		
		///////////////////////////////////////////

	}
	
	/*
	 * 
	 * Afficher l'état du graphe.
	 * 
	 */
	
	private static void displayGraphState() {
		ecrire_console(g.toString());
	}
	
	/*
	 * 
	 * Afficher le menu.
	 * 
	 */
	
	private static void displayMenu() {
		ecrire_console("Commandes:");
		ecrire_console("1 - Creer un nouveau noeud");
		ecrire_console("2 - Supprimer un noeud");
		ecrire_console("3 - Configurer un noeud existant");
		ecrire_console("4 - Creer un lien entre deux noeuds");
		ecrire_console("5 - Supprimer un lien existant entre deux noeuds");
		ecrire_console("6 - Verifier si un noeud a une relation donnee, sinon recherche si elle est accessible a partir de ce noeud");
		ecrire_console("7 - Verifier si un noeud a un attribut donne, sinon recherche parmi ses \"parents\" l'attribut donne");
		ecrire_console("8 - Quitter le programme");
		ecrire_console(Constantes.CONSOLE_SEPARATOR);
	}
	
	/*
	 * 
	 * Traite le cas où l'utilisateur souhaite
	 * créer un nouveau noeud.
	 * 
	 */
	
	private static void handleNewNode() {
		ecrire_console("Nom du nouveau noeud:");
		
		while(!g.addNode(new KnowledgeNode(recupere_string()))) {
			ecrire_console("Ce nom a deja ete attribue.");
		}
		
		ecrire_console("Le noeud a bien ete cree.");
	}
	
	/*
	 * 
	 * Traite le cas où l'utilisateur souhaite
	 * supprimer un noeud existant.
	 * 
	 */
	
	private static void handleRemoveNode() {
		if(g.nbNodes() > 0) {
			ecrire_console("Nom du noeud a supprimer:");
			
			/* on autorise l'utilisateur a revenir en arriere en cas d'erreur */
			if(g.removeNode(recupere_string())) {
				ecrire_console("Le noeud a bien ete supprime.");
			}
			else {
				ecrire_console("Aucun noeud n'a ce nom.");
			}
		}
		else {
			ecrire_console("Aucun noeud n'a encore ete cree.");
		}
	}
	
	/*
	 * 
	 * Demande à l'utilisateur ce qu'il souhaite modifier sur un noeud donné.
	 * 
	 * 
	 */
	
	private static void handleConfigureNode() {
		if(g.nbNodes() > 0) {
			ecrire_console("Nom du noeud a modifier:");
			
			
		}
		else {
			ecrire_console("Aucun noeud n'a encore ete cree.");
		}
	}
	
	/*
	 * 
	 * Traite le cas où l'utilisateur souhaite
	 * créer un lien entre deux noeuds existants.
	 * 
	 */
	
	private static void handleNewRelation() {
		KnowledgeNode node_1, node_2;
		String n_1, n_2;
	
		if(g.nbNodes() > 1) {
			ecrire_console("Nom du premier noeud de la relation :");
			n_1 = recupere_string();
			
			if(g.nodeExists(n_1) > -1) {	
				node_1 = g.getANode(n_1);
				
				ecrire_console("Nom du second noeud de la relation :");
				n_2 = recupere_string();
				
				
				if(g.nodeExists(n_2) > -1) {
					node_2 = g.getANode(n_2);
					
					if((node_1.hasRelation(node_2)) < 0) {		
						ecrire_console("Type de relation à créer (isA, isModelOf, isQuadCoreOf) :");
						String choix = recupere_string();
						
						switch (choix) {
						case "isA":
							node_1.addIsARelation(node_2);
							break;
						case "isModelOf":
							node_1.addIsModelOfRelation(node_2);
							break;
						case "isQuadCoreOf":
							node_1.addIsQuadCoreOfRelation(node_2);			
							break;
						default:
							ecrire_console("Veuillez saisir un type de relation valide : isA, isModelOf, isQuadCoreOf ");
							break;
						}
					}else {
						ecrire_console("Ces noeuds sont déjà liés");		
					}					
				}else {
					ecrire_console("Le noeud saisi n'existe pas");
				}				
			}else {
				ecrire_console("Le noeud saisi n'existe pas");
			}			
		}else {
			ecrire_console("Le graph est vide");
		}
		ecrire_console("La relation a été créée");
	}
	
	/*
	 * 
	 * Traite le cas oÃ¹ l'utilisateur souhaite
	 * supprimer un lien existant entre deux
	 * noeuds.
	 * 
	 */
	
	private static void handleRemoveRelation() {
		KnowledgeNode node_1, node_2;
		String n_1, n_2;
	
		if(g.nbNodes() > 1) {
			ecrire_console("Nom du premier noeud de la relation :");
			n_1 = recupere_string();
			
			if(g.nodeExists(n_1) > -1) {	
				node_1 = g.getANode(n_1);
				
				ecrire_console("Nom du second noeud de la relation :");
				n_2 = recupere_string();
				
				
				if(g.nodeExists(n_2) > -1) {
					node_2 = g.getANode(n_2);
					
					int relation = node_1.hasRelation(node_2);
					
					if( relation >= 0) {		
						switch (relation) {
						case 0:
							node_1.removeIsARelation(node_2);
							break;
						case 1:
							node_1.removeIsModelOfRelation(node_2);
							break;
						case 2:
							node_1.removeIsQuadCoreOfRelation(node_2);			
							break;
						default:
							ecrire_console("Erreur");
							break;
						}
					}else {
						ecrire_console("Ces noeuds ne sont pas liés");		
					}					
				}else {
					ecrire_console("Le noeud saisi n'existe pas");
				}				
			}else {
				ecrire_console("Le noeud saisi n'existe pas");
			}			
		}else {
			ecrire_console("Le graph est vide");
		}
		ecrire_console("La relation a été supprimée");
	}
	
	/*
	 *
	 *
	 *
	 */
	
	private static void handleHasRelation() {
		
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	private static void handleHasAttribute() {
		KnowledgeNode node;
		String nameNode, attribut;
		boolean hasAttribut = false;
		ArrayList<KnowledgeNode> isA;
		ArrayList<KnowledgeNode> isModelOf;
		ArrayList<KnowledgeNode> isQuadCoreOf;
		KnowledgeNode parents[] = new KnowledgeNode[1000];

		
	
		if(g.nbNodes() >= 1) {
			ecrire_console("Nom du noeud :");
			nameNode = recupere_string();
			
			if(g.nodeExists(nameNode) > -1) {	
				node = g.getANode(nameNode);
				isA = node.getIsARelation();
				isModelOf = node.getIsModelOfRelation();
				isQuadCoreOf = node.getIsQuadCoreOfRelation();
				
				ecrire_console("Nom de l'attribut (releaseDate, socket, ram, price) :");
				attribut = recupere_string();
				
				switch (attribut) {
				case "releaseDate":
					if(node.hasReleaseDate()) {
						hasAttribut = true;
					}else {				
						for(int i = 0; i < isA.size(); i++ ) {
							if(isA.get(i).hasReleaseDate()) {
								parents[i+parents.length] = isA.get(i);	
							}
						}
						
						for(int i = 0; i < isModelOf.size(); i++ ) {
							if(isModelOf.get(i).hasReleaseDate()) {
								parents[i+parents.length] = isModelOf.get(i);
							}
						}
						
						for(int i = 0; i < isQuadCoreOf.size(); i++ ) {
							if(isQuadCoreOf.get(i).hasReleaseDate()) {
								parents[i+parents.length] = isQuadCoreOf.get(i);
							}
						}
					}
					break;
				case "socket":
					if(node.hasSocket()) {
						hasAttribut = true;
					}else {				
						for(int i = 0; i < isA.size(); i++ ) {
							if(isA.get(i).hasSocket()) {
								parents[i+parents.length] = isA.get(i);	
							}
						}
						
						for(int i = 0; i < isModelOf.size(); i++ ) {
							if(isModelOf.get(i).hasSocket()) {
								parents[i+parents.length] = isModelOf.get(i);		
							}
						}
						
						for(int i = 0; i < isQuadCoreOf.size(); i++ ) {
							if(isQuadCoreOf.get(i).hasSocket()) {
								parents[i+parents.length] = isQuadCoreOf.get(i);		
							}
						}
					}
					break;
				case "ram":
					if(node.hasRam()) {
						hasAttribut = true;
					}else {				
						for(int i = 0; i < isA.size(); i++ ) {
							if(isA.get(i).hasRam()) {
								parents[i+parents.length] = isA.get(i);		
							}
						}
						
						for(int i = 0; i < isModelOf.size(); i++ ) {
							if(isModelOf.get(i).hasRam()) {
								parents[i+parents.length] = isModelOf.get(i);	
							}
						}
						
						for(int i = 0; i < isQuadCoreOf.size(); i++ ) {
							if(isQuadCoreOf.get(i).hasRam()) {
								parents[i+parents.length] = isQuadCoreOf.get(i);		
							}
						}
					}			
					break;
				case "price":
					if(node.hasPrice()) {
						hasAttribut = true;
					}else {				
						for(int i = 0; i < isA.size(); i++ ) {
							if(isA.get(i).hasPrice()) {
								parents[i+parents.length] = isA.get(i);		
							}
						}
						
						for(int i = 0; i < isModelOf.size(); i++ ) {
							if(isModelOf.get(i).hasPrice()) {
								parents[i+parents.length] = isModelOf.get(i);		
							}
						}
						
						for(int i = 0; i < isQuadCoreOf.size(); i++ ) {
							if(isQuadCoreOf.get(i).hasPrice()) {
								parents[i+parents.length] = isQuadCoreOf.get(i);		
							}
						}
					}			
					break;
				default:
					ecrire_console("Veuillez saisir un attribut valide : releaseDate, socket, ram, price ");
					break;
				}
				
				if(hasAttribut) {
					ecrire_console("Le noeud ["+ nameNode +"] possède l'attribut "+ attribut);
				}else {
					String listParents = "";
					for(int i= 0; i < parents.length; i++) {
						listParents.concat("["+ parents[i].getName() +"] ");
					}
					ecrire_console("L'attribut "+ attribut +" est possédé par les parents suivants : "+ listParents);
				}
	
			}else {
				ecrire_console("Le noeud saisi n'existe pas");
			}			
		}else {
			ecrire_console("Le graph est vide");
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	private static void handleQuit() {
		ecrire_console("Vous quittez le programme...");
		
		isInProgress = false;
	}
	
	/*
	 * 
	 * Récupère le choix de commande de l'utilisateur
	 * et gère le cas à traiter.
	 * 
	 */
	
	private static void handleCommand() {
		ecrire_console("Choix de commande (1 - 4) :");
		
		int choix = recupere_int();
		
		while(!(choix >= 1 && choix <= 8)) {
			ecrire_console("La commande saisie doit etre comprise entre 1 et 8.");
			choix = recupere_int();
		}
		
		switch (choix) {
		case 1:
			handleNewNode();
			break;
		case 2:
			handleRemoveNode();
			break;
		case 3:
			handleConfigureNode();
			break;
		case 4:
			handleNewRelation();
			break;
		case 5:
			handleRemoveRelation();
			break;
		case 6:
			handleHasRelation();
			break;
		case 7:
			handleHasAttribute();
			break;
		case 8:
			handleQuit();
			break;
		default:
			ecrire_console("Erreur.");
			break;
		}
		
	}
	
	/**********************************************************
	 * 
	 * La méthode principale de l'application.
	 * Affiche sur la console la liste des commandes disponibles.
	 * Lui permet d'entrer une commande.
	 * Affiche le résultat.
	 * 
	 *********************************************************/

	public static void main(String[] args) {
		
		/* configuration du programme */
		progConfig();
		
		/* configuration du graphe */
		graphConfig();
		
		/* coeur du programme */
		while (isInProgress) {
			/* affiche l'état du graphe */
			displayGraphState();
			
			/* affiche le menu */
			displayMenu();
			
			/* récupère et traite le choix de l'utilisateur */
			handleCommand();
			
		}
		
	}

}