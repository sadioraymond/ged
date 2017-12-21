package org.sid.dao;

import org.sid.entities.BonCommande;
import org.sid.entities.FicheSortie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FicheSortieRepository extends JpaRepository<FicheSortie, Long> {
	@Query(nativeQuery = true,value="select * from fiche_sortie order by date desc limit 1")
	public FicheSortie dernierefiche();
	@Query("select o from FicheSortie o where o.etat=1")
	public Page<FicheSortie> listfiche(Pageable pageable);
}
