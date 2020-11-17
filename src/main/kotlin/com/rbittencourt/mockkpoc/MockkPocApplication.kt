package com.rbittencourt.mockkpoc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MockkPocApplication

fun main(args: Array<String>) {
	runApplication<MockkPocApplication>(*args)
}
