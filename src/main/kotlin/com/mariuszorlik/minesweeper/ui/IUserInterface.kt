package com.mariuszorlik.minesweeper.ui

import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.Matrix

interface IUserInterface {
    fun askPlayerForNumberOfMines(): Int
    fun drawMatrix(matrix: Matrix)
    fun askPlayerForNextMove(): Coordinates
    fun drawErrorHint()
    fun drawEndGame()
}
