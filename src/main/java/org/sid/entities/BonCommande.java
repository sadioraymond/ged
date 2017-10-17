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
public class BonCommande implements Serializable{
	@Id @GeneratedValue
	private Long id_boncommande;
	private String codebon;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;
	private Date date;
	@OneToMany(mappedBy="boncommandes", fetch= FetchType.LAZY)
	private Collection<DetailBon> detailbons;
	@OneToMany(mappedBy="boncommandes", fetch= FetchType.LAZY)
	private Collection<DetailCommande> detailcommandes;
	@OneToMany(mappedBy="boncommandes", fetch= FetchType.LAZY)
	private Collection<BonLivraison> bonlivraisons;
	@ManyToOne
	@JoinColumn(name="id_bondachat")
	private BonDachat bondachats;
	public Long getId_boncommande() {
		return id_boncommande;
	}
	public void setId_boncommande(Long id_boncommande) {
		this.id_boncommande = id_boncommande;
	}
	public String getCodebon() {
		return codebon;
	}
	public void setCodebon(String codebon) {
		this.codebon = codebon;
	}
	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BonCommande() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BonCommande(String codebon, Date date) {
		super();
		this.codebon = codebon;
		this.date = date;
	}
	public BonCommande(String codebon, Utilisateur utilisateurs, Date date) {
		super();
		this.codebon = codebon;
		this.utilisateurs = utilisateurs;
		this.date = date;
	}
	public Collection<DetailBon> getDetailbons() {
		return detailbons;
	}
	public void setDetailbons(Collection<DetailBon> detailbons) {
		this.detailbons = detailbons;
	}
	public Collection<DetailCommande> getDetailcommandes() {
		return detailcommandes;
	}
	public void setDetailcommandes(Collection<DetailCommande> detailcommandes) {
		this.detailcommandes = detailcommandes;
	}
	public BonCommande(String codebon, Utilisateur utilisateurs, Date date, BonDachat bondachats) {
		super();
		this.codebon = codebon;
		this.utilisateurs = utilisateurs;
		this.date = date;
		this.bondachats = bondachats;
	}
	
	
}
