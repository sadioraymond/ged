package org.sid.metier;



import java.util.List;

import org.sid.entities.BonCommande;
import org.sid.entities.BonDachat;
import org.sid.entities.BonLivraison;
import org.sid.entities.BonSortie;
import org.sid.entities.Categorie;
import org.sid.entities.DemandeAchat;
import org.sid.entities.DemandeAppro;
import org.sid.entities.DemandePrestation;
import org.sid.entities.DetailBon;
import org.sid.entities.DetailCommande;
import org.sid.entities.DetailFiche;
import org.sid.entities.DetailLivraison;
import org.sid.entities.Documents;
import org.sid.entities.DroitAttribues;
import org.sid.entities.DroitDacces;
import org.sid.entities.Entreprise;
import org.sid.entities.FicheSortie;
import org.sid.entities.Fournisseur;
import org.sid.entities.Personne;
import org.sid.entities.Poste;
import org.sid.entities.Produit;
import org.sid.entities.Roles;
import org.sid.entities.Service;
import org.sid.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

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
	public void savedemandeappro(String titre,String texte,Long id_demandeachat);
	public void validedeamandeachat(Long id_demande,Boolean valide);
	public Page<DemandeAppro> listdemandeappro(int page,int size);
	public DemandeAppro findonedemandeappro(Long id_demandeappro);
	public void savebonachat(String titre, String texte, Long id_demandeappro);
	public void updateaccorddemandeappro(Long id_demandeappro, int accord);
	public Page<BonDachat> listbonahat(int page,int size);
	public BonDachat findonebonachat(Long id_bondachat);
	public void validerbonachat(Long id_bonachat);
	public List<BonDachat> allbondachat();
	public List<Produit> allproduit();
	public void savecommande(List<String> tamp,String titre,Long id_bondachat,Long optionsListIds);
	public BonCommande dernierbon();
	public List<DetailCommande> detailbycommande(Long id_bon);
	public Page<BonCommande> listBonCommande(int page,int size);
	public Page<DetailCommande> listdetailBonCommande(int page,int size);
	public BonCommande findoneboncommande(Long id_boncommande);
	public void supprcommande(Long id_boncommande);
	public void supprdetailcommande(Long id_detailcommande);
	public void updatecommande(Long id_commande,String titre, Long id_bondachat,List<String> tamp,List<Long> iddetail,Long id_fournisseur);
	public List<Produit> listprodpourmodif(List<String> listbi);
	public void savemodifcommande(List<String> tamp,BonCommande bcom);
	public List<DemandeAppro> listappro();
	public void savefichesortie(List<String> tamp,String libelle,Long optionsListId);
	public DetailFiche dernierdetailfiche();
	public FicheSortie dernierefiche();
	public List<DetailFiche> detailbyfiche(Long id_fiche);
	public BonSortie bondesorbyfiche(Long id_detail);
	public List<BonSortie> bonbydetailfice(List<DetailFiche> detfiche);
	public Page<FicheSortie> listfiche(int page,int size);
	public FicheSortie findonefichesortie(Long id_fichesortie);
	public void supprbonsortie(Long id_fiche);
	public void supprdetailfiche(Long id_detailfiche);
	public void updatefiche(Long id_fiche,String libelle, Long id_demandeapprot,List<String> tamp,List<Long> iddetail);
	public void savePersonne(String adresse,String tel,String email,String nom);
	public void saveEntreprise(String adresse,String tel,String email,String nom);
	public Page<Fournisseur> listFournisseur(int page,int size);
	public Fournisseur findonefournisseur(Long id_fournisseur);
	public Entreprise findoneentreprise(Long id_fournisseur);
	public Personne findonepersonne(@Param("x")Long id_fournisseur);
	public void updatePersonne(Long id_four,String adresse,String tel,String email,String nom);
	public void updateEntreprise(Long id_four,String adresse,String tel,String email,String nom);
	public void deletefournisseur(Long id_four);
	public void deletefourni(Long id_four);
	public void updatesavepersonne(Long id_four,String adresse,String tel,String email,String nom);
	public void updatesaveentreprise(Long id_four,String adresse,String tel,String email,String nom);
	public Personne findonepersonnesup(Long id_fournisseur);
	public Entreprise findoneentreprisesup(Long id_fournisseur);
	public void savemodifFiche(List<String> tamp,FicheSortie fic,Long optionsListId);
	public List<Fournisseur> allFournisseurs();
	public List<BonCommande> allCommandes();
	public void savelivraison(List<String> tamp,String titre,Long id_boncommande,Long id_fourni);
	public BonLivraison dernierbonliv();
	public List<DetailLivraison> detailbyLivraison(Long id_bon);
	public Page<BonLivraison> listBonLivraison(int page,int size);
	public BonLivraison findonelivraison(Long id_bonlivraison);
	public void deletelivraison(Long id_bonliv);
	public void deletedetailliv(Long id_detail);
	public DetailLivraison findonedetaillivraison(Long id_detail);
	public void updatelivraison(Long idbon,List<String> tamp,String titre,Long id_boncommande,Long id_fourni,List<Long> iddetail);
	public void savemodiflivraison(List<String> tamp,BonLivraison bliv);
	public DetailBon detailbyboncommande(Long id_bon);
	public void savedemandeprestation(String titre,String objet,String demandeur,String nomfichier);
	public int countdoc(String typedoc);
	public DemandePrestation findonedp(Long iddoc);
	public Page<DemandePrestation> listPrestation(String typedoc,int page,int size);
	public DemandePrestation findoneprestation(Long iddoc,String typedoc);
	public void deleteprest(Long iddoc);
	public List<DroitAttribues> listdroitattr(Long iddoc);
	public List<Utilisateur> listutibydroi(List<DroitAttribues> list);
	public void updatedemandeprest(Long iddoc,String titre,String objet,String demandeur,String nomfic,String stringArray[],List<Long> iddetail);
	public void saveuser(String nom,String prenom,String email,String adresse,String username,
			String password,String password1,String tel);
	public Utilisateur verifusername(String username);
	public Utilisateur verifemail(String email);
	public Page<Utilisateur> listusernonval(int page,int size);
	public Page<Utilisateur> listuserval(int page,int size);
	public List<Roles> allroles();
	public void saveuserrole(Long idroles,Long id_user);
	public void activeruser(Long id_user);
}
