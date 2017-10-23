package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DetailFiche implements Serializable{
	@Id @GeneratedValue
	private Long id_detailfiche;
	private Date date;
	private int qte;
	@ManyToOne
	@JoinColumn(name="id_fiche")
	private FicheSortie fichesorties;
	@ManyToOne
	@JoinColumn(name="id_produit")
	private Produit produits;
	@OneToMany(mappedBy="detailfiches", fetch= FetchType.LAZY)
	private Collection<BonSortie> bonsorties;
	public DetailFiche() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailFiche(Date date, int qte, FicheSortie fichesorties, Produit produits) {
		super();
		this.date = date;
		this.qte = qte;
		this.fichesorties = fichesorties;
		this.produits = produits;
	}
	public Long getId_detailfiche() {
		return id_detailfiche;
	}
	public void setId_detailfiche(Long id_detailfiche) {
		this.id_detailfiche = id_detailfiche;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public FicheSortie getFichesorties() {
		return fichesorties;
	}
	public void setFichesorties(FicheSortie fichesorties) {
		this.fichesorties = fichesorties;
	}
	public Produit getProduits() {
		return produits;
	}
	public void setProduits(Produit produits) {
		this.produits = produits;
	}
	public Collection<BonSortie> getBonsorties() {
		return bonsorties;
	}
	public void setBonsorties(Collection<BonSortie> bonsorties) {
		this.bonsorties = bonsorties;
	}
	
}
