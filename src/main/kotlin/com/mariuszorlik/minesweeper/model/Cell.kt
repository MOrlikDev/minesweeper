package com.mariuszorlik.minesweeper.model

data class Cell(
    val coordinates: Coordinates = Coordinates(),
    var cellValue: CellValueEnum = CellValueEnum.NON_MARKED_EMPTY,
) {

    fun setMark() {
        if (cellValue == CellValueEnum.NON_MARKED_EMPTY) {
            cellValue = CellValueEnum.MARKED_EMPTY
        } else if (cellValue == CellValueEnum.NON_MARKED_MINE) {
            cellValue = CellValueEnum.MARKED_MINE
        }
    }

    fun setUnmark() {
        if (cellValue == CellValueEnum.MARKED_EMPTY) {
            cellValue = CellValueEnum.NON_MARKED_EMPTY
        } else if (cellValue == CellValueEnum.MARKED_MINE) {
            cellValue = CellValueEnum.NON_MARKED_MINE
        }
    }


    fun setNonMarkedMine() {
        cellValue = CellValueEnum.NON_MARKED_MINE
    }

    fun isNonMarkedEmpty(): Boolean {
        return cellValue == CellValueEnum.NON_MARKED_EMPTY
    }

    fun isNonMarkedMine(): Boolean {
        return cellValue == CellValueEnum.NON_MARKED_MINE
    }

    fun isMarkedEmpty(): Boolean {
        return cellValue == CellValueEnum.MARKED_EMPTY
    }

    fun isMine(): Boolean {
        return cellValue == CellValueEnum.NON_MARKED_MINE || cellValue == CellValueEnum.MARKED_MINE
    }

    fun isMarked(): Boolean {
        return cellValue == CellValueEnum.MARKED_MINE || cellValue == CellValueEnum.MARKED_EMPTY
    }

    fun isNonMarked(): Boolean {
        return cellValue == CellValueEnum.NON_MARKED_EMPTY || cellValue == CellValueEnum.NON_MARKED_MINE
    }

    fun isHint(): Boolean {
        return cellValue.value in CellValueEnum.HINT_1.value..CellValueEnum.HINT_9.value
    }

    fun isTheSamePosition(cell: Cell): Boolean {
        return coordinates == cell.coordinates
    }

    fun incrementHint() {
        if (!isMine() && cellValue != CellValueEnum.HINT_9) {
            cellValue = if (cellValue == CellValueEnum.NON_MARKED_EMPTY) {
                CellValueEnum.HINT_1
            } else {
                CellValueEnum.findByValue(cellValue.value + 1)
            }
        }
    }

}
