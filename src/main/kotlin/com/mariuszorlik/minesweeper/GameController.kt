package com.mariuszorlik.minesweeper

import com.mariuszorlik.minesweeper.model.NextMoveProcessResult.END_GAME
import com.mariuszorlik.minesweeper.model.NextMoveProcessResult.ERROR_HINT
import com.mariuszorlik.minesweeper.ui.ConsoleUserInterface
import com.mariuszorlik.minesweeper.ui.IUserInterface

class GameController {

    private val ui: IUserInterface = ConsoleUserInterface()
    private val matrixService = MatrixService()

    fun init() {
        val numberOfMines = ui.askPlayerForNumberOfMines()

        matrixService.generateEmptyMatrix()
        matrixService.addRandomMines(numberOfMines)
        matrixService.checkCellsAroundMinesAndIncrementHint()

        ui.drawMatrix(matrixService.matrix)

        play()
    }

    private fun play() {
        val coordinates = ui.askPlayerForNextMove()

        val result = matrixService.processPlayerNextMove(coordinates)

        when (result) {
            ERROR_HINT -> {
                ui.drawErrorHint()
                play()
            }
            END_GAME -> {
                ui.drawMatrix(matrixService.matrix)
                ui.drawEndGame()
            }
            else -> {
                ui.drawMatrix(matrixService.matrix)
                play()
            }
        }
    }
}
