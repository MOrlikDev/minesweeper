package com.mariuszorlik.minesweeper.model

import java.lang.IllegalArgumentException

data class Matrix(
    val cellList: MutableList<Cell> = mutableListOf(),
) {

    fun getCell(coordinates: Coordinates): Cell {
        for (cell in cellList) {
            if (coordinates == cell.coordinates) return cell
        }
        return Cell(Coordinates(0, 0))
//        throw IllegalArgumentException("There is no cell ${coordinates.x}, ${coordinates.y}")
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
            if ((cell.isMine() && !cell.isMarked()) || (!cell.isMine() && cell.isMarked())) return false
        }
        return true
    }

}
