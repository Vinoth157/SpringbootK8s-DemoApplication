package com.vinoth.springbootk8s.studentapplication.AddressService.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinoth.springbootk8s.studentapplication.AddressService.Entity.Address;
import com.vinoth.springbootk8s.studentapplication.AddressService.Repository.AddressRepository;
import com.vinoth.springbootk8s.studentapplication.AddressService.Service.AddressService;	

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	@Override
	public long saveAddress(Address address) {
		return addressRepository.save(address).getAddressId();		
	}

	@Override
	public Address findAddressById(long addressId) {
		return addressRepository.findById(addressId).orElseThrow();
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public List<Long> saveAll(List<Address> address) {		
		return addressRepository.saveAll(address).stream()
				.collect(Collectors.mapping(eachaddress->eachaddress.getAddressId(), Collectors.toUnmodifiableList()));
		
	}
	
	@Override
	public void deleteAll() {
		addressRepository.deleteAll();
	}

	@Override
	public void deleteById(long addressId) {
		addressRepository.deleteById(addressId);		
	}

}
