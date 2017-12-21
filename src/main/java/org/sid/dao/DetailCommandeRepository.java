package org.sid.dao;

import java.util.List;

import org.sid.entities.BonCommande;
import org.sid.entities.DetailCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetailCommandeRepository extends JpaRepository<DetailCommande, Long>{
	@Query(nativeQuery = true,value="select * from detail_commande where id_bon=:x and etat=1")
	public List<DetailCommande> detailbycommande(@Param("x")Long id_bon);
}
