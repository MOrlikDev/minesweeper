package com.mariuszorlik.minesweeper.test.helpers

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.Constants
import com.mariuszorlik.minesweeper.model.Constants.MATRIX_SIZE
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.Matrix

class MatrixTestHelper {

    fun generateMatrixWithData(): Matrix {
        val matrix = Matrix()
        for (x in 1..MATRIX_SIZE) {
            for (y in 1..MATRIX_SIZE) {
                matrix.cellList.add(Cell(Coordinates(x, y)))
            }
        }

        matrix.getCell(Coordinates(2, 3)).incrementHint()
        matrix.getCell(Coordinates(3, 3)).incrementHint()
        matrix.getCell(Coordinates(4, 3)).incrementHint()

        matrix.getCell(Coordinates(2, 4)).incrementHint()
        matrix.getCell(Coordinates(3, 4)).setMine()
        matrix.getCell(Coordinates(4, 4)).incrementHint()

        matrix.getCell(Coordinates(2, 5)).incrementHint()
        matrix.getCell(Coordinates(3, 5)).incrementHint()
        matrix.getCell(Coordinates(4, 5)).incrementHint()

        return matrix
    }

}