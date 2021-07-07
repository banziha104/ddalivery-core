package com.lyj.ddalivery.enums.model

class EnumStringValue(enumModel: EnumStringModel) {
    val key: String
    val value: String

    init {
        key = enumModel.key
        value = enumModel.value
    }
}
