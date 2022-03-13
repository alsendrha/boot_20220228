package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import com.example.service.BookDB;
import com.example.service.ItemDB;
import com.example.service.SequenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    SequenceService sequenceService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    BookDB bookDB;

    // insert 추가
    // select 조회
    // update 수정
    // delete 삭제

    @GetMapping(value = "/delete")
    public String deleteGET(Model model, @RequestParam(name = "id") long code) {

        bookRepository.deleteById(code);
        model.addAttribute("msg", "삭제되었습니다");
        model.addAttribute("url", "/book/selectlist");
        return "alert";

    }

    @PostMapping(value = "/update")
    public String updatePOST(Model model, @ModelAttribute Book book) {
        try {
            // 수정하기 => 기존코드 불러와서 글쓰기
            Book book1 = bookRepository.findById(book.getCode()).orElse(null); // 코드 불러오기
            // 수정항목
            book1.setTitle(book.getTitle());
            book1.setPrice(book.getPrice());
            book1.setWriter(book.getWriter());

            // 수정항목 담기
            bookRepository.save(book1);
            model.addAttribute("msg", "수정되었습니다.");
            model.addAttribute("url", "/book/selectlist");
            return "alert";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/book/update";
        }
    }

    @GetMapping(value = "/update")
    public String updateGET(Model model, @RequestParam(name = "id") long code) {

        Book book = bookRepository.getBookOne(code);
        System.out.println(book.toString());
        model.addAttribute("book", book);
        return "book/update";
    }

    // 127.0.0.1:8080/book/select?page=1&text=한글
    @GetMapping(value = "/selectlist")
    public String selectlistGET(Model model,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "text", defaultValue = "") String text) {

        PageRequest pagerequest = PageRequest.of(page - 1, 10, Sort.Direction.DESC, "code");
        List<Book> list = bookRepository.getBookList(text, pagerequest);
        model.addAttribute("list", list);

        // 페이지네이션 번호 생성
        long pages = bookRepository.getBookCount(text);
        model.addAttribute("pages", (pages - 1) / 10 + 1);
        return "/book/selectlist";
    }

    @GetMapping(value = "/insert")
    public String insertGET() {
        return "/book/insert";
    }

    @PostMapping(value = "/insert")
    public String insertPOST(Model model, @ModelAttribute Book book) {

        book.setCode(sequenceService.generatorSequence("SEQ_BOOK4_CODE"));
        book.setRegdate(new Date());
        Book rebook = bookRepository.save(book);

        if (rebook != null) {
            model.addAttribute("msg", "등록되었습니다");
            model.addAttribute("url", "/book/selectlist");
            return "alert";
        }
        return "redirect:/book/insert";
    }

}
