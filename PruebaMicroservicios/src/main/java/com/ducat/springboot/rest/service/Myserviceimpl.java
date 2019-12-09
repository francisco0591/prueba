package com.ducat.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducat.springboot.rest.dao.Mydaorepository;
import com.ducat.springboot.rest.model.Calculo;
import com.ducat.springboot.rest.model.Cliente;

@Service
public class Myserviceimpl implements Myservice {

	@Autowired
	Mydaorepository dao;

	@Override
	public List<Cliente> getClients() {
		return dao.findAll();
	}
	@Override
	public Optional<Cliente> getClientById(int empid) {
		return dao.findById(empid);
	}
	@Override
	public Cliente addNewClient(Cliente emp) {
		return dao.save(emp);
	}
	@Override
	public Cliente updateClient(Cliente emp) {
		return dao.save(emp);
	}
	@Override
	public void deleteClientById(int empid) {
		dao.deleteById(empid);
	}
	@Override
	public void deleteAllClient() {
		dao.deleteAll();
	}
	
}