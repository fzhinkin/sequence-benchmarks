package org.example;

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

fun <T> Sequence<T>.toListOpt() : List<T> {
    val it = iterator()
    if (!it.hasNext()) {
        return emptyList()
    }
    val element = it.next()
    if (!it.hasNext()) {
        return listOf(element)
    }
    val list = ArrayList<T>()
    list.add(element)
    while (it.hasNext()) list.add(it.next())
    return list
}

fun <T> Sequence<T>.toSetOpt() : Set<T> {
    val it = iterator()
    if (!it.hasNext()) {
        return emptySet()
    }
    val element = it.next()
    if (!it.hasNext()) {
        return setOf(element)
    }
    val set = LinkedHashSet<T>()
    set.add(element)
    while (it.hasNext()) set.add(it.next())
    return set
}

@State(Scope.Benchmark)
open class SequenceToCollectionBenchmark {
    @Param("0", "1", "2", "10")
    var size: Int = 0

    private var seq: Sequence<Int> = emptySequence()

    @Setup
    fun setupSequence() {
        seq = (0 until size).asSequence()
    }

    @Benchmark
    fun toListBaseline() = seq.toList()

    @Benchmark
    fun toListOpt() = seq.toListOpt()

    @Benchmark
    fun toSetBaseline() = seq.toSet()

    @Benchmark
    fun toSetOpt() = seq.toSetOpt()
}