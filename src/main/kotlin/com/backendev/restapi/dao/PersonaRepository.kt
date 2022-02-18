package com.backendev.restapi.dao

import com.backendev.restapi.model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepository:JpaRepository<Persona,Long>
//<Lo que se guarda en el repository, tipo de dato del ID>
// PersonaRepository EXTIENDE de JpaRepository
