package com.example.controller;

import java.util.List;

import com.example.entity.Member;
import com.example.service.MemberDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/member") // {} 하나일땐 안써도됨
public class MemberController {

    // DB의 일을 수행하는 클래스
    // 클래스명 obj = new 클래스명();
    @Autowired
    private MemberDB memberDB;

    @GetMapping(value = "/update") // {} 하나일땐 안써도됨
    public String updateGET(
            Model model,
            @RequestParam(name = "id") String id) {
        // DB에서 내용을 가져오기
        Member member = memberDB.selectOneMember(id);
        // jsp로 전달해줌
        model.addAttribute("member", member);

        return "/member/update"; // 표시할 jsp파일명 확장자X
    }

    // id, name, age
    @PostMapping(value = "/update") // {} 하나일땐 안써도됨
    public String updatePOST(@ModelAttribute Member member) {
        System.out.println(member.toString());

        int result = memberDB.updateMember(member);
        if (result == 1) {
            // post에서는 jsp를 표시 X
            // redirect를 이용하여 주소를 변경
            return "redirect:/member/selectlist";
        }
        // 127.0.0.1:8080/member/update?id=aaas
        return "redirect:/member/update?id=" + member.getId();
    }

    // 127.0.0.1:8080/member/delete?id=aaa
    // <form action="/membet/delete" method="get | post">
    // <input type="text" name="id" value="aaa"/>
    // </form>

    // <a href="/member/delete?id=aaa">버튼</a>

    @GetMapping(value = { "/delete" }) // {} 하나일땐 안써도됨
    public String deleteGET(@RequestParam(name = "id") String id) {
        int ret = memberDB.deleteMember(id);
        if (ret == 1) {
            return "redirect:/member/selectlist";
        }
        return "redirect/member/selectlist";
    }

    // 127.0.0.1:8080/member/selectlist
    @GetMapping(value = { "/selectlist" }) // {} 하나일땐 안써도됨
    public String selectlistGET(Model model) {

        // 1. DB에서 목록 받아오기
        List<Member> list = memberDB.selectListMamner();

        // 2. jsp로 전달하기(jsp에서의 변수명, 실제전송값)
        model.addAttribute("list", list);

        // 3. member폴더의 select.jsp를 표시하라
        return "member/select";
    }

    // 127.0.0.1:8080/member/insert
    @GetMapping(value = { "/insert" }) // {} 하나일땐 안써도됨
    public String insertGET() {
        // member_insert.jsp로 생성
        // member 폴더에 있는 insert
        return "member/insert";
    }

    // post는 사용자가 입력한 내용이 전달되고
    // DB작업을 위해서 필요한 시점
    // jsp를 표시하는게 아니라 주소창에 입력후 엔터키를 누름
    @PostMapping(value = { "/insert" }) // {} 하나일땐 안써도됨
    public String insertPOST(
            @ModelAttribute Member mem) {
        System.out.println(mem.toString());
        memberDB.insertMember(mem);

        // DB에 추기하기
        // 클래스명 객체명 = new 생성자();
        // 위에 @ModelAttribute Member mem)로 생략가능
        // 주소창에 /member/insert를 입력후
        // 엔터키를 누르는것과 같은 역할
        // 주소창을 바꾸는곳
        return "redirect:/member/insert";
    }

}
