package main;

import java.util.Date;

import constantes.Constantes;
import lisibilite_code.ActionConsole;
import modele.KnowledgeGraph;
import modele.KnowledgeNode;
import types.TAttribute;
import types.TRelation;


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
		
//		KnowledgeNode a = new KnowledgeNode("a");
//		KnowledgeNode b = new KnowledgeNode("b");
//		KnowledgeNode c = new KnowledgeNode("c");
//		KnowledgeNode d = new KnowledgeNode("d");
//		KnowledgeNode e = new KnowledgeNode("e");
//		
//		g.addNode(a);
//		g.addNode(b);
//		g.addNode(c);
//		g.addNode(d);
//		g.addNode(e);
		
//		g.getANode("a").addIsARelation(g.getANode("b"));
//		g.getANode("b").addIsARelation(g.getANode("d"));
//		g.getANode("d").addIsARelation(g.getANode("e"));
		
//		g.addRelation("a", "b", TRelation.IS_A);
//		g.addRelation("b", "d", TRelation.IS_A);
//		g.addRelation("d", "e", TRelation.IS_A);
//		g.addRelation("b", "c", TRelation.IS_A);
//		
//		ecrire_console(g.isALoopGenerated("a"));
		
//		g.getANode("e").addIsARelation(g.getANode("e"));
		
//		g.addRelation("e", "e", TRelation.IS_A);
//		
//		ecrire_console(g.isALoopGenerated("a"));
//		
//		g.addRelation("e", "d", TRelation.IS_MODEL_OF);
//		g.addRelation("d", "a", TRelation.IS_QUAD_CORE_OF);
//		
//		ecrire_console(g.firstNodeWithRelation("a", TRelation.IS_MODEL_OF));
//		
//		ecrire_console(g.firstNodeWithRelation("a", TRelation.IS_QUAD_CORE_OF));
		
//		g.removeNode("d");
		
