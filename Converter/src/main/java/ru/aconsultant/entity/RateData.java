package ru.aconsultant.entity;

import java.util.Calendar;
import javax.persistence.*;

@Entity
public class RateData {
	
	@Id
	@Column(name = "currency_id")
	private String currencyId;
	
	@Column
	private String name;
	
	/*@OneToOne(optional = false, mappedBy = "rateData")
	@JoinColumn(name = "currency_id")
    private Currency currency;*/
	
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
	
	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
}
