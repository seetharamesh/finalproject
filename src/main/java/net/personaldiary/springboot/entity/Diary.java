package net.personaldiary.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*We are creating Diary JPA entity and that'll be mapped to RDBMS Table name = "diary" */
@Entity
@Table(name="diary")
public class Diary {
	
	private Diary() {}
	
	public Diary(String date,String entry) {
		this.date = date;
		this.entry = entry;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="entry")
	private String entry;
	
	@Column(name="date")
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "Diary [id=" + id + ", entry=" + entry + ", date=" + date + "]";
	}
	
}
