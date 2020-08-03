package ru.aconsultant.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "Rate")
public class Rate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
    @Column
    private float value;
	
    @Column
    private Calendar date;
	
    public Rate() {}
	
	public Rate(Currency currency, float value, Calendar date) {
		this.currency = currency;
		this.value = value;
		this.date = date;
	}
    
	public int getId() {
		return id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public float getValue() {
		return value;
	}

	public Calendar getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

}
