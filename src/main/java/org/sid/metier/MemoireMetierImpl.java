package org.sid.metier;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.sid.dao.CategorieRepository;
import org.sid.dao.ProduitRepository;
import org.sid.entities.Categorie;
import org.sid.entities.Produit;
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

}
