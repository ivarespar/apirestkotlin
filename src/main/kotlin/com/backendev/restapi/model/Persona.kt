package com.backendev.restapi.model

import java.util.*
import javax.persistence.*

@Entity                     // Llamo apuntador de ORM igual que laravel
@Table(name="persona")      // etiqueta TABLA llamada 'persona'

//  Declaro data class Persona con sus atributos
data class Persona(val dni:Long = 0,val nombre:String ="",
                    val apellido:String ="",val fechaNac:Date ?= null ) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0
}