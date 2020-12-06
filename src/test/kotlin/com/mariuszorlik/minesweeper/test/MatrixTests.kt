package com.mariuszorlik.minesweeper.test

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.CellStateEnum
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.test.helpers.MatrixTestHelper
import com.mariuszorlik.minesweeper.view.ConsoleUserInterfaceImpl
import org.junit.jupiter.api.*
import java.lang.IllegalArgumentException
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatrixTests {

    private val helper: MatrixTestHelper = MatrixTestHelper()

    @Test
    @DisplayName("getCell(): 0,0 -> exception")
    fun getCell_exception() {
        assertThrows(IllegalArgumentException::class.java) {
            helper.generateMatrixWithData().getCell(Coordinates(0, 0))
        }
    }

    @Test
    @DisplayName("getCell(): equal")
    fun getCell_equal() {
        val expected = Cell(Coordinates(2, 3), CellStateEnum.HINT_1)

        val actual = helper.generateMatrixWithData().getCell(Coordinates(2, 3))

        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("getNumberOfMines(): 1")
    fun getNumberOfMines() {
        assertEquals(1, helper.generateMatrixWithData().getNumberOfMines())
    }

    @Test
    @DisplayName("getMinesList(): ok")
    fun getMinesList() {
        val expected = mutableListOf(Cell(Coordinates(3, 4), CellStateEnum.MINE))

        val actual = helper.generateMatrixWithData().getMinesList()

        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("isAllMinesMarked(): false")
    fun isAllMinesMarked_false() {
        assertFalse(helper.generateMatrixWithData().isAllMinesMarked())
    }

    @Test
    @DisplayName("isAllMinesMarked(): false & wrong")
    fun isAllMinesMarked_false_wrong() {
        val actual = helper.generateMatrixWithData()
        actual.getCell(Coordinates(3, 4)).setMarked()
        actual.getCell(Coordinates(1, 1)).setMarked()

        assertFalse(actual.isAllMinesMarked())
    }


    @Test
    @DisplayName("isAllMinesMarked(): false")
    fun isAllMinesMarked_true() {
        val actual = helper.generateMatrixWithData()
        actual.getCell(Coordinates(3, 4)).setMarked()

        assertTrue(actual.isAllMinesMarked())
    }

}