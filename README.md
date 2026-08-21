# Advent of Code

My [Advent of Code](https://adventofcode.com) solutions, one folder per year. Each year was
done in whatever language I felt like at the time, so the layout and build setup differ per
folder — see the notes below.

Puzzle inputs (`*.txt`) are deliberately not committed.

## Progress

**129 / 224 stars**

From 2025 onwards Advent of Code runs for 12 days rather than 25, so that year is out of 24
stars instead of 50.

| Year | Language | Days | Stars | Progress |
| ---- | -------- | ---- | ----- | -------- |
| [2019](2019) | Rust | 5 / 25 | 10 / 50 | `████░░░░░░░░░░░░░░░░` 20% |
| [2020](2020) | Java | 10 / 25 | 20 / 50 | `████████░░░░░░░░░░░░` 40% |
| [2023](2023) | Kotlin | 23 / 25 | 46 / 50 | `██████████████████░░` 92% |
| [2024](2024) | C++ | 16 / 25 | 32 / 50 | `████████████░░░░░░░░` 64% |
| [2025](2025) | Kotlin (scripts) | 10.5 / 12 | 21 / 24 | `█████████████████░░░` 88% |

### Stars by day

`★★` both parts · `★·` part one only · `··` not attempted · blank = no such day that year

| Year | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 |
| ---- |---|---|---|---|---|---|---|---|---|----|----|----|----|----|----|----|----|----|----|----|----|----|----|----|----|
| 2019 |★★|★★|★★|★★|★★|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2020 |★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2023 |★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|··|··|
| 2024 |★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|··|··|··|··|··|··|··|··|··|
| 2025 |··|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★·|   |   |   |   |   |   |   |   |   |   |   |   |   |

## The years

### [2019](2019) — Rust

One Cargo project per day (`day-01` … `day-05`), with each part as its own binary under
`src/bin/`.

```bash
cd 2019/day-01 && cargo run --bin part1
```

### [2020](2020) — Java

Plain single-file `DayNN.java` classes, each with `part1`, `part2` and a `main` that reads
`dayNN.txt` from the working directory.

```bash
cd 2020 && java Day01.java
```

Days 11–25 are present as generated stubs (both parts `return -1L`) — they were never
solved, and are not counted in the totals above.

### [2023](2023) — Kotlin

Built from the [Advent of Code Kotlin Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template).
Solutions live in `src/DayNN.kt` with shared helpers in `src/Utils.kt`.

```bash
cd 2023 && ./gradlew run
```

### [2024](2024) — C++

One file per part, named `<day>_<part>.cpp`, with shared helpers in `util.cpp` / `util.h`.

```bash
cd 2024 && g++ -std=c++20 1_1.cpp util.cpp -o day1_1 && ./day1_1
```

### [2025](2025) — Kotlin scripts

The first 12-day year. Standalone `.kts` scripts named `<day>_<part>.kts`, run directly with
the Kotlin compiler. Day 10 part 2 needs `ojalgo-56.2.1.jar` (bundled here) on the classpath.

```bash
cd 2025 && kotlinc -script 2_1.kts
```

Day 1 was never committed to the original repository, so it is missing here too.
