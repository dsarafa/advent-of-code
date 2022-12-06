package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec02: PuzzleDayTester(2, 2022) {

    override fun part1(): Any = rockPaperScissors()
            .map { it.map { play -> StratGuide.valueOf(play).displayName } }
            .sumOf { matchup ->
        Results.valueOf(matchup.joinToString().replace(", ", "")).score
    }

    override fun part2(): Any = rockPaperScissors()
            .map { it.map { play -> CorrectStratGuide.valueOf(play).displayName } }
            .sumOf { matchup ->
                getResultScore(matchup[0], matchup[1])
            }

    private fun getResultScore(player1: String, player2Need: String) =
            Results.values().first { it.opponentPlay == player1 && it.result == player2Need }.score

    private fun rockPaperScissors() = load(delimiter = "\n")
            .map { matchupList -> matchupList.split(" ") }

}

enum class StratGuide(val displayName: String) {
    A("ROCK"),
    B("PAPER"),
    C("SCISSORS"),
    X("ROCK"),
    Y("PAPER"),
    Z("SCISSORS")
}

enum class CorrectStratGuide(val displayName: String) {
    A("ROCK"),
    B("PAPER"),
    C("SCISSORS"),
    X("LOSE"),
    Y("DRAW"),
    Z("WIN")
}

enum class Results(val opponentPlay: String, val score: Int, val result: String) {
    ROCKROCK("ROCK", 4, "DRAW"),
    PAPERPAPER("PAPER", 5, "DRAW"),
    SCISSORSSCISSORS( "SCISSORS", 6, "DRAW"),
    ROCKPAPER("ROCK",8, "WIN"),
    PAPERSCISSORS( "PAPER",9, "WIN"),
    SCISSORSROCK( "SCISSORS",7, "WIN"),
    ROCKSCISSORS( "ROCK",3, "LOSE"),
    PAPERROCK( "PAPER",1, "LOSE"),
    SCISSORSPAPER("SCISSORS",2, "LOSE");

}