package com.mariuszorlik.minesweeper

import com.mariuszorlik.minesweeper.model.Matrix
import com.mariuszorlik.minesweeper.model.NextMoveResultEnum.*
import com.mariuszorlik.minesweeper.service.MatrixService
import com.mariuszorlik.minesweeper.view.ConsoleUserInterfaceImpl
import com.mariuszorlik.minesweeper.view.UserInterface

class GameController {

    private val ui: UserInterface = ConsoleUserInterfaceImpl()
    private val matrixService = MatrixService()

    fun init() {
        val numberOfMines = ui.askPlayerForNumberOfMines()

        val matrix = matrixService.generateEmptyMatrix()
        matrixService.addMinesInRandomPlaces(matrix, numberOfMines)
        matrixService.addHintsAroundMines(matrix)
        matrixService.setUnexploredCellsInWholeMatrix(matrix)

        ui.drawMatrix(matrix)

        play(matrix)
    }

    private fun play(matrix: Matrix) {
        val nextMove = ui.askPlayerForNextMove()

        val result = matrixService.processPlayerNextMove(matrix, nextMove)

        when (result) {
            ERROR_HINT -> {
                ui.printErrorHint()
                play(matrix)
            }
            GAME_OVER -> {
                matrixService.setExploredCellsInWholeMatrix(matrix)
                ui.drawMatrix(matrix)
                ui.printGameOver()
            }
            END_GAME -> {
                ui.drawMatrix(matrix)
                ui.printEndGame()
            }
            else -> {
                ui.drawMatrix(matrix)
                play(matrix)
            }
        }
    }
}
