package com.ducat.springboot.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ducat.springboot.rest.model.Calculo;
import com.ducat.springboot.rest.model.Cliente;
import com.ducat.springboot.rest.service.Myservice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "prueba")
public class Mycontroller {

	@Autowired
	Myservice service;

	@RequestMapping(value= "/client/listclientes", method= RequestMethod.GET)
	@ApiOperation(value = "Listar clientes", notes = "Retorna  la lista de clientes")
	public List<Cliente> getClient() {
		System.out.println(this.getClass().getSimpleName() + " - Get all client service is invoked.");
		
		return service.getClients();
	}

	@RequestMapping(value= "/client/{id}", method= RequestMethod.GET)
	@ApiOperation(value = "Buscar cliente", notes = "Retorna  la busqueda del cliente por su ID")
	public Cliente getClientById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get client details by id is invoked.");

		Optional<Cliente> emp =  service.getClientById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find client with id- " + id);

		return emp.get();
	}

	@RequestMapping(value= "/client/creacliente", method= RequestMethod.POST)
	@ApiOperation(value = "Crear cliente", notes = "Crea un cliente nuevo")
	public Cliente createClient(@RequestBody Cliente newemp) {
		System.out.println(this.getClass().getSimpleName() + " - Create new client method is invoked.");
		return service.addNewClient(newemp);
	}

	@RequestMapping(value= "/client/update/{id}", method= RequestMethod.PUT)
	@ApiOperation(value = "Actualizar clientes", notes = "Actualiza un cliente por su ID")
	public Cliente updateEmployee(@RequestBody Cliente updemp, @PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update client details by id is invoked.");

		Optional<Cliente> emp =  service.getClientById(id);
		if (!emp.isPresent())
			throw new Exception("Could not find client with id- " + id);

		/* IMPORTANT - To prevent the overiding of the existing value of the variables in the database, 
		 * if that variable is not coming in the @RequestBody annotation object. */		
		if(updemp.getName() == null || updemp.getName().isEmpty())
			updemp.setName(emp.get().getName());
		if(updemp.getLastname() == null || updemp.getLastname().isEmpty())
			updemp.setLastname(emp.get().getLastname());
		if(updemp.getAge() == 0)
			updemp.setAge(emp.get().getAge());
		if(updemp.getBirthdate() == null || updemp.getBirthdate().isEmpty())
			updemp.setLastname(emp.get().getLastname());
		// Required for the "where" clause in the sql query template.
		updemp.setId(id);
		return service.updateClient(updemp);
	}

	@RequestMapping(value= "/client/delete/{id}", method= RequestMethod.DELETE)
	@ApiOperation(value = "Eliminar cliente", notes = "Elimina un cliente por su ID")
	public void deleteEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete client by id is invoked.");

		Optional<Cliente> emp =  service.getClientById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		service.deleteClientById(id);
	}

	@RequestMapping(value= "/client/deleteall", method= RequestMethod.DELETE)
	@ApiOperation(value = "Eliminar todos cliente", notes = "Elimina Todo Los Clientes")
	public void deleteAll() {
		System.out.println(this.getClass().getSimpleName() + " - Delete all client is invoked.");
		service.deleteAllClient();
	}
	
	@RequestMapping(value= "/client/kpideclientes", method= RequestMethod.GET)
	@ApiOperation(value = "Pedir clientes", notes = "Retorna  el promedio y la desviación estandar de la muestra")
	public List<Calculo> getClientPide() {
		System.out.println(this.getClass().getSimpleName() + " - Get all client service is invoked.");
		System.out.println("lista size:"+service.getClients().size());
		int tam = service.getClients().size();
		int sum=0;
		for(Cliente cli : service.getClients()){
			System.out.println("Edad:"+cli.getAge());
			sum += cli.getAge();
		}
		
		float prom = (sum/tam);
		
		
		double desv,z = 0;
		for(Cliente cli : service.getClients()){
			System.out.println("Edad:"+cli.getAge());
			z += Math.pow((cli.getAge() - prom),2);
		}
		desv = Math.sqrt(z/(tam-1));
		System.out.println("Promedio:"+ prom);
		System.out.println("desviacion estandar:"+ desv);
		List<Calculo> lstCalculo = new ArrayList<>();
		Calculo calc = new Calculo();
		calc.setPromedio(prom);
		calc.setDesviacion(desv);
		lstCalculo.add(calc);
		return lstCalculo;
	}
}