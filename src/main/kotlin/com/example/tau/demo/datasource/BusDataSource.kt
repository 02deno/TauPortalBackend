package com.example.tau.demo.datasource

import com.example.tau.demo.model.Bus

interface BusDataSource {
    fun retrieveBuses() : Collection<Bus>
    fun retrieveBus(id : Int): Bus
    fun createBus(Bus: Bus): Bus
    fun deleteBus(id : Int)
    fun updateBus(Bus: Bus): Bus
}