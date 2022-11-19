package day03

import utils.readInput

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        var currentIndex = 0
        val lineLength = input[0].length
        for (line in input.subList(1, input.size)) {
            currentIndex = (currentIndex + 3) % lineLength
            if (line[currentIndex] == '#') {
                result++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 7)


    val input = readInput("day03/Day03")
    println(part1(input))
}