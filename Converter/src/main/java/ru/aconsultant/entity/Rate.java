package ru.aconsultant.entity;

import java.util.Calendar;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Rate")
public class Rate {

	@Getter
    @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	
	@Getter
    @Setter
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_id")
	private Currency currency;
	
	@Getter
    @Setter
    @Column
    private float value;
	
	@Getter
    @Setter
    @Column
    private Calendar date;
	
	public Rate() {}
	
	public Rate(Currency currency, float value, Calendar date) {
		this.currency = currency;
		this.value = value;
		this.date = date;
	}
}
