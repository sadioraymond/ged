package org.sid.dao;

import java.util.List;

import org.sid.entities.BonCommande;
import org.sid.entities.Documents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BonCommandeRepository extends JpaRepository<BonCommande, Long> {
	@Query(nativeQuery = true,value="select * from bon_commande order by date desc limit 1")
	public BonCommande dernierbon();
	@Query(nativeQuery = true,value="select * from bon_commande where id_boncommande=:x and etat=1 limit 1")
	public BonCommande findoneboncommande(@Param("x")Long id_boncommande);
	@Query("select o from BonCommande o where o.etat=1")
	public Page<BonCommande> listBonCommande(Pageable pageable);
	@Query("select o from BonCommande o where o.etat=1")
	public List<BonCommande> allCommandes();
}
