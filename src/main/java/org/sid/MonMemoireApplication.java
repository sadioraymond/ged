package org.sid;

import java.util.Date;

import org.sid.dao.BonCommandeRepository;
import org.sid.dao.BonDachatRepository;
import org.sid.dao.BonLivraisonRepository;
import org.sid.dao.BonSortieRepository;
import org.sid.dao.CategorieRepository;
import org.sid.dao.ConsultationDocumentRepository;
import org.sid.dao.DemandeAchatRepository;
import org.sid.dao.DemandeApproRepository;
import org.sid.dao.DetailBonRepository;
import org.sid.dao.DetailCommandeRepository;
import org.sid.dao.DetailFicheRepository;
import org.sid.dao.DetailLivraisonRepository;
import org.sid.dao.DocumentsRepository;
import org.sid.dao.DroitAttribuesRepository;
import org.sid.dao.DroitDaccesRepository;
import org.sid.dao.FichePosteRepository;
import org.sid.dao.FicheSortieRepository;
import org.sid.dao.FournisseurRepository;
import org.sid.dao.PosteRepository;
import org.sid.dao.ProduitRepository;
import org.sid.dao.ProfilRepository;
import org.sid.dao.ProfilsAttribuesRepository;
import org.sid.dao.ServiceRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.BonCommande;
import org.sid.entities.BonDachat;
import org.sid.entities.BonLivraison;
import org.sid.entities.BonSortie;
import org.sid.entities.Categorie;
import org.sid.entities.ConsultationDocument;
import org.sid.entities.DemandeAchat;
import org.sid.entities.DemandeAppro;
import org.sid.entities.DemandeEmploi;
import org.sid.entities.DemandePrestation;
import org.sid.entities.DetailBon;
import org.sid.entities.DetailCommande;
import org.sid.entities.DetailFiche;
import org.sid.entities.DetailLivraison;
import org.sid.entities.Documents;
import org.sid.entities.DroitAttribues;
import org.sid.entities.DroitDacces;
import org.sid.entities.Entreprise;
import org.sid.entities.FichePoste;
import org.sid.entities.FicheSortie;
import org.sid.entities.Fournisseur;
import org.sid.entities.Personne;
import org.sid.entities.Poste;
import org.sid.entities.Produit;
import org.sid.entities.Profil;
import org.sid.entities.ProfilsAttribues;
import org.sid.entities.Service;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonMemoireApplication implements CommandLineRunner {
	@Autowired
	private DocumentsRepository documentrepository;
	@Autowired
	private DroitDaccesRepository droitdaccesrepo;
	@Autowired
	private DroitAttribuesRepository droitattribuesrepo;
	@Autowired
	private UtilisateurRepository userrepo;
	@Autowired
	private ConsultationDocumentRepository consdocrepo;
	@Autowired
	private ProfilRepository profilrepository;
	@Autowired
	private ProfilsAttribuesRepository profatrirepo;
	@Autowired
	private PosteRepository posterepo;
	@Autowired
	private FichePosteRepository ficheposterepo;
	@Autowired
	private BonCommandeRepository boncomrepo;
	@Autowired
	private FournisseurRepository fournirepo;
	@Autowired
	private DetailBonRepository detailrepo;
	@Autowired
	private ProduitRepository produitrepo;
	@Autowired
	private BonLivraisonRepository bonlivrepo;
	@Autowired
	private DetailLivraisonRepository detlivr;
	@Autowired
	private FicheSortieRepository ficsorrepo;
	@Autowired
	private DetailFicheRepository detfichrepo;
	@Autowired
	private DetailCommandeRepository detcomrepo;
	@Autowired
	private ServiceRepository servrepo;
	@Autowired
	private BonDachatRepository bondachrepo;
	@Autowired
	private DemandeApproRepository demandeaprepo;
	@Autowired
	private BonSortieRepository bonsorrepo;
	@Autowired
	private CategorieRepository categorepo;
	@Autowired
	private DemandeAchatRepository demandeachatrepo;
	public static void main(String[] args) {
		SpringApplication.run(MonMemoireApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		Utilisateur us=userrepo.save(new Utilisateur("us01","SADIO","WALY RAYMOND",
				"SCAT Urbam","777922484",new Date(),"Masc","Célibataire","sadiowaly@gamil.com"));
		Utilisateur us1=userrepo.save(new Utilisateur("us02","CABOU","RAYMOND",
				"SCAT Urbam","777183060",new Date(),"Masc","Marié","cabou@gamil.com"));
		Utilisateur us2=userrepo.save(new Utilisateur("us03","SADIO","Marie",
				"SCAT Urbam","775618351",new Date(),"Féminin","Célibataire","maite@gamil.com"));
		DemandeAchat doc=demandeachatrepo.save(new DemandeAchat(new Date(),us,"DEM001",true,us1));
		Documents doc1=documentrepository.save(new DemandePrestation(new Date(),us,"Raymond","test","DP001"));
		Documents doc2=documentrepository.save(new DemandeEmploi(new Date(),us2,"DE001"));
		DroitDacces droit=droitdaccesrepo.save(new DroitDacces("CO1","Ajouter"));
		DroitAttribues dr=droitattribuesrepo.save(new DroitAttribues(doc,droit,new Date(),new Date(),us1));
		DroitAttribues drs=droitattribuesrepo.save(new DroitAttribues(doc,droit,new Date(),new Date(),us2));
		ConsultationDocument cd=consdocrepo.save(new ConsultationDocument(doc,us,new Date(),new Date()));
		Profil pro=profilrepository.save(new Profil("Administrateur","ADminOOO1"));
		ProfilsAttribues proatt=profatrirepo.save(new ProfilsAttribues(us,pro,new Date(),new Date()));
		Poste pos=posterepo.save(new Poste("POO1","Directeur Général"));
		FichePoste fichepos=ficheposterepo.save(new FichePoste(us2,pos,new Date(),new Date()));
		BonCommande bc=boncomrepo.save(new BonCommande("Bon001",new Date()));
		Fournisseur four=fournirepo.save(new Entreprise("C0001","777922484","SCAT URBAM","four@dof.com","DofTech"));
		Fournisseur four1=fournirepo.save(new Personne("C0001","775618351","SCAT","sadio@dof.com","Raymond SADIO"));
		DetailBon dtb=detailrepo.save(new DetailBon(bc,four,new Date()));
		Produit prod=produitrepo.save(new Produit("P001","Telephone"));
		BonLivraison bl=bonlivrepo.save(new BonLivraison("BL001",new Date()));
		DetailLivraison dtliv=detlivr.save(new DetailLivraison(1234.45,43,prod,bl));
		FicheSortie fics=ficsorrepo.save(new FicheSortie("FIC001","Dafa Leer"));
		DetailFiche dtfic=detfichrepo.save(new DetailFiche(new Date(),30,fics,prod));
		DetailCommande dtcom=detcomrepo.save(new DetailCommande(bc,prod,50));
		Service serv=servrepo.save(new Service("SER001","Direction"));
		Poste pos1=posterepo.save(new Poste("POO1","Directeur adjoint",serv));
		BonLivraison bl1=bonlivrepo.save(new BonLivraison("BL002",new Date(),bc));
		BonDachat bonach=bondachrepo.save(new BonDachat("Bon001",new Date(),"djljfjklsd fdsjqjfkljsljdf"));
		BonCommande bc1=boncomrepo.save(new BonCommande("BON002",us2,new Date(),bonach));
		DemandeAppro dem=demandeaprepo.save(new DemandeAppro("DEM001","kfjksdknklnlfkn snkljklg",new Date(),us));
		BonDachat bonach1=bondachrepo.save(new BonDachat("Bon002",new Date(),"djljfjklsd fdsjqjfkljsljdf",us,dem));
		DemandeAppro dem1=demandeaprepo.save(new DemandeAppro("DEM002","texte bi la bindeu",new Date(),us,doc));
		BonSortie bs=bonsorrepo.save(new BonSortie("BS001",new Date(),us2,dem1,dtfic));
		Categorie cat=categorepo.save(new Categorie("CAT001","Materiaux Bureaux"));
		Produit prod1=produitrepo.save(new Produit("P001","Telephone",cat));
		BonLivraison bl2=bonlivrepo.save(new BonLivraison("BL001",new Date(),bc1,four));
		
	}
}
