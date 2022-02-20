package com.backendev.restapi.business

import com.backendev.restapi.dao.PersonaRepository
import com.backendev.restapi.exception.BusinessException
import com.backendev.restapi.exception.NotFoundException
import com.backendev.restapi.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service    // apuntador que establece que esta clase se encargará de implementar los metodos creados en IPersonaBusiness
class PersonaBusiness : IPersonaBusines {

    @Autowired    // Inyecta dependencias de PersonaBusiness el repository que tiene todos los métodos
    val personaRepository: PersonaRepository? = null    // uso la interface

    @Throws(BusinessException::class)
    // LISTAR PERSONAS DE LA BASE DE DATOS
    override fun list(): List<Persona> {
        // todo metodo que guarde u obtenga datos de la base debe llevar TRY CATCH
        try {
            return personaRepository!!.findAll()    // los !! establecen que personaRepository ya no es nulo
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class) // usa throws de BusinessException y NotFoundException
    // CARGAR PERSONA DE LA BASE DE DATOS
    override fun load(idPersona: Long): Persona {
        val op: Optional<Persona>           // sirve para ver si el dato está en a base de datos
        try {
            op = personaRepository!!.findById(idPersona)    // optiene la persona con el ID
        } catch (e: Exception) {
            throw BusinessException(e.message)              // si no encuentra muestra error de la capa de negocios
        }

        if (!op.isPresent) { // si el valor no está presente en la base de datos
            throw NotFoundException("No se encontró la persona con id $idPersona")  // Muestra error NotFoundExceotion
        }

        return op.get()     // retorna la persona si la encuentra

    }

    @Throws(BusinessException::class)
    // Guardar persona en base de datos
    override fun save(persona: Persona): Persona {
        try {
            return personaRepository!!.save(persona)    // retorna persona guardada en la BD
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    // Eliminar persona de la base de datos
    override fun remove(idPersona: Long) {
        val op: Optional<Persona>           // sirve para ver si el dato está en a base de datos
        try {
            op = personaRepository!!.findById(idPersona)    // optiene la persona con el ID
        } catch (e: Exception) {
            throw BusinessException(e.message)              // si no encuentra muestra error de la capa de negocios
        }

        if (!op.isPresent) { // si el valor no está presente en la base de datos
            throw NotFoundException("No se encontró la persona con id $idPersona")  // Muestra error NotFoundExceotion
        }else{
            try {
                personaRepository!!.deleteById(idPersona)   // Elimina persona de base de datos
            }catch (e:Exception){
                throw BusinessException(e.message)          // SI no lo puede eliminar muestra el mensaje de error
            }
        }
    }
}