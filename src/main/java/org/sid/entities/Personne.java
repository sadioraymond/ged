package org.sid.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Pers")
public class Personne extends Fournisseur{
	private String nompersonne;

	public String getNompersonne() {
		return nompersonne;
	}

	public void setNompersonne(String nompersonne) {
		this.nompersonne = nompersonne;
	}

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personne(String code_fournisseur, String tel, String adresse, String email, String nompersonne) {
		super(code_fournisseur, tel, adresse, email);
		this.nompersonne = nompersonne;
	}
	
}
