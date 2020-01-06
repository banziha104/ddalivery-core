package ddalivery.jwt

import org.apache.commons.lang3.StringUtils

class JwtHeaderTokenExtractor : TokenExtractor {
    override fun extract(header: String): String {
        var jwtToken: String? = null
        if (StringUtils.isNotBlank(header) && header.length > HEADER_PREFIX.length) {
            jwtToken = header.substring(HEADER_PREFIX.length, header.length)
        }
        return jwtToken!!
    }

    companion object {
        var HEADER_PREFIX = "Bearer "
    }
}
