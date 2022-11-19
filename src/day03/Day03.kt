package day03

import utils.readInput

fun main() {
    fun part1(input: List<String>, rightMove: Int, downMove: Int): Int {
        var result = 0
        var currentIndex = 0
        val lineLength = input[0].length
        var row = 0
        while (row < input.size) {
            val line = input[row]
            if (line[currentIndex] == '#') {
                result++
            }
            currentIndex = (currentIndex + rightMove) % lineLength
            row += downMove
        }
        return result
    }

    val pairs = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
    fun part2(input: List<String>): Int {
        var result: Long = 1
        for (slope in pairs) {
            result *= part1(input, slope.first, slope.second)
        }
        return result.toInt()
    }

    val testInput = readInput("day03/Day03_test")
    check(part1(testInput, 3, 1) == 7)
    check(part2(testInput) == 336)


    val input = readInput("day03/Day03")
    println(part1(input, 3, 1))
    println(part2(input))
}