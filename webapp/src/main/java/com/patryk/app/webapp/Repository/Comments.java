package com.patryk.app.webapp.Repository;

import com.patryk.app.webapp.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Comments extends JpaRepository<Comment, Long> {
}
