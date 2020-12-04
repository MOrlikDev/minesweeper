package com.mariuszorlik.minesweeper.test

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.CellValueEnum
import com.mariuszorlik.minesweeper.model.Coordinates
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CellTests {

    @Nested
    inner class Setters {

        @Test
        @DisplayName("setMark(): value = NON_MARKED_EMPTY -> MARKED_EMPTY")
        fun setMark_MARKED_EMPTY() {
            val expected = Cell(cellValue = CellValueEnum.MARKED_EMPTY)

            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)
            actual.setMark()

            assertEquals(expected, actual)
        }

        @Test
        @DisplayName("setMark(): value = NON_MARKED_MINE -> MARKED_MINE")
        fun setMark_MARKED_MINE() {
            val expected = Cell(cellValue = CellValueEnum.MARKED_MINE)

            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)
            actual.setMark()

            assertEquals(expected, actual)
        }

        @Test
        @DisplayName("setUnmark(): value = MARKED_EMPTY -> NON_MARKED_EMPTY")
        fun setUnmark_NON_MARKED_EMPTY() {
            val expected = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)

            val actual = Cell(cellValue = CellValueEnum.MARKED_EMPTY)
            actual.setUnmark()

            assertEquals(expected, actual)
        }

        @Test
        @DisplayName("setUnmark(): value = MARKED_MINE -> NON_MARKED_MINE")
        fun setUnmark_NON_MARKED_MINE() {
            val expected = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)

            val actual = Cell(cellValue = CellValueEnum.MARKED_MINE)
            actual.setUnmark()

            assertEquals(expected, actual)
        }

        @Test
        @DisplayName("setNonMarkedMine(): value = NON_MARKED_MINE")
        fun setNonMarkedMine() {
            val expected = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)

            val actual = Cell(cellValue = CellValueEnum.MARKED_MINE)
            actual.setUnmark()
            assertEquals(expected, actual)
        }

    }

    @Nested
    inner class Getters {

        @Test
        @DisplayName("isMine(): NON_MARKED_EMPTY -> false")
        fun isMine_NON_MARKED_EMPTY() {
            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)

            assertFalse(actual.isMine())
        }

        @Test
        @DisplayName("isMine(): MARKED_MINE -> true")
        fun isMine_MARKED_MINE() {
            val actual = Cell(cellValue = CellValueEnum.MARKED_MINE)

            assertTrue(actual.isMine())
        }

        @Test
        @DisplayName("isMine(): NON_MARKED_MINE -> true")
        fun isMine_NON_MARKED_MINE() {
            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)

            assertTrue(actual.isMine())
        }

        @Test
        @DisplayName("isMine(): MARKED_EMPTY -> false")
        fun isMine_MARKED_EMPTY() {
            val actual = Cell(cellValue = CellValueEnum.MARKED_EMPTY)

            assertFalse(actual.isMine())
        }

        @Test
        @DisplayName("isMarked(): MARKED_MINE -> true")
        fun isMarked_MARKED_MINE() {
            val actual = Cell(cellValue = CellValueEnum.MARKED_MINE)

            assertTrue(actual.isMarked())
        }

        @Test
        @DisplayName("isMarked(): MARKED_EMPTY -> true")
        fun isMarked_MARKED_EMPTY() {
            val actual = Cell(cellValue = CellValueEnum.MARKED_EMPTY)

            assertTrue(actual.isMarked())
        }

        @Test
        @DisplayName("isMarked(): NON_MARKED_MINE -> false")
        fun isMarked_NON_MARKED_MINE() {
            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)

            assertFalse(actual.isMarked())
        }

        @Test
        @DisplayName("isMarked(): NON_MARKED_EMPTY -> false")
        fun isMarked_NON_MARKED_EMPTY() {
            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)

            assertFalse(actual.isMarked())
        }

        @Test
        @DisplayName("isNonMarked(): NON_MARKED_EMPTY -> true")
        fun isNonMarked_NON_MARKED_EMPTY() {
            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)

            assertTrue(actual.isNonMarked())
        }

        @Test
        @DisplayName("isNonMarked(): NON_MARKED_MINE -> true")
        fun isNonMarked_NON_MARKED_MINE() {
            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)

            assertTrue(actual.isNonMarked())
        }

        @Test
        @DisplayName("isNonMarked(): MARKED_EMPTY -> false")
        fun isNonMarked_MARKED_EMPTY() {
            val actual = Cell(cellValue = CellValueEnum.MARKED_EMPTY)

            assertFalse(actual.isNonMarked())
        }

        @Test
        @DisplayName("isNonMarked(): MARKED_MINE -> false")
        fun isNonMarked_MARKED_MINE() {
            val actual = Cell(cellValue = CellValueEnum.MARKED_MINE)

            assertFalse(actual.isNonMarked())
        }

        @Test
        @DisplayName("isNonMarkedEmpty(): NON_MARKED_EMPTY -> true")
        fun isNonMarkedEmpty() {
            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)

            assertTrue(actual.isNonMarkedEmpty())
        }

        @Test
        @DisplayName("isNonMarkedEmpty(): !NON_MARKED_EMPTY -> false")
        fun isNonMarkedEmptyFalse() {
            val actual1 = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)
            val actual2 = Cell(cellValue = CellValueEnum.MARKED_EMPTY)
            val actual3 = Cell(cellValue = CellValueEnum.MARKED_MINE)

            assertFalse(actual1.isNonMarkedEmpty())
            assertFalse(actual2.isNonMarkedEmpty())
            assertFalse(actual3.isNonMarkedEmpty())
        }

        @Test
        @DisplayName("isNonMarkedMine(): NON_MARKED_MINE -> true")
        fun isNonMarkedMine() {
            val actual = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)

            assertTrue(actual.isNonMarkedMine())
        }

        @Test
        @DisplayName("isNonMarkedMine(): !NON_MARKED_MINE -> false")
        fun isNonMarkedMineFalse() {
            val actual1 = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)
            val actual2 = Cell(cellValue = CellValueEnum.MARKED_EMPTY)
            val actual3 = Cell(cellValue = CellValueEnum.MARKED_MINE)

            assertFalse(actual1.isNonMarkedMine())
            assertFalse(actual2.isNonMarkedMine())
            assertFalse(actual3.isNonMarkedMine())
        }

        @Test
        @DisplayName("isMarkedEmpty(): MARKED_EMPTY -> true")
        fun isMarkedEmpty() {
            val actual = Cell(cellValue = CellValueEnum.MARKED_EMPTY)

            assertTrue(actual.isMarkedEmpty())
        }

        @Test
        @DisplayName("isMarkedEmpty(): !MARKED_EMPTY -> false")
        fun isMarkedEmptyFalse() {
            val actual1 = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)
            val actual2 = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)
            val actual3 = Cell(cellValue = CellValueEnum.MARKED_MINE)

            assertFalse(actual1.isMarkedEmpty())
            assertFalse(actual2.isMarkedEmpty())
            assertFalse(actual3.isMarkedEmpty())
        }

        @Test
        @DisplayName("isHint(): HINT_1..HINT_9 -> true")
        fun isHint_1_9() {
            val actual1 = Cell(cellValue = CellValueEnum.HINT_1)
            val actual2 = Cell(cellValue = CellValueEnum.HINT_2)
            val actual3 = Cell(cellValue = CellValueEnum.HINT_3)
            val actual4 = Cell(cellValue = CellValueEnum.HINT_4)
            val actual5 = Cell(cellValue = CellValueEnum.HINT_5)
            val actual6 = Cell(cellValue = CellValueEnum.HINT_6)
            val actual7 = Cell(cellValue = CellValueEnum.HINT_7)
            val actual8 = Cell(cellValue = CellValueEnum.HINT_8)
            val actual9 = Cell(cellValue = CellValueEnum.HINT_9)

            assertTrue(actual1.isHint())
            assertTrue(actual2.isHint())
            assertTrue(actual3.isHint())
            assertTrue(actual4.isHint())
            assertTrue(actual5.isHint())
            assertTrue(actual6.isHint())
            assertTrue(actual7.isHint())
            assertTrue(actual8.isHint())
            assertTrue(actual9.isHint())
        }

        @Test
        @DisplayName("isHint(): !hint -> false")
        fun isHint_false() {
            val actual1 = Cell(cellValue = CellValueEnum.MARKED_MINE)
            val actual2 = Cell(cellValue = CellValueEnum.MARKED_EMPTY)
            val actual3 = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)
            val actual4 = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)

            assertFalse(actual1.isHint())
            assertFalse(actual2.isHint())
            assertFalse(actual3.isHint())
            assertFalse(actual4.isHint())
        }

    }

    @Test
    @DisplayName("isTheSamePosition(): true")
    fun isTheSamePosition_true() {
        val expected = Cell(Coordinates(2, 3))

        val actual = Cell(Coordinates(2, 3))
        assertTrue(actual.isTheSamePosition(expected))
    }

    @Test
    @DisplayName("isTheSamePosition(): false")
    fun isTheSamePosition_false() {
        val expected = Cell(Coordinates(2, 3))

        val actual = Cell(Coordinates(4, 5))
        assertFalse(actual.isTheSamePosition(expected))
    }

    @Test
    @DisplayName("incrementHint(): no change")
    fun incrementHint_noChange() {
        val expectation = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)

        val actual = Cell(cellValue = CellValueEnum.NON_MARKED_MINE)
        actual.incrementHint()

        assertEquals(expectation, actual)
    }

    @Test
    @DisplayName("incrementHint(): empty -> 1")
    fun incrementHint_empty_1() {
        val expectation = Cell(cellValue = CellValueEnum.HINT_1)

        val actual = Cell(cellValue = CellValueEnum.NON_MARKED_EMPTY)
        actual.incrementHint()

        assertEquals(expectation, actual)
    }

    @Test
    @DisplayName("incrementHint(): 5 -> 6")
    fun incrementHint_5_6() {
        val expectation = Cell(cellValue = CellValueEnum.HINT_6)

        val actual = Cell(cellValue = CellValueEnum.HINT_5)
        actual.incrementHint()

        assertEquals(expectation, actual)
    }

    @Test
    @DisplayName("incrementHint(): 9 -> 9")
    fun incrementHint_9() {
        val expectation = Cell(cellValue = CellValueEnum.HINT_9)

        val actual = Cell(cellValue = CellValueEnum.HINT_9)
        actual.incrementHint()

        assertEquals(expectation, actual)
    }

}