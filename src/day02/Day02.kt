package day02

import utils.readInput

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val splitLine = line.split(":", "-", " ")
            val minValue = splitLine[0].toInt()
            val maxValue = splitLine[1].toInt()
            val character = splitLine[2][0]
            val password = splitLine[4].trim()
            if (password.count { it == character } in minValue..maxValue) {
                result++
            }
        }
        return result
    }


    fun part2(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val splitLine = line.split(":", "-", " ")
            val minValue = splitLine[0].toInt()
            val maxValue = splitLine[1].toInt()
            val character = splitLine[2][0]
            val password = splitLine[4].trim()
            if ((password[minValue - 1] == character).xor(password[maxValue - 1] == character)) {
                result++
            }
        }
        return result
    }

    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 1)

    val input = readInput("day02/Day02")
    println(part1(input))
    println(part2(input))
}