package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.entity.Board;
import com.example.repository.BoardRepository;
import com.example.service.SequenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping(value = "/board") // 대소문자 구분할것
public class BoardController {

    // 서비스 => mybatis => 설계 + 구현(SQL문)

    // 저장소 => jpa, hibernate => 설계 + 구현(SQL)
    @Autowired
    BoardRepository bRepository;

    @Autowired
    SequenceService sequenceService;

    // RedirectAttributes = POST에서 GET으로 데이터를 전송
    @PostMapping(value = "/action")
    public String actionPOST(Model model, RedirectAttributes redirectAttributes, @RequestParam(name = "btn") String btn,
            @RequestParam(name = "rad") long no) {
        try {
            // int long, char, String = if(btn == 1), equals => String 문자열은 안됨
            if (btn.equals("1개 삭제")) {
                bRepository.deleteById(no);
                model.addAttribute("msg", "삭제되었습니다.");
                model.addAttribute("url", "/board/selectlist");
                return "alert"; // 알림창 띄우고 url변경 자동화

            } else if (btn.equals("1개 수정")) {
                // GET방식 url에 parameter로 붙임
                redirectAttributes.addAttribute("no", no);
                // POST방식 1번만 전송 새로고침시 데이터는 소멸
                // 세션에 추가하는 방식
                redirectAttributes.addFlashAttribute("no1", no);
                return "redirect:/board/update";
            }
            return "redirect:/board/selectlist";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home";
        }
    }

    @GetMapping(value = "/update")
    public String updateGET(Model model,
            HttpServletRequest request,
            @RequestParam(name = "no") long no) {
        System.out.println("no : " + no);

        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            long no1 = (long) map.get("no1");
            System.out.println("no1 : " + no1);
        }

        // null에 대한 처리
        Board board = bRepository.findById(no).orElse(null);
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping(value = "/update")
    public String updatePOST(Model model, @ModelAttribute Board board) {

        // 추가 => 기본키를 다르게해서 저장
        // 수정 => 기본키에 해당하는 글번호를 동일하게 해서 저장

        try {

            // 기존내용을 읽음
            Board board1 = bRepository.findById(board.getNo()).orElse(null);

            // 변경할 항목만 board1에 다시 저장
            board1.setTitle(board.getTitle());
            board1.setContent(board.getContent());
            board1.setWriter(board.getWriter());

            // 최종적으로 board1의 값을 저장
            bRepository.save(board1);

            model.addAttribute("msg", "수정되었습니다.");
            model.addAttribute("url", "/board/selectlist");
            return "alert";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home";
        }
    }

    @GetMapping(value = "/selectlist")
    public String selectlistGET(Model model) {
        List<Board> list = bRepository.findAll();
        model.addAttribute("list", list);
        return "board/selectlist";
    }

    @GetMapping(value = "/insert")
    public String insertGET() {
        return "board/insert";
    }

    @PostMapping(value = "/insert")
    public String insertPOST(Model model, @ModelAttribute Board board) {
        System.out.println(board.toString());
        // "SEQ_BOARD4_NO"
        board.setNo(sequenceService.generatorSequence("SEQ_BOARD4_NO")); // 안들어가면 entity확인
        board.setRegdate(new Date());
        Board retBoard = bRepository.save(board);

        if (retBoard != null) {
            model.addAttribute("msg", "글쓰기 완료");
            model.addAttribute("url", "/board/selectlist");
            return "alert";

        }
        return "redirect:/board/insert";

    }

    // 127.0.0.1:8080/board/selectfind?type=title&text=%09ewrterter
    @GetMapping(value = "selectfind")
    public String selectfindGET(Model model,
            @RequestParam(name = "text", defaultValue = "", required = false) String text,
            @RequestParam(name = "type", defaultValue = "", required = false) String type,

            @RequestParam(name = "hit", defaultValue = "0", required = false) long hit1,
            @RequestParam(name = "type1", defaultValue = "0", required = false) int type1,

            @RequestParam(name = "no", defaultValue = "0", required = false) List<Long> no,
            @RequestParam(name = "type2", defaultValue = "0", required = false) int type2

    ) {

        List<Board> list = null;
        if (type.equals("title")) {
            // list = bRepository.findByTitle(text);
            list = bRepository.getBoardTitle(text); // *********직접 구현
        } else if (type.equals("writer")) {
            list = bRepository.getBoardWriter(text); // *********직접 구현
            // list = bRepository.findByWriter(text);
        } else if (type.equals("hit")) {
            long hit = 0L;
            try {
                // 문자로 되어 있는 숫자를 숫자형으로변경
                // "1234" => 1234
                // "" => X
                hit = Long.parseLong(text);
            } catch (Exception e) {
                hit = 0L;
            }
            // list = bRepository.findByHit(hit);
            list = bRepository.getBoardHit(hit); // *********직접 구현

        }
        if (text.length() == 0) {
            list = bRepository.findAll();
        }

        if (type1 == 1) { // 이상
            list = bRepository.findByHitGreaterThanEqual(hit1);
        } else if (type1 == 2) { // 미만
            list = bRepository.findByHitLessThan(hit1);
        }

        if (type2 == 1) { // 포함
            list = bRepository.findByNoIn(no);
        } else if (type2 == 1) { // 포함X
            list = bRepository.findByNoNotIn(no);
        }
        model.addAttribute("list", list);

        return "board/selectfind";
    }

}
