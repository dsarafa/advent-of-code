package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec01: PuzzleDayTester(1, 2022) {

    override fun part1(): Any =
            calorieCounter().sumEach().max()

    override fun part2(): Any =
            calorieCounter().sumEach().sortedDescending().take(3).sum()

    private fun calorieCounter() = load(delimiter = "\n\n")
            .map { elfList -> elfList.split("\n")
                    .map { elfFood -> elfFood.toInt() } }

    fun List<List<Int>>.sumEach(): List<Int> = this.map { it.sum() }

}