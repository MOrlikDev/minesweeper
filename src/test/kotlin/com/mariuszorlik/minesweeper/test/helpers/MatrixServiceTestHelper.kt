package com.mariuszorlik.minesweeper.test.helpers

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.Matrix

class MatrixServiceTestHelper {

    fun generateEmptyMatrixForTest(): Matrix {
        val expected = Matrix()
        for (x in 1..expected.matrixSize) {
            for (y in 1..expected.matrixSize) {
                expected.cellList.add(Cell(Coordinates(x, y)))
            }
        }
        return expected
    }


}