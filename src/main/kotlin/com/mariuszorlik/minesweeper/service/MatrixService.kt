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

    fun processPlayerNextMove(matrix: Matrix, nextMove: NextMove): NextMoveResultEnum {
        val result: NextMoveResultEnum
        val cell = matrix.getCell(nextMove.coordinates)

        result = when {
            cell.isExplored() -> {
                nextMoveExplored()
            }
            nextMove.nextMoveEnum == NextMoveEnum.FREE -> {
                nextMoveFree(matrix, cell)
            }
            nextMove.nextMoveEnum == NextMoveEnum.MINE -> {
                nextMoveMine(matrix, cell)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }

        return result
    }

    private fun incrementCell(matrix: Matrix, coordinates: Coordinates) {
        matrix.getCell(coordinates).incrementHint()
    }

    private fun nextMoveMine(matrix: Matrix, cell: Cell): NextMoveResultEnum {
        nextMoveMarkCell(cell)

        return if (matrix.isAllMinesMarked()) {
            NextMoveResultEnum.END_GAME
        } else {
            NextMoveResultEnum.CONTINUE
        }

    }

    private fun nextMoveFree(matrix: Matrix, cell: Cell): NextMoveResultEnum {
        return if (cell.isMine()) {
            nextMoveGameOver()
        } else {
            nextMoveContinue(matrix, cell)
        }
    }

    private fun nextMoveMarkCell(cell: Cell) {
        if (cell.isMarked()) {
            cell.setUnmarked()
        } else if (!cell.isMarked()) {
            cell.setMarked()
        }
    }

    private fun nextMoveContinue(matrix: Matrix, cell: Cell): NextMoveResultEnum {
        discoverFreeCells(matrix, cell)
        return NextMoveResultEnum.CONTINUE
    }

    private fun nextMoveGameOver(): NextMoveResultEnum {
        return NextMoveResultEnum.GAME_OVER
    }

    private fun nextMoveExplored(): NextMoveResultEnum {
        return NextMoveResultEnum.EXPLORED
    }

    private fun discoverFreeCells(matrix: Matrix, cell: Cell) {

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

