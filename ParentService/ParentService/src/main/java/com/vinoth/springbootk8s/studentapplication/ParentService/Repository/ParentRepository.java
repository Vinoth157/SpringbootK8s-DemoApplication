package com.vinoth.springbootk8s.studentapplication.ParentService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinoth.springbootk8s.studentapplication.ParentService.Entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {

}
