package com.adsfatec.lime.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adsfatec.lime.models.Comment;
import com.adsfatec.lime.models.Book;
import com.adsfatec.lime.models.enums.MediaType;
import com.adsfatec.lime.services.BookService;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String getBooks(Model model) {
        List<Book> books = service.getTableData();
        model.addAttribute("books", books);

        return "books/books";
    }

    @GetMapping("/list")
    public String getBooksList(Model model) {
        List<Book> books = service.getTableData();
        model.addAttribute("books", books);

        return "books/books :: list";
    }

    @GetMapping("/table")
    public String getBooksTable(Model model) {
        List<Books> books = service.getTableData();
        model.addAttribute("books", books);

        return "books/books :: table";
    }

    @GetMapping("/new")
    public String getBookForm(Model model) {
        Book b = new Book();
        model.addAttribute("book", b);

        return "books/form :: form";
    }

    @GetMapping("/{bookId}/update")
    public String getUpdateBooksForm(@PathVariable String bookId, Model model) {
        Book b = service.getDetails(bookId);
        model.addAttribute("book", b);

        return "books/form :: form";
    }

    @PostMapping
    public String insertBook(@ModelAttribute Book book, Model model) {
        service.insert(book);

        List<Book> books = service.getTableData();
        model.addAttribute("books", books);

        return "books/books :: table";
    }

    @PutMapping("/{bookId}")
    public String updateBook(@PathVariable String bookId, @ModelAttribute Book book, Model model) {
        service.update(bookId, book);

        Book b = service.getDetails(bookId);
        model.addAttribute("book", b);

        return "books/details :: details";
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId, Model model) {
        service.delete(bookId);

        List<Book> books = service.getTableData();
        model.addAttribute("books", books);

        return "books/books :: table";
    }

    @GetMapping("/{bookId}")
    public String getBook(@PathVariable String bookId, Model model) {
        Book book = service.getDetails(bookId);

        model.addAttribute("book", book);

        return "books/books :: details";
    }

    @GetMapping("/{bookId}/comments/new")
    public String getCommentsForm(@PathVariable String bookId, Model model) {
        Comment c = new Comment(bookId, MediaType.BOOK);

        model.addAttribute("comment", c);

        return "comments/form :: form";
    }
}
