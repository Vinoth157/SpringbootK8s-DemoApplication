package com.vinoth.springbootk8s.studentapplication.AddressService.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinoth.springbootk8s.studentapplication.AddressService.DTO.AddressDTO;
import com.vinoth.springbootk8s.studentapplication.AddressService.Entity.Address;
import com.vinoth.springbootk8s.studentapplication.AddressService.Service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Value(value = "${server.port}")
	private String portNumber;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/save")
	public long saveAddress(@RequestBody AddressDTO addressDTO) {		
		return addressService.saveAddress(modelMapper.map(addressDTO, Address.class));
	}
	
	@GetMapping("")
	public String displayMessage() {
		return "Address Service is working in port number : "+portNumber;
	}
	
	@GetMapping("/find/{id}")
	public AddressDTO findByAddressId(@PathVariable("id")  long addressId) {
		return modelMapper.map(addressService.findAddressById(addressId), AddressDTO.class) ;
	}
	
	@GetMapping("/")
	public List<AddressDTO> findAll(){
		return addressService.findAll().stream()
				.map(eachAddress-> modelMapper.map(eachAddress, AddressDTO.class)).toList();
	}
	
	@PostMapping("/")
	public List<Long> saveAll(List<Address> address){
		return addressService.saveAll(address);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") long addressId) {
		addressService.deleteById(addressId);
	}
	
	@DeleteMapping("/")
	public void deleteAll() {
		addressService.deleteAll();
	}
	
}
