package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.Book;
import com.example.service.BookDB;
import com.example.service.SequenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 1. 컨트롤러
@Controller
@RequestMapping(value = "/admin") // admin 폴더
public class AdminController {

    @Autowired
    BookDB bookDB;

    @Autowired
    SequenceService sequence;

    @PostMapping(value = "/action")
    public String actionPOST(
            @RequestParam(name = "btn") String btn,
            @RequestParam(name = "chk") long[] code) {
        System.out.println(btn); // 일괄삭제 or 일괄수정

        if (btn.equals("일괄삭제")) {
            // 1) DB에 삭제하기 구현
            // 2) 회원목록, 물품목록 검색기능 추가하기

        } else if (btn.equals("일괄수정")) {

        }
        // 목록으로 이동하기
        return "redirect:/admin/selectlist";
    }

    // 127.0.0.1:8080/admin/insertbatch
    @GetMapping(value = "/insertbatch")
    public String insertGET() {

        return "admin/insertbatch";
    }

    @PostMapping(value = "/insertbatch")
    public String insertPOST(
            @RequestParam(name = "title") String[] title,
            @RequestParam(name = "price") long[] price,
            @RequestParam(name = "writer") String[] writer,
            @RequestParam(name = "category") String[] category) {

        // 1. 빈 리스트 만들기

        List<Book> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) { // 0, 1
            System.out.println(title[i] + "," + price[i] + "," + writer[i] + "," + category[i]);
            // 2. book객체 민들기
            // 시퀀스명 SEQ_BOOK4_CODE
            Book book = new Book();
            book.setCode(sequence.generatorSequence("SEQ_BOOK4_CODE"));
            book.setTitle(title[i]);
            book.setPrice(price[i]);
            book.setWriter(writer[i]);
            book.setCategory(category[i]);
            book.setRegdate(new Date());
            // 3. 리스트에 추가하기
            list.add(book);
        }
        // 배열4개로 => list<Book>
        // 시퀀스로 코드를 채워, 날짜도 채워
        bookDB.insertBatchBook(list);
        return "redirect:/admin/insertbatch";
    }

    @GetMapping(value = "/selectlist")
    public String selectlistGET(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "text", defaultValue = "") String text) {

        // 페이지+검색
        List<Book> list = bookDB.selectListSearchBook(page, text);
        long pages = bookDB.countSearchBook(text);

        // jsp로 전달(변수, 값) => 변수
        model.addAttribute("list", list);
        model.addAttribute("pages", (pages - 1) / 10 + 1);

        return "admin/selectlist";
    }

}
