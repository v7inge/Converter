package ru.aconsultant.entity;

import java.util.Calendar;
import javax.persistence.*;

@Entity
public class RateData {

	@Id
	@Column
	private String name;
	
	@Column
	private Calendar date;
	
	@Column
	private float value;
	
	public RateData() { }
	
	public RateData(String name, float value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}	
}
