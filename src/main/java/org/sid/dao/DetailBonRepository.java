package org.sid.dao;

import java.util.List;

import org.sid.entities.DetailBon;
import org.sid.entities.DetailLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetailBonRepository extends JpaRepository<DetailBon, Long> {
	@Query(nativeQuery = true,value="select * from detail_bon where id_bon=:x and etat=1 limit 1")
	public DetailBon detailbyboncommande(@Param("x")Long id_bon);
}
