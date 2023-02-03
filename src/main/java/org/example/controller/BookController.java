package org.example.controller;

import java.util.List;
import java.util.Map;
import org.example.domain.entity.Book;
import org.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  // Find
  @GetMapping("/books")
  List<Book> findAll() {
    return bookService.findAll();
  }

  // Save
  @PostMapping("/books")
  // return 201 instead of 200
  @ResponseStatus(HttpStatus.CREATED)
  Book newBook(@RequestBody Book newBook) {
    return bookService.newBook(newBook);
  }

  // Find
  @GetMapping("/books/{id}")
  Book findOne(@PathVariable Long id) {
    return bookService.findOne(id);
  }

  // Save or update
  @PutMapping("/books/{id}")
  Book saveOrUpdate(@PathVariable Long id, @RequestBody Book newBook) {
    return bookService.saveOrUpdate(id, newBook);
  }

  // update author only
  @PatchMapping("/books/{id}")
  Book patch(@PathVariable Long id, @RequestBody Map<String, String> update) {
    return bookService.patch(id, update);
  }

  @DeleteMapping("/books/{id}")
  void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }
}
