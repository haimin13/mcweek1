package org.example.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.models.FriendInfo
import org.example.models.User
import org.example.models.SearchResponse
import org.example.storage.UserStorage
import io.ktor.http.HttpStatusCode

fun Route.friendsRoute() {
    route("/friends") {
        get() {
            call.respondText("Hello from friends!")
        }
        get("/{id}") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                val type = call.request.queryParameters["type"] ?: "all"

                if (id == null) {
                    call.respondText("Invalid ID", status = io.ktor.http.HttpStatusCode.BadRequest)
                    return@get
                }
                if (type != "all" && type != "close") {
                    call.respondText(
                        "Invalid type",
                        status = io.ktor.http.HttpStatusCode.BadRequest
                    )
                    return@get
                }

                val user = UserStorage.findUserById(id)

                if (user == null) {
                    call.respondText(
                        "User not found",
                        status = io.ktor.http.HttpStatusCode.NotFound
                    )
                    return@get
                }

                // return List<FriendInfo>
                val friendsList = when (type) {
                "close" -> user.closeFriends?.map { friendId ->
                    val friend = UserStorage.findUserById(friendId)
                    FriendInfo(
                        friendId = friendId,
                        nickname = friend?.nickname ?: "",
                        thumbnailId = friend?.thumbnailId,
                        isCloseFriend = true
                    )
                } ?: emptyList()

                else -> user.friends?.map { friendId ->
                    val friend = UserStorage.findUserById(friendId)
                    FriendInfo(
                        friendId = friendId,
                        nickname = friend?.nickname ?: "",
                        thumbnailId = friend?.thumbnailId,
                        isCloseFriend = user.closeFriends?.contains(friendId) ?: false
                    )
                } ?: emptyList()
            }
            call.respond(friendsList)
        } catch (e: Exception) {
            call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
            e.printStackTrace()
        }
        }
        get("/{id}/search") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                val targetName = call.request.queryParameters["targetName"]
                if (id == null) {
                    call.respondText("Invalid parameters", status = HttpStatusCode.BadRequest)
                    return@get
                }
                val result = UserStorage.searchFriend(id, targetName)

                // return SearchResponse
                val response = when (result) {
                    0 -> SearchResponse(success = false, message = "Invalid requester")
                    1 -> SearchResponse(success = false, message = "Query is empty")
                    2 -> SearchResponse(success = false, message = "User not found")
                    3 -> SearchResponse(success = false, message = "You can't add yourself a friend.")
                    4 -> SearchResponse(success = false, message = "Already friends")
                    else -> SearchResponse(success = true, message = "You can add this user as a friend")
                }
                call.respond(response)
            } catch (e: Exception) {
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
                e.printStackTrace()
            }
        }
        post("/{id}/add") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                val targetId = call.request.queryParameters["targetId"]?.toIntOrNull()

                if (id == null || targetId == null) {
                    call.respondText("Invalid parameters", status = HttpStatusCode.BadRequest)
                    return@post
                }

                // 친구 추가 로직
                val result = UserStorage.addFriend(id, targetId)
                if (result) {
                    call.respond(SearchResponse(success = true, message = "친구 추가 완료"))
                } else {
                    call.respond(SearchResponse(success = false, message = "친구 추가 실패"))
                }
            } catch (e: Exception) {
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
                e.printStackTrace()
            }
        }
        delete("/{id}/remove/{targetId}") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                val targetId = call.parameters["targetId"]?.toIntOrNull()

                if (id == null || targetId == null) {
                    call.respondText("Invalid parameters", status = HttpStatusCode.BadRequest)
                    return@delete
                }

                val result = UserStorage.removeFriend(id, targetId)
                if (result) {
                    call.respond(SearchResponse(success = true, message = "친구 삭제 완료"))
                } else {
                    call.respond(SearchResponse(success = false, message = "친구 삭제 실패"))
                }
            } catch (e: Exception) {
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
                e.printStackTrace()
            }
        }
        post("/{id}/close/add/{targetId}") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                val targetId = call.parameters["targetId"]?.toIntOrNull()

                if (id == null || targetId == null) {
                    call.respondText("Invalid parameters", status = HttpStatusCode.BadRequest)
                    return@post
                }

                val result = UserStorage.addCloseFriend(id, targetId)
                if (result) {
                    call.respond(SearchResponse(success = true, message = "친한 친구 추가 완료"))
                } else {
                    call.respond(SearchResponse(success = false, message = "친한 친구 추가 실패"))
                }
            } catch (e: Exception) {
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
                e.printStackTrace()
            }
        }
        delete("/{id}/close/remove/{targetId}") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                val targetId = call.parameters["targetId"]?.toIntOrNull()

                if (id == null || targetId == null) {
                    call.respondText("Invalid parameters", status = HttpStatusCode.BadRequest)
                    return@delete
                }

                val result = UserStorage.removeCloseFriend(id, targetId)
                if (result) {
                    call.respond(SearchResponse(success = true, message = "친한 친구 삭제 완료"))
                } else {
                    call.respond(SearchResponse(success = false, message = "친한 친구 삭제 실패"))
                }
            } catch (e: Exception) {
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
                e.printStackTrace()
            }
        }
    }
}
