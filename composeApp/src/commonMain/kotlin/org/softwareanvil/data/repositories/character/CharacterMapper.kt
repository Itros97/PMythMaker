package org.softwareanvil.data.repositories.character

import org.softwareanvil.db.Characters
import org.softwareanvil.db.SelectByCountryName
import org.softwareanvil.domain.models.Character
import org.softwareanvil.domain.models.Country

fun Characters.toDomain(): Character =
    Character(
        id = id,
        firstName = first_name,
        lastName = last_name,
        age = age?.toInt(),
        country = null,
        occupation = occupation,
        description = description,
        createdAt = created_at
    )

fun SelectByCountryName.toDomain(): Character =
    Character(
        id = id,
        firstName = first_name,
        lastName = last_name,
        age = age?.toInt(),
        country =
            if (country_id != null && country_name != null) {
                Country(
                    id = country_id,
                    name = country_name,
                    description = country_description,
                    foundationYear = null,
                    motto = null
                )
            } else {
                null
            },
        occupation = occupation,
        description = description,
        createdAt = created_at
    )

