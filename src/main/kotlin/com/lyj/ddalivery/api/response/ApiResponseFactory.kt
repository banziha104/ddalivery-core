package com.lyj.ddalivery.api.response

import java.lang.Exception

class ApiResponseFactory {

    companion object {
        val DEFAULT_OK = ApiResponse<String>("OK","요청이성공하였습니다",null)

        fun <T> createOK(data: T): ApiResponse<T> {
            return ApiResponse(ApiResponseCode.OK.getKey(),ApiResponseCode.OK.getValue(), data)
        }

        fun createException(e : Exception) : ApiResponse<*>{
//            return ApiResponse("FAIL",e.message,e.stackTrace.contentDeepToString())
            return ApiResponse("FAIL",e.message,null)
        }
        fun createException(e: ApiException): ApiResponse<ApiException> {
            return ApiResponse(e.status.getKey(),e.message, e)
        }

        fun createException(code: ApiResponseCode, message: String): ApiResponse<String> {
            return ApiResponse(code.getKey(), message, "")
        }

        fun <T> createException(code: ApiResponseCode, data: T): ApiResponse<T> {
            return ApiResponse<T>(code.getKey(),code.getValue(), data)
        }
    }
}