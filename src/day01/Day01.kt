package day01

import utils.readInput

fun main() {

    fun part1(input: List<String>, desiredSum: Int): Int {
        val foundNumbers = HashSet<Int>()
        for (number in input) {
            val currentNumber = number.toInt()
            val numberToFind = desiredSum - currentNumber
            if (foundNumbers.contains(numberToFind)) {
                return currentNumber * numberToFind
            } else {
                foundNumbers.add(currentNumber)
            }
        }
        throw RuntimeException("Could not found any pair")
    }

    // Does not work
    fun part2(input: List<String>): Int {
        // this is ugly, but I don't want to change the method signature
        val sortedInput = input.map { it.toInt() }.sorted().map { it.toString() }.toList()

        for ((index, number) in sortedInput.withIndex()) {
            try {
                val part1 = part1(
                    sortedInput.subList(index, sortedInput.lastIndex),
                    2020 - number.toInt()
                )
                return number.toInt() * part1
            } catch (e: RuntimeException) {
                continue
            }
        }
        throw RuntimeException("Could not found any pair")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput, 2020) == 514579)
    check(part2(testInput) == 241861950)


    val input = readInput("day01/Day01")
    println(part1(input, 2020))
    println(part2(input))
}
