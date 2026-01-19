package org.softwareanvil.data.seed

import app.cash.sqldelight.db.SqlDriver
import org.softwareanvil.db.SyllablesQueries

class SyllableSeedInitializer(
    private val driver: SqlDriver,
    private val syllablesQueries: SyllablesQueries
) {

    private fun validateSeedStatement(sql: String) {
        val normalized = sql.trim().uppercase()

        require(normalized.startsWith("INSERT")) {
            """
        Invalid seed statement detected.
        Seed files may ONLY contain INSERT statements.
        
        Offending SQL:
        $sql
        """.trimIndent()
        }
    }

    fun seedIfNeeded(sqlFileContent: String) {
        val count = syllablesQueries.countAll().executeAsOne()
        if (count > 0L) return

        val statements = splitSqlStatements(sqlFileContent)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .filterNot { it.startsWith("--") }

        require(statements.isNotEmpty()) {
            "Seed SQL is empty after filtering"
        }

        syllablesQueries.transaction {
            statements.forEach { sql ->
                validateSeedStatement(sql)

                driver.execute(
                    identifier = null,
                    sql = sql,
                    parameters = 0
                )
            }
        }
    }



    private fun splitSqlStatements(sql: String): List<String> =
        sql
            .split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
}
