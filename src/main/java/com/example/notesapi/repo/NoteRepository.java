package com.example.notesapi.repo;

import com.example.notesapi.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {
}
