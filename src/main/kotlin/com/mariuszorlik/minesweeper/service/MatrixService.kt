package com.mariuszorlik.minesweeper.service

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.Matrix
import com.mariuszorlik.minesweeper.model.NextMoveProcessResultEnum
import java.lang.IllegalArgumentException
import java.util.logging.Logger
import kotlin.random.Random

class MatrixService {

    fun generateEmptyMatrix(): Matrix {
        val matrix = Matrix()
        for (x in 1..matrix.matrixSize) {
            for (y in 1..matrix.matrixSize) {
                matrix.cellList.add(Cell(Coordinates(x, y)))
            }
        }
        return matrix
    }

    fun addRandomMines(matrix: Matrix, numberOfMinesToAdd: Int) {
        if (numberOfMinesToAdd > 0 && matrix.getNumberOfMines() < (matrix.matrixSize * matrix.matrixSize)) {
            val x = Random.nextInt(1, matrix.matrixSize + 1)
            val y = Random.nextInt(1, matrix.matrixSize + 1)

            val cell = matrix.getCell(Coordinates(x, y))
            if (cell.isNonMarkedEmpty()) {
                cell.setNonMarkedMine()
                addRandomMines(matrix, numberOfMinesToAdd - 1)
            } else {
                addRandomMines(matrix, numberOfMinesToAdd)
            }
        }
    }

    fun checkCellsAroundMinesAndIncrementHint(matrix: Matrix) {
        for (cell in matrix.getMinesList()) {

            // x -1
            incrementCell(matrix, Coordinates(cell.coordinates.x - 1, cell.coordinates.y - 1))
            incrementCell(matrix, Coordinates(cell.coordinates.x - 1, cell.coordinates.y))
            incrementCell(matrix, Coordinates(cell.coordinates.x - 1, cell.coordinates.y + 1))

            // x
            incrementCell(matrix, Coordinates(cell.coordinates.x, cell.coordinates.y - 1))
            incrementCell(matrix, Coordinates(cell.coordinates.x, cell.coordinates.y + 1))

            // x + 1
            incrementCell(matrix, Coordinates(cell.coordinates.x + 1, cell.coordinates.y - 1))
            incrementCell(matrix, Coordinates(cell.coordinates.x + 1, cell.coordinates.y))
            incrementCell(matrix, Coordinates(cell.coordinates.x + 1, cell.coordinates.y + 1))
        }
    }

    private fun incrementCell(matrix: Matrix, coordinates: Coordinates) {
        try {
            matrix.getCell(coordinates).incrementHint()
        } catch (e: IllegalArgumentException) {
//            Logger.getLogger(MatrixService::class.java.name).info()
        }
    }

    fun processPlayerNextMove(matrix: Matrix, nextMoveCoordinates: Coordinates): NextMoveProcessResultEnum {
        val result: NextMoveProcessResultEnum
        val cell = matrix.getCell(nextMoveCoordinates)
        if (cell.isHint()) {
            result = NextMoveProcessResultEnum.ERROR_HINT
        } else {
            if (cell.isMarked()) {
                cell.setUnmark()
            } else if (cell.isNonMarked()) {
                cell.setMark()
            }

            if (matrix.isAllMinesMarked()) {
                result = NextMoveProcessResultEnum.END_GAME
            } else {
                result = NextMoveProcessResultEnum.CONTINUE
            }
        }
        return result
    }
}
