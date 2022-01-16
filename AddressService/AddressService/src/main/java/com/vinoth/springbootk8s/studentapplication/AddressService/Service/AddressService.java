package com.vinoth.springbootk8s.studentapplication.AddressService.Service;

import java.util.List;

import com.vinoth.springbootk8s.studentapplication.AddressService.Entity.Address;

public interface AddressService {

	long saveAddress(Address address);
	Address findAddressById(long addressId);
	List<Address> findAll();
	List<Long> saveAll(List<Address> address);	
	void deleteAll();
	void deleteById(long addressId);
}
