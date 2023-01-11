package com.frank.graphviz

fun main() {
    // Stdout can be copied to a file by setting "Run Configuration / Logs / Save console output to file"
    val fileName = "c43_1_2_3_4_7_11_14_15_17_19_20.cir"
    val disruptedDiagonalIndex = 1

    val adjacencyMatrix = parseAdjacencyMatrix(fileName)

    val disruptedMatrix = List(43) {
        MutableList(43) { false }
    }
    for (rowIndex in adjacencyMatrix.indices) {
        for (colIndex in adjacencyMatrix.indices) {
            disruptedMatrix[rowIndex][colIndex] = adjacencyMatrix[rowIndex][colIndex]
        }
    }
    for (rowIndex in adjacencyMatrix.indices) {
        val disruptedColIndex = rowIndex + disruptedDiagonalIndex
        if (rowIndex % 4 == 0 && disruptedColIndex < adjacencyMatrix.size) {
            disruptedMatrix[rowIndex][disruptedColIndex] = false
            disruptedMatrix[disruptedColIndex][rowIndex] = false
        }
    }
    printAdjacencyMatrix(disruptedMatrix)
}

fun printAdjacencyMatrix(adjacencyMatrix: List<List<Boolean?>>) {
    for (colIndex in adjacencyMatrix.indices) {
        print(" ${"%2d".format(colIndex)}")
    }
    println()
    for (colIndex in adjacencyMatrix.indices) {
        print("---")
    }
    println()
    for (rowIndex in adjacencyMatrix.indices) {
        for (colIndex in adjacencyMatrix.indices) {
            val edgeValue = when (adjacencyMatrix[rowIndex][colIndex]) {
                null -> -1
                true -> 1
                false -> 0
            }
            print("  $edgeValue")
        }
        println()
    }
}
