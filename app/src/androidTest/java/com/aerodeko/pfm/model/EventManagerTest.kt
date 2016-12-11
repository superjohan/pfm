package com.aerodeko.pfm.model

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by rm on 11/12/2016.
 */
@RunWith(AndroidJUnit4::class)
class EventManagerTest {
    private lateinit var eventManager: EventManager

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()

        eventManager = EventManager(context, TimeZone.getTimeZone("GMT"))
        eventManager.clearEvents()
    }

    @Test
    fun test_adding_event_creates_valid_event() {
        val expectedValue = 1.0
        val date = Date(1234567890)

        val event = eventManager.addEvent(expectedValue, date)
        val events = eventManager.getEvents(date)

        assertEquals("only one event should be received", 1, events.size)

        val eventFromDatabase = events[0]

        assertEquals("events should be equal", event, eventFromDatabase)
    }

    @Test
    fun test_getting_event_for_a_certain_date_only_gets_events_for_that_date() {
        val date = Calendar.getInstance()
        val otherDate = date.clone() as Calendar
        otherDate.add(Calendar.DAY_OF_MONTH, 1)

        val event1_date1 = eventManager.addEvent(1.0, date.time)
        val event1_date2 = eventManager.addEvent(3.0, date.time)
        val event2 = eventManager.addEvent(2.0, otherDate.time)
        val events1 = eventManager.getEvents(date.time)
        val events2 = eventManager.getEvents(otherDate.time)

        assertEquals("only two events for date", 2, events1.size)
        assertEquals("only one event for date", 1, events2.size)
        assertEquals("events for the date should be equal", event1_date1, events1[0])
        assertEquals("events for the date should be equal", event1_date2, events1[1])
        assertEquals("events for the date should be equal", event2, events2[0])
    }
}
