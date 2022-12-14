package com.example.tau.demo.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.Null
import java.util.*

class MockBusDataSourceTest {
    private val mockBusDataSource = MockBusDataSource()

    @Test
    fun `should provide a collection of buses`() {

        // when
        val buses = mockBusDataSource.retrieveBuses()


        // then
        assertThat(buses.size).isGreaterThanOrEqualTo(2)

    }

    @Test
    fun `should provide some mock data`() {

        // when
        val buses = mockBusDataSource.retrieveBuses()
        // then
        assertThat(buses).allMatch {it.departure_time != null }
        assertThat(buses).allMatch {it.departure_point != null}

    }
}