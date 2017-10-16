package org.sid;

import java.util.Date;

import org.sid.dao.DocumentsRepository;
import org.sid.dao.DroitAttribuesRepository;
import org.sid.dao.DroitDaccesRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Documents;
import org.sid.entities.DroitAttribues;
import org.sid.entities.DroitDacces;
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
		Documents doc=documentrepository.save(new Documents(new Date(),us));
		DroitDacces droit=droitdaccesrepo.save(new DroitDacces("CO1","Ajouter"));
		DroitAttribues dr=droitattribuesrepo.save(new DroitAttribues(doc,droit,new Date(),new Date(),us1));
		DroitAttribues drs=droitattribuesrepo.save(new DroitAttribues(doc,droit,new Date(),new Date(),us2));
		
	}
}
