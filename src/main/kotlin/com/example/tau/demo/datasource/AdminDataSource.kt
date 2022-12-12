package com.example.tau.demo.datasource

import com.example.tau.demo.model.Admin

interface AdminDataSource {
    fun retrieveAdmins() : Collection<Admin>
    fun retrieveAdmin(no:String): Admin
    fun createAdmin(admin: Admin): Admin
    fun deleteAdmin(no:String)
    fun updateAdmin(admin: Admin): Admin
}