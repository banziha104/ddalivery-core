package com.lyj.ddalivery.enums.model

class EnumIntegerValue(enumModel: EnumIntegerModel) {
    val key: Int
    val value: String

    init {
        key = enumModel.key
        value = enumModel.value
    }
}
