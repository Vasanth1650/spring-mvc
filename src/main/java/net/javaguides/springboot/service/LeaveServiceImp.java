package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Leave;
import net.javaguides.springboot.model.Leave;
import net.javaguides.springboot.repository.LeaveRepository;

@Service
public class LeaveServiceImp implements LeaveService{
	
	@Autowired
	private LeaveRepository leaveRepository;
	@Override
	public List<Leave> getAllLeaves() {
		return leaveRepository.findAll();
	}

	@Override
	public void saveLeave(Leave leave) {
		this.leaveRepository.save(leave);
	}
	@Override
	public void deleteLeaveById(long empid) {
		this.leaveRepository.deleteById(empid);
	}

	@Override
	public Leave getLeaveById(long empid) {
		Optional<Leave> optional = leaveRepository.findById(empid);
		Leave leave = null;
		if (optional.isPresent()) {
			leave = optional.get();
		} else {
			throw new RuntimeException(" Leave report not found for id :: " + empid);
		}
		return leave;
	}
	@Override
	public Page<Leave> findPaginated1(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.leaveRepository.findAll(pageable);
	}
}
