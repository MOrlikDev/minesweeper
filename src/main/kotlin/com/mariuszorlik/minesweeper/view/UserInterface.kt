package com.mariuszorlik.minesweeper.view

import com.mariuszorlik.minesweeper.model.Matrix
import com.mariuszorlik.minesweeper.model.NextMove

interface UserInterface {
    fun askPlayerForNumberOfMines(): Int
    fun drawMatrix(matrix: Matrix)
    fun askPlayerForNextMove(): NextMove
    fun printErrorHint()
    fun printGameOver()
    fun printEndGame()
}
