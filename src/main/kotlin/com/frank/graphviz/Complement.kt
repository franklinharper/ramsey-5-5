package com.frank.graphviz


fun main() {
    // Stdout can be copied to a file by setting "Run Configuration / Logs / Save console output to file"
    val adjacencyMatrix = parseAdjacencyMatrix("c43_1_2_3_4_7_11_14_15_17_19_20.dis")
    for (rowIndex in adjacencyMatrix.indices) {
//        print("row: $rowIndex ")
        for (colIndex in adjacencyMatrix.indices) {
            val complementaryEdge = if(rowIndex == colIndex || adjacencyMatrix[rowIndex][colIndex]) 0 else 1
            print("$complementaryEdge " )
        }
        println()
    }
}
