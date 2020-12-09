package com.mariuszorlik.minesweeper.test.helpers

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.Constants.MATRIX_SIZE
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.Matrix

internal class MatrixServiceTestHelper {

    internal fun generateEmptyMatrixForTest(): Matrix {
        val expected = Matrix()
        for (x in 1..MATRIX_SIZE) {
            for (y in 1..MATRIX_SIZE) {
                expected.cellList.add(Cell(Coordinates(x, y)))
            }
        }
        return expected
    }


}