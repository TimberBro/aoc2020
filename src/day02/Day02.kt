package day02

import utils.readInput

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val tempSplit = line.split(":")
            val policy = tempSplit[0]
            val password = tempSplit[1].trim()
            val tempSplit1 = policy.split(" ")
            val range = tempSplit1[0]
            val character = tempSplit1[1]
            val rangeSplit = range.split("-")
            val minValue = rangeSplit[0].toInt()
            val maxValue = rangeSplit[1].toInt()
            if (password.count { it == character[0] } in minValue..maxValue) {
                result++
            }
        }
        return result
    }


    fun part2(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val tempSplit = line.split(":")
            val policy = tempSplit[0]
            val password = tempSplit[1].trim()
            val tempSplit1 = policy.split(" ")
            val range = tempSplit1[0]
            val character = tempSplit1[1]
            val rangeSplit = range.split("-")
            val minValue = rangeSplit[0].toInt()
            val maxValue = rangeSplit[1].toInt()
            if ((password[minValue - 1] == character[0]).xor(password[maxValue - 1] == character[0])) {
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