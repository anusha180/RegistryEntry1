package edu.umsl.esi.domain;

public class RegistryEntry {

	private int id;
	private String scope;
	private String name;
	private String value;
	private int confidential;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getConfidential() {
		return confidential;
	}

	public void setConfidential(int confidential) {
		this.confidential = confidential;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
