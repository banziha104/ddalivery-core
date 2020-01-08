package com.lyj.ddalivery.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.commons.lang3.math.NumberUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider {
    //@NonNull
//private final UserDetailsService userDetailsService;
    private val settings = JwtSettings()
    //@NonNull
//private final TokenExtractor tokenExtractor;
    /**
     * Jwt 토큰 생성
     *
     * @param session 사용자 정보
     * @param roles 사용자 역할
     * @return JWT 토큰
     */
    fun createToken(session: JWTSession): String {

        val currentTime = LocalDateTime.now()
        val claims: Claims = Jwts.claims().setSubject(session.loginId)

        val issuedAt = Date.from(currentTime.toInstant(ZoneOffset.UTC))
        val expiration = Date.from(currentTime.plusHours(settings.tokenExpirationTime).toInstant(ZoneOffset.UTC))
        claims.put("name", session.name)
        claims.put("address", session.address)

        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuer(settings.tokenIssuer) // 토큰 발행 서비스
                .setIssuedAt(issuedAt) // 토큰 발행일자
                .setExpiration(expiration) // 토큰 만료일자
                .signWith(SignatureAlgorithm.HS512, settings.tokenSigningKey) // 암호화 알고리즘, secret값 세팅
                .compact()
    }

    /**
     * 인증토큰으로 사용자 저정보 조회
     *
     * @return 사용자 정보
     */
    val currentUser: JWTSession
        get() = SecurityContextHolder.getContext().authentication.principal as JWTSession

    /**
     * Jwt 토큰으로 인증 정보를 조회
     *
     * @param token
     * @return 인증 정보
     */
    fun getAuthentication(token: String?): Authentication { //UserDetails userDetails = userDetailsService.loadUserByUsername(this.getLoginId(token));
        val claims: Claims = Jwts.parser().setSigningKey(settings.tokenSigningKey).parseClaimsJws(token).getBody()
        val jwtSession: JWTSession = JWTSession(
                claims.get("accountId") as Long,
                claims.get("loginId").toString(),
                claims.get("address").toString(),
                claims.get("name").toString())
        return UsernamePasswordAuthenticationToken(jwtSession, "")
    }

    /**
     * Jwt 토큰에서 게정 고유번호 정보 추출
     *
     * @param token
     * @return
     */
    fun getAccountId(token: String?): Long {
        return NumberUtils.toLong(Jwts.parser().setSigningKey(settings.tokenSigningKey).parseClaimsJws(token).getBody().getSubject())
    }

    fun getLoginId(token: String?): String {
        return Jwts.parser().setSigningKey(settings.tokenSigningKey).parseClaimsJws(token).getBody().get("loginId").toString()
    }

    /**
     * Request의 Header에서 token 파싱 : "Authorization: jwt토큰"
     *
     * @param request
     * @return
     */
    fun resolveToken(request: HttpServletRequest): String {
        val tokenPayload = request.getHeader("Authorization")
        val tokenExtractor: TokenExtractor = JwtHeaderTokenExtractor()
        return tokenExtractor.extract(tokenPayload)
    }

    /**
     * Jwt 토큰의 유효성 + 만료일자 확인
     *
     * @param jwtToken
     * @return
     */
    fun validateToken(jwtToken: String?): Boolean {
        return try {
            val claims: Jws<Claims> = Jwts.parser().setSigningKey(settings.tokenSigningKey).parseClaimsJws(jwtToken)
            !claims.getBody().getExpiration().before(Date())
        } catch (e: Exception) {
            false
        }
    }
}