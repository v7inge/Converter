package ru.aconsultant.entity;

import java.util.Calendar;

import javax.persistence.*;


@Entity
@Table
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	
	@Column(name = "currency_id_1")
	private String currencyId1;
	
	@Column(name = "currency_id_2")
	private String currencyId2;
	
	@Column
	private float sum;
	
	@Column
	private float result;
	
	@Column
	private Calendar date;
	
	public Event() { }
	
	public Event(String currencyId1, String currencyId2, float sum, float result) {
		
		this.currencyId1 = currencyId1;
		this.currencyId2 = currencyId2;
		this.sum = sum;
		this.result = result;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		this.date = c;
	}

	public int getId() {
		return id;
	}

	public String getCurrencyId1() {
		return currencyId1;
	}

	public String getCurrencyId2() {
		return currencyId2;
	}

	public float getSum() {
		return sum;
	}

	public float getResult() {
		return result;
	}

	public Calendar getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCurrencyId1(String currencyId1) {
		this.currencyId1 = currencyId1;
	}

	public void setCurrencyId2(String currencyId2) {
		this.currencyId2 = currencyId2;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public void setResult(float result) {
		this.result = result;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
}
