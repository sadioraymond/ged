package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DP")
public class DemandePrestation extends Documents {
	private String demandeur;
	private String objet;
	private String codeDP;
	public DemandePrestation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDemandeur() {
		return demandeur;
	}
	public void setDemandeur(String demandeur) {
		this.demandeur = demandeur;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getCodeDP() {
		return codeDP;
	}
	public void setCodeDP(String codeDP) {
		this.codeDP = codeDP;
	}
	public DemandePrestation(Date datecreation, Utilisateur usercreateur, String demandeur, String objet,
			String codeDP) {
		super(datecreation, usercreateur);
		this.demandeur = demandeur;
		this.objet = objet;
		this.codeDP = codeDP;
	}
	
}
