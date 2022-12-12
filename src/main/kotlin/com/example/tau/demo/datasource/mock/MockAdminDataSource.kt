package com.example.tau.demo.datasource.mock

import com.example.tau.demo.datasource.AdminDataSource
import com.example.tau.demo.model.Admin
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

@Repository
class MockAdminDataSource : AdminDataSource {
    val admins = mutableListOf<Admin>(
        Admin("deniz"),
        Admin("dilanur"),
        Admin("hatice"),
        Admin("hasan"),
        
    )

    override fun retrieveAdmins(): Collection<Admin> = admins

    override fun retrieveAdmin(admin_no: String): Admin = admins.firstOrNull() {it.admin_no == admin_no}
        ?:  throw NoSuchElementException("Could not find a Admin with admin no $admin_no")

    override fun createAdmin(admin: Admin): Admin {
        if(admins.any {it.admin_no == admin.admin_no}) {
            throw IllegalArgumentException("Admin with admin no ${admin.admin_no} already exists")
        }
        admins.add(admin)
        return admin
    }

    override fun deleteAdmin(admin_no:String) {
        val currentAdmin = admins.firstOrNull() { it.admin_no == admin_no}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Admin with admin no ${admin_no}")

        admins.remove(currentAdmin)
    }

    override fun updateAdmin(admin: Admin): Admin {
        val currentAdmin = admins.firstOrNull() { it.admin_no == admin.admin_no}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Admin with admin no ${admin.admin_no}")

        admins.remove(currentAdmin)
        admins.add(admin)
        return admin
    }

}