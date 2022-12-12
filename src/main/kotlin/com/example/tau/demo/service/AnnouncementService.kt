package com.example.tau.demo.service


import com.example.tau.demo.datasource.AnnouncementDataSource
import com.example.tau.demo.model.Announcement
import org.springframework.stereotype.Service

@Service
class AnnouncementService(private val dataSource : AnnouncementDataSource) {
    fun getAnnouncements(): Collection<Announcement> = dataSource.retrieveAnnouncements()
    fun getAnnouncement(text: String): Announcement = dataSource.retrieveAnnouncement(text)
    fun addAnnouncement(Announcement: Announcement): Announcement = dataSource.createAnnouncement(Announcement)
    fun deleteAnnouncement(text: String) = dataSource.deleteAnnouncement(text)
    fun updateAnnouncement(Announcement: Announcement): Announcement = dataSource.updateAnnouncement(Announcement)
}