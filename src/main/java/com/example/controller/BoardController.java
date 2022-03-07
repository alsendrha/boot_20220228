package com.example.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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

@Controller
@RequestMapping(value = "/board") // 대소문자 구분할것
public class BoardController {

    // 서비스 => mybatis => 설계 + 구현(SQL문)

    // 저장소 => jpa, hibernate => 설계 + 구현(SQL)
    @Autowired
    BoardRepository bRepository;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    HttpSession httpSession;

    @GetMapping(value = "/update")
    public String updateGET(Model model) {
        Board no = (Board) httpSession.getAttribute("rad");

        Board board2 = bRepository.save(no);
        model.addAttribute("board2", board2);
        return "redirect:/board/update";
    }

    @PostMapping(value = "/action")
    public String actionPOST(@RequestParam(name = "btn") String btn, @RequestParam(name = "rad") long no) {
        System.out.println(btn);
        System.out.println(no);

        if (btn.equals("1개 삭제")) {
            bRepository.deleteById(no);
        } else if (btn.equals("1개 수정")) {

            // 화면전환하기 /board/update
            // bRepository.save를 이용 단, _id가 조건으로 됨.
            httpSession.setAttribute("rad", no);
            return "redirect:/board/selectlist";
        }
        return "redirect:/board/selectlist";
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

}
