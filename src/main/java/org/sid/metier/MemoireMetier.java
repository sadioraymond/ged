package org.sid.metier;



import java.util.List;

import org.sid.entities.Categorie;
import org.sid.entities.Produit;
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
}
