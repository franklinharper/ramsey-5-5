package com.frank.graphviz

import org.jgrapht.Graph
import org.jgrapht.alg.clique.BronKerboschCliqueFinder
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph


fun main() {
    // Stdout can be copied to a file by setting "Run Configuration / Logs / Save console output to file"
    val fileName = "c43_1_2_3_4_7_11_14_15_17_19_20.dis.com"
    val adjacencyMatrix = parseAdjacencyMatrix(fileName)
    val graph = adjacencyMatrixToGraph(adjacencyMatrix)
    println(graph.toString())
    BronKerboschCliqueFinder(graph).iterator()
        .forEach { clique ->
            if (clique.size > 4) {
                println("K_${clique.size}: $clique")
            }
        }
}

private fun adjacencyMatrixToGraph(adjacencyMatrix: List<List<Boolean>>): Graph<String, DefaultEdge> {

    val g = SimpleGraph<String, DefaultEdge>(DefaultEdge::class.java)
    for (i in 0..42) {
        g.addVertex("v$i")
    }
    for (rowIndex in adjacencyMatrix.indices) {
        for (colIndex in rowIndex..adjacencyMatrix.lastIndex) {
            if (adjacencyMatrix[rowIndex][colIndex]) {
                g.addEdge("v${rowIndex}", "v${colIndex}")
            }
        }
    }
    return g
}
