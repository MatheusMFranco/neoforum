package br.com.magalzim.neoforum.exception

import java.lang.RuntimeException
import kotlin.reflect.KClass

class NotFoundException(entity: KClass<*>) : RuntimeException("${entity.simpleName} ID not found!")