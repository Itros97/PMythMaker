package org.softwareanvil.domain.models.enums

enum class SyllableType(val dbValue: String) {
    PREFIX("prefix"),
    CORE("core"),
    SUFFIX("suffix");

    companion object {
        fun fromDb(value: String): SyllableType =
            entries.first { it.dbValue == value }
    }
}
