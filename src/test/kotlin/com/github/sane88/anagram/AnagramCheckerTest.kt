package com.github.sane88.anagram

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

internal class AnagramCheckerTest {

    @ParameterizedTest(name = " should return {2} for {3}: ['{0}', '{1}']")
    @MethodSource("anagramCheckParams")
    fun `anagram check`(first: String, second: String, expected: Boolean, case: String) {
        assertEquals(AnagramChecker(first, second).check(), expected, case)
    }

    companion object {
        @JvmStatic
        fun anagramCheckParams() = listOf(
            Arguments.of("restful", "fluster", true, "single word anagram"),
            Arguments.of("forty five", "over fifty", true, "a phrase anagram"),
            Arguments.of("New York Times", "monkeys write", true, "anagram with case difference"),
            Arguments.of("She Sells Sanctuary", "Santa; shy, less cruel", true, "anagram with splitting chars"),
            Arguments.of("car", "bye", false, "not anagram with the same word length"),
            Arguments.of("good", "google", false, "for not anagram")
        )
    }
}