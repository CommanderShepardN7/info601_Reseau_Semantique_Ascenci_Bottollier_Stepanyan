package modele;

import java.util.ArrayList;
import java.util.Date;

import constantes.Constantes;
import types.TRelation;

/**********************************************************
 * 
 * 
 * 
 *********************************************************/

public class KnowledgeNode extends OrientedNode {
	
	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/
	
	protected ArrayList<KnowledgeNode> isA;
	protected ArrayList<KnowledgeNode> isModelOf;
	protected ArrayList<KnowledgeNode> isQuadCoreOf;
	
	protected Date releaseDate;
	protected String socket;
	protected String ram;
	protected int price;

	/**********************************************************
	 * 
	 * 
	 * 
	 *********************************************************/

	public KnowledgeNode(String nodeName) {
		this(nodeName, Constantes.NOT_SET_RELEASEDATE, Constantes.NOT_SET_SOCKET, Constantes.NOT_SET_RAM, Constantes.NOT_SET_PRICE);
	}
	
	public KnowledgeNode(String nodeName, Date releaseDate, String socket, String ram, int price) {
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
	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
		public int hasRelation(KnowledgeNode node) {
		int hasARelation = -1;
		
		for(int i = 0; i < isA.size(); i++ ) {
			if(isA.get(i).equals(node)) {
				hasARelation = 0;		
			}		
		}
		
		for(int i = 0; i < isModelOf.size(); i++ ) {
			if(isModelOf.get(i).equals(node)) {
				hasARelation = 1;		
			}
		}
		
		for(int i = 0; i < isQuadCoreOf.size(); i++ ) {
			if(isQuadCoreOf.get(i).equals(node)) {
				hasARelation = 2;		
			}
		}
		return hasARelation;
	}
	
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
	
}
