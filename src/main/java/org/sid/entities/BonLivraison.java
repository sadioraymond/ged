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
public class BonLivraison implements Serializable {
	@Id @GeneratedValue
	private Long id_bonlivraison;
	private String code_bonlivraison;
	private Date date;
	@OneToMany(mappedBy="bonlivraisons", fetch= FetchType.LAZY)
	private Collection<DetailLivraison> detaillivraisons;
	@ManyToOne
	@JoinColumn(name="id_boncommande")
	private BonCommande boncommandes;
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private Fournisseur fournisseurs;
	public BonLivraison() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BonLivraison(String code_bonlivraison, Date date) {
		super();
		this.code_bonlivraison = code_bonlivraison;
		this.date = date;
	}
	
	public BonLivraison(String code_bonlivraison, Date date, BonCommande boncommandes, Fournisseur fournisseurs) {
		super();
		this.code_bonlivraison = code_bonlivraison;
		this.date = date;
		this.boncommandes = boncommandes;
		this.fournisseurs = fournisseurs;
	}
	public Fournisseur getFournisseurs() {
		return fournisseurs;
	}
	public void setFournisseurs(Fournisseur fournisseurs) {
		this.fournisseurs = fournisseurs;
	}
	public Long getId_bonlivraison() {
		return id_bonlivraison;
	}
	public void setId_bonlivraison(Long id_bonlivraison) {
		this.id_bonlivraison = id_bonlivraison;
	}
	public String getCode_bonlivraison() {
		return code_bonlivraison;
	}
	public void setCode_bonlivraison(String code_bonlivraison) {
		this.code_bonlivraison = code_bonlivraison;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Collection<DetailLivraison> getDetaillivraisons() {
		return detaillivraisons;
	}
	public void setDetaillivraisons(Collection<DetailLivraison> detaillivraisons) {
		this.detaillivraisons = detaillivraisons;
	}
	public BonCommande getBoncommandes() {
		return boncommandes;
	}
	public void setBoncommandes(BonCommande boncommandes) {
		this.boncommandes = boncommandes;
	}
	public BonLivraison(String code_bonlivraison, Date date, BonCommande boncommandes) {
		super();
		this.code_bonlivraison = code_bonlivraison;
		this.date = date;
		this.boncommandes = boncommandes;
	}
	
	
	
}
