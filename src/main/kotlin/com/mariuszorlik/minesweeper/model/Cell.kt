package com.mariuszorlik.minesweeper.model

data class Cell(
    val coordinates: Coordinates = Coordinates(),
    var cellState: CellStateEnum = CellStateEnum.FREE,
    var explored: Boolean = false,
    var marked: Boolean = false,
) {

    fun isNull(): Boolean {
        return coordinates.x == 0 && coordinates.y == 0
    }

    fun setUnexplored() {
        explored = false
    }

    fun setExplored() {
        explored = true
    }

    fun isExplored(): Boolean {
        return explored
    }

    fun setMarked() {
        marked = true
    }

    fun setUnmarked() {
        marked = false
    }

    fun isMarked(): Boolean {
        return marked
    }

    fun setMine() {
        cellState = CellStateEnum.MINE
    }

    fun isMine(): Boolean {
        return cellState == CellStateEnum.MINE
    }

    fun isFree(): Boolean {
        return cellState == CellStateEnum.FREE
    }

    fun isHint(): Boolean {
        return cellState.value in CellStateEnum.HINT_1.value..CellStateEnum.HINT_8.value
    }


    fun getSymbol(): Char {
        if (explored) return cellState.symbol
        else if (marked) return CellStateEnum.MARKED.symbol
        else return CellStateEnum.UNEXPLORED.symbol
    }

    fun isTheSamePosition(cell: Cell): Boolean {
        return coordinates == cell.coordinates
    }

    fun incrementHint() {
        if (!isMine() && cellState != CellStateEnum.HINT_8) {
            cellState = if (cellState == CellStateEnum.FREE) {
                CellStateEnum.HINT_1
            } else {
                CellStateEnum.findByValue(cellState.value + 1)
            }
        }
    }

}
