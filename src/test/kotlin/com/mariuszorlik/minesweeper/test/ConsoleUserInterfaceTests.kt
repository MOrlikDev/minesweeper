package com.mariuszorlik.minesweeper.test

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import com.mariuszorlik.minesweeper.MatrixService
import com.mariuszorlik.minesweeper.ui.ConsoleUserInterface
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertFailsWith

class ConsoleUserInterfaceTests {

    @Test
    @DisplayName("askPlayerForNumberOfMines() - input = output")
    fun askPlayerForNumberOfMines_inputEqualOutput() {
        withTextFromSystemIn("5")
            .execute {
                Assertions.assertEquals(5, ConsoleUserInterface().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines() - minimum value 1")
    fun askPlayerForNumberOfMines_minValueOk() {
        withTextFromSystemIn("1")
            .execute {
                Assertions.assertEquals(1, ConsoleUserInterface().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines() - minimum value 0 - Exception")
    fun askPlayerForNumberOfMines_minValueException() {
        withTextFromSystemIn("0")
            .andExceptionThrownOnInputEnd(IllegalArgumentException())
            .execute() {
                assertFailsWith<IllegalArgumentException>(
                    block = { ConsoleUserInterface().askPlayerForNumberOfMines() }
                )
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines() - maximum value 20")
    fun askPlayerForNumberOfMines_maxValueOk() {
        withTextFromSystemIn("20")
            .execute {
                Assertions.assertEquals(20, ConsoleUserInterface().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines() - maximum value 21 - Exception")
    fun askPlayerForNumberOfMines_maxValueException() {
        withTextFromSystemIn("21")
            .andExceptionThrownOnInputEnd(IllegalArgumentException())
            .execute() {
                assertFailsWith<IllegalArgumentException>(
                    block = { ConsoleUserInterface().askPlayerForNumberOfMines() }
                )
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines() - not an integer - Exception")
    fun askPlayerForNumberOfMines_notIntException() {
        withTextFromSystemIn("qwerty")
            .andExceptionThrownOnInputEnd(IllegalArgumentException())
            .execute() {
                assertFailsWith<IllegalArgumentException>(
                    block = { ConsoleUserInterface().askPlayerForNumberOfMines() }
                )
            }
    }

    @Test
    @Ignore
    fun drawEmptyMatrixBetter() {
        val matrixService = MatrixService()
        matrixService.generateEmptyMatrix()
        val matrix = matrixService.matrix

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

        val actual = tapSystemOut { ConsoleUserInterface().drawMatrix(matrix) }
        assertEquals(expected.toString(), actual)
    }

    @Test
    fun drawEmptyMatrixProper() {
        val matrixService = MatrixService()
        matrixService.generateEmptyMatrix()
        val matrix = matrixService.matrix

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

        val actual = tapSystemOut { ConsoleUserInterface().drawMatrix(matrix) }
        assertEquals(expected.toString(), actual)
    }
}
