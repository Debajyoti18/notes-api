package com.example.notesapi.service;

import com.example.notesapi.dto.NoteDTO;
import com.example.notesapi.entity.Note;
import com.example.notesapi.exception.NoteNotFoundException;
import com.example.notesapi.repo.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;
    private final ModelMapper mapper;

    public NoteService(NoteRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public NoteDTO create(NoteDTO dto) {
        Note note = mapper.map(dto, Note.class);
        Note saved = repository.save(note);
        return mapper.map(saved, NoteDTO.class);
    }

    public List<NoteDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(note -> mapper.map(note, NoteDTO.class))
                .toList();
    }

    public NoteDTO getById(int id) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id " + id));
        return mapper.map(note, NoteDTO.class);
    }

    public NoteDTO update(int id, NoteDTO dto) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id " + id));

        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        return mapper.map(repository.save(note), NoteDTO.class);
    }

    public void delete(int id) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id " + id));
        repository.delete(note);
    }
}
