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
public class BonDachat implements Serializable {
	@Id @GeneratedValue
	private Long id_bondachat;
	private String code_bon;
	private Date date;
	@OneToMany(mappedBy="bondachats", fetch= FetchType.LAZY)
	private Collection<BonCommande> boncommandes;
	private String texte;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;
	@ManyToOne
	@JoinColumn(name="id_demandeappro")
	private DemandeAppro demandeappros;
	public Long getId_bondachat() {
		return id_bondachat;
	}
	public void setId_bondachat(Long id_bondachat) {
		this.id_bondachat = id_bondachat;
	}
	public String getCode_bon() {
		return code_bon;
	}
	public void setCode_bon(String code_bon) {
		this.code_bon = code_bon;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public BonDachat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collection<BonCommande> getBoncommandes() {
		return boncommandes;
	}
	public void setBoncommandes(Collection<BonCommande> boncommandes) {
		this.boncommandes = boncommandes;
	}
	public BonDachat(String code_bon, Date date, String texte) {
		super();
		this.code_bon = code_bon;
		this.date = date;
		this.texte = texte;
	}
	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public BonDachat(String code_bon, Date date, String texte, Utilisateur utilisateurs) {
		super();
		this.code_bon = code_bon;
		this.date = date;
		this.texte = texte;
		this.utilisateurs = utilisateurs;
	}
	public DemandeAppro getDemandeappros() {
		return demandeappros;
	}
	public void setDemandeappros(DemandeAppro demandeappros) {
		this.demandeappros = demandeappros;
	}
	public BonDachat(String code_bon, Date date, String texte, Utilisateur utilisateurs, DemandeAppro demandeappros) {
		super();
		this.code_bon = code_bon;
		this.date = date;
		this.texte = texte;
		this.utilisateurs = utilisateurs;
		this.demandeappros = demandeappros;
	}
	
}
