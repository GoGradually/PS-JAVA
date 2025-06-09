package june_25.BOJ13460

import kotlin.math.max
import kotlin.math.min

fun main() {
    val split = readLine()!!.split(" ")
    val n = split[0].toInt()
    val m = split[1].toInt()

    val arr = Array(n) { IntArray(m) { 0 } }

    for (i in 0 until n) {
        val line = readLine()!!
        for (j in 0 until m) {
            if (line[j] == '#') arr[i][j] = 1
            if (line[j] == 'O') arr[i][j] = 2
            if (line[j] == 'R') arr[i][j] = 3
            if (line[j] == 'B') arr[i][j] = 4
        }
    }
    val beads = Beads(arr)
    val result = play(1, beads)
    if (result > 10) println(-1)
    else println(result)
}

fun play(now: Int, beads: Beads): Int {
    if (now > 10) return now
    var copy = beads.arr.map { innerArray -> innerArray.copyOf() }.toTypedArray()

    var min = 20
    try {
        val down = beads.down()
        min = if (down) min(min, now) else min(min, play(now + 1, beads))
    } catch (e: RuntimeException) {

    } finally {
        beads.arr = copy
        copy = beads.arr.map { innerArray -> innerArray.copyOf() }.toTypedArray()
    }

    try {
        val up = beads.up()
        min = if (up) min(min, now) else min(min, play(now + 1, beads))
    } catch (e: RuntimeException) {

    } finally {
        beads.arr = copy
        copy = beads.arr.map { innerArray -> innerArray.copyOf() }.toTypedArray()
    }

    try {
        val left = beads.left()
        min = if (left) min(min, now) else min(min, play(now + 1, beads))

    } catch (e: RuntimeException) {

    } finally {
        beads.arr = copy
        copy = beads.arr.map { innerArray -> innerArray.copyOf() }.toTypedArray()
    }

    try {
        val right = beads.right()
        min = if (right) min(min, now) else min(min, play(now + 1, beads))
    } catch (e: RuntimeException) {

    } finally {
        beads.arr = copy
        copy = beads.arr.map { innerArray -> innerArray.copyOf() }.toTypedArray()
    }
    return min
}

class Beads(var arr: Array<IntArray>) {

    fun down(): Boolean {
        var blue = false
        var red = false

        fun move() {
            for (j in arr[0].indices) {
                for (i in 1..arr.size - 1) {
                    if (arr[i - 1][j] < 3) continue
                    if (arr[i][j] == 1 || arr[i][j] == 3 || arr[i][j] == 4) {
                        continue
                    } else if (arr[i - 1][j] == 3 && arr[i][j] == 2) {
                        arr[i - 1][j] = 0
                        red = true
                    } else if (arr[i - 1][j] == 4 && arr[i][j] == 2) {
                        arr[i - 1][j] = 0
                        blue = true
                    } else if ((arr[i - 1][j] == 3 || arr[i - 1][j] == 4) && arr[i][j] == 0) {
                        arr[i][j] = arr[i - 1][j]
                        arr[i - 1][j] = 0
                    }
                }
            }
        }

        move()
        move()
        if (blue) {
            throw RuntimeException()
        } else if (red) {
            return true
        }
        return false
    }

    fun up(): Boolean {
        var blue = false
        var red = false

        fun move() {
            for (j in arr[0].indices) {
                for (i in arr.size - 2 downTo 0) {
                    if (arr[i + 1][j] < 3) continue
                    if (arr[i][j] == 1 || arr[i][j] == 3 || arr[i][j] == 4) {
                        continue
                    } else if (arr[i + 1][j] == 3 && arr[i][j] == 2) {
                        arr[i + 1][j] = 0
                        red = true
                    } else if (arr[i + 1][j] == 4 && arr[i][j] == 2) {
                        arr[i + 1][j] = 0
                        blue = true
                    } else if ((arr[i + 1][j] == 3 || arr[i + 1][j] == 4) && arr[i][j] == 0) {
                        arr[i][j] = arr[i + 1][j]
                        arr[i + 1][j] = 0
                    }
                }
            }
        }

        move()
        move()
        if (blue) {
            throw RuntimeException()
        } else if (red) {
            return true
        }
        return false
    }

    fun left(): Boolean {
        var blue = false
        var red = false

        fun move() {
            for (i in arr.indices) {
                for (j in arr[0].size - 2 downTo 0) {
                    if (arr[i][j + 1] < 3) continue
                    if (arr[i][j] == 1 || arr[i][j] == 3 || arr[i][j] == 4) {
                        continue
                    } else if (arr[i][j + 1] == 3 && arr[i][j] == 2) {
                        arr[i][j + 1] = 0
                        red = true
                    } else if (arr[i][j + 1] == 4 && arr[i][j] == 2) {
                        arr[i][j + 1] = 0
                        blue = true
                    } else if ((arr[i][j + 1] == 3 || arr[i][j + 1] == 4) && arr[i][j] == 0) {
                        arr[i][j] = arr[i][j + 1]
                        arr[i][j + 1] = 0
                    }
                }
            }
        }

        move()
        move()
        if (blue) {
            throw RuntimeException()
        } else if (red) {
            return true
        }
        return false
    }

    fun right(): Boolean {

        var blue = false
        var red = false

        fun move() {
            for (i in arr.indices) {
                for (j in 1..arr[0].size - 1) {
                    if (arr[i][j - 1] < 3) continue
                    if (arr[i][j] == 1 || arr[i][j] == 3 || arr[i][j] == 4) {
                        continue
                    } else if (arr[i][j - 1] == 3 && arr[i][j] == 2) {
                        arr[i][j - 1] = 0
                        red = true
                    } else if (arr[i][j - 1] == 4 && arr[i][j] == 2) {
                        arr[i][j - 1] = 0
                        blue = true
                    } else if ((arr[i][j - 1] == 3 || arr[i][j - 1] == 4) && arr[i][j] == 0) {
                        arr[i][j] = arr[i][j - 1]
                        arr[i][j - 1] = 0
                    }
                }
            }
        }

        move()
        move()
        if (blue) {
            throw RuntimeException()
        } else if (red) {
            return true
        }
        return false
    }


    fun get(i: Int, j: Int): Int = arr[i][j]
}

/**
 * 구슬 빼내기
 * 최소 몇번만에?
 * 10번 이상 걸리면 -1
 * 4^10 -> 2^20 -> 100만
 * 현재 상태를 BFS로 저장 후?
 * 근데 그러면 현재 상태가 너무 커지지 않나
 * 그냥 DFS로 전부 탐색 후 최솟값 확인?
 * 한칸씩 이동하면서, 공이 구멍에 빠지는지 확인도 해야 함
 * 구슬이 두개가 동시에 있을 경우, 둘 다 구멍에 빠지면 실패
 * 빨간 구슬이 먼저 빠지고 파란 구슬이 빠져도 실패 -> 20번 순회함
 */