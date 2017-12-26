package org.sid.dao;

import java.util.List;

import org.sid.entities.DroitAttribues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DroitAttribuesRepository extends JpaRepository<DroitAttribues, Long>{
	@Query(nativeQuery = true,value="select * from droit_attribues where code_doc=:x")
	public List<DroitAttribues> listdroitattr(@Param("x")Long iddoc);
}
