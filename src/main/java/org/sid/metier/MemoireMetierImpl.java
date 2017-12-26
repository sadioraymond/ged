package org.sid.metier;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sid.dao.BonCommandeRepository;
import org.sid.dao.BonDachatRepository;
import org.sid.dao.BonLivraisonRepository;
import org.sid.dao.BonSortieRepository;
import org.sid.dao.CategorieRepository;
import org.sid.dao.DemandeAchatRepository;
import org.sid.dao.DemandeApproRepository;
import org.sid.dao.DetailBonRepository;
import org.sid.dao.DetailCommandeRepository;
import org.sid.dao.DetailFicheRepository;
import org.sid.dao.DetailLivraisonRepository;
import org.sid.dao.DocumentsRepository;
import org.sid.dao.DroitAttribuesRepository;
import org.sid.dao.DroitDaccesRepository;
import org.sid.dao.FicheSortieRepository;
import org.sid.dao.FournisseurRepository;
import org.sid.dao.PosteRepository;
import org.sid.dao.ProduitRepository;
import org.sid.dao.RolesRepository;
import org.sid.dao.ServiceRepository;
import org.sid.dao.User_RolesRepository;
import org.sid.dao.UtilisateurRepository;
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
import org.sid.entities.Users_Roles;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//pour que spring puisse instancier cette classe au demarrage
	@Service
	//pour demander a spring de gerer les transactions au niveau de la couche metier
	@Transactional
