package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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

    @Autowired
    HttpSession httpSession;

    @PostMapping(value = "/updatebatch")
    public String updatebatchPOST(Model model,
            @RequestParam(name = "code") long[] code,
            @RequestParam(name = "title") String[] title,
            @RequestParam(name = "price") long[] price,
            @RequestParam(name = "writer") String[] writer,
            @RequestParam(name = "category") String[] category) {

        List<Book> list = new ArrayList<>();
        for (int i = 0; i < code.length; i++) {
            Book book = new Book();
            book.setCode(code[i]);
            book.setTitle(title[i]);
            book.setPrice(price[i]);
            book.setWriter(writer[i]);
            book.setCategory(category[i]);

            list.add(book);
        }
        long result = bookDB.updateBactchBook(list);
        if (result == 1) {
            model.addAttribute("msg", "일괄수정 되었습니다"); // 알림창 필요할때 사용
            model.addAttribute("url", "/admin/selectlist");
            return "alert";
        }
        // jsp를 만들어서 알림을 띄우고 redirect를 수행
        model.addAttribute("msg", "일괄수정 실패했습니다.");
        model.addAttribute("url", "/admin/selectlist");
        return "alert";

        // 알림창을 표시할 수 없음
        // return "redirect:/admin/selectlist";
    }

    // 127.0.0.1:8080/admin/updatebatch
    @GetMapping(value = "/updatebatch")
    public String updateGET(Model model) {
        // 세션에 넣어서 이동

        // 형변환을 하면 데이터가 안전하지 않음을 경고
        // 세션에 추가할때와 가지고올때의 타입을 정확하게 매칭
        @SuppressWarnings({ "unchecked" })
        List<Long> code = (List<Long>) httpSession.getAttribute("CHK"); // 형변환

        List<Book> list = bookDB.selectListWhereIn(code);
        model.addAttribute("list", list);

        // DB에서 code에 해당하는 항복 정보만 가져옴
        // jsp로 전달함.
        // jsp로 표시함.
        return "admin/updatebatch";
    }

    @PostMapping(value = "/action")
    public String actionPOST(
            @RequestParam(name = "btn") String btn,
            @RequestParam(name = "chk") List<Long> code) { // long[] 배열형태로 받음

        // 전달되는 chk값 출력하기
        for (Long tmp : code) {
            System.out.println(tmp);
        }
        // warpper 클래스( 구글에 래퍼 클래스 검색)
        // private long a = 0L
        // private Long a = null
        // long Long *********long을 클래스화 시킴 Long
        // int Integer
        // double Double
        // long[] == list<long> 배열 = 리스트 같음
        System.out.println(btn); // 일괄삭제 or 일괄수정

        if (btn.equals("일괄삭제")) {
            bookDB.deleteBatchBook(code);

        } else if (btn.equals("일괄수정")) {
            // 세션에 넣어서 이동
            httpSession.setAttribute("CHK", code);
            return "redirect:/admin/updatebatch";

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
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "text", defaultValue = "") String text) {

        // 페이지+검색
        List<Book> list = bookDB.selectListSearchBook(page, text);
        long pages = bookDB.countSearchBook(text);

        // jsp로 전달(변수, 값) => 변수
        model.addAttribute("list", list);
        model.addAttribute("pages", (pages - 1) / 10 + 1);

        return "admin/selectlist";
    }

    @GetMapping(value = "/delete")
    public String deleteGET(@RequestParam(name = "id") long code) {

        int result = bookDB.deleteBook(code);
        if (result == 1) {
            return "admin/selectlist";
        }
        return "admin/selectlist";
    }

}
