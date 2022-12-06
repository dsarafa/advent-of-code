package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec04 : PuzzleDayTester(4, 2022) {

    override fun part1(): Any = cleaningPairs().sumOf {
        it.compareFullyContainedRanges().toInt()
    }

    override fun part2(): Any = cleaningPairs().sumOf {
        it.compareAnyMatchInRange().toInt()
    }

    private fun Boolean.toInt() = if (this) 1 else 0

    private fun List<IntRange>.compareFullyContainedRanges(): Boolean =
        this[0].all { it in this[1] } ||
                this[1].all { it in this[0] }

    private fun List<IntRange>.compareAnyMatchInRange(): Boolean =
            this[0].any { it in this[1] }

    private fun cleaningPairs() =
            load(delimiter = "\n").map { pairs ->
                pairs.split(",").map { pair ->
                    pair.split("-").let { it[0].toInt()..it[1].toInt() }
                }
            }
}