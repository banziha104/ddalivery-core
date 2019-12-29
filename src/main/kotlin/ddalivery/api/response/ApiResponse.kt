package com.lyj.ddalivery.ddalivery.api.response

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ApiResponse<T>(var code : String? , var message: String? , var data: T?)
