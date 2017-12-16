package org.sid.dao;

import org.sid.entities.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentsRepository extends JpaRepository<Documents, Long>{
	@Query(nativeQuery = true,value="select * from Documents order by datecreation desc limit 1")
	public Documents dernierdoc();
}
