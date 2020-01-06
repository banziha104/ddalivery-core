package com.lyj.ddalivery.ddalivery.api.response

data class ApiResponse<T>(var code : String? , var message: String? , var data: T?)
