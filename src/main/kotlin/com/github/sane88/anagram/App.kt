package com.github.sane88.anagram


class AnagramChecker(private val first: String, private val second: String) {

    companion object {
        val CHARS_TO_SKIP = arrayOf(' ', ';', '.', ',', '-')
    }

    fun check(): Boolean {
        if (first.length == second.length) {
            if (first == second) return true
        }

        val firstCleared = first.asIterable().filterNot { CHARS_TO_SKIP.contains(it) }
        val secondCleared = second.asIterable().filterNot { CHARS_TO_SKIP.contains(it) }

        if (firstCleared.size != secondCleared.size) return false

        return firstCleared.map { it.lowercase() }.sorted() == secondCleared.map { it.lowercase() }.sorted()
    }
}

private const val FIRST_STRING_ARG_KEY = "first="
private const val SECOND_STRING_ARG_KEY = "second="

fun main(args: Array<String>) {
    val firstString: String
    val secondString: String
    val argsString = args.joinToString(separator = " ")
    val firstIndex = argsString.indexOf(FIRST_STRING_ARG_KEY)
    val secondIndex = argsString.indexOf(SECOND_STRING_ARG_KEY)
    if (firstIndex != -1 && secondIndex != -1) {
        if (firstIndex < secondIndex) {
            firstString = readFromArgs(argsString, firstIndex + FIRST_STRING_ARG_KEY.length, secondIndex - 1)
            secondString = readFromArgs(argsString, secondIndex + SECOND_STRING_ARG_KEY.length, argsString.length)
        } else {
            firstString = readFromArgs(argsString, firstIndex + FIRST_STRING_ARG_KEY.length, argsString.length)
            secondString = readFromArgs(argsString, secondIndex + SECOND_STRING_ARG_KEY.length, firstIndex - 1)
        }
        println("The phrases are ${if (AnagramChecker(firstString,secondString).check()) "anagrams" else "not anagrams"}")
    } else {
        println("No phrases provided. Pass them as program arguments with $FIRST_STRING_ARG_KEY and $SECOND_STRING_ARG_KEY keys")
    }
}

private fun readFromArgs(argsString: String, beginIndex: Int, endIndex: Int) =
    argsString.substring(beginIndex until endIndex)