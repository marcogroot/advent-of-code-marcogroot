# Advent of Code

My [Advent of Code](https://adventofcode.com) solutions, one folder per year. Each year was
done in whatever language I felt like at the time, so the layout and build setup differ per
folder — see the notes below.

Puzzle inputs (`*.txt`) are deliberately not committed.

## Progress

**150 / 524 stars** · 2025 complete ✅

Advent of Code has run every December since 2015. From 2025 onwards it runs for 12 days
rather than 25, so that year is out of 24 stars instead of 50.

| Year | Language | Days | Stars | Progress |
| ---- | -------- | ---- | ----- | -------- |
| [2015](2015) | C, Rust | 6 / 25 | 12 / 50 | `█████░░░░░░░░░░░░░░░` 24% |
| 2016 | — | 0 / 25 | 0 / 50 | `░░░░░░░░░░░░░░░░░░░░` 0% |
| 2017 | — | 0 / 25 | 0 / 50 | `░░░░░░░░░░░░░░░░░░░░` 0% |
| 2018 | — | 0 / 25 | 0 / 50 | `░░░░░░░░░░░░░░░░░░░░` 0% |
| [2019](2019) | Rust | 5 / 25 | 10 / 50 | `████░░░░░░░░░░░░░░░░` 20% |
| [2020](2020) | Java | 13 / 25 | 26 / 50 | `██████████░░░░░░░░░░` 52% |
| 2021 | — | 0 / 25 | 0 / 50 | `░░░░░░░░░░░░░░░░░░░░` 0% |
| 2022 | — | 0 / 25 | 0 / 50 | `░░░░░░░░░░░░░░░░░░░░` 0% |
| [2023](2023) | Kotlin | 23 / 25 | 46 / 50 | `██████████████████░░` 92% |
| [2024](2024) | C++ | 16 / 25 | 32 / 50 | `████████████░░░░░░░░` 64% |
| [2025](2025) | Kotlin (scripts) | 12 / 12 | 24 / 24 | `████████████████████` 100% |

Years without a link have not been started — no folder for them yet.

### Stars by day

`★★` both parts · `★·` part one only · `··` not attempted · blank = no such day that year

| Year | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 |
| ---- |---|---|---|---|---|---|---|---|---|----|----|----|----|----|----|----|----|----|----|----|----|----|----|----|----|
| 2015 |★★|★★|★★|★★|★★|★★|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2016 |··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2017 |··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2018 |··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2019 |★★|★★|★★|★★|★★|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2020 |★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|··|··|··|··|··|··|··|··|··|··|··|··|
| 2021 |··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2022 |··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|··|
| 2023 |★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|··|··|
| 2024 |★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|··|··|··|··|··|··|··|··|··|
| 2025 |★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|★★|   |   |   |   |   |   |   |   |   |   |   |   |   |

## The years

Not started yet: **2016**, **2017**, **2018**, **2021**, **2022**.

### [2015](2015) — C and Rust

Days 1–2 are single C files (`1/1.c`, `1/2.c`, …). From day 3 on it is one Cargo project per
day (`day-03` … `day-06`) with each part as its own binary under `src/bin/`.

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

Days 14–25 are present as generated stubs (both parts `return -1L`) — they were never
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

The first 12-day year, and a full clear — all 24 stars. Standalone `.kts` scripts named
`<day>_<part>.kts`, run directly with the Kotlin compiler. Day 10 part 2 needs
`ojalgo-56.2.1.jar` (bundled here) on the classpath.

```bash
cd 2025 && kotlinc -script 2_1.kts
```

Two gaps in the source, both accounted for:

- **Day 1** was solved at the time but the scripts were lost before they were ever committed.
  `1_1.kts` and `1_2.kts` are placeholders, not the original solutions.
- **Day 12 part 2** has no script because it needs none — the final puzzle's second star
  unlocks automatically once every other star for the year has been collected.
