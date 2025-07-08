package org.example.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.*
import org.example.models.Playlist
import org.example.storage.PlaylistStorage
import io.ktor.http.*


fun Route.playlistRoute() {
  // 플리 추가
  post("/playlists/add") {
    try {
        val playlist = call.receive<Playlist>()
        println("받은 플리: $playlist")
        PlaylistStorage.add(playlist)
        call.respondText("플리 추가 완료")
    } catch (e: Exception) {
        println("오류 발생: ${e.message}")
        // call.respond(HttpStatusCode.BadRequest, "역직렬화 오류: ${e.message}")
    }
  }

  // 유저의 플리 목록 조회
  get("/playlists/list") {
      val id = call.request.queryParameters["id"]?.toIntOrNull()
      val type = call.request.queryParameters["type"] ?: "all"

      if (id == null) {
          call.respond(HttpStatusCode.BadRequest, "쿼리 파라미터 'id'는 필수입니다.")
          return@get
      }

      val result = PlaylistStorage.getByUser(id, type)
      call.respond(result)
  }

  // 최근 생성된 플리 목록 조회
  get("/playlists/recent") {
    val result = PlaylistStorage.playlists.sortedByDescending { it.createdAt }.take(20)
    call.respond(result)
  }

  // 특정 플리 상세 조회
  get("/playlists/{id}") {
    val playlistId = call.parameters["id"]?.toIntOrNull()
    if (playlistId == null) {
      call.respond(HttpStatusCode.BadRequest, "플리 ID가 유효하지 않습니다.")
      return@get
    }

    val playlist = PlaylistStorage.playlists.find { it.id == playlistId }
    if (playlist == null) {
      call.respond(HttpStatusCode.NotFound, "해당 ID의 플리를 찾을 수 없습니다.")
    } else {
      call.respond(playlist)
    }
  }
}
