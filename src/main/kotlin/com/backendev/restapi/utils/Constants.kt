package com.backendev.restapi.utils

// Declaro constantes para la conexión
class Constants {
    companion object{
        private const val  URL_API_BASE = "/api"
        private const val  URL_API_VERSION = "/v1"
        private const val  URL_BASE = URL_API_BASE + URL_API_VERSION

        // Base API endpoint para persona
        const val URL_BASE_PERSONAS = "$URL_BASE/personas"     // URL a la que se va a conectar
                                                               // ejemplo: /api/v1/personas

    }
}