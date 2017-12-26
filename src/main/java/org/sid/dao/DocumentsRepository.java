package org.sid.dao;

import org.sid.entities.DemandePrestation;
import org.sid.entities.Documents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentsRepository extends JpaRepository<Documents, Long>{
	@Query(nativeQuery = true,value="select * from Documents order by datecreation desc limit 1")
	public Documents dernierdoc();
	@Query(nativeQuery = true,value="select COUNT(*) from Documents where type_documents=:x")
	public int countdoc(@Param("x")String typedoc);
	@Query(nativeQuery = true,value="select * from Documents where iddoc=:x")
	public DemandePrestation findonedp(@Param("x")Long iddoc);
	@Query("select o from Documents o where o.etat=1 and type_documents=:x")
	public Page<DemandePrestation> listPrestation(@Param("x")String typedoc,Pageable pageable);
	@Query(nativeQuery = true,value="select * from Documents where iddoc=:y and etat=1 and type_documents=:x limit 1")
	public DemandePrestation findoneprestation(@Param("y")Long iddoc,@Param("x")String typedoc);
}
