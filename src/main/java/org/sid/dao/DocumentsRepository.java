package org.sid.dao;

import org.sid.entities.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsRepository extends JpaRepository<Documents, Long>{

}
