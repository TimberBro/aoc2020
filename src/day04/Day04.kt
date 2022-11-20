package day04

import utils.readInput

fun main() {
    fun separatePassports(input: List<String>): List<List<String>> {
        val passportsList = ArrayList<List<String>>()
        var passport = ArrayList<String>()
        val iterator = input.listIterator()
        while (iterator.hasNext()) {
            val line = iterator.next()
            if (line.isNotEmpty()) {
                passport.add(line)
            } else if (line.isEmpty()) {
                passportsList.add(passport)
                passport = ArrayList()
            }
        }
        passportsList.add(passport)
        return passportsList
    }


    fun part1Valid(passport: Map<String, String>): Boolean {
        if (passport.size == 8 || (passport.size == 7 && !passport.contains("cid"))) {
            return true
        }
        return false
    }

    fun byrValid(byr: String): Boolean {
        val intValue = byr.toInt()
        if (intValue in 1920..2002) {
            return true
        }
        return false
    }

    fun iyrValid(iyr: String): Boolean {
        val intValue = iyr.toInt()
        if (intValue in 2010..2020) {
            return true
        }
        return false
    }

    fun eyrValid(eyr: String): Boolean {
        val intValue = eyr.toInt()
        if (intValue in 2020..2030) {
            return true
        }
        return false
    }

    fun hgtValid(hgt: String): Boolean {
        val valuePattern = "((\\d{3}cm)|(\\d{2}in))"
        val splitPattern = "((?=cm)|(?=in))"
        if (hgt.matches(valuePattern.toRegex())) {
            val strings = hgt.split(splitPattern.toRegex())
            when (strings[1]) {
                "cm" -> return (strings[0].toInt() >= 150 || strings[0].toInt() <= 193)
                "in" -> return (strings[0].toInt() >= 59 || strings[0].toInt() <= 76)
            }
            return false
        }
        return false
    }

    fun hclValid(hcl: String): Boolean {
        val pattern = "(#[\\da-f]{6})"
        return hcl.matches(pattern.toRegex())
    }

    fun eclValid(ecl: String): Boolean {
        val pattern = "((amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth))"
        return ecl.matches(pattern.toRegex())
    }

    fun pidValid(pid: String): Boolean {
        // this works, but need to understand it fully
        val pattern = "(?<!\\d)(\\d{9})(?!\\d)"
        return pid.matches(pattern.toRegex())
    }

    // chain all validation functions
    fun validPassport(passport: Map<String, String>): Boolean {
        return passport["byr"]?.let { byrValid(it) } ?: false &&
                passport["iyr"]?.let { iyrValid(it) } ?: false &&
                passport["eyr"]?.let { eyrValid(it) } ?: false &&
                passport["hgt"]?.let { hgtValid(it) } ?: false &&
                passport["hcl"]?.let { hclValid(it) } ?: false &&
                passport["ecl"]?.let { eclValid(it) } ?: false &&
                passport["pid"]?.let { pidValid(it) } ?: false
    }


    fun part1(input: List<String>): Int {
        var result = 0
        val passportsList = separatePassports(input)
        for (passport in passportsList) {
            val parsedFields = passport.stream()
                .map { it.split(" ") }
                .flatMap { it.stream() }
                .map { it.split(":") }
                .map { it[0] to it[1] }
                .toList().filterNotNull().toMap()

            if (part1Valid(parsedFields)) {
                result++
            }
        }
        return result
    }


    fun part2(input: List<String>): Int {
        var result = 0
        val passportsList = separatePassports(input)
        for (passport in passportsList) {
            val parsedFields = passport.stream()
                .map { it.split(" ") }
                .flatMap { it.stream() }
                .map { it.split(":") }
                .map { it[0] to it[1] }
                .toList().filterNotNull().toMap()

            if (validPassport(parsedFields)) {
                result++
            }
        }
        return result
    }


    val testInput = readInput("day04/Day04_test")
    check(part1(testInput) == 2)

    val invalidPassports = readInput("day04/InvalidPassports")
    check(part2(invalidPassports) == 0)
    val validPassports = readInput("day04/ValidPassports")
    check(part2(validPassports) == 4)

    val input = readInput("day04/Day04")
    println(part1(input))
    println(part2(input))
}