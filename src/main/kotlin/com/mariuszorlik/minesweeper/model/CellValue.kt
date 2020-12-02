package com.mariuszorlik.minesweeper.model

enum class CellValue(val symbol: Char, val value: Int = 0, val mine: Boolean = false) {
    NON_MARKED_EMPTY('.', mine = false),
    NON_MARKED_MINE('.', mine = true),
    MARKED_EMPTY('*', mine = false),
    MARKED_MINE('*', mine = true),
    HINT_1('1', value = 1),
    HINT_2('2', value = 2),
    HINT_3('3', value = 3),
    HINT_4('4', value = 4),
    HINT_5('5', value = 5),
    HINT_6('6', value = 6),
    HINT_7('7', value = 7),
    HINT_8('8', value = 8),
    HINT_9('9', value = 9),
    NULL(' ', 0);

    companion object {
        fun findByValue(value: Int): CellValue {
            for (enum in CellValue.values()) {
                if (value == enum.value) return enum
            }
            return NULL
        }
    }
}
