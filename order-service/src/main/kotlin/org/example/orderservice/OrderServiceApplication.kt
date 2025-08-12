package org.example.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
class OrderServiceApplication

fun main(args: Array<String>) {
	runApplication<OrderServiceApplication>(*args)
}
