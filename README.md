# 🚀 Simple CMS REST API Project

본 프로젝트는 **Spring Boot 4.0.3**과 **Java 25** 환경에서 구축된 콘텐츠 관리 시스템(CMS) 백엔드 API입니다.

## 1. 프로젝트 실행 방법

### 🛠️ 개발 및 실행 환경
- **Java**: JDK 25
- **Framework**: Spring Boot 4.0.3
- **Database**: H2 (In-Memory)
- **IDE**: STS 4 (Lombok 플러그인 필요)

## 2. 구현 내용 및 추가 구현 기능

### 📑 핵심 구현 내용
- **콘텐츠 CRUD API**: 게시글 등록, 상세 조회(조회수 증가), 목록 조회(Paging), 수정, 삭제 기능을 완벽히 구현했습니다.
- **DB Schema 설계**: JPA 엔티티 매핑을 통해 `contents`와 `member` 테이블 간의 관계를 설계하고, `Auditing`을 통해 데이터 생성/수정 시점을 자동 관리합니다.
- **데이터베이스**: 과제 요구사항에 따라 별도의 설치가 필요 없는 **H2 인메모리 DB**를 연동했습니다.

### 🛡️ 예외 처리 (Exception Handling)
- **비즈니스 예외**: 존재하지 않는 ID 조회 시 `IllegalArgumentException`을 발생시켜 클라이언트에게 명확한 에러 메시지를 반환합니다.
- **권한 방어**: 게시글 수정 및 삭제 시, **'작성자 본인'** 또는 **'관리자(ROLE_ADMIN)'** 여부를 서비스 계층에서 검증하여 부정 접근을 차단합니다.
- **인증 예외**: 인증되지 않은 사용자가 보안이 필요한 API를 호출할 경우 Spring Security가 `401 Unauthorized`를 반환하도록 설계했습니다.

### ✨ 추가 구현 기능
- **Spring Security 보안**:
  - **HTTP Basic Auth**: RESTful 환경에 최적화된 인증 방식을 도입했습니다.
  - **세부 접근 제어**: 목록/상세 조회는 누구나 가능(`permitAll`)하지만, 등록/수정/삭제는 인증된 사용자만 가능하도록 권한을 분리했습니다.
- **Swagger UI 연동**: 별도의 문서 제출 없이도 API 테스트와 명세 확인이 가능하도록 `SpringDoc`을 적용했습니다.
- **JUnit 5 테스트 코드**: Repository 및 Service 계층에 대한 단위 테스트를 작성하여 로직의 안정성을 확보했습니다.
---

## 3. 📖 API 주요 명세
| Method | URL | Description | Auth |
|---|---|---|---|
| GET | `/api/contents` | 콘텐츠 목록 조회 (Paging 지원) | PermitAll |
| GET | `/api/contents/{id}` | 콘텐츠 상세 조회 (조회수 증가) | PermitAll |
| POST | `/api/contents` | 콘텐츠 등록 | Authenticated |
| PUT | `/api/contents/{id}` | 콘텐츠 수정 (작성자/관리자 전용) | Authenticated |
| DELETE | `/api/contents/{id}` | 콘텐츠 삭제 (작성자/관리자 전용) | Authenticated |

## 4. 🧪 테스트 실행
- `src/test/java` 경로에 `MemberRepository` 조회 테스트 코드가 포함되어 있습니다. (JUnit5 활용)

## 5. 사용한 AI 도구 및 참고 자료
- **Gemini**: gradle 설정 및 코드 검증과 최종 README 구조화에 활용했습니다.
