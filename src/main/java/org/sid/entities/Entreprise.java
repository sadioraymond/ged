package org.sid.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Entr")
public class Entreprise extends Fournisseur{
	private String nomEntreprise;

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String code_fournisseur, String tel, String adresse, String email, String nomEntreprise) {
		super(code_fournisseur, tel, adresse, email);
		this.nomEntreprise = nomEntreprise;
	}
	
}
