package example

import java.util.*


enum class Direction {
    NORTH, SOUTH, EAST, WEST
}

fun String.parseDirection_1() =
        when (this.toLowerCase(Locale.ROOT)) {
            "north" -> Direction.NORTH
            "south" -> Direction.SOUTH
            "east" -> Direction.EAST
            "west" -> Direction.WEST
            else -> null
        }

fun String.parseDirection_2() =
        Direction.values().find { it.name.equals(this, ignoreCase = true) }
