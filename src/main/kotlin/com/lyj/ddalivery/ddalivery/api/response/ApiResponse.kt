package com.lyj.ddalivery.ddalivery.api.response

import com.lyj.ddalivery.ddalivery.api.response.ApiException
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString

@Getter
@Setter
class ApiResponse<T>(var code : String? , var message: String? , var data: T?)
