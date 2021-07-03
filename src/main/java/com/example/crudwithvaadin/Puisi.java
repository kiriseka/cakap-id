package com.example.crudwithvaadin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Puisi {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 100)
	private String judulPuisi;

	@Column(length = 100000)
	private String isiPuisi;


	protected Puisi() {
	}

	public Puisi(String judulPuisi, String isiPuisi) {
		this.judulPuisi = judulPuisi;
		this.isiPuisi = isiPuisi;
	}

	public Long getId() {
		return id;
	}

	public String getJudulPuisi() {
		return judulPuisi;
	}

	public void setJudulPuisi(String firstName) {
		this.judulPuisi = firstName;
	}

	public String getIsiPuisi() {
		return isiPuisi;
	}

	public void setIsiPuisi(String lastName) {
		this.isiPuisi = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, judulPuisi='%s', isiPuisi='%s']", id,
				judulPuisi, isiPuisi);
	}

}
