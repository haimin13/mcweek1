package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.data.FriendInfo
import org.example.data.UserRepository
//import org.example.data.User // Import the User data class

fun Route.friendsRoutes() {
    route("/friends") {
        get() {
            call.respondText("Hello from friends!")
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val type = call.request.queryParameters["type"] ?: "all"

            if (id == null) {
                call.respondText("Invalid ID", status = io.ktor.http.HttpStatusCode.BadRequest)
                return@get
            }
            if (type != "all" && type != "close") {
                call.respondText("Invalid type", status = io.ktor.http.HttpStatusCode.BadRequest)
                return@get
            }

            val user = UserRepository.findUserById(id)

            if (user == null) {
                call.respondText("User not found", status = io.ktor.http.HttpStatusCode.NotFound)
                return@get
            }

            val friends = when (type) {
                "close" -> user.closeFriends?.map { friendId ->
                    val friend = UserRepository.findUserById(friendId)
                    FriendInfo(
                        friendId = friendId,
                        nickname = friend?.nickname ?: "",
                        thumbnailId = friend?.thumbnaliId,
                        isCloseFriend = true
                    )
                } ?: emptyList()
                else -> user.friends?.map { friendId ->
                    val friend = UserRepository.findUserById(friendId)
                    FriendInfo(
                        friendId = friendId,
                        nickname = friend?.nickname ?: "",
                        thumbnailId = friend?.thumbnaliId,
                        isCloseFriend = user.closeFriends?.contains(friendId) ?: false
                    )
                } ?: emptyList()
            }

            call.respond(friends)
        }
    }
}
