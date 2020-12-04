package com.mariuszorlik.minesweeper.model

import java.lang.IllegalArgumentException

data class Matrix(
    val cellList: MutableList<Cell> = mutableListOf(),
    val matrixSize: Int = 9,
) {

    fun getCell(coordinates: Coordinates): Cell {
        for (cell in cellList) {
            if (coordinates == cell.coordinates) return cell
        }
        throw IllegalArgumentException("There is no cell ${coordinates.x}, ${coordinates.y}")
    }

    fun getMinesList(): MutableList<Cell> {
        val minesList = mutableListOf<Cell>()
        for (cell in cellList) {
            if (cell.isMine()) minesList.add(cell)
        }
        return minesList
    }

    fun getNumberOfMines(): Int {
        return getMinesList().size
    }

    fun isAllMinesMarked(): Boolean {
        for (cell in cellList) {
            if (cell.isNonMarkedMine() || cell.isMarkedEmpty()) return false
        }
        return true
    }

}
