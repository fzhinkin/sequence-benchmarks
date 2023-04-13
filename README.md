# Sequence::toList and Sequence::toSet benchmarks

Benchmarks compare performance of existing implementations of `Sequence::toSet` and `Sequence::toList` extension functions with optimized version that avoids allocation of underlying collection when possible.

Optimized implementations are based on proposal from [KT-55091](https://youtrack.jetbrains.com/issue/KT-55091).

Measurements were performed using JDK-8 and JDK-11 on a host with Apple M2 Max CPU.

The only varying parameter `size` affect the size of a sequence transformed to either a list, or a set depending on a particular benchmark.

## Results
### JDK-8
[Throughput](https://jmh.morethan.io/?source=https://raw.githubusercontent.com/fzhinkin/sequence-benchmarks/main/results/jdk8-results.json)

[Allocation rate](https://jmh.morethan.io/?source=https://raw.githubusercontent.com/fzhinkin/sequence-benchmarks/main/results/jdk8-gc-results.json)

### JDK-11
[Throughput](https://jmh.morethan.io/?source=https://raw.githubusercontent.com/fzhinkin/sequence-benchmarks/main/results/jdk11-results.json)

[Allocation rate](https://jmh.morethan.io/?source=https://raw.githubusercontent.com/fzhinkin/sequence-benchmarks/main/results/jdk11-gc-results.json)

## Conclusions

Optimized implementations of `Sequence::toList` and `Sequence::toSet` should either superior, or the same performance as existing implementation. Optimized implementations also reduce allocation rate (as expected).