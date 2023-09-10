package com.take_notes.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.take_notes.entities.Note;
import com.take_notes.services.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@PostMapping
	public Note addNote(@RequestBody Note note, @RequestHeader("Authorization") String token) {
		return noteService.addNote(note, token);
	}

	@GetMapping
	public List<Note> getAllNotesFromUser(@RequestHeader("Authorization") String token) {
		return noteService.getAllNotesFromUser(token);
	}

	@PutMapping("/{id}")
	public Note updateNote(@PathVariable int id, @RequestBody Note note, @RequestHeader("Authorization") String token) {
		return noteService.updateNote(id, note, token);
	}

	@DeleteMapping("/{id}")
	public String deleteNote(@PathVariable int id, @RequestHeader("Authorization") String token) {
		noteService.deleteNote(id, token);
		return "Note Delete With Id : " + id;

	}
}
