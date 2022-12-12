package com.example.tau.demo.service

import com.example.tau.demo.datasource.AdminDataSource
import com.example.tau.demo.model.Admin
import org.springframework.stereotype.Service

@Service
class AdminService(private val dataSource : AdminDataSource) {
    fun getAdmins(): Collection<Admin> = dataSource.retrieveAdmins()
    fun getAdmin(no:String): Admin = dataSource.retrieveAdmin(no)
    fun addAdmin(admin: Admin): Admin = dataSource.createAdmin(admin)
    fun deleteAdmin(no:String) = dataSource.deleteAdmin(no)
    fun updateAdmin(admin: Admin): Admin = dataSource.updateAdmin(admin)
}