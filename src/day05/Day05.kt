package day05

import utils.readInput

fun main() {
    val rowsMaxValue = 127
    val columnMaxValue = 7
    val splitRegex = "((?=([RL]){3}))"
    fun binarySearchByPattern(minValue: Int = 0, maxValue: Int, patternChar: Char): Pair<Int, Int> {
        var tempMax = maxValue
        var tempMin = minValue
        val midValue = (maxValue + minValue) / 2
        when (patternChar) {
            'F' -> tempMax = midValue
            'L' -> tempMax = midValue
            'B' -> tempMin = midValue + 1
            'R' -> tempMin = midValue + 1
        }
        return tempMin to tempMax
    }

    fun countSeatID(pattern: String): Int {
        val strings = pattern.split(splitRegex.toRegex())
        val rowsPattern = strings[0]
        val columnsPatter = strings[1]
        var initColumnPair = 0 to columnMaxValue
        var initRowsPair = 0 to rowsMaxValue

        for (char in rowsPattern.toCharArray()) {
            initRowsPair = binarySearchByPattern(initRowsPair.first, initRowsPair.second, char)
        }

        for (char in columnsPatter.toCharArray()) {
            initColumnPair =
                binarySearchByPattern(initColumnPair.first, initColumnPair.second, char)
        }
        return (initRowsPair.first * 8) + initColumnPair.first
    }


    fun part1(input: List<String>): Int {
        return input.stream().map { countSeatID(it) }.toList().max()
    }

    fun part2(input: List<String>): Int {
        val sortedSeatIDs = input.stream().map { countSeatID(it) }.sorted().toList()
        val max = sortedSeatIDs[sortedSeatIDs.size - 1]
        val min = sortedSeatIDs[0]
        var expectedSum = 0
        for (i in min..max) {
            expectedSum += i
        }
        return expectedSum - input.stream().map { countSeatID(it) }.toList().sum()
    }


    val testInput = readInput("day05/day05_test")
    check(part1(testInput) == 820)


    val input = readInput("day05/day05")
    println(part1(input))
    println(part2(input))

}