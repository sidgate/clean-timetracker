package io.reflectoring.cleantimetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CleanTimetrackerApplication

fun main(args: Array<String>) {
    runApplication<CleanTimetrackerApplication>(*args)
}
