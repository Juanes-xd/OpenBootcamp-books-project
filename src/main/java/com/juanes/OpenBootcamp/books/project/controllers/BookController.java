package com.juanes.OpenBootcamp.books.project.controllers;

import com.juanes.OpenBootcamp.books.project.Repository.BookRepository;
import com.juanes.OpenBootcamp.books.project.entities.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Obtener todos los libros
    @GetMapping
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    //Obtener un solo libro
    @GetMapping("{id}")
    public ResponseEntity<Book> findBook(@PathVariable Long id){
        Optional<Book> optBook = bookRepository.findById(id);
        if(optBook.isPresent()) {
            return ResponseEntity.ok(optBook.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
        /*Otras opciones
         return optBook.orElse(null);
        return optBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        */

    }
    //Crear un libro
    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book book){

        if(book.getId() != null){
            return ResponseEntity.badRequest().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }
    //Eliminar un libro
    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            ResponseEntity.notFound().build();
        }
         bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    //Actualizar un libro
    @PutMapping("{id}")
    public ResponseEntity<Book> putBook(@PathVariable Long id, @RequestBody Book newBook){
       Optional<Book> book = bookRepository.findById(id);
       if (book.isPresent()){
         Book updateBook =  book.get();
         if(newBook.getTitle() != null) {
             updateBook.setTitle(newBook.getTitle());
         }
           if(newBook.getAuthor() != null) {
               updateBook.setAuthor(newBook.getAuthor());
           }
           if(newBook.getPages() != null && newBook.getPages() > 0) {
               updateBook.setPages(newBook.getPages());
           }
           if(newBook.getPrice() != null) {
               updateBook.setPrice(newBook.getPrice());
           }
           if(newBook.getOnline() != null) {
               updateBook.setOnline(newBook.getOnline());
           }
          return ResponseEntity.ok(updateBook);
       }
       return ResponseEntity.notFound().build();
    }
}
