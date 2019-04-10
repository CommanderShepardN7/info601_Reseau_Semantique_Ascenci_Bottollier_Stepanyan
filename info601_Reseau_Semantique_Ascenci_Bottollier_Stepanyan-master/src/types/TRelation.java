package types;

public enum TRelation {
	IS_A("is a"),
	IS_MODEL_OF("is model of"),
	IS_QUAD_CORE_OF("is quad core of");
	
	private String name;

	TRelation (String name) {
		this.name = name;
	}
	
	public String getNom() {
		return this.name();
	}
}
