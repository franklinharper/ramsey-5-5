package com.frank.graphviz

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val jumpNumbers = "c43_1_2_3_4_7_11_14_15_17_19_20"
        .split("_")
        .drop(1)
        .map { it.toInt() }

    val row = MutableList<Boolean?>(43) { null }
    row[0] = false
    for (jumpNumber in 1..21) {
        row[jumpNumber] = jumpNumber in jumpNumbers
    }
    // User symmetry to complete the rest of the row. The first column is not
    for (index in 22..42) {
        row[index] = row[43 - index]
    }
    val matrix = List(43) { index ->
        if (index != 0) Collections.rotate(row, 1)
        ArrayList(row)
    }
    // Create symmetry by copying the upper right half of the matrix into the bottom left half.
    for (rowIndex in 0..matrix.lastIndex) {
        for (colIndex in rowIndex+1..matrix.lastIndex) {
            matrix[colIndex][rowIndex] = matrix[rowIndex][colIndex]
        }
    }
    printAdjacencyMatrix(matrix)
}