package com.backendev.restapi.business

import com.backendev.restapi.model.Persona

interface IPersonaBusines {

    // Aquí se crean todos los metodos que se usarán
    fun list():List<Persona>            // Devuelve la lista de personas
    fun load(idPersona:Long):Persona    // Cargar persona usando el ID : Devuelve persona
    fun save(persona:Persona):Persona   // Guardar persona: devuelve persona que se guardo
    fun remove(idPersona:Long)          // Remover persona de la base de datos
}