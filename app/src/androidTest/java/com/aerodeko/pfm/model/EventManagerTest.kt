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
}
