package org.softwareanvil.domain.generator

interface Generator<T> {
    fun generate(): T
}