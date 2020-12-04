package com.mariuszorlik.minesweeper.view

import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.Matrix

interface UserInterface {
    fun askPlayerForNumberOfMines(): Int
    fun drawMatrix(matrix: Matrix)
    fun askPlayerForNextMove(): Coordinates
    fun drawErrorHint()
    fun drawEndGame()
}
