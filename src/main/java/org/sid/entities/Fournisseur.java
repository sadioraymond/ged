package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_Fournisseur",discriminatorType=DiscriminatorType.STRING,length=4)
public abstract class Fournisseur implements Serializable {
	@Id @GeneratedValue
	private Long id_fournisseur;
	private String code_fournisseur;
	private String tel;
	private String adresse;
	private String email;
	@OneToMany(mappedBy="fournisseurs", fetch= FetchType.LAZY)
	private Collection<DetailBon> detailbons;
	@OneToMany(mappedBy="fournisseurs", fetch= FetchType.LAZY)
	private Collection<BonLivraison> bonlivraisons;
	public Collection<BonLivraison> getBonlivraisons() {
		return bonlivraisons;
	}
	public void setBonlivraisons(Collection<BonLivraison> bonlivraisons) {
		this.bonlivraisons = bonlivraisons;
	}
	public Long getId_fournisseur() {
		return id_fournisseur;
	}
	public void setId_fournisseur(Long id_fournisseur) {
		this.id_fournisseur = id_fournisseur;
	}
	public String getCode_fournisseur() {
		return code_fournisseur;
	}
	public void setCode_fournisseur(String code_fournisseur) {
		this.code_fournisseur = code_fournisseur;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Fournisseur(String code_fournisseur, String tel, String adresse, String email) {
		super();
		this.code_fournisseur = code_fournisseur;
		this.tel = tel;
		this.adresse = adresse;
		this.email = email;
	}
	public Fournisseur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collection<DetailBon> getDetailbons() {
		return detailbons;
	}
	public void setDetailbons(Collection<DetailBon> detailbons) {
		this.detailbons = detailbons;
	}
	

}
