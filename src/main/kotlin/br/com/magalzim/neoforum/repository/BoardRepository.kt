package br.com.magalzim.neoforum.repository

import br.com.magalzim.neoforum.model.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long>