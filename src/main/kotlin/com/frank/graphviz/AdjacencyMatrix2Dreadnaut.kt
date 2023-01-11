package com.frank.graphviz

fun main() {
    // Stdout can be copied to a file by setting "Run Configuration / Logs / Save console output to file"
    val fileName = "mckay248.txt"
    val adjacencyMatrix = parseAdjacencyMatrix(fileName)
    println("n=42 \$=0 g ! input data from $fileName")
    for (rowIndex in adjacencyMatrix.indices) {
        print("$rowIndex :")
        for (colIndex in rowIndex+1..adjacencyMatrix.lastIndex) {
            val isEdge = adjacencyMatrix[rowIndex][colIndex]
            if (isEdge) {
                print(" $colIndex")
            }
        }
        println(";")
    }
}