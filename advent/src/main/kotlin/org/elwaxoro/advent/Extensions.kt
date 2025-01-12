package org.elwaxoro.advent

/**
 * Nullable Long plusser, null defaults to 0
 */
fun Long.plusNull(that: Long?): Long = (that ?: 0L) + this

/**
 * Split a string into a list of integers
 * Ex: "01234" becomes [0, 1, 2, 3, 4]
 */
fun String.splitToInt(): List<Int> = map(Character::getNumericValue)

/**
 * Replace matching values
 */
fun List<Int>.replace(oldInt: Int, newInt: Int): List<Int> = map { it.takeUnless { it == oldInt } ?: newInt }

/**
 * Based on https://stackoverflow.com/questions/9562605/in-kotlin-can-i-create-a-range-that-counts-backwards
 * Kotlin ranges don't support positive or negative directions at the same time
 */
fun Int.toward(to: Int): IntProgression = IntProgression.fromClosedRange(this, to, 1.takeIf { this <= to } ?: -1)

/**
 * Pads a progression of Int to the desired size, using the final Int as the pad value
 */
fun IntProgression.padTo(newSize: Int): List<Int> = toList().padTo(newSize)

/**
 * Pads a list of anything to the desired size, using the final object as the pad object
 * ex
 * listOf(1,2,3).padTo(10)
 * becomes
 * [1, 2, 3, 3, 3, 3, 3, 3, 3, 3]
 */
fun <T> List<T>.padTo(newSize: Int): List<T> = takeIf { size >= newSize } ?: plus(List(newSize - size) { last() })

/**
 * Get the median from a list of Int
 */
fun List<Int>.median(): Double = sorted().let {
    if (size % 2 == 0) {
        (it[size / 2] + it[size / 2 - 1]) / 2.0
    } else {
        it[size / 2].toDouble()
    }
}

/**
 * Merge a list of maps by adding up values for matching keys
 * Merge values of matching keys using the provided merge function
 */
fun <T, U> List<Map<T, U>>.merge(merger: (value: U, existing: U?) -> U): Map<T, U> = fold(mutableMapOf()) { acc, map ->
    acc.also {
        map.entries.map { (key, value) ->
            acc[key] = merger(value, acc[key])
        }
    }
}
