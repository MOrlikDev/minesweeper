package com.mariuszorlik.minesweeper.test

import com.mariuszorlik.minesweeper.model.Cell
import com.mariuszorlik.minesweeper.model.CellStateEnum
import com.mariuszorlik.minesweeper.model.Coordinates
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CellTests {

    @Nested
    inner class Setters {

        @Test
        @DisplayName("setMarked(): ok")
        fun setMarked() {
            val expected = Cell(marked = true)

            val actual = Cell(marked = false)
            actual.setMarked()

            assertEquals(expected, actual)
        }

        @Test
        @DisplayName("setUnmark(): ok")
        fun setUnmarked() {
            val expected = Cell(marked = false)

            val actual = Cell(marked = true)
            actual.setUnmarked()

            assertEquals(expected, actual)
        }

        @Test
        @DisplayName("setExmploted(): ok")
        fun setExmploted() {
            val expected = Cell(explored = true)

            val actual = Cell(explored = false)
            actual.setExplored()

            assertEquals(expected, actual)
        }

    }

    @Nested
    inner class Getters {

        @Test
        @DisplayName("isMine(): true")
        fun isMine_true() {
            val actual = Cell(cellState = CellStateEnum.MINE)

            assertTrue(actual.isMine())
        }

        @Test
        @DisplayName("isMine(): false")
        fun isMine_false() {
            val actual = Cell(cellState = CellStateEnum.FREE)

            assertFalse(actual.isMine())
        }

        @Test
        @DisplayName("isFree(): true")
        fun isFree_true() {
            val actual = Cell(cellState = CellStateEnum.FREE)

            assertTrue(actual.isFree())
        }

        @Test
        @DisplayName("isFree(): false")
        fun isFree_false() {
            val actual = Cell(cellState = CellStateEnum.MINE)

            assertFalse(actual.isFree())
        }

        @Test
        @DisplayName("isHint(): HINT_1..HINT_8 -> true")
        fun isHint_1_8() {
            val actual1 = Cell(cellState = CellStateEnum.HINT_1)
            val actual2 = Cell(cellState = CellStateEnum.HINT_2)
            val actual3 = Cell(cellState = CellStateEnum.HINT_3)
            val actual4 = Cell(cellState = CellStateEnum.HINT_4)
            val actual5 = Cell(cellState = CellStateEnum.HINT_5)
            val actual6 = Cell(cellState = CellStateEnum.HINT_6)
            val actual7 = Cell(cellState = CellStateEnum.HINT_7)
            val actual8 = Cell(cellState = CellStateEnum.HINT_8)

            assertTrue(actual1.isHint())
            assertTrue(actual2.isHint())
            assertTrue(actual3.isHint())
            assertTrue(actual4.isHint())
            assertTrue(actual5.isHint())
            assertTrue(actual6.isHint())
            assertTrue(actual7.isHint())
            assertTrue(actual8.isHint())
        }

        @Test
        @DisplayName("isHint(): !hint -> false")
        fun isHint_false() {
            val actual1 = Cell(cellState = CellStateEnum.FREE)
            val actual2 = Cell(cellState = CellStateEnum.MINE)

            assertFalse(actual1.isHint())
            assertFalse(actual2.isHint())
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
        val expectation = Cell(cellState = CellStateEnum.MINE)

        val actual = Cell(cellState = CellStateEnum.MINE)
        actual.incrementHint()

        assertEquals(expectation, actual)
    }

    @Test
    @DisplayName("incrementHint(): free -> 1")
    fun incrementHint_free_1() {
        val expectation = Cell(cellState = CellStateEnum.HINT_1)

        val actual = Cell(cellState = CellStateEnum.FREE)
        actual.incrementHint()

        assertEquals(expectation, actual)
    }

    @Test
    @DisplayName("incrementHint(): 5 -> 6")
    fun incrementHint_5_6() {
        val expectation = Cell(cellState = CellStateEnum.HINT_6)

        val actual = Cell(cellState = CellStateEnum.HINT_5)
        actual.incrementHint()

        assertEquals(expectation, actual)
    }

    @Test
    @DisplayName("incrementHint(): 8 -> 8")
    fun incrementHint_9() {
        val expectation = Cell(cellState = CellStateEnum.HINT_8)

        val actual = Cell(cellState = CellStateEnum.HINT_8)
        actual.incrementHint()

        assertEquals(expectation, actual)
    }

}