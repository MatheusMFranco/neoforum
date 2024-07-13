package br.com.magalzim.neoforum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val javaMailSender: JavaMailSender) {

    fun notify(author: String) {
        val message = SimpleMailMessage()
        message.subject = "[Received Messages] Topic"
        message.text = "Hello, your topic was answered"
        message.setTo(author)
        javaMailSender.send(message)
    }
}
