package org.sid.dao;


import org.sid.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	@Query(nativeQuery = true,value="select * from utilisateur where username=:x and active=true limit 1")
	public Utilisateur verifusername(@Param("x")String username);
	@Query(nativeQuery = true,value="select * from utilisateur where email=:x and active=true limit 1")
	public Utilisateur verifemail(@Param("x")String email);
	@Query("select o from Utilisateur o where o.active=false")
	public Page<Utilisateur> listusernonval(Pageable pageable);
	@Query("select o from Utilisateur o where o.active=true")
	public Page<Utilisateur> listuserval(Pageable pageable);
}
