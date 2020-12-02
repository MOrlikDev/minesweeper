package com.mariuszorlik.minesweeper.model

data class Matrix(
    val cellList: MutableList<Cell> = mutableListOf(),
    val mineList: MutableList<Cell> = mutableListOf(),
    val matrixSize: Int = 9
) {
    fun getCell(coordinates: Coordinates): Cell? {
        for (cell in cellList) {
            if (coordinates.equals(cell.coordinates)) return cell
        }
        return null
    }

    fun getNumberOfMines(): Int {
        return mineList.size + 1
    }

    fun isAllMinesMarked(): Boolean {
        if (mineList.size != getMarkedList().size) {
            return false
        } else {
            for (mine in mineList) {
                if (!isCellInMarkedList(mine)) {
                    return false
                }
            }
        }
        return true
    }

    private fun getMarkedList(): MutableList<Cell> {
        val markedList = mutableListOf<Cell>()
        for (cell in cellList) {
            if (cell.isMarked()) markedList.add(cell)
        }
        return markedList
    }

    private fun isCellInMarkedList(cell: Cell): Boolean {
        for (marked in getMarkedList()) {
            if (cell.isTheSamePosition(marked)) {
                return true
            }
        }
        return false
    }
}
