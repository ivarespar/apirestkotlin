package com.backendev.restapi.exception

// Excepción que deuelve mensaje si el dato no está en la base
class NotFoundException(message:String?):Exception(message) {
}