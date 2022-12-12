package com.example.tau.demo.datasource.mock

import com.example.tau.demo.datasource.BusDataSource
import com.example.tau.demo.model.Bus
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

@Repository
class MockBusDataSource : BusDataSource {
    val buses = mutableListOf<Bus>(
        Bus(1, LocalDateTime.of(2022, 12, 12, 12, 0),"Kavacık"),
        Bus(2, LocalDateTime.of(2022, 12, 12, 12, 30),"Kavacık"),
        Bus(3, LocalDateTime.of(2022, 12, 12, 13, 0),"Kavacık"),

        Bus(4, LocalDateTime.of(2022, 12, 12, 16, 10),"Türk Alman Üniversitesi"),
        Bus(5, LocalDateTime.of(2022, 12, 12, 16, 30),"Türk Alman Üniversitesi"),
        Bus(6, LocalDateTime.of(2022, 12, 12, 18, 45),"Türk Alman Üniversitesi")
    )

    override fun retrieveBuses(): Collection<Bus> = buses

    override fun retrieveBus(id: Int): Bus = buses.firstOrNull() {it.ID == id}
        ?:  throw NoSuchElementException("Could not find a Bus with id $id")

    override fun createBus(Bus: Bus): Bus {
        if(buses.any {it.ID == Bus.ID}) {
            throw IllegalArgumentException("Bus with id ${Bus.ID} already exists")
        }
        buses.add(Bus)
        return Bus
    }

    override fun deleteBus(id: Int) {
        val currentBus = buses.firstOrNull() { it.ID == id}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Bus with id ${id}")

        buses.remove(currentBus)
    }

    override fun updateBus(Bus: Bus): Bus {
        val currentBus = buses.firstOrNull() { it.ID == Bus.ID}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Bus with id ${Bus.ID}")

        buses.remove(currentBus)
        buses.add(Bus)
        return Bus
    }

}