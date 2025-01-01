package kk.kkhouse777.okogeprogressbar.okoge

import org.junit.jupiter.api.Test

class OkogeIconStreamTest {
    @Test
    fun testSameInstance() {
        val instance1 = OkogeIconStream.instance
        val instance2 = OkogeIconStream.instance
        assert(instance1 === instance2)
    }

    // TODO mock IconLoader
}
