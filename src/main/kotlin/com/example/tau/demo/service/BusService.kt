package com.example.tau.demo.service

import com.example.tau.demo.datasource.BusDataSource
import com.example.tau.demo.model.Bus
import org.springframework.stereotype.Service

@Service
class BusService(private val dataSource : BusDataSource) {
    fun getBuses(): Collection<Bus> = dataSource.retrieveBuses()
    fun getBus(id:Int): Bus = dataSource.retrieveBus(id)
    fun addBus(Bus: Bus): Bus = dataSource.createBus(Bus)
    fun deleteBus(id:Int) = dataSource.deleteBus(id)
    fun updateBus(Bus: Bus): Bus = dataSource.updateBus(Bus)
}