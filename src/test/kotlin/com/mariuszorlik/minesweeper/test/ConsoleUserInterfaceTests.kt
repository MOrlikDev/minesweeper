package com.mariuszorlik.minesweeper.test

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.service.MatrixService
import com.mariuszorlik.minesweeper.view.ConsoleUserInterfaceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ConsoleUserInterfaceTests {

    private val matrixService = MatrixService()

    @Test
    @DisplayName("askPlayerForNumberOfMines(): input = output")
    fun askPlayerForNumberOfMines_inputEqualOutput() {
        withTextFromSystemIn("5")
            .execute {
                assertEquals(5, ConsoleUserInterfaceImpl().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines(): minimum value 1")
    fun askPlayerForNumberOfMines_minValueOk() {
        withTextFromSystemIn("1")
            .execute {
                assertEquals(1, ConsoleUserInterfaceImpl().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines(): minimum value 0 - Exception")
    fun askPlayerForNumberOfMines_minValueException() {
        withTextFromSystemIn("0")
            .andExceptionThrownOnInputEnd(IllegalArgumentException())
            .execute {
                assertFailsWith<IllegalArgumentException>(
                    block = { ConsoleUserInterfaceImpl().askPlayerForNumberOfMines() }
                )
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines(): maximum value 20")
    fun askPlayerForNumberOfMines_maxValueOk() {
        withTextFromSystemIn("20")
            .execute {
                assertEquals(20, ConsoleUserInterfaceImpl().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines(): maximum value 21 - Exception")
    fun askPlayerForNumberOfMines_maxValueException() {
        withTextFromSystemIn("21")
            .andExceptionThrownOnInputEnd(IllegalArgumentException())
            .execute {
                assertFailsWith<IllegalArgumentException>(
                    block = { ConsoleUserInterfaceImpl().askPlayerForNumberOfMines() }
                )
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines(): not an integer - Exception")
    fun askPlayerForNumberOfMines_notIntException() {
        withTextFromSystemIn("qwerty")
            .andExceptionThrownOnInputEnd(IllegalArgumentException())
            .execute {
                assertFailsWith<IllegalArgumentException>(
                    block = { ConsoleUserInterfaceImpl().askPlayerForNumberOfMines() }
                )
            }
    }

    @Test
    @Disabled
    @DisplayName("drawEmptyMatrixBetter(): ok")
    fun drawEmptyMatrixBetter() {
        val expected = StringBuilder()
        expected.append("\r\n")
        expected.append("  |  1  2  3  4  5  6  7  8  9  |\r\n")
        expected.append("—-|-----------------------------|\r\n")
        expected.append("1 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("2 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("3 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("4 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("5 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("6 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("7 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("8 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("9 |  .  .  .  .  .  .  .  .  .  |\r\n")
        expected.append("—-|-----------------------------|\r\n")

        val matrix = matrixService.generateEmptyMatrix()
        val actual = tapSystemOut { ConsoleUserInterfaceImpl().drawMatrix(matrix) }

        assertEquals(expected.toString(), actual)
    }

    @Test
    @DisplayName("drawEmptyMatrixProper(): ok")
    fun drawEmptyMatrixProper() {
        val expected = StringBuilder()
        expected.append("\r\n")
        expected.append(" |123456789|\r\n")
        expected.append("—|—————————|\r\n")
        expected.append("1|.........|\r\n")
        expected.append("2|.........|\r\n")
        expected.append("3|.........|\r\n")
        expected.append("4|.........|\r\n")
        expected.append("5|.........|\r\n")
        expected.append("6|.........|\r\n")
        expected.append("7|.........|\r\n")
        expected.append("8|.........|\r\n")
        expected.append("9|.........|\r\n")
        expected.append("—|—————————|\r\n")

        val matrix = matrixService.generateEmptyMatrix()
        val actual = tapSystemOut { ConsoleUserInterfaceImpl().drawMatrix(matrix) }

        assertEquals(expected.toString(), actual)
    }

    @Test
    @DisplayName("askPlayerForNextMove(): ok")
    fun askPlayerForNextMove() {
        withTextFromSystemIn("2 3")
            .execute {
                assertEquals(Coordinates(2, 3), ConsoleUserInterfaceImpl().askPlayerForNextMove())
            }
    }

    @Test
    @DisplayName("askPlayerForNextMove(): exception")
    fun askPlayerForNextMove_exception() {
        withTextFromSystemIn("23")
            .andExceptionThrownOnInputEnd(IllegalArgumentException())
            .execute {
                assertFailsWith<IllegalArgumentException>(
                    block = { ConsoleUserInterfaceImpl().askPlayerForNextMove() }
                )
            }
    }

    @Test
    @DisplayName("drawErrorHint()")
    fun drawErrorHint() {
        assertEquals("There is a number here!\r\n", tapSystemOut { ConsoleUserInterfaceImpl().drawErrorHint() })
    }

    @Test
    @DisplayName("drawEndGame()")
    fun drawEndGame() {
        assertEquals("Congratulations! You found all the mines!\r\n",
            tapSystemOut { ConsoleUserInterfaceImpl().drawEndGame() })
    }

}
