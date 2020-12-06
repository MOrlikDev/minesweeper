package com.mariuszorlik.minesweeper.test

import com.mariuszorlik.minesweeper.model.*
import com.mariuszorlik.minesweeper.model.Constants.MATRIX_SIZE
import com.mariuszorlik.minesweeper.service.MatrixService
import com.mariuszorlik.minesweeper.test.helpers.MatrixServiceTestHelper
import com.mariuszorlik.minesweeper.test.helpers.MatrixTestHelper
import com.mariuszorlik.minesweeper.view.ConsoleUserInterfaceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatrixServiceTests {

    private val matrixService: MatrixService = MatrixService()
    private val serviceHelper: MatrixServiceTestHelper = MatrixServiceTestHelper()
    private val matrixHelper: MatrixTestHelper = MatrixTestHelper()

    @Test
    @DisplayName("generateEmptyMatrix(): ok")
    fun generateEmptyMatrix() {
        val expectedNumberOfCells = MATRIX_SIZE * MATRIX_SIZE
        val expectedCellState = CellStateEnum.FREE

        val actual = matrixService.generateEmptyMatrix()

//        ConsoleUserInterfaceImpl().drawMatrix(actual)
        assertEquals(expectedNumberOfCells, actual.cellList.size)

        for (cell in actual.cellList) {
            Assertions.assertNotNull(cell.coordinates)
            Assertions.assertNotNull(cell.coordinates.x)
            Assertions.assertNotNull(cell.coordinates.y)
            Assertions.assertEquals(expectedCellState, cell.cellState)
        }
    }

    @Test
    @DisplayName("addMinesInRandomPlaces(): 5=5")
    fun addMinesInRandomPlaces() {
        val matrix = serviceHelper.generateEmptyMatrixForTest()
        matrixService.addMinesInRandomPlaces(matrix, 5)

        assertEquals(5, matrix.getNumberOfMines())
    }

    @Test
    @DisplayName("addHintsAroundMines(): ok")
    fun addHintsAroundMines() {
        val matrix = serviceHelper.generateEmptyMatrixForTest()
        matrix.getCell(Coordinates(2, 2)).setMine()

        matrixService.addHintsAroundMines(matrix)

        assertEquals(Cell(Coordinates(1, 1), CellStateEnum.HINT_1), matrix.getCell(Coordinates(1, 1)))
        assertEquals(Cell(Coordinates(2, 1), CellStateEnum.HINT_1), matrix.getCell(Coordinates(2, 1)))
        assertEquals(Cell(Coordinates(3, 1), CellStateEnum.HINT_1), matrix.getCell(Coordinates(3, 1)))

        assertEquals(Cell(Coordinates(1, 2), CellStateEnum.HINT_1), matrix.getCell(Coordinates(1, 2)))
        assertEquals(Cell(Coordinates(2, 2), CellStateEnum.MINE), matrix.getCell(Coordinates(2, 2)))
        assertEquals(Cell(Coordinates(3, 2), CellStateEnum.HINT_1), matrix.getCell(Coordinates(3, 2)))

        assertEquals(Cell(Coordinates(1, 3), CellStateEnum.HINT_1), matrix.getCell(Coordinates(1, 3)))
        assertEquals(Cell(Coordinates(2, 3), CellStateEnum.HINT_1), matrix.getCell(Coordinates(2, 3)))
        assertEquals(Cell(Coordinates(3, 3), CellStateEnum.HINT_1), matrix.getCell(Coordinates(3, 3)))

    }

    @Test
    @DisplayName("setUnexploredCellsInWholeMatrix(): ok")
    fun setUnexploredCellsInWholeMatrix(){
        val matrix = serviceHelper.generateEmptyMatrixForTest()
        matrix.getCell(Coordinates(2, 2)).setMine()
        matrixService.addHintsAroundMines(matrix)

        matrixService.setUnexploredCellsInWholeMatrix(matrix)

        for (cell in matrix.cellList) {
            assertFalse(cell.explored)
        }
    }

    @Test
    @DisplayName("processPlayerNextMove(): EXPLORED")
    fun processPlayerNextMove_EXPLORED() {
        val matrix = matrixHelper.generateMatrixWithData()
        matrix.getCell(Coordinates(2,3)).setExplored()
//        ConsoleUserInterfaceImpl().drawMatrix(matrix)

        assertEquals(NextMoveResultEnum.EXPLORED, matrixService.processPlayerNextMove(matrix, NextMove(Coordinates(2, 3), NextMoveEnum.FREE)))
    }

    @Test
    @DisplayName("processPlayerNextMove(): HINT -> CONTINUE")
    fun processPlayerNextMove_HINT_CONTINUE() {
        val matrix = matrixHelper.generateMatrixWithData()

        assertEquals(NextMoveResultEnum.CONTINUE, matrixService.processPlayerNextMove(matrix, NextMove(Coordinates(2, 3), NextMoveEnum.FREE)))
    }

    @Test
    @DisplayName("processPlayerNextMove(): FREE -> CONTINUE")
    fun processPlayerNextMove_FREE_CONTINUE() {
        val matrix = matrixHelper.generateMatrixWithData()

        assertEquals(NextMoveResultEnum.CONTINUE, matrixService.processPlayerNextMove(matrix, NextMove(Coordinates(1, 1), NextMoveEnum.FREE)))
    }

    @Test
    @DisplayName("processPlayerNextMove(): END_GAME")
    fun processPlayerNextMove_END_GAME() {
        val matrix = matrixHelper.generateMatrixWithData()

        assertEquals(NextMoveResultEnum.END_GAME, matrixService.processPlayerNextMove(matrix, NextMove(Coordinates(3, 4), NextMoveEnum.MINE)))
    }



}
