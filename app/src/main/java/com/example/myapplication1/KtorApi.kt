package com.example.myapplication1

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun startKtorApi() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/") {
                call.respondText("Hello from Ktor!")
            }
            get("/data") {
                call.respond(mapOf("message" to "This is some data from Ktor API"))
            }
        }
    }.start(wait = false)
}