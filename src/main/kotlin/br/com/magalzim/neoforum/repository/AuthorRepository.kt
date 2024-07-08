package br.com.magalzim.neoforum.repository

import br.com.magalzim.neoforum.model.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository: JpaRepository<Author, Long>