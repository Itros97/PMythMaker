package org.softwareanvil.testutil

import kotlin.test.Test
import kotlin.test.assertEquals
import org.softwareanvil.testutil.createTestDatabase

class SyllablesQueriesTest {

    @Test
    fun insert_and_select_syllables_work() {
        val db = createTestDatabase()

        // Insertar datos
        db.syllablesQueries.insert(
            type = "prefix",
            category = "name",
            syllabe = "ka",
            weight = 2
        )

        db.syllablesQueries.insert(
            type = "core",
            category = "name",
            syllabe = "dor",
            weight = 1
        )

        // Leer datos
        val prefixes = db.syllablesQueries
            .selectByType("prefix")
            .executeAsList()

        // Verificaciones
        assertEquals(1, prefixes.size)
        assertEquals("ka", prefixes.first().syllabe)
        assertEquals(2, prefixes.first().weight)
    }
}
