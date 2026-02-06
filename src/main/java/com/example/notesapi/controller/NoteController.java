package com.example.notesapi.controller;

import com.example.notesapi.dto.NoteDTO;
import com.example.notesapi.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @PostMapping
    public NoteDTO create(@Valid @RequestBody NoteDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<NoteDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public NoteDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public NoteDTO update(@PathVariable int id, @Valid @RequestBody NoteDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Note deleted successfully";
    }
}
