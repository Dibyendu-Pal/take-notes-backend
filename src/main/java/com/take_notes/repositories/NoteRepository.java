package com.take_notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.take_notes.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{

}
