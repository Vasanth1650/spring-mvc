package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Leave;
import net.javaguides.springboot.repository.LeaveRepository;

@Service
public class LeaveServiceV {
	
	@Autowired
	private LeaveRepository repository;
	
	public List<Leave> getAllByID(long empid){
		return repository.findAllByEmpid(empid);
	}

}
