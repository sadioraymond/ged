package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produit implements Serializable{
	@Id @GeneratedValue
	private Long id_produit;
	private String code_produit;
	private String libelle;
	@OneToMany(mappedBy="produits", fetch= FetchType.LAZY)
	private Collection<DetailLivraison> detaillivraisons;
	@OneToMany(mappedBy="produits", fetch= FetchType.LAZY)
	private Collection<DetailFiche> detailfiches;
	@OneToMany(mappedBy="produits", fetch= FetchType.LAZY)
	private Collection<DetailCommande> detailcommandes;
	@ManyToOne
	@JoinColumn(name="id_categorie")
	private Categorie categories;
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Produit(String code_produit, String libelle) {
		super();
		this.code_produit = code_produit;
		this.libelle = libelle;
	}
	public Produit(String code_produit, String libelle, Categorie categories) {
		super();
		this.code_produit = code_produit;
		this.libelle = libelle;
		this.categories = categories;
	}
	public Categorie getCategories() {
		return categories;
	}
	public void setCategories(Categorie categories) {
		this.categories = categories;
	}
	public Long getId_produit() {
		return id_produit;
	}
	public void setId_produit(Long id_produit) {
		this.id_produit = id_produit;
	}
	public String getCode_produit() {
		return code_produit;
	}
	public void setCode_produit(String code_produit) {
		this.code_produit = code_produit;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Collection<DetailLivraison> getDetaillivraisons() {
		return detaillivraisons;
	}
	public void setDetaillivraisons(Collection<DetailLivraison> detaillivraisons) {
		this.detaillivraisons = detaillivraisons;
	}
	public Collection<DetailFiche> getDetailfiches() {
		return detailfiches;
	}
	public void setDetailfiches(Collection<DetailFiche> detailfiches) {
		this.detailfiches = detailfiches;
	}
	public Collection<DetailCommande> getDetailcommandes() {
		return detailcommandes;
	}
	public void setDetailcommandes(Collection<DetailCommande> detailcommandes) {
		this.detailcommandes = detailcommandes;
	}
	

}
