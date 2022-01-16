package com.vinoth.springbootk8s.studentapplication.ParentService.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinoth.springbootk8s.studentapplication.ParentService.DTO.ParentDTO;
import com.vinoth.springbootk8s.studentapplication.ParentService.Entity.Parent;
import com.vinoth.springbootk8s.studentapplication.ParentService.Service.ParentService;

@RestController
@RequestMapping(value = "/parent")
public class ParentController {
	
	@Autowired
	private ParentService parentService;
	
	@Autowired
	private ModelMapper modelMapper;
		
	@PostMapping("/save")
	public long saveParent(@RequestBody ParentDTO parentDTO) {
		return parentService.saveParent(modelMapper.map(parentDTO, Parent.class), parentDTO.getAddress());
	}
	
	@PostMapping("/saveAll")
	public List<Long> saveAllParent(@RequestBody List<ParentDTO> parentDTO) {
		return parentService.saveAll(parentDTO);
	}
	
	@GetMapping("")
	public String displayMessage() {
		return "Parent Service is working and "+ parentService.testAddress();
	}
	
	@GetMapping("/find/{id}")
	public ParentDTO findParentById(@PathVariable("id") long parentId) {
		return parentService.findParentById(parentId);
	}
	
	@GetMapping("/findAll")
	public List<ParentDTO> findAll() throws InterruptedException, ExecutionException {
		return parentService.findAll();
	}
	
	

}
