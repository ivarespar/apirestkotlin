package com.backendev.restapi.web

import com.backendev.restapi.business.IPersonaBusines
import com.backendev.restapi.exception.BusinessException
import com.backendev.restapi.exception.NotFoundException
import com.backendev.restapi.model.Persona
import com.backendev.restapi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException.NotFound

@RestController                 // ANOTADOR, APUNTADOR que permite usar dependencias
@RequestMapping(Constants.URL_BASE_PERSONAS)     // genera el URL. Ejemplo: localhost:8080/api/v1/personas
class PersonaRestControl {
    @Autowired
    val personaBusiness: IPersonaBusines ?= null    // Inyectar métodos de personaBusiness al RestController
                                                    // de esta forma puedo llamar a los métodos de esta forma:
                                                    //  personaBusiness.list() , etc

    @GetMapping()                                   // permite poner la URL en linea
    fun list(): ResponseEntity<List<Persona>>{      // metodo que extiende de RerponseEntity que
                                                    // retornará una lista de personas
        return try {
            ResponseEntity(personaBusiness!!.list(),HttpStatus.OK)  // Retorna lista de personas, con http estatus "OK"
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)        // Retorna error Http cuando no encuentra en el servidor
        }
    }

    @GetMapping("/{id}")                            // Traer persona dependiendo de ID
    // Path que va cambiando, que retorna dato de tipo Persona
    fun load(@PathVariable("id") idPersona:Long): ResponseEntity<Persona>{
        return try {
            ResponseEntity(personaBusiness!!.load(idPersona),HttpStatus.OK)     // Retorna datos de persona dependiendo del ID
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)    // Error si hubo error en el servidor de respuesta
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)                // No encontró el ID
        }
    }

    @PostMapping("")                                                        // Colocar datos en la base
    fun insert(@RequestBody persona: Persona):ResponseEntity<Any> {          // RequestBody establece que en lo que voy  insertar debe ir un objeto de tipo persona
        return try {
            personaBusiness!!.save(persona)                                 // Guardar persona
            val responseHeader = HttpHeaders()                              // Creao un HTTPHeader para obtener el ID
            responseHeader.set("location",Constants.URL_BASE_PERSONAS + "/" + persona.id)   // y retornarlo en el Header
            ResponseEntity(responseHeader,HttpStatus.CREATED)               // Enviio respuesta Http: CREADO
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)                // Error interno del servidor
        }
    }

    @PutMapping("")                                                 // Actualizar datos en la base de datos
    fun update(@RequestBody persona: Persona): ResponseEntity<Any>{ // RequestBody establece que en lo que voy  insertar debe ir un objeto de tipo persona
        return try {
            personaBusiness!!.save(persona)                         // Guarda persona
            ResponseEntity(HttpStatus.OK)
        } catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)                // Error interno del servidor
        }
    }

    @DeleteMapping("/{id}")                                        // Elimina de la base de datos
    fun delete(@PathVariable("id") idPersona: Long): ResponseEntity<Any>{
        return try {
            personaBusiness!!.remove(idPersona)
            ResponseEntity(HttpStatus.OK)
        } catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)    // Error interno del servidor

        } catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)                // No encontró el ID
        }
    }

}