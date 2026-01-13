package org.softwareanvil.data.seed

import app.cash.sqldelight.db.SqlDriver
import org.softwareanvil.db.SyllablesQueries

class SyllableSeedInitializer(
    private val driver: SqlDriver,
    private val syllablesQueries: SyllablesQueries
) {

    fun seedIfNeeded(sqlFileContent: String) {
        val count = syllablesQueries.countAll().executeAsOne()
        if (count > 0L) return

        val statements = splitSqlStatements(sqlFileContent)

        syllablesQueries.transaction {
            statements.forEach { sql ->
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
