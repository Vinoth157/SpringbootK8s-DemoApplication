package com.vinoth.springbootk8s.studentapplication.AddressService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinoth.springbootk8s.studentapplication.AddressService.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
