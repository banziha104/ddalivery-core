package com.lyj.ddalivery.ddalivery.base

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.domain.Persistable
import java.io.Serializable

open abstract class BaseEntity<PK : Serializable?> : Persistable<PK>, Serializable {
    @JsonIgnore
    override fun isNew(): Boolean {
        return null == id
    }
}
