package com.backendev.restapi

import com.backendev.restapi.dao.PersonaRepository
import com.backendev.restapi.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class RestapiApplication:CommandLineRunner{				// Extiende de dependencia que ejecuta metodo RUN

	@Autowired
	val personaRepository:PersonaRepository ?= null		// Cargo personaRepository inicialmente nulo
	override fun run(vararg args: String?) {			// función RUN con parámetros Arreglo STRING
		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")	//Establesco formateador para fecha
		val persona1 =  Persona(38762264,"Gaston","Saillen", LocalDate.parse("28-05-1995",formatter))	// Cargo datos de una persona
		personaRepository!!.save(persona1)	// Guardo datos de persona
	}
}

fun main(args: Array<String>) {
	runApplication<RestapiApplication>(*args)
}
