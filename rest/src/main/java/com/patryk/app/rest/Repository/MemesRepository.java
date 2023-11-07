package com.patryk.app.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patryk.app.rest.Model.Meme;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemesRepository extends JpaRepository<Meme, Long> {
}
