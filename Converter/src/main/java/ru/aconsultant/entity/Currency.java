package ru.aconsultant.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "Currency")
public class Currency {

	
	@Id
	@Column
	private String id;
	
	@Column
	private String numcode;
	
	@Column
	private String charcode;
	
	@Column
	private String name;

	@Transient
	private float value;
	
    @OneToMany(mappedBy = "currency", fetch = FetchType.LAZY)
    private Collection<Rate> rates;
    
	
	public Currency() {}
	
	
	public Currency(String id, String numcode, String charcode, String name, float value) {
		
		this.id = id;
		this.numcode = numcode;
		this.charcode = charcode;
		this.name = name;
		this.value = value;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumcode() {
		return numcode;
	}

	public void setNumcode(String numcode) {
		this.numcode = numcode;
	}

	public String getCharcode() {
		return charcode;
	}

	public void setCharcode(String charcode) {
		this.charcode = charcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
}
