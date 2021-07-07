package com.lyj.ddalivery.jwt

interface TokenExtractor {
    fun extract(payload: String): String
}
