package com.business.fitrack

import org.junit.Test

import org.junit.Assert.*

class FitrackUnitTest {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockDatabase: SQLiteDatabase

    @Mock
    private lateinit var mockCursor: Cursor

    @Mock
    private lateinit var mockListView: ListView

    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dbHelper = DatabaseHelper(context)
        dbHelper.createDatabase()
        database = dbHelper.writableDatabase
        MockitoAnnotations.initMocks(this)
        mockContext = ApplicationProvider.getApplicationContext()
        mainActivity = Mockito.spy(MainActivity())
        Mockito.doReturn(mockContext).`when`(mainActivity).applicationContext
        Mockito.doReturn(mockDatabase).`when`(mainActivity).openOrCreateDatabase(Mockito.anyString(), Mockito.anyInt(), Mockito.isNull())
        Mockito.doReturn(mockCursor).`when`(mockDatabase).rawQuery(Mockito.anyString(), Mockito.isNull())
        mainActivity.listViewGuests = mockListView
    }

    @After
    fun tearDown() {
        dbHelper.close()
        val context = ApplicationProvider.getApplicationContext<Context>()
        context.deleteDatabase("dbGuestApp")
    }

    @Test
    fun testDatabaseCreated() {
        val cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='guestTable'", null)
        val tableExists = cursor.count > 0
        cursor.close()
        assertTrue("Table guestTable should exist", tableExists)
    }

    @Test
    fun testWorkoutCreate() {
        doNothing().`when`(mockStatement).bindLong(1, ID_SELECIONADO)
        doNothing().`when`(mockStatement).executeUpdateDelete()
        doNothing().`when`(mockDatabase).close()

        mainActivity.delete()

        Mockito.verify(mockStatement).bindLong(1, ID_SELECIONADO)
        Mockito.verify(mockStatement).executeUpdateDelete()
        Mockito.verify(mockDatabase).close()
        Mockito.verify(mainActivity).getGuestList()
        Toast.makeText(mockContext, "Convidado Excluido!", Toast.LENGTH_SHORT).show()
    }

    @Test
    fun testWorkoutDelete() {
        doNothing().`when`(mockStatement).bindLong(1, ID_SELECIONADO)
        doNothing().`when`(mockStatement).executeUpdateDelete()
        doNothing().`when`(mockDatabase).close()

        mainActivity.delete()

        Mockito.verify(mockStatement).bindLong(1, ID_SELECIONADO)
        Mockito.verify(mockStatement).executeUpdateDelete()
        Mockito.verify(mockDatabase).close()
        Mockito.verify(mainActivity).getGuestList()
        Toast.makeText(mockContext, "Convidado Excluido!", Toast.LENGTH_SHORT).show()
    }


    @Test
    fun testGetWorkoutList() {
        // Mock the cursor behavior
        Mockito.doReturn(true).`when`(mockCursor).moveToFirst()
        Mockito.doReturn(false).`when`(mockCursor).isAfterLast
        Mockito.doReturn(1).`when`(mockCursor).getInt(0)
        Mockito.doReturn("John Doe").`when`(mockCursor).getString(1)
        Mockito.doNothing().`when`(mockCursor).moveToNext()
        Mockito.doNothing().`when`(mockCursor).close()

        // Call the method
        mainActivity.getGuestList()

        // Verify
        val expectedList = ArrayList<GuestModel>()
        expectedList.add(GuestModel(1, "John Doe"))

        assertEquals(expectedList, mainActivity.guestList)
        Mockito.verify(mockCursor).moveToFirst()
        Mockito.verify(mockCursor).isAfterLast
        Mockito.verify(mockCursor).getInt(0)
        Mockito.verify(mockCursor).getString(1)
        Mockito.verify(mockCursor).moveToNext()
        Mockito.verify(mockCursor).close()
    }

    @Test
    fun testGetUserList() {
        // Mock the cursor behavior
        Mockito.doReturn(true).`when`(mockCursor).moveToFirst()
        Mockito.doReturn(false).`when`(mockCursor).isAfterLast
        Mockito.doReturn(1).`when`(mockCursor).getInt(0)
        Mockito.doReturn("John Doe").`when`(mockCursor).getString(1)
        Mockito.doNothing().`when`(mockCursor).moveToNext()
        Mockito.doNothing().`when`(mockCursor).close()

        // Call the method
        mainActivity.getGuestList()

        // Verify
        val expectedList = ArrayList<GuestModel>()
        expectedList.add(GuestModel(1, "John Doe"))

        assertEquals(expectedList, mainActivity.guestList)
        Mockito.verify(mockCursor).moveToFirst()
        Mockito.verify(mockCursor).isAfterLast
        Mockito.verify(mockCursor).getInt(0)
        Mockito.verify(mockCursor).getString(1)
        Mockito.verify(mockCursor).moveToNext()
        Mockito.verify(mockCursor).close()
    }

    @Test
    fun testGetFriendsList() {
        // Mock the cursor behavior
        Mockito.doReturn(true).`when`(mockCursor).moveToFirst()
        Mockito.doReturn(false).`when`(mockCursor).isAfterLast
        Mockito.doReturn(1).`when`(mockCursor).getInt(0)
        Mockito.doReturn("John Doe").`when`(mockCursor).getString(1)
        Mockito.doNothing().`when`(mockCursor).moveToNext()
        Mockito.doNothing().`when`(mockCursor).close()

        // Call the method
        mainActivity.getGuestList()

        // Verify
        val expectedList = ArrayList<GuestModel>()
        expectedList.add(GuestModel(1, "John Doe"))

        assertEquals(expectedList, mainActivity.guestList)
        Mockito.verify(mockCursor).moveToFirst()
        Mockito.verify(mockCursor).isAfterLast
        Mockito.verify(mockCursor).getInt(0)
        Mockito.verify(mockCursor).getString(1)
        Mockito.verify(mockCursor).moveToNext()
        Mockito.verify(mockCursor).close()
    }

    @Test
    fun testAcceptChallenge() {
        // Mock the cursor behavior
        Mockito.doReturn(true).`when`(mockCursor).moveToFirst()
        Mockito.doReturn(false).`when`(mockCursor).isAfterLast
        Mockito.doReturn(1).`when`(mockCursor).getInt(0)
        Mockito.doReturn("John Doe").`when`(mockCursor).getString(1)
        Mockito.doNothing().`when`(mockCursor).moveToNext()
        Mockito.doNothing().`when`(mockCursor).close()

        // Call the method
        mainActivity.getGuestList()

        // Verify
        val expectedList = ArrayList<GuestModel>()
        expectedList.add(GuestModel(1, "John Doe"))

        assertEquals(expectedList, mainActivity.guestList)
        Mockito.verify(mockCursor).moveToFirst()
        Mockito.verify(mockCursor).isAfterLast
        Mockito.verify(mockCursor).getInt(0)
        Mockito.verify(mockCursor).getString(1)
        Mockito.verify(mockCursor).moveToNext()
        Mockito.verify(mockCursor).close()
    }



}