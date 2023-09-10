package com.take_notes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.take_notes.entities.Note;
import com.take_notes.entities.User;
import com.take_notes.repositories.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private UserService userService;

	public Note addNote(Note note, String token) {

		User user = userService.getUserByToken(token);

		note.setUser(user);

		return noteRepository.save(note);
	}

	public Note getNoteById(int id) {
		return noteRepository.findById(id).orElseThrow();
	}

	public List<Note> getAllNotesFromUser(String token) {
		User user = userService.getUserByToken(token);
		return user.getNotes();
	}

	public void deleteNote(int id, String token) {
		User user = userService.getUserByToken(token);
		Note note = getNoteById(id);
		noteRepository.delete(note);
	}

	public Note updateNote(int id, Note note, String token) {
		User user = userService.getUserByToken(token);
		
		Note noteById = getNoteById(id);

		noteById.setTitle(note.getTitle());
		noteById.setDescription(note.getDescription());

		return noteRepository.save(noteById);
	}

}
