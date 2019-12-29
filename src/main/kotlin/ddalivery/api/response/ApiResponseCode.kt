package com.lyj.ddalivery.ddalivery.api.response


import lombok.Getter
import lombok.RequiredArgsConstructor


@Getter
@RequiredArgsConstructor
enum class ApiResponseCode(var message : String) {
    OK("요청이 성공하였습니다."),
    BAD_PARAMETER("요청 파라미터가 잘못되었습니다."),
    NOT_FOUND("리소스를 찾지 못했습니다."),
    LOGINID_DEPLICATION("중복된 아이디 입니다."),
    UNAUTHORIZED("리소스에 접근하기 위한 권한이 없습니다"),
    FORBIDDEN("보유한 권한으로 접근할수 없는 리소스 입니다."),
    SERVER_ERROR("서버 에러입니다."),
    NO_CONTENTS("송신할 컨텐츠가 없습니다"),
    UNSUPPORTED_MEDIA_TYPE("Unsupported Media Type");

    fun getKey() = name
    fun getValue() = message

}