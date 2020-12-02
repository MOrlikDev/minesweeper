package com.mariuszorlik.minesweeper.model

class Cell(val coordinates: Coordinates, var cellValue: CellValue = CellValue.NON_MARKED_EMPTY) {

    fun setMark() {
        if (cellValue == CellValue.NON_MARKED_EMPTY) {
            cellValue = CellValue.MARKED_EMPTY
        } else if (cellValue == CellValue.NON_MARKED_MINE) {
            cellValue = CellValue.MARKED_MINE
        }
    }

    fun setUnmark() {
        if (cellValue == CellValue.MARKED_EMPTY) {
            cellValue = CellValue.NON_MARKED_EMPTY
        } else if (cellValue == CellValue.MARKED_MINE) {
            cellValue = CellValue.NON_MARKED_MINE
        }
    }

    fun isMine(): Boolean {
        return cellValue == CellValue.NON_MARKED_MINE || cellValue == CellValue.MARKED_MINE
    }

    fun isMarked(): Boolean {
        return cellValue == CellValue.MARKED_MINE || cellValue == CellValue.MARKED_EMPTY
    }

    fun isNonMarked(): Boolean {
        return cellValue == CellValue.NON_MARKED_EMPTY || cellValue == CellValue.NON_MARKED_MINE
    }

    fun isHint(): Boolean {
        return cellValue.value in CellValue.HINT_1.value..CellValue.HINT_9.value
    }

    fun setNonMarkedMine() {
        cellValue = CellValue.NON_MARKED_MINE
    }

    fun isNonMarkedEmpty(): Boolean {
        return cellValue == CellValue.NON_MARKED_EMPTY
    }

    fun isTheSamePosition(cell: Cell): Boolean {
        return coordinates.equals(cell.coordinates)
    }

    fun incrementHint() {
        if (!isMine()) {
            cellValue = if (cellValue == CellValue.NON_MARKED_EMPTY) {
                CellValue.HINT_1
            } else {
                CellValue.findByValue(cellValue.value + 1)
            }
        }
    }
}
