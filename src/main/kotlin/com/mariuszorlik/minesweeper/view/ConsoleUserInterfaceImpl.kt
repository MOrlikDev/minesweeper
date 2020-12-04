package com.mariuszorlik.minesweeper.view

import com.mariuszorlik.minesweeper.model.Constants
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.Matrix
import java.util.Scanner

class ConsoleUserInterfaceImpl : UserInterface {

    private val scanner = Scanner(System.`in`)

    override fun askPlayerForNumberOfMines(): Int {
        print("How many mines do you want on the field? ")
        try {
            val numberOfMine = scanner.nextInt()
            if (numberOfMine < Constants.NUMBER_OF_MINES_MIN || numberOfMine > Constants.NUMBER_OF_MINES_MAX) {
                throw IllegalArgumentException("The number of mines should be between 1 and 20")
            } else {
                return numberOfMine
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("The number of mines should be between 1 and 20")
        }
    }

    override fun drawMatrix(matrix: Matrix) {
//        drawMatrixBetter(matrix)
        drawMatrixProper(matrix)
    }

    private fun drawMatrixProper(matrix: Matrix) {
        println()
        println(" |123456789|")
        println("—|—————————|")
        for (y in 1..matrix.matrixSize) {
            val row = StringBuffer("$y|")
            for (x in 1..matrix.matrixSize) {
                row.append(matrix.getCell(Coordinates(x, y)).cellValue.symbol)
            }
            row.append("|")
            println(row)
        }
        println("—|—————————|")
    }

    @Deprecated("better but not proper")
    private fun drawMatrixBetter(matrix: Matrix) {
        println()
        println("  |  1  2  3  4  5  6  7  8  9  |")
        println("—-|-----------------------------|")
        for (y in 1..matrix.matrixSize) {
            val row = StringBuffer("$y |  ")
            for (x in 1..matrix.matrixSize) {
                row.append(matrix.getCell(Coordinates(x, y)).cellValue.symbol)
                row.append("  ")
            }
            row.append("|")
            println(row)
        }
        println("—-|-----------------------------|")
    }

    override fun askPlayerForNextMove(): Coordinates {
        print("Set/delete mine marks (x and y coordinates): ")
        try {
            val x = scanner.nextInt()
            val y = scanner.nextLine().trim().toInt()
            return Coordinates(x, y)
        } catch (e: Exception) {
            throw IllegalArgumentException("You should enter two numbers 1-9 (e.g '3 6')")
        }
    }

    override fun drawErrorHint() {
        println("There is a number here!")
    }

    override fun drawEndGame() {
        println("Congratulations! You found all the mines!")
    }
}
