package com.frank.graphviz

data class Edge(
    val rowIndex: Int,
    val colIndex: Int,
)

fun main() {
    // Stdout can be copied to a file by setting "Run Configuration / Logs / Save console output to file"
    val fileName = "c43_1_2_3_4_7_11_14_15_17_19_20.cir"
    val disruptedDiagonalIndex = 2

    val adjacencyMatrix = parseAdjacencyMatrix(fileName)
    val n = adjacencyMatrix.size

    val disruptedMatrix = List(n) {
        MutableList(n) { false }
    }
    for (rowIndex in adjacencyMatrix.indices) {
        for (colIndex in adjacencyMatrix.indices) {
            disruptedMatrix[rowIndex][colIndex] = adjacencyMatrix[rowIndex][colIndex]
        }
    }
    val edgesInDiagonal = buildSet {
        for (row in 0 until n) {
            val col = (row + disruptedDiagonalIndex) % n
            add(Edge(row, col))
        }
    }
    val allCombinationsOfEdges = combinationsOf(edgesInDiagonal)

    for (edge in edgesInDiagonal) {
        disruptedMatrix[edge.rowIndex][edge.colIndex] = false
        disruptedMatrix[edge.colIndex][edge.rowIndex] = false
    }
    printAdjacencyMatrix(disruptedMatrix)
}

fun combinationsOf(edgeSet: Set<Edge>): Set<Set<Edge>> {
}
