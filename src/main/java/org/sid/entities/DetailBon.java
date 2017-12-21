package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DetailBon implements Serializable {
	@Id @GeneratedValue
	private Long id_detail;
	@ManyToOne
	@JoinColumn(name="id_bon")
	private BonCommande boncommandes;
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private Fournisseur fournisseurs;
	private Date date;
	private int etat;
	public DetailBon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailBon(BonCommande boncommandes, Fournisseur fournisseurs, Date date) {
		super();
		this.boncommandes = boncommandes;
		this.fournisseurs = fournisseurs;
		this.date = date;
	}
	public Long getId_detail() {
		return id_detail;
	}
	public void setId_detail(Long id_detail) {
		this.id_detail = id_detail;
	}
	
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public BonCommande getBoncommandes() {
		return boncommandes;
	}
	public void setBoncommandes(BonCommande boncommandes) {
		this.boncommandes = boncommandes;
	}
	public Fournisseur getFournisseurs() {
		return fournisseurs;
	}
	public void setFournisseurs(Fournisseur fournisseurs) {
		this.fournisseurs = fournisseurs;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
