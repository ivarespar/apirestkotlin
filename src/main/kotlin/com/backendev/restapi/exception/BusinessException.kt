package com.backendev.restapi.exception

// Creo excepcion que retorne mensaje de error
class BusinessException(message:String?):Exception(message) {   // se agrega ? para establecer que message puede ser nulo
}