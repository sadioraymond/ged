package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "DA")
public class DemandeAchat extends Documents{
	private String code_demandeachat;
	private Boolean valide;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;
	@OneToMany(mappedBy="demandeachats", fetch= FetchType.LAZY)
	private Collection<DemandeAppro> demandeappros;
	private String textedemande;
	public String getCode_demandeachat() {
		return code_demandeachat;
	}
	
	public String getTextedemande() {
		return textedemande;
	}

	public void setTextedemande(String textedemande) {
		this.textedemande = textedemande;
	}

	public void setCode_demandeachat(String code_demandeachat) {
		this.code_demandeachat = code_demandeachat;
	}
	public Boolean getValide() {
		return valide;
	}
	public void setValide(Boolean valide) {
		this.valide = valide;
	}
	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public DemandeAchat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collection<DemandeAppro> getDemandeappros() {
		return demandeappros;
	}
	public void setDemandeappros(Collection<DemandeAppro> demandeappros) {
		this.demandeappros = demandeappros;
	}
	public DemandeAchat(Date datecreation, Utilisateur usercreateur, String code_demandeachat, Boolean valide,
			Utilisateur utilisateurs) {
		super(datecreation, usercreateur);
		this.code_demandeachat = code_demandeachat;
		this.valide = valide;
		this.utilisateurs = utilisateurs;
	}

}
