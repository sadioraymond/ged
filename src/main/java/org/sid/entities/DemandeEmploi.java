package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DE")
public class DemandeEmploi extends Documents {
	private String codeDE;

	public String getCodeDE() {
		return codeDE;
	}

	public void setCodeDE(String codeDE) {
		this.codeDE = codeDE;
	}

	public DemandeEmploi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DemandeEmploi(Date datecreation, Utilisateur usercreateur, String codeDE) {
		super(datecreation, usercreateur);
		this.codeDE = codeDE;
	}
	
}
