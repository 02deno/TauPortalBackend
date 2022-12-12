package com.example.tau.demo.datasource

import com.example.tau.demo.model.Announcement

interface AnnouncementDataSource {
    fun retrieveAnnouncements() : Collection<Announcement>
    fun retrieveAnnouncement(text: String): Announcement
    fun createAnnouncement(announcement: Announcement): Announcement
    fun deleteAnnouncement(text: String)
    fun updateAnnouncement(announcement: Announcement): Announcement
}