package com.lyj.ddalivery.jwt


class JwtSettings {
    val tokenExpirationTime: Long = 365
    val tokenIssuer: String = "http://ddalivery.com"
    val tokenSigningKey: String = "ddaliveryapp"
    val refreshTokenExpTime: Long = 365
}
