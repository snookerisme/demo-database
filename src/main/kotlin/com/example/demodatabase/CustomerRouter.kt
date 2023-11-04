package com.example.demodatabase

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CustomerRouter {

    @Bean
    fun demoRoutes(customerHandler: CustomerHandler
    ) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/save", customerHandler::save)
            POST("/get", customerHandler::get)
            POST("/update", customerHandler::update)
            POST("/delete", customerHandler::delete)
            GET("/getAll", customerHandler::getAll)
        }
    }

}