package br.com.magalzim.neoforum.util

import br.com.magalzim.neoforum.model.Topic
import java.util.*

fun List<Topic>.getTopic(id: Long): Optional<Topic> {
    return this.stream().filter { item ->
        item.id == id
    }.findFirst()
}