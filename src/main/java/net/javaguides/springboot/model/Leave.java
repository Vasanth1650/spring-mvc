package net.javaguides.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table
public class Leave{
	@Id
	@Column

	private long empid;
	
	
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	@Column
	private String leavereason;
	@Column
	private String leavestatus;
	@Column
	private String description;
	public long getEmpid() {
		return empid;
	}
	public void setEmpid(long empid) {
		this.empid = empid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLeavereason() {
		return leavereason;
	}
	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}
	public String getLeavestatus() {
		return leavestatus;
	}
	public void setLeavestatus(String leavestatus) {
		this.leavestatus = leavestatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Leave(long empid, Date date, String leavereason, String leavestatus, String description) {
		super();
		this.empid = empid;
		this.date = date;
		this.leavereason = leavereason;
		this.leavestatus = leavestatus;
		this.description = description;
	}
	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
