# 🎧 MyPlaylistApp

**MyPlaylistApp**은 음악을 중심으로 친구들과의 소셜 인터랙션을 즐기고, 나만의 플레이리스트를 만들고 공유할 수 있는 **소셜 뮤직 플랫폼**입니다.  
Jetpack Compose 기반의 Android 프론트엔드와 Ktor 서버 기반의 백엔드로 구성되어 있으며, MVVM 아키텍처와 REST API 통신을 통해 다양한 음악 경험을 제공합니다.

---

## 🧩 프로젝트 구조 및 진행 현황

- **프론트엔드**: Kotlin + Jetpack Compose
- **백엔드**: Kotlin + Ktor
- **아키텍처**: MVVM + Repository 패턴
- **시연 영상 기준 브랜치**: `be_jh` 최신 커밋 기준
- **API 연동 진행 중인 브랜치**: `be_jh`, `be_hm`
  - `/friends` 및 `/profile` API 연동 중

---

## 🛠️ 핵심 기능 요약

### 🔀 탭 구조
- 탭 전환: Home / Playlist / Friends

### 🏠 Home
- 최근 생성 플레이리스트 조회
- 친구 업데이트 리스트 (무한 스크롤)
- 트렌딩 콘텐츠: 최근 인기 노래/플리

### 🎵 Playlist
- 내가 만든 플레이리스트
- 좋아요한 플레이리스트
- 차트 기반 플레이리스트
- 각 리스트: 하트/메뉴/상세 이동 가능

### 👥 Friends
- 친구 / 친한 친구 목록
- 친구 추가 및 검색
- 친구 활동 내역 확인
- 친구 프로필 및 선호 장르 조회

---

## 📡 API 연동 현황

📡 API 연동 현황
대부분의 핵심 API는 이미 연동이 완료되었습니다.

현재 연동 중인 주요 API:

/friends/{id}: 친구 목록 조회 (all/close)

/profile/{id}: 유저 프로필 조회

해당 연동 작업은 be_jh, be_hm 브랜치에서 진행 중입니다.

시연 영상은 be_jh 브랜치의 최신 커밋 기준으로 제작되었습니다.
