package br.com.magalzim.neoforum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class NeoforumApplication

fun main(args: Array<String>) {
	runApplication<NeoforumApplication>(*args)
}
