package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Leave;

public interface LeaveService {
	List<Leave> getAllLeaves();
	void saveLeave(Leave leave);
	Leave getLeaveById(long empid);
	void deleteLeaveById(long empid);
	Page<Leave> findPaginated1(int pageNo, int pageSize, String sortField, String sortDirection);
}
