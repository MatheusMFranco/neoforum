package br.com.magalzim.neoforum.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class BoardController {

    @GetMapping
    fun find(): String {
        return "Board!"
    }

}