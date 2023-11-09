package com.patryk.app.rest.Service;

import com.patryk.app.rest.Model.Meme;
import com.patryk.app.rest.Repository.MemesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@AllArgsConstructor
public class PaginationService {
    private final MemesRepository MEMES_REPOSITORY;
    private final String MAIN_PAGE = "mainPage";

    public String showMainPage(int page, Model model) {
        int pageSize = 1;
        Page<Meme> memesPage = MEMES_REPOSITORY.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"));

        int totalPages = memesPage.getTotalPages();
        int maxPageLinks = 5;
        int firstPage = Math.max(0, page - (maxPageLinks / 2));
        int lastPage = Math.min(totalPages - 1, firstPage + (maxPageLinks - 1));
        firstPage = Math.max(0, lastPage - maxPageLinks + 1);

        model.addAttribute("memes", memesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);

        return MAIN_PAGE;
    }

}
