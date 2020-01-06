package com.lyj.ddalivery.ddalivery.api.response

class ApiResponseFactory {

    companion object {
        fun <T> createOK(data: T): ApiResponse<T> {
            return ApiResponse<T>(ApiResponseCode.OK.getKey(),ApiResponseCode.OK.getValue(), data)
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