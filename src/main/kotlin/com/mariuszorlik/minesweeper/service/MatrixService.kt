package com.mariuszorlik.minesweeper.service

import com.mariuszorlik.minesweeper.model.*
import com.mariuszorlik.minesweeper.model.Constants.MATRIX_SIZE
import java.lang.IllegalArgumentException
import kotlin.random.Random

class MatrixService {

    fun generateEmptyMatrix(): Matrix {
        val matrix = Matrix()
        for (x in 1..MATRIX_SIZE) {
            for (y in 1..MATRIX_SIZE) {
                matrix.cellList.add(Cell(Coordinates(x, y)))
            }
        }
        return matrix
    }

    fun addMinesInRandomPlaces(matrix: Matrix, numberOfMinesToAdd: Int) {
        if (numberOfMinesToAdd > 0 && matrix.getNumberOfMines() < (MATRIX_SIZE * MATRIX_SIZE)) {
            val x = Random.nextInt(1, MATRIX_SIZE + 1)
            val y = Random.nextInt(1, MATRIX_SIZE + 1)

            val cell = matrix.getCell(Coordinates(x, y))
            if (cell.isFree()) {
                cell.setMine()
                addMinesInRandomPlaces(matrix, numberOfMinesToAdd - 1)
            } else {
                addMinesInRandomPlaces(matrix, numberOfMinesToAdd)
            }
        }
    }

    fun addHintsAroundMines(matrix: Matrix) {
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

    fun setUnexploredCellsInWholeMatrix(matrix: Matrix) {
        for (cell in matrix.cellList) {
            cell.setUnexplored()
        }
    }

    fun setExploredCellsInWholeMatrix(matrix: Matrix) {
        for (cell in matrix.cellList) {
            cell.setExplored()
        }
    }

    private fun incrementCell(matrix: Matrix, coordinates: Coordinates) {
        try {
            matrix.getCell(coordinates).incrementHint()
        } catch (e: IllegalArgumentException) {
//            Logger.getLogger(MatrixService::class.java.name).info()
        }
    }

    fun processPlayerNextMove(matrix: Matrix, nextMove: NextMove): NextMoveResultEnum {
        val result: NextMoveResultEnum
        val cell = matrix.getCell(nextMove.coordinates)

        if (cell.isExplored()) {
            result = NextMoveResultEnum.EXPLORED
        } else if (nextMove.nextMoveEnum == NextMoveEnum.FREE) {

            if (cell.isMine()) {
                result = NextMoveResultEnum.GAME_OVER
            } else {

                discoverFreeCells(matrix, cell)
                result = NextMoveResultEnum.CONTINUE

            }

        } else if (nextMove.nextMoveEnum == NextMoveEnum.MINE) {

            if (cell.isMarked()) {
                cell.setUnmarked()
            } else if (!cell.isMarked()) {
                cell.setMarked()
            }

            if (matrix.isAllMinesMarked()) {
                result = NextMoveResultEnum.END_GAME
            } else {
                result = NextMoveResultEnum.CONTINUE
            }

        } else {
            throw IllegalArgumentException()
        }

        return result
    }

    fun discoverFreeCells(matrix: Matrix, cell: Cell) {

        if (!cell.isNull() && !cell.isExplored()) {

            if (cell.isFree()) {
                cell.setExplored()

                discoverFreeCells(matrix, matrix.getCell(Coordinates(cell.coordinates.x - 1, cell.coordinates.y - 1)))
                discoverFreeCells(matrix, matrix.getCell(Coordinates(cell.coordinates.x - 1, cell.coordinates.y)))
                discoverFreeCells(matrix, matrix.getCell(Coordinates(cell.coordinates.x - 1, cell.coordinates.y + 1)))
                discoverFreeCells(matrix, matrix.getCell(Coordinates(cell.coordinates.x, cell.coordinates.y - 1)))
                discoverFreeCells(matrix, matrix.getCell(Coordinates(cell.coordinates.x, cell.coordinates.y + 1)))
                discoverFreeCells(matrix, matrix.getCell(Coordinates(cell.coordinates.x + 1, cell.coordinates.y - 1)))
                discoverFreeCells(matrix, matrix.getCell(Coordinates(cell.coordinates.x + 1, cell.coordinates.y)))
                discoverFreeCells(matrix, matrix.getCell(Coordinates(cell.coordinates.x + 1, cell.coordinates.y + 1)))

            } else if (cell.isHint()) {
                cell.setExplored()
            }

        }

    }
}

