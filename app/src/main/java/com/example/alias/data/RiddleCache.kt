package com.example.alias.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RiddleCache : RealmObject(), Riddle {
    @PrimaryKey
    var riddle: String = ""
    var answer: String = ""

    override suspend fun <T> map(mapper: Riddle.Mapper<T>): T = mapper.map(riddle, answer)
}
