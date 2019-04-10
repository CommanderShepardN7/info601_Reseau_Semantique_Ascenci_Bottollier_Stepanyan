package types;

public enum TAttribute {
	RELEASE_DATE("release date"),
	SOCKET("socket"),
	RAM("RAM"),
	PRICE("price");
	
	private String name;

	TAttribute (String name) {
		this.name = name;
	}
	
	public String getNom() {
		return this.name();
	}
}
