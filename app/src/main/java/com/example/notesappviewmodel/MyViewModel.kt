package com.example.notesappviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {
    private val connection: NotesDoa = NotesDatabase.getInstance(application).notesDoa()
    private val notes: LiveData<List<Data>> = connection.gettingData()

    fun gettingNotes(): LiveData<List<Data>>{
        return notes
    }

    fun addNote(note: Data){
        CoroutineScope(IO).launch {
            connection.addNewNote(note)
        }
    }

    fun updateNote(note: Data){
        CoroutineScope(IO).launch {
            connection.updateNote(note)
        }
    }

    fun deleteNote(note: Data){
        CoroutineScope(IO).launch {
            connection.deleteNote(note)
        }
    }
}