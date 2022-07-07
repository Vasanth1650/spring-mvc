package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>{

	List<Leave> findAllByEmpid(long empid);

}
