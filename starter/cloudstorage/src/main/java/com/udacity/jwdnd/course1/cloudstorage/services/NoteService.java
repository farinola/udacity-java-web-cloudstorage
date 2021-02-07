package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes() {
        return this.noteMapper.getAll();
    }

    public int createOrUpdateNote(Note note) {
        if (note.getId() != null) {
            return this.noteMapper.update(note);
        }
        return this.noteMapper.insert(note);
    }

    public void deleteNoteById(int id) {
        this.noteMapper.delete(id);
    }
}
