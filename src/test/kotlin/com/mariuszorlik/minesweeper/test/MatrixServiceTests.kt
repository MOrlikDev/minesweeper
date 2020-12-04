package com.mariuszorlik.minesweeper.test

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.CellValueEnum
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.NextMoveProcessResultEnum
import com.mariuszorlik.minesweeper.service.MatrixService
import com.mariuszorlik.minesweeper.test.helpers.MatrixServiceTestHelper
import com.mariuszorlik.minesweeper.test.helpers.MatrixTestHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatrixServiceTests {

    private val matrixService: MatrixService = MatrixService()
    private val serviceHelper: MatrixServiceTestHelper = MatrixServiceTestHelper()
    private val matrixHelper: MatrixTestHelper = MatrixTestHelper()

    @Test
    @DisplayName("generateEmptyMatrix(): ok")
    fun generateEmptyMatrix() {
        val expected = serviceHelper.generateEmptyMatrixForTest()

        val actual = matrixService.generateEmptyMatrix()

        Assertions.assertNotNull(actual)
        Assertions.assertNotNull(actual.cellList)
        Assertions.assertEquals(expected.cellList.size, actual.cellList.size)
        for (cell in actual.cellList) {
            Assertions.assertNotNull(cell.coordinates)
            Assertions.assertNotNull(cell.coordinates.x)
            Assertions.assertNotNull(cell.coordinates.y)
            Assertions.assertEquals(CellValueEnum.NON_MARKED_EMPTY, cell.cellValue)
        }
    }

    @Test
    @DisplayName("addRandomMines(): 5=5")
    fun addRandomMines() {
        val matrix = serviceHelper.generateEmptyMatrixForTest()
        matrixService.addRandomMines(matrix, 5)

        assertEquals(5, matrix.getNumberOfMines())
    }

    @Test
    @DisplayName("checkCellsAroundMinesAndIncrementHint(): ok")
    fun checkCellsAroundMinesAndIncrementHint() {
        val matrix = serviceHelper.generateEmptyMatrixForTest()
        matrix.getCell(Coordinates(2, 2)).setNonMarkedMine()

        matrixService.checkCellsAroundMinesAndIncrementHint(matrix)

        assertEquals(Cell(Coordinates(1, 1), CellValueEnum.HINT_1), matrix.getCell(Coordinates(1, 1)))
        assertEquals(Cell(Coordinates(2, 1), CellValueEnum.HINT_1), matrix.getCell(Coordinates(2, 1)))
        assertEquals(Cell(Coordinates(3, 1), CellValueEnum.HINT_1), matrix.getCell(Coordinates(3, 1)))

        assertEquals(Cell(Coordinates(1, 2), CellValueEnum.HINT_1), matrix.getCell(Coordinates(1, 2)))
        assertEquals(Cell(Coordinates(2, 2), CellValueEnum.NON_MARKED_MINE), matrix.getCell(Coordinates(2, 2)))
        assertEquals(Cell(Coordinates(3, 2), CellValueEnum.HINT_1), matrix.getCell(Coordinates(3, 2)))

        assertEquals(Cell(Coordinates(1, 3), CellValueEnum.HINT_1), matrix.getCell(Coordinates(1, 3)))
        assertEquals(Cell(Coordinates(2, 3), CellValueEnum.HINT_1), matrix.getCell(Coordinates(2, 3)))
        assertEquals(Cell(Coordinates(3, 3), CellValueEnum.HINT_1), matrix.getCell(Coordinates(3, 3)))

    }

    @Test
    @DisplayName("processPlayerNextMove(): ERROR_HINT")
    fun processPlayerNextMove_ERROR_HINT() {
        val matrix = matrixHelper.generateMatrixWithData()

        assertEquals(NextMoveProcessResultEnum.ERROR_HINT, matrixService.processPlayerNextMove(matrix, Coordinates(2, 3)))
    }

    @Test
    @DisplayName("processPlayerNextMove(): END_GAME")
    fun processPlayerNextMove_END_GAME() {
        val matrix = matrixHelper.generateMatrixWithData()

        assertEquals(NextMoveProcessResultEnum.END_GAME, matrixService.processPlayerNextMove(matrix, Coordinates(3, 4)))
    }

    @Test
    @DisplayName("processPlayerNextMove(): CONTINUE")
    fun processPlayerNextMove_CONTINUE() {
        val matrix = matrixHelper.generateMatrixWithData()

        assertEquals(NextMoveProcessResultEnum.CONTINUE, matrixService.processPlayerNextMove(matrix, Coordinates(1, 1)))
    }

}
