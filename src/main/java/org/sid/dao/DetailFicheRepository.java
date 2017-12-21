package org.sid.dao;

import java.util.List;

import org.sid.entities.DetailCommande;
import org.sid.entities.DetailFiche;
import org.sid.entities.FicheSortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetailFicheRepository extends JpaRepository<DetailFiche, Long>{
	@Query(nativeQuery = true,value="select * from detail_fiche order by date desc limit 1")
	public DetailFiche dernierdetailfiche();
	@Query(nativeQuery = true,value="select * from detail_fiche where id_fiche=:x and etat=1")
	public List<DetailFiche> detailbyfiche(@Param("x")Long id_fiche);
}
