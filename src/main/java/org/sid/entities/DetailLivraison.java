package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DetailLivraison implements Serializable {
	@Id @GeneratedValue
	private Long id_detaillivraison;
	private Double prix;
	private int qte;
	@ManyToOne
	@JoinColumn(name="id_produit")
	private Produit produits;
	@ManyToOne
	@JoinColumn(name="id_bonlivraison")
	private BonLivraison bonlivraisons;
	private int etat;
	private Date date;
	public DetailLivraison() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailLivraison(Double prix, int qte, Produit produits, BonLivraison bonlivraisons) {
		super();
		this.prix = prix;
		this.qte = qte;
		this.produits = produits;
		this.bonlivraisons = bonlivraisons;
	}
	
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getId_detaillivraison() {
		return id_detaillivraison;
	}
	public void setId_detaillivraison(Long id_detaillivraison) {
		this.id_detaillivraison = id_detaillivraison;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public Produit getProduits() {
		return produits;
	}
	public void setProduits(Produit produits) {
		this.produits = produits;
	}
	public BonLivraison getBonlivraisons() {
		return bonlivraisons;
	}
	public void setBonlivraisons(BonLivraison bonlivraisons) {
		this.bonlivraisons = bonlivraisons;
	}
	
}
