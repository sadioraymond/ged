package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DetailCommande implements Serializable {
	@Id @GeneratedValue
	private Long id_detailcommande;
	@ManyToOne
	@JoinColumn(name="id_bon")
	private BonCommande boncommandes;
	@ManyToOne
	@JoinColumn(name="id_produit")
	private Produit produits;
	private int qte;
	public DetailCommande() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailCommande(BonCommande boncommandes, Produit produits, int qte) {
		super();
		this.boncommandes = boncommandes;
		this.produits = produits;
		this.qte = qte;
	}
	public Long getId_detailcommande() {
		return id_detailcommande;
	}
	public void setId_detailcommande(Long id_detailcommande) {
		this.id_detailcommande = id_detailcommande;
	}
	public BonCommande getBoncommandes() {
		return boncommandes;
	}
	public void setBoncommandes(BonCommande boncommandes) {
		this.boncommandes = boncommandes;
	}
	public Produit getProduits() {
		return produits;
	}
	public void setProduits(Produit produits) {
		this.produits = produits;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	

}
