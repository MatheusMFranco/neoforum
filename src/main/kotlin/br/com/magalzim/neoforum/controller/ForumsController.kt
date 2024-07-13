package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.service.TopicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/forums")
class ForumsController(private val service: TopicService) {

    @GetMapping
    fun forums(model: Model): String {
        model.addAttribute("topicsByBoard", service.forums())
        return "forums"
    }

}