package com.example.tau.demo.datasource.mock

import com.example.tau.demo.datasource.AnnouncementDataSource
import com.example.tau.demo.model.Announcement
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.NoSuchElementException

@Repository
class MockAnnouncementDataSource : AnnouncementDataSource {
    val announcements = mutableListOf<Announcement>(
        Announcement(1,"Yoğun kar yağışından dolayı dersler iptal olmuştur."),
        Announcement(2,"Finallere son 4 gün..."),
        Announcement(3,"Üniversitemiz ödül almıştır")

    )

    override fun retrieveAnnouncements(): Collection<Announcement> = announcements

    override fun retrieveAnnouncement(text: String): Announcement = announcements.firstOrNull() {it.text == text}
        ?:  throw NoSuchElementException("Could not find a Announcement with text $text")

    override fun createAnnouncement(announcement: Announcement): Announcement {
        if(announcements.any {it.text == announcement.text}) {
            throw IllegalArgumentException("Announcement with text ${announcement.text} already exists")
        }
        announcements.add(announcement)
        return announcement
    }

    override fun deleteAnnouncement(text: String) {
        val currentAnnouncement = announcements.firstOrNull() { it.text == text}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Announcement with text ${text}")

        announcements.remove(currentAnnouncement)
    }

    override fun updateAnnouncement(Announcement: Announcement): Announcement {
        val currentAnnouncement = announcements.firstOrNull() { it.text == Announcement.text}
        // if not found * not such element exception
            ?: throw NoSuchElementException("Could not find a Announcement with text ${Announcement.text}")

        announcements.remove(currentAnnouncement)
        announcements.add(Announcement)
        return Announcement
    }

}