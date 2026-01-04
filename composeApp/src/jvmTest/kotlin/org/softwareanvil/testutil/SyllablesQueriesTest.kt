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
            syllable = "ka",
            weight = 2
        )

        db.syllablesQueries.insert(
            type = "core",
            category = "name",
            syllable = "dor",
            weight = 1
        )

        // Leer datos
        val prefixes = db.syllablesQueries
            .selectByType("prefix")
            .executeAsList()

        // Verificaciones
        assertEquals(1, prefixes.size)
        assertEquals("ka", prefixes.first().syllable)
        assertEquals(2, prefixes.first().weight)
    }

    @Test
    fun delete_single_syllable_by_id() {
        val db = createTestDatabase()

        // Insertamos dos sílabas
        db.syllablesQueries.insert("prefix", "name", "ka", 1)
        db.syllablesQueries.insert("prefix", "name", "el", 1)

        // Leemos todas
        val syllables = db.syllablesQueries
            .selectByType("prefix")
            .executeAsList()

        assertEquals(2, syllables.size)

        val idToDelete = syllables.first().id

        // Borramos solo una
        db.syllablesQueries.deleteById(idToDelete)

        // Volvemos a leer
        val remaining = db.syllablesQueries
            .selectByType("prefix")
            .executeAsList()

        assertEquals(1, remaining.size)
        assertEquals("el", remaining.first().syllable)
    }

}
