package org.sid.dao;

import java.util.List;

import org.sid.entities.BonSortie;
import org.sid.entities.FicheSortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BonSortieRepository extends JpaRepository<BonSortie, Long> {
	@Query(nativeQuery = true,value="select * from bon_sortie where id_detailfiche=:x and etat=1 limit 1")
	public BonSortie bondesorbyfiche(@Param("x")Long id_detail);
}
