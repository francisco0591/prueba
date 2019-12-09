package com.ducat.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import com.ducat.springboot.rest.model.Calculo;
import com.ducat.springboot.rest.model.Cliente;

public interface Myservice {

	public List<Cliente> getClients();
	public Optional<Cliente> getClientById(int empid);
	public Cliente addNewClient(Cliente emp);
	public Cliente updateClient(Cliente emp);
	public void deleteClientById(int empid);
	public void deleteAllClient();

}