public class MemoireMetierImpl implements MemoireMetier{
	@Autowired
	private CategorieRepository categorierepository;
	@Autowired
	private ProduitRepository produitrepository;
	@Autowired
	private ServiceRepository servicerepo;
	@Autowired
	private PosteRepository posterepo;
	@Autowired
	private UtilisateurRepository userrepo;
	@Autowired
	private DocumentsRepository docrepo;
	@Autowired
	private DemandeAchatRepository demachrepo;
	@Autowired
	private DroitDaccesRepository droitaccerepo;
	@Autowired
	private DroitAttribuesRepository droitattrrepo;
	@Autowired
	private DemandeApproRepository demapprorepo;
	@Autowired
	private BonDachatRepository bonacharepo;
	@Autowired
	private BonCommandeRepository boncomrepo;
	@Autowired
	private DetailCommandeRepository detcomrepo;
	@Autowired
	private FicheSortieRepository ficsortrepo;
	@Autowired
	private DetailFicheRepository detficsort;
	@Autowired
	private BonSortieRepository bonsortrepo;
	@Autowired
	private FournisseurRepository fourrepo;
	@Autowired
	private BonLivraisonRepository blrepo;
	@Autowired
	private DetailLivraisonRepository detlivrepo;
	@Autowired
	private DetailBonRepository detbonrepo;
	@Autowired
	private RolesRepository rolerepo;
	@Autowired
	private User_RolesRepository userrolerepo;
	@Override
	public void savecategorie(String libelle) {
		Long nbr=categorierepository.count()+1;
		String code_categorie="Cat"+nbr;
		Categorie cat=new Categorie(code_categorie,libelle);
		categorierepository.save(cat);
	}
	/*@Override
	public Page<Categorie> listCategorie(int page, int size) {
		// TODO Auto-generated method stub
		return categorierepository.listCategorie(new PageRequest(page, size));
	}*/
	@Override
	public void saveproduit(String libelle,Long id_categorie) {
		// TODO Auto-generated method stub
		Long nbr=produitrepository.count()+1;
		String code_produit="Prod"+nbr;
		Categorie cat=new Categorie();
		cat=findonecategorie(id_categorie);
		Produit prod=new Produit(code_produit, libelle,cat);
		produitrepository.save(prod);
	}
	@Override
	public Page<Categorie> listCategorie(int page, int size) {
		// TODO Auto-generated method stub
		return categorierepository.findAll(new PageRequest(page, size));
	}
	@Override
	public Page<Produit> listProduit(int page, int size) {
		// TODO Auto-generated method stub
		return produitrepository.findAll(new PageRequest(page, size));
	}
	@Override
	public Categorie findonecategorie(Long id_categorie) {
		// TODO Auto-generated method stub
		Categorie cat=new Categorie();
		cat=categorierepository.findOne(id_categorie);
		return cat;
		
	}
	@Override
	public void deletecategorie(Long id_categorie) {
		// TODO Auto-generated method stub
		Categorie cat=new Categorie();
		cat=findonecategorie(id_categorie);
		categorierepository.delete(cat);
		
	}
	@Override
	public void modifiercategorie(Long id_categorie, String libelle) {
		// TODO Auto-generated method stub
		Categorie cat=new Categorie();
		cat=findonecategorie(id_categorie);
		cat.setLibelle(libelle);
		categorierepository.save(cat);
	}
	@Override
	public List<Categorie> allcategorie() {
		// TODO Auto-generated method stub
		List<Categorie> cat;
		cat=categorierepository.findAll();
		return cat;
	}
	@Override
	public Produit findoneproduit(Long id_produit) {
		// TODO Auto-generated method stub
		Produit prod=new Produit();
		prod=produitrepository.findOne(id_produit);
		return prod;
	}
	@Override
	public void modifierproduit(Long id_produit, String libelle, Long id_categorie) {
		// TODO Auto-generated method stub
		Categorie cat=new Categorie();
		cat=findonecategorie(id_categorie);
		Produit prod=new Produit();
		prod=findoneproduit(id_produit);
		prod.setCategories(cat);
		prod.setLibelle(libelle);
		produitrepository.save(prod);
	}
	@Override
	public void saveService(String libelle) {
		// TODO Auto-generated method stub
		Long nbr=servicerepo.count()+1;
		String codeservice="Ser"+nbr;
		org.sid.entities.Service serv= new org.sid.entities.Service(codeservice,libelle);
		servicerepo.save(serv);
	}
	@Override
	public Page<org.sid.entities.Service> listService(int page, int size) {
		// TODO Auto-generated method stub
		return servicerepo.findAll(new PageRequest(page, size));
	}
	@Override
	public void modifierservice(Long id_service, String libelle) {
		// TODO Auto-generated method stub
		org.sid.entities.Service cat=new org.sid.entities.Service();
		cat=findoneservice(id_service);
		cat.setLibelle(libelle);
		servicerepo.save(cat);
	}
	@Override
	public org.sid.entities.Service findoneservice(Long id_service) {
		// TODO Auto-generated method stub
		org.sid.entities.Service prod=new org.sid.entities.Service();
		prod=servicerepo.findOne(id_service);
		return prod;
	}
	@Override
	public List<org.sid.entities.Service> allservice() {
		// TODO Auto-generated method stub
		List<org.sid.entities.Service> cat;
		cat=servicerepo.findAll();
		return cat;
	}
	@Override
	public void saveposte(String libelle, Long id_service) {
		// TODO Auto-generated method stub
		Long nbr=posterepo.count()+1;
		String code_poste="Post"+nbr;
		org.sid.entities.Service cat=new org.sid.entities.Service();
		cat=findoneservice(id_service);
		Poste po=new Poste(code_poste, libelle, cat);
		posterepo.save(po);
	}
	@Override
	public Page<Poste> listPoste(int page, int size) {
		// TODO Auto-generated method stub
		return posterepo.findAll(new PageRequest(page, size));
	}
	@Override
	public Poste findoneposte(Long id_poste) {
		// TODO Auto-generated method stub
		Poste prod=new Poste();
		prod=posterepo.findOne(id_poste);
		return prod;
	}
	@Override
	public void modifierposte(Long id_poste, String libelle, Long id_service) {
		// TODO Auto-generated method stub
		org.sid.entities.Service ser=new org.sid.entities.Service();
		ser=findoneservice(id_service);
		Poste po=new Poste();
		po =posterepo.findOne(id_poste);
		po.setServices(ser);
		po.setLibelle(libelle);
		posterepo.save(po);
		
	}
	@Override
	public List<Utilisateur> allsuser() {
		// TODO Auto-generated method stub
		List<Utilisateur> users;
		users=userrepo.findAll();
		return users;
	}
	@Override
	public Page<Utilisateur> listUser(int page, int size) {
		// TODO Auto-generated method stub
		return userrepo.findAll(new PageRequest(page, size));
	}
	@Override
	public void savedemandeachat(String titre, String text) {
		// TODO Auto-generated method stub
		DemandeAchat doc=new DemandeAchat();
		doc.setTitre(titre);
		Long nbr=demachrepo.count()+1;
		String codedemach="DEM"+nbr;
		doc.setCode_demandeachat(codedemach);
		doc.setDatecreation(new Date());
		doc.setTextedemande(text);
		doc.setValide(false);
		demachrepo.save(doc);
	}
	@Override
	public Documents dernierdoc() {
		// TODO Auto-generated method stub
		return docrepo.dernierdoc();
	}
	@Override
	public Page<DroitDacces> listdroit(int page, int size) {
		// TODO Auto-generated method stub
		return droitaccerepo.findAll(new PageRequest(page, size));
	}
	@Override
	public void savedroitattribue(Long iddoc,String listbi[]) {
		// TODO Auto-generated method stub
		for(int i=0;i<listbi.length;i++) {
			DroitAttribues drattr=new DroitAttribues();
			Documents doc=findonedoc(iddoc);
			String[] parts = listbi[i].split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			long id_user = Long.parseLong(part1);
			long iddroit = Long.parseLong(part2);
			Utilisateur use=findoneuser(id_user);
			DroitDacces dr=findonedroit(iddroit);
			drattr.setDocumentss(doc);
			drattr.setUtilisateurs(use);
			drattr.setDroitdaccess(dr);
			droitattrrepo.save(drattr);
		}
		
	}
	@Override
	public Documents findonedoc(Long id_doc) {
		// TODO Auto-generated method stub
		Documents prod=docrepo.findOne(id_doc);
		return prod;	
		}
	@Override
	public DroitDacces findonedroit(Long id_droit) {
		// TODO Auto-generated method stub
		DroitDacces prod=new DroitDacces();
		prod=droitaccerepo.findOne(id_droit);
		return prod;
	}
	@Override
	public Utilisateur findoneuser(Long id_user) {
		// TODO Auto-generated method stub
		Utilisateur prod=new Utilisateur();
		prod=userrepo.findOne(id_user);
		return prod;
	}
	@Override
	public Page<DemandeAchat> listdemandeahat(int page, int size) {
		// TODO Auto-generated method stub
		return demachrepo.findAll(new PageRequest(page, size));
	}
	@Override
	public DemandeAchat findonedemandeachat(Long id_demandeachat) {
		// TODO Auto-generated method stub
		DemandeAchat demach=new DemandeAchat();
		demach=demachrepo.findOne(id_demandeachat);
		return demach;
	}
	@Override
	public void savedemandeappro(String titre, String texte,Long id_demandeachat) {
		// TODO Auto-generated method stub
		DemandeAppro doc=new DemandeAppro();
		doc.setTitre(titre);
		Long nbr=demapprorepo.count()+1;
		String codedemach="DEMAppro"+nbr;
		doc.setCode_demande(codedemach);
		doc.setTexte(texte);
		DemandeAchat dema=new DemandeAchat();
		dema=findonedemandeachat(id_demandeachat);
		doc.setDemandeachats(dema);
		demapprorepo.save(doc);
	}
	@Override
	public void validedeamandeachat(Long id_demande,Boolean valide) {
		// TODO Auto-generated method stub
		DemandeAchat demach=new DemandeAchat();
		demach=demachrepo.findOne(id_demande);
		demach.setValide(valide);
		demachrepo.save(demach);
	}
	@Override
	public Page<DemandeAppro> listdemandeappro(int page, int size) {
		// TODO Auto-generated method stub
		return demapprorepo.findAll(new PageRequest(page, size));
	}
	@Override
	public DemandeAppro findonedemandeappro(Long id_demandeappro) {
		// TODO Auto-generated method stub
		DemandeAppro demappro=new DemandeAppro();
		demappro=demapprorepo.findOne(id_demandeappro);
		return demappro;
	}
	@Override
	public void savebonachat(String titre, String texte, Long id_demandeappro) {
		// TODO Auto-generated method stub
		Long nbr=produitrepository.count()+1;
		String code_bon="Bon"+nbr;
		DemandeAppro demapp=new DemandeAppro();
		demapp=findonedemandeappro(id_demandeappro);
		BonDachat bon=new BonDachat();
		bon.setCode_bon(code_bon);
		bon.setTexte(texte);
		bon.setTitre(titre);
		bon.setDemandeappros(demapp);
		bon.setValide(false);
		bonacharepo.save(bon);
		
	}
	@Override
	public void updateaccorddemandeappro(Long id_demandeappro, int accord) {
		// TODO Auto-generated method stub
		DemandeAppro demapp=new DemandeAppro();
		demapp=findonedemandeappro(id_demandeappro);
		demapp.setAccorde(accord);
		demapprorepo.save(demapp);
		
	}
	@Override
	public Page<BonDachat> listbonahat(int page, int size) {
		// TODO Auto-generated method stub
		return bonacharepo.findAll(new PageRequest(page, size));
	}
	@Override
	public BonDachat findonebonachat(Long id_bondachat) {
		// TODO Auto-generated method stub
		BonDachat bonach=new BonDachat();
		bonach=bonacharepo.findOne(id_bondachat);
		return bonach;
	}
	@Override
	public void validerbonachat(Long id_bonachat) {
		// TODO Auto-generated method stub
		BonDachat bonach=new BonDachat();
		bonach=findonebonachat(id_bonachat);
		bonach.setValide(true);
		bonacharepo.save(bonach);
	}
	@Override
	public List<BonDachat> allbondachat() {
		// TODO Auto-generated method stub
		List<BonDachat> cat;
		cat=bonacharepo.findAll();
		return cat;
	}
	@Override
	public List<Produit> allproduit() {
		// TODO Auto-generated method stub
		List<Produit> cat;
		cat=produitrepository.findAll();
		return cat;
	}
	@Override
	public void savecommande(List<String> tamp, String titre, Long id_bondachat,Long optionsListIds) {
		// TODO Auto-generated method stub
		BonCommande boncom=new BonCommande();
		boncom.setTitre(titre);
		BonDachat bondachats=new BonDachat();
		bondachats=findonebonachat(id_bondachat);
		boncom.setBondachats(bondachats);
		boncom.setDate(new Date());
		Long nbr=boncomrepo.count()+1;
		String code_bon="Bon"+nbr;
		boncom.setCodebon(code_bon);
		boncom.setEtat(1);
		boncomrepo.save(boncom);
		BonCommande bcom=new BonCommande();
		bcom=dernierbon();
		Fournisseur fou=findonefournisseur(optionsListIds);
		DetailBon detbon=new DetailBon();
		detbon.setBoncommandes(bcom);
		detbon.setDate(new Date());
		detbon.setEtat(1);
		detbon.setFournisseurs(fou);
		detbonrepo.save(detbon);
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			String part2 = parts[1];
			Long produit=Long.parseLong(part2);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			DetailCommande dt=new DetailCommande();
			dt.setProduits(prod);
			dt.setQte(qte);
			dt.setBoncommandes(bcom);
			dt.setEtat(1);
			detcomrepo.save(dt);
		}
		
	}
	@Override
	public BonCommande dernierbon() {
		// TODO Auto-generated method stub
		return boncomrepo.dernierbon();
	}
	@Override
	public List<DetailCommande> detailbycommande(Long id_bon) {
		// TODO Auto-generated method stub
		return detcomrepo.detailbycommande(id_bon);
	}
	@Override
	public Page<BonCommande> listBonCommande(int page, int size) {
		// TODO Auto-generated method stub
		return boncomrepo.listBonCommande(new PageRequest(page, size));
	}
	@Override
	public Page<DetailCommande> listdetailBonCommande(int page, int size) {
		// TODO Auto-generated method stub
		return detcomrepo.findAll(new PageRequest(page, size));
	}
	@Override
	public BonCommande findoneboncommande(Long id_boncommande) {
		// TODO Auto-generated method stub
		return boncomrepo.findoneboncommande(id_boncommande);
	}
	@Override
	public void supprcommande(Long id_boncommande) {
		// TODO Auto-generated method stub
		BonCommande bccom=new BonCommande();
		bccom=findoneboncommande(id_boncommande);
		bccom.setEtat(0);
	}
	@Override
	public void supprdetailcommande(Long id_detailcommande) {
		// TODO Auto-generated method stub
		DetailCommande dtcom=new DetailCommande();
		dtcom=detcomrepo.findOne(id_detailcommande);
		dtcom.setEtat(0);
	}
	@Override
	public void updatecommande(Long id_commande, String titre, Long id_bondachat,
			List<String> tamp,List<Long> iddetail,Long id_fournisseur) {
		// TODO Auto-generated method stub
		BonCommande bncom=new BonCommande();
		bncom=findoneboncommande(id_commande);
		bncom.setTitre(titre);
		BonDachat bnach=new BonDachat();
		bnach=findonebonachat(id_bondachat);
		bncom.setBondachats(bnach);
		boncomrepo.save(bncom);
		Fournisseur fou=findonefournisseur(id_fournisseur);
		DetailBon detbon=new DetailBon();
		detbon.setBoncommandes(bncom);
		detbon.setDate(new Date());
		detbon.setEtat(1);
		detbon.setFournisseurs(fou);
		detbonrepo.save(detbon);
		Long iddet[] = iddetail.toArray(new Long[0]);
		int i=0;
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			String part2 = parts[1];
			Long produit=Long.parseLong(part2);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			DetailCommande dt=detcomrepo.findOne(iddet[i]);
			dt.setProduits(prod);
			dt.setQte(qte);
			detcomrepo.save(dt);
			i++;
		}
	}
	@Override
	public List<Produit> listprodpourmodif(List<String> listbi) {
		// TODO Auto-generated method stub
		List<Produit> listprod=new ArrayList<>();
		List<Produit> prodyi=allproduit();
		for (Produit produit : prodyi) {
			int cpt=0;
			int k=0;
			for (String string : listbi) {
				String[] parts = string.split("-");
				String part2 = parts[1];
				Long produits=Long.parseLong(part2);
				k++;
				if(produit.getId_produit()==produits) {
					cpt++;
				}
				if(cpt==0 && k==listbi.size()) {
					listprod.add(produit);
				}
			}
		}
		return listprod;
	}
	@Override
	public void savemodifcommande(List<String> tamp,BonCommande bcom) {
		// TODO Auto-generated method stub
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			String part2 = parts[1];
			Long produit=Long.parseLong(part2);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			DetailCommande dt=new DetailCommande();
			dt.setProduits(prod);
			dt.setQte(qte);
			dt.setBoncommandes(bcom);
			dt.setEtat(1);
			detcomrepo.save(dt);
		}
		
	}
	@Override
	public List<DemandeAppro> listappro() {
		// TODO Auto-generated method stub
		List<DemandeAppro> dem=demapprorepo.findAll();
		return dem;
	}
	@Override
	public void savefichesortie(List<String> tamp, String libelle, Long optionsListId) {
		// TODO Auto-generated method stub
		FicheSortie ficsor=new FicheSortie();
		ficsor.setLibelle(libelle);
		Long nbr=ficsortrepo.count()+1;
		String code_bon="FIC"+nbr;
		ficsor.setCode_fiche(code_bon);
		ficsor.setEtat(1);
		ficsor.setDate(new Date());
		ficsortrepo.save(ficsor);
		FicheSortie fic=new FicheSortie();
		fic=dernierefiche();
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			String part2 = parts[1];
			Long produit=Long.parseLong(part2);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			DetailFiche dt=new DetailFiche();
			dt.setProduits(prod);
			dt.setQte(qte);
			dt.setFichesorties(fic);
			dt.setEtat(1);
			dt.setDate(new Date());
			detficsort.save(dt);
			BonSortie bsor=new BonSortie();
			DetailFiche dtd=dernierdetailfiche();
			Long nbrs=bonsortrepo.count()+1;
			String code_bons="BS"+nbrs;
			bsor.setCode_bonsortie(code_bons);
			bsor.setDate(new Date());
			DemandeAppro dapp=findonedemandeappro(optionsListId);
			bsor.setDemandeappros(dapp);
			bsor.setDetailfiches(dtd);
			bsor.setEtat(1);
			bonsortrepo.save(bsor);
		}
	}
	@Override
	public DetailFiche dernierdetailfiche() {
		// TODO Auto-generated method stub
		return detficsort.dernierdetailfiche();
	}
	@Override
	public FicheSortie dernierefiche() {
		// TODO Auto-generated method stub
		return ficsortrepo.dernierefiche();
	}
	@Override
	public List<DetailFiche> detailbyfiche(Long id_fiche) {
		// TODO Auto-generated method stub
		return detficsort.detailbyfiche(id_fiche);
	}
	@Override
	public BonSortie bondesorbyfiche(Long id_detail) {
		// TODO Auto-generated method stub
		return bonsortrepo.bondesorbyfiche(id_detail);
	}
	@Override
	public List<BonSortie> bonbydetailfice(List<DetailFiche> detfiche) {
		// TODO Auto-generated method stub
		List<BonSortie> bsort=new ArrayList<>();
		for (DetailFiche detfic : detfiche) {
			BonSortie bs=new BonSortie();
			bs=bondesorbyfiche(detfic.getId_detailfiche());
			bsort.add(bs);
		}
		return bsort;
	}
	@Override
	public Page<FicheSortie> listfiche(int page, int size) {
		// TODO Auto-generated method stub
		return ficsortrepo.listfiche(new PageRequest(page, size));
	}
	@Override
	public FicheSortie findonefichesortie(Long id_fichesortie) {
		// TODO Auto-generated method stub
		return ficsortrepo.findOne(id_fichesortie);
	}
	@Override
	public void supprbonsortie(Long id_fiche) {
		// TODO Auto-generated method stub
		FicheSortie ficsort = new FicheSortie();
		ficsort=findonefichesortie(id_fiche);
		ficsort.setEtat(0);
	}
	@Override
	public void supprdetailfiche(Long id_detailfiche) {
		// TODO Auto-generated method stub
		DetailFiche dtcom=new DetailFiche();
		dtcom=detficsort.findOne(id_detailfiche);
		dtcom.setEtat(0);
	}
	@Override
	public void updatefiche(Long id_fiche, String libelle, Long id_demandeapprot, List<String> tamp,
			List<Long> iddetail) {
		// TODO Auto-generated method stub
		FicheSortie ficsor=new FicheSortie();
		ficsor=findonefichesortie(id_fiche);
		ficsor.setLibelle(libelle);
		ficsortrepo.save(ficsor);
		Long iddet[] = iddetail.toArray(new Long[0]);
		int i=0;
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			String part2 = parts[1];
			Long produit=Long.parseLong(part2);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			DetailFiche dt=detficsort.findOne(iddet[i]);
			dt.setProduits(prod);
			dt.setQte(qte);
			detficsort.save(dt);
			BonSortie bsor=new BonSortie();
			bsor=bondesorbyfiche(iddet[i]);
			DemandeAppro demap=new DemandeAppro();
			demap=findonedemandeappro(id_demandeapprot);
			bsor.setDemandeappros(demap);
			bsor.setDetailfiches(dt);
			bonsortrepo.save(bsor);
			i++;
		}
	}
	@Override
	public void savePersonne(String adresse, String tel, String email, String nom) {
		// TODO Auto-generated method stub
		Personne p=new Personne();
		p.setAdresse(adresse);
		p.setTel(tel);
		p.setEmail(email);
		p.setNompersonne(nom);
		p.setNom(nom);
		Long nbr=fourrepo.count()+1;
		String code_bon="FOUR"+nbr;
		p.setCode_fournisseur(code_bon);
		p.setEtat(1);
		fourrepo.save(p);
	}
	@Override
	public void saveEntreprise(String adresse, String tel, String email, String nom) {
		// TODO Auto-generated method stub
		Entreprise e=new Entreprise();
		e.setAdresse(adresse);
		e.setTel(tel);
		e.setEmail(email);
		e.setNomEntreprise(nom);
		e.setNom(nom);
		Long nbr=fourrepo.count()+1;
		String code_bon="FOUR"+nbr;
		e.setCode_fournisseur(code_bon);
		e.setEtat(1);
		fourrepo.save(e);
	}
	@Override
	public Page<Fournisseur> listFournisseur(int page, int size) {
		// TODO Auto-generated method stub
		return fourrepo.listFournisseur(new PageRequest(page, size));
	}
	@Override
	public Fournisseur findonefournisseur(Long id_fournisseur) {
		// TODO Auto-generated method stub
		return fourrepo.findonefournisseur(id_fournisseur);
	}
	@Override
	public Entreprise findoneentreprise(Long id_fournisseur) {
		// TODO Auto-generated method stub
		return fourrepo.findoneentreprise(id_fournisseur);
	}
	@Override
	public Personne findonepersonne(Long id_fournisseur) {
		// TODO Auto-generated method stub
		return fourrepo.findonepersonne(id_fournisseur);
	}
	@Override
	public void updatePersonne(Long id_four, String adresse, String tel, String email, String nom) {
		// TODO Auto-generated method stub
		Personne pe=new Personne();
		pe=findonepersonne(id_four);
		pe.setAdresse(adresse);
		pe.setTel(tel);
		pe.setEmail(email);
		pe.setNompersonne(nom);
		pe.setNom(nom);
		fourrepo.save(pe);
	}
	@Override
	public void deletefournisseur(Long id_four) {
		// TODO Auto-generated method stub
		Fournisseur four=findonefournisseur(id_four);
		four.setEtat(0);
	}
	@Override
	public void deletefourni(Long id_four) {
		// TODO Auto-generated method stub
		Fournisseur four=findonefournisseur(id_four);
		four.setEtat(2);
	}
	@Override
	public void updatesavepersonne(Long id_four, String adresse, String tel, String email, String nom) {
		// TODO Auto-generated method stub
		 Entreprise e=new Entreprise();
			e=findoneentreprisesup(id_four);
			Personne p=new Personne();
			p.setAdresse(adresse);
			p.setTel(tel);
			p.setEmail(email);
			p.setNompersonne(nom);
			p.setNom(nom);
			p.setCode_fournisseur(e.getCode_fournisseur());
			p.setEtat(1);
			deletefourni(id_four);
			fourrepo.save(p);
	}
	@Override
	public void updatesaveentreprise(Long id_four, String adresse, String tel, String email, String nom) {
		// TODO Auto-generated method stub
		 Personne pe=new Personne();
			pe=findonepersonnesup(id_four);
			Entreprise e=new Entreprise();
			e.setAdresse(adresse);
			e.setTel(tel);
			e.setEmail(email);
			e.setNomEntreprise(nom);
			e.setNom(nom);
			e.setCode_fournisseur(pe.getCode_fournisseur());
			e.setEtat(1);
			deletefourni(id_four);
			fourrepo.save(e);
	}
	@Override
	public void updateEntreprise(Long id_four, String adresse, String tel, String email, String nom) {
		// TODO Auto-generated method stub
		Entreprise e=new Entreprise();
		e=findoneentreprise(id_four);
		e.setAdresse(adresse);
		e.setTel(tel);
		e.setEmail(email);
		e.setNomEntreprise(nom);
		e.setNom(nom);
		fourrepo.save(e);
	}
	@Override
	public Personne findonepersonnesup(Long id_fournisseur) {
		// TODO Auto-generated method stub
		return fourrepo.findonepersonnesup(id_fournisseur);
	}
	@Override
	public Entreprise findoneentreprisesup(Long id_fournisseur) {
		// TODO Auto-generated method stub
		return fourrepo.findoneentreprisesup(id_fournisseur);
	}
	@Override
	public void savemodifFiche(List<String> tamp, FicheSortie fic,Long optionsListId) {
		// TODO Auto-generated method stub
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			String part2 = parts[1];
			Long produit=Long.parseLong(part2);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			DetailFiche dt=new DetailFiche();
			dt.setProduits(prod);
			dt.setQte(qte);
			dt.setFichesorties(fic);
			dt.setEtat(1);
			dt.setDate(new Date());
			detficsort.save(dt);
			BonSortie bsor=new BonSortie();
			DetailFiche dtd=dernierdetailfiche();
			Long nbrs=bonsortrepo.count()+1;
			String code_bons="BS"+nbrs;
			bsor.setCode_bonsortie(code_bons);
			bsor.setDate(new Date());
			DemandeAppro dapp=findonedemandeappro(optionsListId);
			bsor.setDemandeappros(dapp);
			bsor.setDetailfiches(dtd);
			bsor.setEtat(1);
			bonsortrepo.save(bsor);
		}
	}
	@Override
	public List<Fournisseur> allFournisseurs() {
		// TODO Auto-generated method stub
		return fourrepo.allFournisseurs();
	}
	@Override
	public List<BonCommande> allCommandes() {
		// TODO Auto-generated method stub
		return boncomrepo.allCommandes();
	}
	@Override
	public void savelivraison(List<String> tamp, String titre, Long id_boncommande, Long id_fourni) {
		// TODO Auto-generated method stub
		BonLivraison bol=new BonLivraison();
		Long nbrs=blrepo.count()+1;
		String code_bons="BL"+nbrs;
		bol.setCode_bonlivraison(code_bons);
		bol.setTitre(titre);
		BonCommande bc=new BonCommande();
		bc=findoneboncommande(id_boncommande);
		Fournisseur fou=findonefournisseur(id_fourni);
		bol.setBoncommandes(bc);
		bol.setFournisseurs(fou);
		bol.setDate(new Date());
		bol.setEtat(1);
		blrepo.save(bol);
		BonLivraison bliv=new BonLivraison();
		bliv=dernierbonliv();
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			Long produit=Long.parseLong(parts[1]);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			Double prix=Double.parseDouble(parts[2]);
			DetailLivraison detliv=new DetailLivraison();
			detliv.setBonlivraisons(bliv);
			detliv.setDate(new Date());
			detliv.setPrix(prix);
			detliv.setQte(qte);
			detliv.setProduits(prod);
			detliv.setEtat(1);
			detlivrepo.save(detliv);
		}
	}
	@Override
	public BonLivraison dernierbonliv() {
		// TODO Auto-generated method stub
		return blrepo.dernierbonliv();
	}
	@Override
	public List<DetailLivraison> detailbyLivraison(Long id_bon) {
		// TODO Auto-generated method stub
		return detlivrepo.detailbyLivraison(id_bon);
	}
	@Override
	public Page<BonLivraison> listBonLivraison(int page, int size) {
		// TODO Auto-generated method stub
		return blrepo.listBonLivraison(new PageRequest(page, size));
	}
	@Override
	public BonLivraison findonelivraison(Long id_bonlivraison) {
		// TODO Auto-generated method stub
		return blrepo.findonelivraison(id_bonlivraison);
	}
	@Override
	public void deletelivraison(Long id_bonliv) {
		// TODO Auto-generated method stub
		BonLivraison bonl=new BonLivraison();
		bonl=findonelivraison(id_bonliv);
		bonl.setEtat(0);
		blrepo.save(bonl);
	}
	@Override
	public void deletedetailliv(Long id_detail) {
		// TODO Auto-generated method stub
		DetailLivraison dtl=new DetailLivraison();
		dtl=findonedetaillivraison(id_detail);
		dtl.setEtat(0);
		detlivrepo.save(dtl);
	}
	@Override
	public DetailLivraison findonedetaillivraison(Long id_detail) {
		// TODO Auto-generated method stub
		return detlivrepo.findonedetaillivraison(id_detail);
	}
	@Override
	public void updatelivraison(Long idbon,List<String> tamp, String titre, Long id_boncommande, Long id_fourni,
			List<Long> iddetail) {
		// TODO Auto-generated method stub
		BonLivraison bol=new BonLivraison();
		bol=findonelivraison(idbon);
		bol.setTitre(titre);
		BonCommande bc=new BonCommande();
		bc=findoneboncommande(id_boncommande);
		Fournisseur fou=findonefournisseur(id_fourni);
		bol.setBoncommandes(bc);
		bol.setFournisseurs(fou);
		bol.setDate(new Date());
		blrepo.save(bol);
		int k=0;
		Long iddet[] = iddetail.toArray(new Long[0]);
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			Long produit=Long.parseLong(parts[1]);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			Double prix=Double.parseDouble(parts[2]);
			DetailLivraison detliv=new DetailLivraison();
			detliv=findonedetaillivraison(iddet[k]);
			detliv.setBonlivraisons(bol);
			detliv.setDate(new Date());
			detliv.setPrix(prix);
			detliv.setQte(qte);
			detliv.setProduits(prod);
			detlivrepo.save(detliv);
			k++;
		}
	}
	@Override
	public void savemodiflivraison(List<String> tamp, BonLivraison bliv) {
		// TODO Auto-generated method stub
		for (String string : tamp) {
			String[] parts = string.split("-");
			String part1 = parts[0];
			int qte=Integer.parseInt(part1);
			Long produit=Long.parseLong(parts[1]);
			Produit prod=new Produit();
			prod=findoneproduit(produit);
			Double prix=Double.parseDouble(parts[2]);
			DetailLivraison detliv=new DetailLivraison();
			detliv.setBonlivraisons(bliv);
			detliv.setDate(new Date());
			detliv.setPrix(prix);
			detliv.setQte(qte);
			detliv.setProduits(prod);
			detliv.setEtat(1);
			detlivrepo.save(detliv);
		}
	}
	@Override
	public DetailBon detailbyboncommande(Long id_bon) {
		// TODO Auto-generated method stub
		return detbonrepo.detailbyboncommande(id_bon);
	}
	@Override
	public void savedemandeprestation(String titre, String objet, String demandeur, String nomfichier) {
		// TODO Auto-generated method stub
		DemandePrestation demprest=new DemandePrestation();
		demprest.setTitre(titre);
		demprest.setObjet(objet);
		demprest.setDemandeur(demandeur);
		demprest.setNomfichier(nomfichier);
		demprest.setDatecreation(new Date());
		String dp="DP";
		int nbrs=countdoc(dp);
		String code_bons="DMP"+nbrs;
		demprest.setCodeDP(code_bons);
		demprest.setEtat(1);
		docrepo.save(demprest);
	}
	@Override
	public int countdoc(String typedoc) {
		// TODO Auto-generated method stub
		return docrepo.countdoc(typedoc);
	}
	@Override
	public DemandePrestation findonedp(Long iddoc) {
		// TODO Auto-generated method stub
		return docrepo.findonedp(iddoc);
	}
	@Override
	public Page<DemandePrestation> listPrestation(String typedoc, int page, int size) {
		// TODO Auto-generated method stub
		return docrepo.listPrestation(typedoc, new PageRequest(page, size));
	}
	@Override
	public DemandePrestation findoneprestation(Long iddoc, String typedoc) {
		// TODO Auto-generated method stub
		return docrepo.findoneprestation(iddoc, typedoc);
	}
	@Override
	public void deleteprest(Long iddoc) {
		// TODO Auto-generated method stub
		DemandePrestation demprest=findoneprestation(iddoc, "DP");
		demprest.setEtat(0);
	}
	@Override
	public List<DroitAttribues> listdroitattr(Long iddoc) {
		// TODO Auto-generated method stub
		return droitattrrepo.listdroitattr(iddoc);
	}
	@Override
	public List<Utilisateur> listutibydroi(List<DroitAttribues> list) {
		// TODO Auto-generated method stub
		List<Utilisateur> listuti=new ArrayList<>();
		for (DroitAttribues droitAttribues : list) {
			Utilisateur uti=findoneuser(droitAttribues.getUtilisateurs().getId_user());
			if(!(listuti.contains(uti))) {
				listuti.add(uti);
			}
			
		}
		return listuti;
	}
	@Override
	public void updatedemandeprest(Long iddoc, String titre, String objet, String demandeur, String nomfic,
			String stringArray[],List<Long> iddetail) {
		// TODO Auto-generated method stub
		DemandePrestation dempres=new DemandePrestation();
		dempres=findoneprestation(iddoc, "DP");
		dempres.setTitre(titre);
		dempres.setObjet(objet);
		dempres.setDemandeur(demandeur);
		dempres.setNomfichier(nomfic);
		docrepo.save(dempres);
		Documents doc=findonedoc(iddoc);
		List<DroitAttribues> listbi=listdroitattr(iddoc);
		for (DroitAttribues droitAttribues : listbi) {
			droitattrrepo.delete(droitAttribues.getIddroitattribues());
		}
			for(int i=0;i<stringArray.length;i++) {
				String[] parts = stringArray[i].split("-");
				String part1 = parts[0];
				String part2 = parts[1];
				long id_user = Long.parseLong(part1);
				long iddroit = Long.parseLong(part2);
				Utilisateur use=findoneuser(id_user);
				DroitDacces dr=findonedroit(iddroit);
				DroitAttribues drattr=new DroitAttribues();
				drattr.setDocumentss(doc);
				drattr.setUtilisateurs(use);
				drattr.setDroitdaccess(dr);
				droitattrrepo.save(drattr);
			}
		
	}
	@Override
	public void saveuser(String nom,String prenom,String email,String adresse,String username,
			String password,String password1,String tel) {
		// TODO Auto-generated method stub
		Utilisateur us=new Utilisateur();
		Long nbrs=userrepo.count();
		String code_bons="DMP"+nbrs;
		if(!(password.equals(password1))) {
			throw new RuntimeException("Les mots de passes ne correspondent pas");
		}
		if(password.length()<8) {
			throw new RuntimeException("Le mot de passe doit depasser 8 caracteres");
		}
		us.setCode_user(code_bons);
		us.setNom_user(nom);
		us.setPrenom_user(prenom);
		Utilisateur us1=verifemail(email);
		if(us1!=null) {
			throw new RuntimeException("Cet Email est deja pris");
		}else {
			us.setEmail(email);
		}
		us.setAdresse(adresse);
		us.setPassword(password);
		us.setTel(tel);
		us.setActive(false);
		Utilisateur us2=verifusername(username);
		if(us2!=null) {
			throw new RuntimeException("Ce username n est pas disponible");
		}else {
			us.setUsername(username);
		}
		userrepo.save(us);
	}
	@Override
	public Utilisateur verifusername(String username) {
		// TODO Auto-generated method stub
		return userrepo.verifusername(username);
	}
	@Override
	public Utilisateur verifemail(String email) {
		// TODO Auto-generated method stub
		return userrepo.verifemail(email);
	}
	@Override
	public Page<Utilisateur> listusernonval(int page, int size) {
		// TODO Auto-generated method stub
		return userrepo.listusernonval(new PageRequest(page, size));
	}
	@Override
	public Page<Utilisateur> listuserval(int page, int size) {
		// TODO Auto-generated method stub
		return userrepo.listuserval(new PageRequest(page, size));
	}
	@Override
	public List<Roles> allroles() {
		// TODO Auto-generated method stub
		List<Roles> listrol=new ArrayList<>();
		List<Roles> listroles=new ArrayList<>();
		listrol=rolerepo.findAll();
		for (Roles roles : listrol) {
			if(!(roles.getRole().equals("SUPERADMIN")) && !(roles.getRole().equals("ADMIN")) && !(roles.getRole().equals("DG")) ) {
				listroles.add(roles);
			}
		}
		return listroles;
	}
	@Override
	public void saveuserrole(Long idroles,Long id_user) {
		// TODO Auto-generated method stub
		Roles ro=new Roles();
		Utilisateur us=new Utilisateur();
		us=findoneuser(id_user);
		ro=rolerepo.findOne(idroles);
		if(idroles==3) {
			Users_Roles usr=new Users_Roles();
			usr.setRoles(ro.getRole());
			usr.setUsername(us.getUsername());
			userrolerepo.save(usr);
			Users_Roles usrs=new Users_Roles();
			usrs.setRoles("ED");
			usrs.setUsername(us.getUsername());
			userrolerepo.save(usrs);
			Users_Roles usrss=new Users_Roles();
			usrss.setRoles("USER");
			usrss.setUsername(us.getUsername());
			userrolerepo.save(usrss);
		}else {
			if(idroles==4) {
				Users_Roles usrs=new Users_Roles();
				usrs.setRoles("ED");
				usrs.setUsername(us.getUsername());
				userrolerepo.save(usrs);
				Users_Roles usrss=new Users_Roles();
				usrss.setRoles("USER");
				usrss.setUsername(us.getUsername());
				userrolerepo.save(usrss);
			}else {
				if(idroles==6) {
					Users_Roles usrss=new Users_Roles();
					usrss.setRoles("USER");
					usrss.setUsername(us.getUsername());
					userrolerepo.save(usrss);
				}else {
					if(idroles==7) {
						Users_Roles usr=new Users_Roles();
						usr.setRoles(ro.getRole());
						usr.setUsername(us.getUsername());
						userrolerepo.save(usr);
						Users_Roles usrss=new Users_Roles();
						usrss.setRoles("USER");
						usrss.setUsername(us.getUsername());
						userrolerepo.save(usrss);
					}
				}
			}
		}
	}
	@Override
	public void activeruser(Long id_user) {
		// TODO Auto-generated method stub
		Utilisateur us=new Utilisateur();
		us=findoneuser(id_user);
		us.setActive(true);
		userrepo.save(us);
	}
	

}
