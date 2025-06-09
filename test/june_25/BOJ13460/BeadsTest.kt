package june_25.BOJ13460

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BeadsTest {
    @Test
    fun `down - 구슬 1개 각자`() {
        val array = Array(10) { IntArray(3) { 1 } }
        for (i in 1..8) array[i][1] = 0
        array[5][1] = 1
        array[6][1] = 3
        array[1][1] = 4
        val beads = Beads(array)

        val result = beads.down()

        assertEquals(false, result)

        assertEquals(1, beads.get(5, 1))
        assertEquals(4, beads.get(4, 1))
        assertEquals(0, beads.get(3, 1))
        assertEquals(0, beads.get(2, 1))
        assertEquals(0, beads.get(1, 1))

        assertEquals(1, beads.get(9, 1))
        assertEquals(3, beads.get(8, 1))
        assertEquals(0, beads.get(7, 1))
        assertEquals(0, beads.get(6, 1))
    }

    @Test
    fun `down - 파란구슬 구멍 IN`() {
        val array = Array(10) { IntArray(3) { 1 } }
        for (i in 1..8) array[i][1] = 0
        array[5][1] = 2
        array[6][1] = 3
        array[1][1] = 4
        val beads = Beads(array)

        val result = beads.down()

        assertEquals(false, result)

        assertEquals(2, beads.get(5, 1))
        assertEquals(0, beads.get(4, 1))
        assertEquals(0, beads.get(3, 1))
        assertEquals(0, beads.get(2, 1))
        assertEquals(0, beads.get(1, 1))

        assertEquals(1, beads.get(9, 1))
        assertEquals(3, beads.get(8, 1))
        assertEquals(0, beads.get(7, 1))
        assertEquals(0, beads.get(6, 1))
    }
    @Test
    fun `down - 빨간구슬 구멍 IN`() {
        val array = Array(10) { IntArray(3) { 1 } }
        for (i in 1..8) array[i][1] = 0
        array[5][1] = 2
        array[6][1] = 4
        array[1][1] = 3
        val beads = Beads(array)

        val result = beads.down()

        assertEquals(true, result)

        assertEquals(2, beads.get(5, 1))
        assertEquals(0, beads.get(4, 1))
        assertEquals(0, beads.get(3, 1))
        assertEquals(0, beads.get(2, 1))
        assertEquals(0, beads.get(1, 1))

        assertEquals(1, beads.get(9, 1))
        assertEquals(4, beads.get(8, 1))
        assertEquals(0, beads.get(7, 1))
        assertEquals(0, beads.get(6, 1))
    }

}

/**
 * 구슬 한개씩 각자 있을 때 테스트
 * 구슬 2개 한 라인에 있을 때 테스트
 * 구슬 2개 분리된 한 라인에 있을 때 테스트
 */