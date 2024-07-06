package br.com.magalzim.neoforum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NeoforumApplication

fun main(args: Array<String>) {
	runApplication<NeoforumApplication>(*args)
}
