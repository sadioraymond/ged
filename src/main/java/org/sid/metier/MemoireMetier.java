package org.sid.metier;



import java.util.List;

import org.sid.entities.Categorie;
import org.sid.entities.DemandeAchat;
import org.sid.entities.Documents;
import org.sid.entities.DroitDacces;
import org.sid.entities.Poste;
import org.sid.entities.Produit;
import org.sid.entities.Service;
import org.sid.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemoireMetier {
	public void savecategorie(String libelle);
	public void saveproduit(String libelle,Long id_categorie);
	public Page<Categorie> listCategorie(int page,int size);
	public Page<Produit> listProduit(int page,int size);
	public Categorie findonecategorie(Long id_categorie);
	public void deletecategorie(Long id_categorie);
	public void modifiercategorie(Long id_categorie,String libelle);
	public List<Categorie> allcategorie();
	public Produit findoneproduit(Long id_produit);
	public void modifierproduit(Long id_produit,String libelle,Long id_categorie);
	public void saveService(String libelle);
	public Page<Service> listService(int page,int size);
	public void modifierservice(Long id_service,String libelle);
	public Service findoneservice(Long id_service);
	public List<Service> allservice();
	public void saveposte(String libelle,Long id_service);
	public Page<Poste> listPoste(int page,int size);
	public Poste findoneposte(Long id_poste);
	public void modifierposte(Long id_poste, String libelle, Long id_service);
	public List<Utilisateur> allsuser();
	public Page<Utilisateur> listUser(int page,int size);
	public void savedemandeachat(String titre, String text);
	public Documents dernierdoc();
	public Page<DroitDacces> listdroit(int page,int size);
	public void savedroitattribue(Long iddoc,String listbi[]);
	public Documents findonedoc(Long id_doc);
	public DroitDacces findonedroit(Long id_droit);
	public Utilisateur findoneuser(Long id_user);
	public Page<DemandeAchat> listdemandeahat(int page,int size);
	public DemandeAchat findonedemandeachat(Long id_demandeachat);
}
