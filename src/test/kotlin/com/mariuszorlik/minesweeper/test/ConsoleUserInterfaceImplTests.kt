package com.mariuszorlik.minesweeper.test

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import com.mariuszorlik.minesweeper.model.Coordinates
import com.mariuszorlik.minesweeper.model.NextMove
import com.mariuszorlik.minesweeper.model.NextMoveEnum
import com.mariuszorlik.minesweeper.service.MatrixService
import com.mariuszorlik.minesweeper.view.ConsoleUserInterfaceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class ConsoleUserInterfaceImplTests {

    private val matrixService = MatrixService()

    @Test
    @DisplayName("askPlayerForNumberOfMines(): input = output")
    internal fun askPlayerForNumberOfMines_inputEqualOutput() {
        withTextFromSystemIn("5")
            .execute {
                assertEquals(5, ConsoleUserInterfaceImpl().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines(): minimum value 1")
    internal fun askPlayerForNumberOfMines_minValueOk() {
        withTextFromSystemIn("1")
            .execute {
                assertEquals(1, ConsoleUserInterfaceImpl().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines(): minimum value 0 - Exception")
    internal fun askPlayerForNumberOfMines_minValueException() {
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
    internal fun askPlayerForNumberOfMines_maxValueOk() {
        withTextFromSystemIn("20")
            .execute {
                assertEquals(20, ConsoleUserInterfaceImpl().askPlayerForNumberOfMines())
            }
    }

    @Test
    @DisplayName("askPlayerForNumberOfMines(): maximum value 21 - Exception")
    internal fun askPlayerForNumberOfMines_maxValueException() {
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
    internal fun askPlayerForNumberOfMines_notIntException() {
        withTextFromSystemIn("qwerty")
            .andExceptionThrownOnInputEnd(IllegalArgumentException())
            .execute {
                assertFailsWith<IllegalArgumentException>(
                    block = { ConsoleUserInterfaceImpl().askPlayerForNumberOfMines() }
                )
            }
    }

//    @Test
//    @Disabled
//    @DisplayName("drawEmptyMatrixBetter(): ok")
//    internal fun drawEmptyMatrixBetter() {
//        val expected = StringBuilder()
//        expected.append("\r\n")
//        expected.append("  |  1  2  3  4  5  6  7  8  9  |\r\n")
//        expected.append("—-|-----------------------------|\r\n")
//        expected.append("1 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("2 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("3 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("4 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("5 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("6 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("7 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("8 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("9 |  .  .  .  .  .  .  .  .  .  |\r\n")
//        expected.append("—-|-----------------------------|\r\n")
//
//        val matrix = matrixService.generateEmptyMatrix()
//        val actual = tapSystemOut { ConsoleUserInterfaceImpl().drawMatrix(matrix) }
//
//        assertEquals(expected.toString(), actual)
//    }

    @Test
    @DisplayName("drawEmptyMatrixProper(): ok")
    internal fun drawEmptyMatrixProper() {
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
    internal fun askPlayerForNextMove() {
        withTextFromSystemIn("2 3 free")
            .execute {
                assertEquals(NextMove(Coordinates(2, 3), NextMoveEnum.FREE), ConsoleUserInterfaceImpl().askPlayerForNextMove())
            }
    }

    @Test
    @DisplayName("askPlayerForNextMove(): exception")
    internal fun askPlayerForNextMove_exception() {
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
    internal fun drawErrorHint() {
        assertEquals("There is a number here!\r\n", tapSystemOut { ConsoleUserInterfaceImpl().printErrorHint() })
    }

    @Test
    @DisplayName("printEndGame()")
    internal fun printEndGame() {
        assertEquals("Congratulations! You found all the mines!\r\n",
            tapSystemOut { ConsoleUserInterfaceImpl().printEndGame() })
    }

    @Test
    @DisplayName("printGameOver()")
    internal fun printGameOver() {
        assertEquals("You stepped on a mine and failed!\r\n",
            tapSystemOut { ConsoleUserInterfaceImpl().printGameOver() })

    }
}
