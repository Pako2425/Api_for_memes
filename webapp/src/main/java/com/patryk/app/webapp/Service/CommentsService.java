package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.Comment;
import com.patryk.app.webapp.Repository.CommentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentsService {
    /*
    private final CommentsRepository commentsRepository;

    public void addComment(long userId, long memeId, String content, long parentCommentId) {
        //commentsRepository.save(userId, memeId, content, parentCommentId=0);
        Comment comment = new Comment();
        private Long comment_id;
        private Long meme_id;
        private Long user_id;
        private String content;
        private Long parent_comment_id;
        commentsRepository.save()
    }

    public void addReplyComment(long userId, long userIdToReply, long memeId, String content) {
        //commentsRepository.save(userId, memeId, content, parentCommentId=userIdToReply);
    }

    public List<Comment> readAllCommentsUnderMeme(long memeId) {
        return commentsRepository.findAllByMemeId(memeId);
    }
    */
}
