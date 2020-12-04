package com.mariuszorlik.minesweeper

import com.mariuszorlik.minesweeper.model.Matrix
import com.mariuszorlik.minesweeper.model.NextMoveProcessResultEnum.END_GAME
import com.mariuszorlik.minesweeper.model.NextMoveProcessResultEnum.ERROR_HINT
import com.mariuszorlik.minesweeper.service.MatrixService
import com.mariuszorlik.minesweeper.view.ConsoleUserInterfaceImpl
import com.mariuszorlik.minesweeper.view.UserInterface

class GameController {

    private val ui: UserInterface = ConsoleUserInterfaceImpl()
    private val matrixService = MatrixService()

    fun init() {
        val numberOfMines = ui.askPlayerForNumberOfMines()

        val matrix = matrixService.generateEmptyMatrix()
        matrixService.addRandomMines(matrix, numberOfMines)
        matrixService.checkCellsAroundMinesAndIncrementHint(matrix)

        ui.drawMatrix(matrix)

        play(matrix)
    }

    private fun play(matrix: Matrix) {
        val coordinates = ui.askPlayerForNextMove()

        val result = matrixService.processPlayerNextMove(matrix, coordinates)

        when (result) {
            ERROR_HINT -> {
                ui.drawErrorHint()
                play(matrix)
            }
            END_GAME -> {
                ui.drawMatrix(matrix)
                ui.drawEndGame()
            }
            else -> {
                ui.drawMatrix(matrix)
                play(matrix)
            }
        }
    }
}
