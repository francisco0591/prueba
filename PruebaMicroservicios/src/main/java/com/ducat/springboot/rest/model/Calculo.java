package com.ducat.springboot.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;


public class Calculo {

	
	private float promedio;
	private double desviacion;
	

	public Calculo() { }

	public float getPromedio() {
		return promedio;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}

	public double getDesviacion() {
		return desviacion;
	}


	public void setDesviacion(double desviacion) {
		this.desviacion = desviacion;
	}

	
	
}