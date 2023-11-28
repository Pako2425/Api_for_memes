package com.patryk.app.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patryk.app.webapp.Model.Meme;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemesRepository extends JpaRepository<Meme, Long> {
}
