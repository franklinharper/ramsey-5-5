package com.frank

import java.io.File

// -99 is a dummy value use to make
val inverses = arrayOf(-99, 1, 21, 14, 11, 17, 7, 6, 16, 19, 13, 4, 18, 10, 3, 20, 8, 5, 12, 9, 15, 2)

fun main() {
    // Stdout can be redirected to a file in "Run Configuration / Logs / Save console output to file"
    val fileName = "levis_c43_results.txt"
    println("Starting execution: $fileName")
    System.out.flush()
    File(fileName).forEachLine { line ->
        val jumpNumbers = com.frank.graphviz.parse(line)

        val failed = !isHypothesisValid(jumpNumbers)
        println("jumpNumbers: $jumpNumbers")
        if (failed) {
            println("hypothesis failed for jumpNumbers: $jumpNumbers")
        } else {
            println("hypothesis succeeded for jumpNumbers: $jumpNumbers")
        }

        val complementaryJumpNumbers = com.frank.graphviz.complement(jumpNumbers)
        val complementFailed = !isHypothesisValid(complementaryJumpNumbers)
        println("complementary jump numbers: $complementaryJumpNumbers")
        if (complementFailed) {
            println("hypothesis failed for complementary jumpNumbers: $complementaryJumpNumbers")
        } else {
            println("hypothesis succeeded for jumpNumbers: $complementaryJumpNumbers")
        }

        println()
        System.out.flush()
    }
}

fun isHypothesisValid(jumpNumbers: List<Int>): Boolean {
    return jumpNumbers
        .map { startingPoint ->
            ramseyRow(startingPoint, jumpNumbers)
        }.any { row ->
            println("row: $row")
            1 in row && 2 in row && 3 in row
        }
}

fun ramseyRow(startingPoint: Int, jumpNumbers: List<Int>): List<Int> {
    return jumpNumbers
        .map { jumpNumber ->
            val inverse = inverses[jumpNumber]
            ramseyProd(43, startingPoint, inverse)
        }
}

fun ramseyProd(modulo: Int, jumpNumber: Int, inverse: Int): Int {
    val res = (jumpNumber * inverse) % modulo
    return if (res > modulo / 2)
        modulo - res
    else
        res
}
