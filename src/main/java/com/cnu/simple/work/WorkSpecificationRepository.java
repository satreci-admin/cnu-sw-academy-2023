package com.cnu.simple.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkSpecificationRepository extends JpaRepository<WorkSpecification, Long> {
    List<WorkSpecification> findAllByMemberId(UUID memberId);
}