//		g.setAttribute("a", TAttribute.PRICE, "10");
//		g.setAttribute("d", TAttribute.RAM, "DDR3");
//		
//		ecrire_console(g.firstNodeWithAttribute("a", TAttribute.PRICE));
//		ecrire_console(g.firstNodeWithAttribute("a", TAttribute.RAM));
//		ecrire_console(g.firstNodeWithAttribute("a", TAttribute.RELEASE_DATE));
		
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
		ecrire_console("6 - Verifier si un type de relation existe parmi les noeuds connectes a un noeud precis");
		ecrire_console("7 - Verifier si un type d'attribut existe parmi les noeuds connectes a un noeuds precis via des relations \"is a\"");
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
			
			String nodeName = recupere_string();
			
			/* si le noeud existe */
			if(g.nodeExists(nodeName) != -1) {
				TAttribute attr;
				
				ecrire_console("Quel attribut souhaitez-vous mettre a jour?");
				
				/* affichage des attributs */
				int j = 0;
				while (j<TAttribute.values().length) {
					/* on récupère une relation */
					attr = TAttribute.values()[j];
					ecrire_console((j+1) + "- " + attr.getNom());
					
					/* on passe à la relation suivante */
					j++;
				}
				
				ecrire_console("Choix de commande (1 - " + j + ") :");
				
				int choix = recupere_int();
				
				while(!(choix >= 1 && choix <= j)) {
					ecrire_console("La commande saisie doit etre comprise entre 1 et " + j + ".");
					choix = recupere_int();
				}
				
				ecrire_console("Entrez votre valeur:");
				String value = recupere_string();
				
				switch(choix) {
				case Constantes.CHOIX_SET_RELEASE_DATE:
					g.setAttribute(nodeName, TAttribute.RELEASE_DATE, value);
					break;
				case Constantes.CHOIX_SET_SOCKET:
					g.setAttribute(nodeName, TAttribute.SOCKET, value);
					break;
				case Constantes.CHOIX_SET_RAM:
					g.setAttribute(nodeName, TAttribute.RAM, value);
					break;
				case Constantes.CHOIX_SET_PRICE:
					g.setAttribute(nodeName, TAttribute.PRICE, value);
					break;
				default:
				}
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
	 * Traite le cas où l'utilisateur souhaite
	 * créer un lien entre deux noeuds existants.
	 * 
	 */
	
	private static void handleNewRelation() {
		if(g.nbNodes() > 0) {
					
			ecrire_console("Nom du premier noeud:");
			
			String nodeName = recupere_string();
			
			ecrire_console("Nom du successeur:");
			
			String succName = recupere_string();
			
			/* si les noeuds existent */
			if(g.nodeExists(nodeName) != -1 && g.nodeExists(succName) != -1) {
				TRelation rel;
				
				ecrire_console("Quel type de relation souhaitez-vous entre ces noeuds?");
				
				/* affichage des attributs */
				int j = 0;
				while (j<TRelation.values().length) {
					/* on récupère une relation */
					rel = TRelation.values()[j];
					ecrire_console((j+1) + "- " + rel.getNom());
					
					/* on passe à la relation suivante */
					j++;
				}
				
				ecrire_console("Choix de commande (1 - " + j + ") :");
				
				int choix = recupere_int();
				
				while(!(choix >= 1 && choix <= j)) {
					ecrire_console("La commande saisie doit etre comprise entre 1 et " + j + ".");
					choix = recupere_int();
				}
				
				switch(choix) {
				case Constantes.CHOIX_SET_IS_A:
					g.addRelation(nodeName, succName, TRelation.IS_A);
					break;
				case Constantes.CHOIX_SET_IS_MODEL_OF:
					g.addRelation(nodeName, succName, TRelation.IS_MODEL_OF);
					break;
				case Constantes.CHOIX_SET_IS_QUAD_CORE_OF:
					g.addRelation(nodeName, succName, TRelation.IS_QUAD_CORE_OF);
					break;
				default:
				}
				
			}
			else {
				ecrire_console("Au moins un des deux noeuds n'existe pas.");
			}
		}
	}
	
	/*
	 * 
	 * Traite le cas où l'utilisateur souhaite
	 * supprimer un lien existant entre deux
	 * noeuds.
	 * 
	 */
	
	private static void handleRemoveRelation() {
		if(g.nbNodes() > 0) {
			
			ecrire_console("Nom du premier noeud:");
			
			String nodeName = recupere_string();
			
			ecrire_console("Nom du successeur:");
			
			String succName = recupere_string();
			
			/* si les noeuds existent */
			if(g.nodeExists(nodeName) != -1 && g.nodeExists(succName) != -1) {
				TRelation rel;
				
				ecrire_console("Quel type de relation souhaitez-vous supprimer entre ces noeuds?");
				
				/* affichage des attributs */
				int j = 0;
				while (j<TRelation.values().length) {
					/* on récupère une relation */
					rel = TRelation.values()[j];
					ecrire_console((j+1) + "- " + rel.getNom());
					
					/* on passe à la relation suivante */
					j++;
				}
				
				ecrire_console("Choix de commande (1 - " + j + ") :");
				
				int choix = recupere_int();
				
				while(!(choix >= 1 && choix <= j)) {
					ecrire_console("La commande saisie doit etre comprise entre 1 et " + j + ".");
					choix = recupere_int();
				}
				
				switch(choix) {
				case Constantes.CHOIX_SET_IS_A:
					g.removeRelation(nodeName, succName, TRelation.IS_A);
					break;
				case Constantes.CHOIX_SET_IS_MODEL_OF:
					g.removeRelation(nodeName, succName, TRelation.IS_MODEL_OF);
					break;
				case Constantes.CHOIX_SET_IS_QUAD_CORE_OF:
					g.removeRelation(nodeName, succName, TRelation.IS_QUAD_CORE_OF);
					break;
				default:
				}
				
			}
			else {
				ecrire_console("Au moins un des deux noeuds n'existe pas.");
			}
		}
	}
	
	/*
	 *
	 *
	 *
	 */
	
	private static void handleHasRelation() {
		if(g.nbNodes() > 0) {
			
			ecrire_console("Nom du noeud de depart:");
			
			String nodeName = recupere_string();
			
			/* si les noeuds existent */
			if(g.nodeExists(nodeName) != -1) {
				TRelation rel;
				
				ecrire_console("Quel type de relation souhaitez-vous retrouver?");
				
				/* affichage des attributs */
				int j = 0;
				while (j<TRelation.values().length) {
					/* on récupère une relation */
					rel = TRelation.values()[j];
					ecrire_console((j+1) + "- " + rel.getNom());
					
					/* on passe à la relation suivante */
					j++;
				}
				
				ecrire_console("Choix de commande (1 - " + j + ") :");
				
				int choix = recupere_int();
				
				while(!(choix >= 1 && choix <= j)) {
					ecrire_console("La commande saisie doit etre comprise entre 1 et " + j + ".");
					choix = recupere_int();
				}
				
				KnowledgeNode nodeRes;
				TRelation wanted = null;
				
				switch(choix) {
				case Constantes.CHOIX_SET_IS_A:
					wanted = TRelation.IS_A;
					break;
				case Constantes.CHOIX_SET_IS_MODEL_OF:
					wanted = TRelation.IS_MODEL_OF;
					break;
				case Constantes.CHOIX_SET_IS_QUAD_CORE_OF:
					wanted = TRelation.IS_QUAD_CORE_OF;
					break;
				default:
				}
				
				nodeRes = g.firstNodeWithRelation(nodeName, wanted);
				if(nodeRes != null) {
					ecrire_console("(" + nodeRes.getName() + " -> " + wanted + " -> " + nodeRes.getRelation(wanted).get(0).getName() + ")");
				}
				else {
					ecrire_console("Aucun noeud connecte a " + nodeName + " ne possède encore cette relation.");
				}
				
			}
			else {
				ecrire_console("Au moins un des deux noeuds n'existe pas.");
			}
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	private static void handleHasAttribute() {
		if(g.nbNodes() > 0) {
			
			ecrire_console("Nom du noeud de depart:");
			
			String nodeName = recupere_string();
			
			/* si les noeuds existent */
			if(g.nodeExists(nodeName) != -1) {
				TAttribute attr;
				
				ecrire_console("Quel type d'attribut souhaitez-vous retrouver?");
				
				/* affichage des attributs */
				int j = 0;
				while (j<TAttribute.values().length) {
					/* on récupère une relation */
					attr = TAttribute.values()[j];
					ecrire_console((j+1) + "- " + attr.getNom());
					
					/* on passe à la relation suivante */
					j++;
				}
				
				ecrire_console("Choix de commande (1 - " + j + ") :");
				
				int choix = recupere_int();
				
				while(!(choix >= 1 && choix <= j)) {
					ecrire_console("La commande saisie doit etre comprise entre 1 et " + j + ".");
					choix = recupere_int();
				}
				
				KnowledgeNode nodeRes;
				TAttribute wanted = null;
				
				switch(choix) {
				case Constantes.CHOIX_SET_RELEASE_DATE:
					wanted = TAttribute.RELEASE_DATE;
					break;
				case Constantes.CHOIX_SET_SOCKET:
					wanted = TAttribute.SOCKET;
					break;
				case Constantes.CHOIX_SET_RAM:
					wanted = TAttribute.RAM;
					break;
				case Constantes.CHOIX_SET_PRICE:
					wanted = TAttribute.PRICE;
					break;
				default:
				}
				
				nodeRes = g.firstNodeWithAttribute(nodeName, wanted);
				if(nodeRes != null) {
					ecrire_console("(" + nodeRes.getName() + " -> " + wanted + " : " + nodeRes.getAttribute(wanted) + ")");
				}
				else {
					ecrire_console("Aucun noeud connecte a " + nodeName + " ne possède encore cet attribut.");
				}
				
			}
			else {
				ecrire_console("Au moins un des deux noeuds n'existe pas.");
			}
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