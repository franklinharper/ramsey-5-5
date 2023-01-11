package com.frank.graphviz

import java.io.File
import kotlin.system.exitProcess

fun main() {
    // Stdout can be redirected to a file in "Run Configuration / Logs / Save console output to file"
    val fileName = "levis_c43_results.txt"
    File(fileName).forEachLine { line ->
        println("graph G {")

        for (i in 0..20) {
            println("$i -- ${i + 1}")
        }
        println("21 -- 0")

        val jumpNumbers = parse(line)
        printSubGraph(jumpNumbers, color = "blue")
        val complementaryJumpNumbers = complement(jumpNumbers)
        printSubGraph(complementaryJumpNumbers, color = "red")
        println("}")
        exitProcess(0)
    }
}

private fun printSubGraph(jumpNumbers: List<Int>, color: String) {
    for (i in jumpNumbers.indices) {
        println("0 -- ${jumpNumbers[i]} [color=\"$color\"]")
    }
}

fun parse(input: String): List<Int> {
    val columns = input.split("\t")
    val jumpNumbers = columns[4]
    return jumpNumbers
        .split("_")
        .drop(1) // Ignore the prefix. E.g. "c43".
        .map { it.toInt() }
}

// A list of numbers containing 1, 2, 3, ... 21
private val allJumpNumbers = List(21) { index ->
    index + 1
}

fun complement(jumpNumbers: List<Int>): List<Int> {
    return allJumpNumbers.mapNotNull { jumpNumber ->
        if (jumpNumber in jumpNumbers) null else jumpNumber
    }
}
