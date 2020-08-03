package ru.aconsultant.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_id_1")
	private Currency currency1;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_id_2")
	private Currency currency2;
	
	@Column
	private float sum;
	
	@Column
	private float result;
	
	@Column
	private Calendar date;
	
	public Event() { }
	
	public Event(Currency currency1, Currency currency2, float sum, float result) {
		
		this.currency1 = currency1;
		this.currency2 = currency2;
		this.sum = sum;
		this.result = result;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		this.date = c;
	}
	

	public String getDateAsString() {
		
		Date date = this.getDate().getTime();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return format.format(date);
	}
	
	public int getId() {
		return id;
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

	public void setSum(float sum) {
		this.sum = sum;
	}

	public void setResult(float result) {
		this.result = result;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Currency getCurrency1() {
		return currency1;
	}

	public Currency getCurrency2() {
		return currency2;
	}

	public void setCurrency1(Currency currency1) {
		this.currency1 = currency1;
	}

	public void setCurrency2(Currency currency2) {
		this.currency2 = currency2;
	}
	
}
