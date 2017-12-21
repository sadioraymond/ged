package org.sid.dao;


import org.sid.entities.BonLivraison;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BonLivraisonRepository extends JpaRepository<BonLivraison, Long>{
	@Query(nativeQuery = true,value="select * from bon_livraison order by date desc limit 1")
    public BonLivraison dernierbonliv();
	@Query("select o from BonLivraison o where o.etat=1")
	public Page<BonLivraison> listBonLivraison(Pageable pageable);
	@Query(nativeQuery = true,value="select * from bon_livraison where id_bonlivraison=:x and etat=1 limit 1")
	public BonLivraison findonelivraison(@Param("x")Long id_bonlivraison);
}
