package org.sid.dao;

import java.util.List;

import org.sid.entities.DetailLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetailLivraisonRepository extends JpaRepository<DetailLivraison, Long>{
	@Query(nativeQuery = true,value="select * from detail_livraison where id_bonlivraison=:x and etat=1")
	public List<DetailLivraison> detailbyLivraison(@Param("x")Long id_bon);
}
