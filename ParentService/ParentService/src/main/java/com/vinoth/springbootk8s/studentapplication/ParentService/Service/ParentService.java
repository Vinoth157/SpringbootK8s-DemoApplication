package com.vinoth.springbootk8s.studentapplication.ParentService.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.vinoth.springbootk8s.studentapplication.ParentService.DTO.AddressDTO;
import com.vinoth.springbootk8s.studentapplication.ParentService.DTO.ParentDTO;
import com.vinoth.springbootk8s.studentapplication.ParentService.Entity.Parent;

public interface ParentService {

	long saveParent(Parent parent, AddressDTO address);
	ParentDTO findParentById(long parentId);
	List<ParentDTO> findAll() throws InterruptedException, ExecutionException;
	List<Long> saveAll(List<ParentDTO> parent);	
	String testAddress();
}
