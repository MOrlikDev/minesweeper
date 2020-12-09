package com.mariuszorlik.minesweeper.model

import java.lang.IllegalArgumentException

enum class CellStateEnum(val symbol: Char, val value: Int = 0) {

    UNEXPLORED('.'),
    MARKED('*'),

    FREE('/'),
    MINE('X'),

    HINT_1('1', value = 1),
    HINT_2('2', value = 2),
    HINT_3('3', value = 3),
    HINT_4('4', value = 4),
    HINT_5('5', value = 5),
    HINT_6('6', value = 6),
    HINT_7('7', value = 7),
    HINT_8('8', value = 8);

    companion object {
        fun findByValue(value: Int): CellStateEnum {
            for (enum in values()) {
                if (value == enum.value) return enum
            }
            throw IllegalArgumentException()
        }
    }
}
