package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec03 : PuzzleDayTester(3, 2022) {

    val rucksacks = rucksacks()
    var priority = 0

    override fun part1(): Any = rucksacks().sumOf {
        it.searchRucksackCompartmentsForMatch().prioritizeItem()
    }

    override fun part2(): Any = rucksacks.forEachIndexed { index, _ ->
        if (index % 3 == 0) {
            priority += rucksacks.formTriple(index).searchManyRucksacksForMatch().prioritizeItem()
        }
    }.let {
        priority
    }

    private fun List<Pair<String, String>>.formTriple(index: Int): Triple<String, String, String> =
            Triple(this[index].first.plus(this[index].second),
                    this[index + 1].first.plus(this[index + 1].second),
                    this[index + 2].first.plus(this[index + 2].second))

    private fun Triple<String, String, String>.searchManyRucksacksForMatch(): Char =
            this.first.toCharArray().intersect(this.second.toSet()).intersect(this.third.toSet()).first()

    private fun Pair<String, String>.searchRucksackCompartmentsForMatch(): Char =
            this.first.toCharArray().intersect(this.second.toSet()).single()

    private fun Char.prioritizeItem(): Int =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(this) + 1

    private fun rucksacks() = load(delimiter = "\n")
            .map { rucksack -> Pair(rucksack.drop(rucksack.length / 2), rucksack.dropLast(rucksack.length / 2)) }
}

// GbccTtTSGGbgrcWBGGrdgT
// nVQnCmNpCJlNnNPVfClcnN