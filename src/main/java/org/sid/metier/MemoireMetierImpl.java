package org.sid.metier;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Date;
import java.util.List;

import org.sid.dao.CategorieRepository;
import org.sid.dao.DemandeAchatRepository;
import org.sid.dao.DocumentsRepository;
import org.sid.dao.DroitAttribuesRepository;
import org.sid.dao.DroitDaccesRepository;
import org.sid.dao.PosteRepository;
import org.sid.dao.ProduitRepository;
import org.sid.dao.ServiceRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Categorie;
import org.sid.entities.DemandeAchat;
import org.sid.entities.Documents;
import org.sid.entities.DroitAttribues;
import org.sid.entities.DroitDacces;
import org.sid.entities.Poste;
import org.sid.entities.Produit;
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

}
