package org.sid.dao;

import java.util.List;

import org.sid.entities.BonCommande;
import org.sid.entities.Entreprise;
import org.sid.entities.Fournisseur;
import org.sid.entities.Personne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{
	@Query("select o from Fournisseur o where o.etat=1")
	public Page<Fournisseur> listFournisseur(Pageable pageable);
	@Query(nativeQuery = true,value="select * from fournisseur where id_fournisseur=:x and etat=1 limit 1")
	public Fournisseur findonefournisseur(@Param("x")Long id_fournisseur);
	@Query(nativeQuery = true,value="select * from fournisseur where id_fournisseur=:x and etat=1 limit 1")
	public Entreprise findoneentreprise(@Param("x")Long id_fournisseur);
	@Query(nativeQuery = true,value="select * from fournisseur where id_fournisseur=:x and etat=1 limit 1")
	public Personne findonepersonne(@Param("x")Long id_fournisseur);
	@Query(nativeQuery = true,value="select * from fournisseur where id_fournisseur=:x limit 1")
	public Personne findonepersonnesup(@Param("x")Long id_fournisseur);
	@Query(nativeQuery = true,value="select * from fournisseur where id_fournisseur=:x limit 1")
	public Entreprise findoneentreprisesup(@Param("x")Long id_fournisseur);
	@Query("select o from Fournisseur o where o.etat=1")
	public List<Fournisseur> allFournisseurs();
}
