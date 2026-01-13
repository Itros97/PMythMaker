package org.softwareanvil.domain.models.enums

enum class SyllableCategory(val dbValue: String) {
    COUNTRY("country"),
    PERSON("person"),
    CITY("city"),
    GOD("god"),
    RELIGION("religion");

    companion object {
        fun fromDb(value: String): SyllableCategory =
            entries.first { it.dbValue == value }
    }
}
