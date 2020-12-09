package com.mariuszorlik.minesweeper.test

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.CellStateEnum
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.test.helpers.MatrixTestHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MatrixTests {

    private val helper: MatrixTestHelper = MatrixTestHelper()

    @Test
    @DisplayName("getCell(): 0,0 -> null")
    internal fun getCell_null() {
        assertTrue(helper.generateMatrixWithData().getCell(Coordinates(0, 0)).isNull())
    }

    @Test
    @DisplayName("getCell(): equal")
    internal fun getCell_equal() {
        val expected = Cell(Coordinates(2, 3), CellStateEnum.HINT_1)

        val actual = helper.generateMatrixWithData().getCell(Coordinates(2, 3))

        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("getNumberOfMines(): 1")
    internal fun getNumberOfMines() {
        assertEquals(1, helper.generateMatrixWithData().getNumberOfMines())
    }

    @Test
    @DisplayName("getMinesList(): ok")
    internal fun getMinesList() {
        val expected = mutableListOf(Cell(Coordinates(3, 4), CellStateEnum.MINE))

        val actual = helper.generateMatrixWithData().getMinesList()

        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("isAllMinesMarked(): false")
    internal fun isAllMinesMarked_false() {
        assertFalse(helper.generateMatrixWithData().isAllMinesMarked())
    }

    @Test
    @DisplayName("isAllMinesMarked(): false")
    internal fun isAllMinesMarked_true() {
        val actual = helper.generateMatrixWithData()
        actual.getCell(Coordinates(3, 4)).setMarked()

        assertTrue(actual.isAllMinesMarked())
    }


    @Test
    @DisplayName("isAllMinesMarked(): false & wrong")
    internal fun isAllMinesMarked_false_wrong() {
        val actual = helper.generateMatrixWithData()
        actual.getCell(Coordinates(3, 4)).setMarked()
        actual.getCell(Coordinates(1, 1)).setMarked()

        assertFalse(actual.isAllMinesMarked())
    }

}