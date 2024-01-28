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
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
@AllArgsConstructor
public class PaginationService {
    private final MemesRepository memesRepository;
    private final UsersRepository usersRepository;
    private final CommentsRepository commentsRepository;
    private final ImagesRepository imagesRepository;

    private static final int MAIN_PAGE_SIZE = 2;
    private static final int MAX_MAIN_PAGE_LINKS = 5;

    public void pagePagination(int maxPageLinks, int totalPages, int currentPageIndex, Model model) {
        int firstPage = Math.max(0, currentPageIndex - (maxPageLinks / 2));
        int lastPage = Math.min(totalPages - 1, firstPage + (maxPageLinks - 1));
        firstPage = Math.max(0, lastPage - maxPageLinks + 1);

        model.addAttribute("currentPage", currentPageIndex);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);
    }

    public void showMainPage(int pageIndex, Model model) {
        Page<Meme> memesPage = memesRepository.findAll(PageRequest.of(pageIndex, MAIN_PAGE_SIZE, Sort.Direction.DESC, "id"));
        List<Meme> memes = memesPage.getContent();

        List<PostDAO> posts = memes.stream()
                .map(meme -> new PostDAO(meme, usersRepository, imagesRepository, commentsRepository))
                .toList();

        model.addAttribute("posts", posts);
        int totalPages = memesPage.getTotalPages();

        pagePagination(MAX_MAIN_PAGE_LINKS, totalPages, pageIndex, model);
    }

    public void showRandomPage(Model model) {
        long totalElements = memesRepository.count();
        long randomMemeId = ThreadLocalRandom.current().nextLong(totalElements);
        Page<Meme> randomMemePage = memesRepository.findAll(PageRequest.of(Long.valueOf(randomMemeId).intValue(), 1));
        model.addAttribute("randomMeme", randomMemePage.getContent());
    }
}
