package com.juanes.OpenBootcamp.books.project.Repository;

import com.juanes.OpenBootcamp.books.project.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
