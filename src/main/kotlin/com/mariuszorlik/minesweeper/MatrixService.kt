package com.mariuszorlik.minesweeper

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.Matrix
import com.mariuszorlik.minesweeper.model.NextMoveProcessResult
import kotlin.random.Random

class MatrixService {

    val matrix = Matrix()

    fun generateEmptyMatrix() {
        for (x in 1..matrix.matrixSize) {
            for (y in 1..matrix.matrixSize) {
                matrix.cellList.add(Cell(Coordinates(x, y)))
            }
        }
    }

    fun addRandomMines(numberOfMinesToAdd: Int) {
        if (numberOfMinesToAdd > 0 && matrix.getNumberOfMines() < (matrix.matrixSize * matrix.matrixSize)) {
            val x = Random.nextInt(1, matrix.matrixSize + 1)
            val y = Random.nextInt(1, matrix.matrixSize + 1)

            val cell = matrix.getCell(Coordinates(x, y))
            if (cell!!.isNonMarkedEmpty()) {
                cell.setNonMarkedMine()
                matrix.mineList.add(cell)
                addRandomMines(numberOfMinesToAdd - 1)
            } else {
                addRandomMines(numberOfMinesToAdd)
            }
        }
    }

    fun checkCellsAroundMinesAndIncrementHint() {
        for (cell in matrix.mineList) {

            // x -1
            matrix.getCell(Coordinates(cell.coordinates.x - 1, cell.coordinates.y - 1))?.incrementHint()
            matrix.getCell(Coordinates(cell.coordinates.x - 1, cell.coordinates.y))?.incrementHint()
            matrix.getCell(Coordinates(cell.coordinates.x - 1, cell.coordinates.y + 1))?.incrementHint()

            // x
            matrix.getCell(Coordinates(cell.coordinates.x, cell.coordinates.y - 1))?.incrementHint()
            matrix.getCell(Coordinates(cell.coordinates.x, cell.coordinates.y + 1))?.incrementHint()

            // x + 1
            matrix.getCell(Coordinates(cell.coordinates.x + 1, cell.coordinates.y - 1))?.incrementHint()
            matrix.getCell(Coordinates(cell.coordinates.x + 1, cell.coordinates.y))?.incrementHint()
            matrix.getCell(Coordinates(cell.coordinates.x + 1, cell.coordinates.y + 1))?.incrementHint()
        }
    }

    fun processPlayerNextMove(coordinates: Coordinates): NextMoveProcessResult {
        val result: NextMoveProcessResult
        val cell = matrix.getCell(coordinates)
        if (cell!!.isHint()) {
            result = NextMoveProcessResult.ERROR_HINT
        } else {
            if (cell.isMarked()) {
                cell.setUnmark()
            } else if (cell.isNonMarked()) {
                cell.setMark()
            }

            if (matrix.isAllMinesMarked()) {
                result = NextMoveProcessResult.END_GAME
            } else {
                result = NextMoveProcessResult.CONTINUE
            }
        }
        return result
    }
}
