package br.com.magalzim.neoforum.exception

import java.lang.RuntimeException

class NotFoundException(message: String?): RuntimeException(message) {
}