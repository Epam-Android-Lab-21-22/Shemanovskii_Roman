package com.beleavemebe.solevarnya.mapper

import com.beleavemebe.solevarnya.data.model.NoteDto
import com.beleavemebe.solevarnya.domain.model.Note
import java.util.*

object NoteMapper {
    fun noteToNoteDto(note: Note): NoteDto {
        return NoteDto(0, note.text, note.date.time)
    }

    fun noteDtoToNote(note: NoteDto): Note {
        return Note(note.text, Date(note.date))
    }
}
