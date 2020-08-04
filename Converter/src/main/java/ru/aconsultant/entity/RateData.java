package ru.aconsultant.entity;

import java.util.Calendar;
import javax.persistence.*;

@Entity
public class RateData {
	
	@Id
	@Column
	private String name;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
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

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
