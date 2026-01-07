package com.Markdown_note_taking_app.service;

import com.Markdown_note_taking_app.dto.NoteRequest;
import com.Markdown_note_taking_app.entity.Note;
import com.Markdown_note_taking_app.repository.NoteRepository;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final Parser parser;
    private final HtmlRenderer renderer;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note createNote(NoteRequest noteRequest) {
        Note note = new Note();
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());
        note.setHtmlContent(renderMarkdownToHtml(noteRequest.getContent()));
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, NoteRequest noteRequest) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(noteRequest.getTitle());
            note.setContent(noteRequest.getContent());
            note.setHtmlContent(renderMarkdownToHtml(noteRequest.getContent()));
            return noteRepository.save(note);
        }).orElseThrow(() -> new RuntimeException("Note not found with id " + id));
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    private String renderMarkdownToHtml(String markdown) {
        if (markdown == null) {
            return "";
        }
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }
}
