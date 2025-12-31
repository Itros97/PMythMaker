package org.softwareanvil.testutil

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.softwareanvil.db.PocketMythDatabase


fun createTestDatabase(): PocketMythDatabase {
    val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    PocketMythDatabase.Schema.create(driver)
    return PocketMythDatabase(driver)
}
