package br.com.magalzim.neoforum.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}
