package com.frank.graphviz

import java.io.File

fun main() {
    // Stdout can be copied to a file by setting "Run Configuration / Logs / Save console output to file"
    val fileName = "mckay248_molnar_figure_7b.txt"
    val adjacencyMatrix = parseAdjacencyMatrix(fileName)
    println("graph G {")
    println("// Adjacency matrix from $fileName")
    println("bgcolor=\"transparent\"")
    for (rowIndex in adjacencyMatrix.indices) {
        for (colIndex in rowIndex + 1..adjacencyMatrix.lastIndex) {
            val isEdge = adjacencyMatrix[rowIndex][colIndex]
            if (isEdge) {
                println("$rowIndex -- $colIndex [color=\"red\"]")
            }
        }
    }
    println("}")
}

fun parseAdjacencyMatrix(fileName: String): List<List<Boolean>> {
    val lines = File(fileName).readLines()
    return lines.map { line ->
        line.split(" ")
            .filter {
                it.isNotEmpty()
            }
            .map {
                it.toInt() == 1
            }
    }
}
