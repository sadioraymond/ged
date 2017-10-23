package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie implements Serializable {
	@Id @GeneratedValue
	private Long id_categorie;
	private String code_categorie;
	private String libelle;
	@OneToMany(mappedBy="categories", fetch= FetchType.LAZY)
	private Collection<Produit> produits;
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(Long id_categorie) {
		this.id_categorie = id_categorie;
	}
	public String getCode_categorie() {
		return code_categorie;
	}
	public void setCode_categorie(String code_categorie) {
		this.code_categorie = code_categorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Categorie(String code_categorie, String libelle) {
		super();
		this.code_categorie = code_categorie;
		this.libelle = libelle;
	}
	public Collection<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}
	
}
