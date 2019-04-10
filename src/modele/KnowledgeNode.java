package modele;

import java.util.ArrayList;

import constantes.Constantes;
import types.TAttribute;
import types.TRelation;

/**********************************************************
 * 
 * 
 * 
 *********************************************************/

public class KnowledgeNode extends OrientedNode<KnowledgeNode> {
	
	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/
	
	protected ArrayList<KnowledgeNode> isA;
	protected ArrayList<KnowledgeNode> isModelOf;
	protected ArrayList<KnowledgeNode> isQuadCoreOf;
	
	protected String releaseDate;
	protected String socket;
	protected String ram;
	protected String price;

	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/

	public KnowledgeNode(String nodeName) {
		this(nodeName, Constantes.NOT_SET_RELEASEDATE, Constantes.NOT_SET_SOCKET, Constantes.NOT_SET_RAM, Constantes.NOT_SET_PRICE);
	}
	
	public KnowledgeNode(String nodeName, String releaseDate, String socket, String ram, String price) {
		setIsARelation(new ArrayList<KnowledgeNode>());
		setIsModelOfRelation(new ArrayList<KnowledgeNode>());
		setIsQuadCoreOfRelation(new ArrayList<KnowledgeNode>());
		setName(nodeName);
		setReleaseDate(releaseDate);
		setSocket(socket);
		setRam(ram);
		setPrice(price);
	}
	
	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/
	
	public ArrayList<KnowledgeNode> getIsARelation() {
		return this.isA;
	}
	
	public void setIsARelation(ArrayList<KnowledgeNode> isA) {
		this.isA = isA;
	}
	
	public ArrayList<KnowledgeNode> getIsModelOfRelation() {
		return this.isModelOf;
	}
	
	public void setIsModelOfRelation(ArrayList<KnowledgeNode> isModelOf) {
		this.isModelOf = isModelOf;
	}
	
	public ArrayList<KnowledgeNode> getIsQuadCoreOfRelation() {
		return this.isQuadCoreOf;
	}
	
	public void setIsQuadCoreOfRelation(ArrayList<KnowledgeNode> isQuadCoreOf) {
		this.isQuadCoreOf = isQuadCoreOf;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<KnowledgeNode> getRelation(TRelation relation) {
		switch(relation) {
		case IS_A:
			return getIsARelation();
		case IS_MODEL_OF:
			return getIsModelOfRelation();
		case IS_QUAD_CORE_OF:
			return getIsQuadCoreOfRelation();
		default:
			return (ArrayList<KnowledgeNode>) Constantes.NOT_SET_OBJECT;
		}
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSocket() {
		return socket;
	}

	public void setSocket(String socket) {
		this.socket = socket;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public void setAttribute(TAttribute attr, String value) {
		switch(attr) {
		case RELEASE_DATE:
			setReleaseDate(value);
			break;
		case SOCKET:
			setSocket(value);
			break;
		case RAM:
			setRam(value);
			break;
		case PRICE:
			setPrice(value);
			break;
		default:
		}
	}
	
	public Object getAttribute(TAttribute attr) {
		switch(attr) {
		case RELEASE_DATE:
			return getReleaseDate();
		case SOCKET:
			return getSocket();
		case RAM:
			return getRam();
		case PRICE:
			return getPrice();
		default:
			return Constantes.NOT_SET_OBJECT;
		}
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
	
	public boolean hasReleaseDate() {
		return getReleaseDate() != Constantes.NOT_SET_RELEASEDATE;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean hasSocket() {
		return getSocket() != Constantes.NOT_SET_SOCKET;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean hasRam() {
		return getRam() != Constantes.NOT_SET_RAM;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean hasPrice() {
		return getPrice() != Constantes.NOT_SET_PRICE;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean hasAttribute(TAttribute attr) {
		switch(attr) {
		case RELEASE_DATE:
			return hasReleaseDate();
		case SOCKET:
			return hasSocket();
		case RAM:
			return hasRam();
		case PRICE:
			return hasPrice();
		default:
			return false;
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean addIsARelation(KnowledgeNode node) {
		return this.isA.add(node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean addIsModelOfRelation(KnowledgeNode node) {
		return this.isModelOf.add(node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean addIsQuadCoreOfRelation(KnowledgeNode node) {
		return this.isQuadCoreOf.add(node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean addRelation (KnowledgeNode node, TRelation relation) {
		switch(relation) {
		case IS_A:
			return addIsARelation(node);
		case IS_MODEL_OF:
			return addIsModelOfRelation(node);
		case IS_QUAD_CORE_OF:
			return addIsQuadCoreOfRelation(node);
		default:
			return false;
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean removeIsARelation(KnowledgeNode node) {
		return this.isA.remove(node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean removeIsModelOfRelation(KnowledgeNode node) {
		return this.isModelOf.remove(node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean removeIsQuadCoreOfRelation(KnowledgeNode node) {
		return this.isQuadCoreOf.remove(node);
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean removeRelation (KnowledgeNode node, TRelation relation) {
		switch(relation) {
		case IS_A:
			return removeIsARelation(node);
		case IS_MODEL_OF:
			return removeIsModelOfRelation(node);
		case IS_QUAD_CORE_OF:
			return removeIsQuadCoreOfRelation(node);
		default:
			return false;
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean hasIsARelation() {
		return getIsARelation().size()>0;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean hasIsModelOfRelation() {
		return getIsModelOfRelation().size()>0;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean hasIsQuadCoreOfRelation() {
		return getIsQuadCoreOfRelation().size()>0;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public boolean hasRelation(TRelation relation) {
		switch(relation) {
		case IS_A:
			return hasIsARelation();
		case IS_MODEL_OF:
			return hasIsModelOfRelation();
		case IS_QUAD_CORE_OF:
			return hasIsQuadCoreOfRelation();
		default:
			return false;
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	public String toString () {
		String s = "";
		
		// attributes
		
		s += "(Nom:         " + getName();
		s += (hasSocket()? ",\n Socket:      " + getSocket() : "");
		s += (hasRam()? ",\n Ram:         " + getRam() : "");
		s += (hasPrice()? ",\n Price:       " + getPrice() + "€" : "");
		s += (hasReleaseDate()? ",\n ReleaseDate: " + getReleaseDate() : "");
		s += "):\n";
		
		int nbIsA = getIsARelation().size();
		int nbIsModelOf = getIsModelOfRelation().size();
		int nbIsQuadCore = getIsQuadCoreOfRelation().size();
		
		// relations
		
		if(nbIsA > 0 || nbIsModelOf > 0 || nbIsQuadCore > 0) {
			if(nbIsA > 0) {
				s += "    Is A => (";
				for(int j=0; j < nbIsA; j++) {
					s += "(" + getIsARelation().get(j).getName() + ")";
				}
				s += ")\n";
			}
			if(nbIsModelOf > 0) {
				s += "    Is Model Of => (";
				for(int j=0; j < nbIsModelOf; j++) {
					s += "(" + getIsModelOfRelation().get(j).getName() + ")";
				}
				s += ")\n";
			}
			if(nbIsQuadCore > 0) {
				s += "    Is Quad Core Of => (";
				for(int j=0; j < nbIsQuadCore; j++) {
					s += "(" + getIsQuadCoreOfRelation().get(j).getName() + ")";
				}
				s += ")\n";
			}
		} else {
			s += "    Aucune relation n'a encore été créée.\n";
		}
		
		return s;
	}
	
}
