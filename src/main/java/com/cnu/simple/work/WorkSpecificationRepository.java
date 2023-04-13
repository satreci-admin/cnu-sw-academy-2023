package com.cnu.simple.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSpecificationRepository extends JpaRepository<WorkSpecification, Long> {
}
