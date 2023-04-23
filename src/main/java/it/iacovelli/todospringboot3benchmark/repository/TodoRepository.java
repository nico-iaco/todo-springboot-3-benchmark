package it.iacovelli.todospringboot3benchmark.repository;

import it.iacovelli.todospringboot3benchmark.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {

    @Query("SELECT t from Todo t order by t.publishedDate ASC")
    List<Todo> findAllByOrderByPublishedDateAsc();

}
