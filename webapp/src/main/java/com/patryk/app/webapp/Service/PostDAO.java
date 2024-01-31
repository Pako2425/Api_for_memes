package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.Comment;
import com.patryk.app.webapp.Model.Image;
import com.patryk.app.webapp.Model.Meme;
import com.patryk.app.webapp.Model.User;
import com.patryk.app.webapp.Repository.CommentsRepository;
import com.patryk.app.webapp.Repository.ImagesRepository;
import com.patryk.app.webapp.Repository.MemesRepository;
import com.patryk.app.webapp.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PostDAO {
    private long memeId;
    private String username;
    private String title;
    private String imagePath;
    private int likesNumber;
    private int commentsNumber;
    private List<Comment> comments;

    public PostDAO(Meme meme, UsersRepository usersRepository, ImagesRepository imagesRepository, CommentsRepository commentsRepository) {
        this.memeId = meme.getId();
        User user = usersRepository.getReferenceById(meme.getUserId());
        this.username = user.getUsername();
        this.title = meme.getTitle();
        Image image = imagesRepository.getReferenceById(meme.getImageId());
        this.imagePath = image.getFilePath();
        this.likesNumber = meme.getLikesNumber();
        this.commentsNumber = meme.getCommentsNumber();
        this.comments = commentsRepository.findAllByMemeId(meme.getId());
    }
}
