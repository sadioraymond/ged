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
public class DemandeAppro implements Serializable{
	@Id @GeneratedValue
	private Long id_demandeappro;
	private String code_demande;
	private String texte;
	private Date date;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;
	@OneToMany(mappedBy="demandeappros", fetch= FetchType.LAZY)
	private Collection<BonDachat> bondachats;
	public Long getId_demandeappro() {
		return id_demandeappro;
	}
	public void setId_demandeappro(Long id_demandeappro) {
		this.id_demandeappro = id_demandeappro;
	}
	public String getCode_demande() {
		return code_demande;
	}
	public void setCode_demande(String code_demande) {
		this.code_demande = code_demande;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public DemandeAppro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DemandeAppro(String code_demande, String texte, Date date, Utilisateur utilisateurs) {
		super();
		this.code_demande = code_demande;
		this.texte = texte;
		this.date = date;
		this.utilisateurs = utilisateurs;
	}
	public Collection<BonDachat> getBondachats() {
		return bondachats;
	}
	public void setBondachats(Collection<BonDachat> bondachats) {
		this.bondachats = bondachats;
	}
	
}
