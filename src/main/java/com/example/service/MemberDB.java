package com.example.service;

import java.util.List;

import com.example.entity.Member;

import org.springframework.stereotype.Service;

// DB와 연동하는 부분 구현하는 설계부분 interface로 만듬 (class = X)
@Service
public interface MemberDB {

    // 추가할내용을 member로 주면 추가한후에
    // 실제 추가된 내용을 반환
    public Member insertMember(Member member);

    // 회원 전체목록(page, search X)
    public List<Member> selectListMamner();

    // 회원 1명 삭제(회원 아이디가 오면 삭제 후 -1 또는 1로 리턴)
    public int deleteMember(String id);

    // 회원 1명 조회하기(아이디를 전달하면 회원 1명의 정보)
    public Member selectOneMember(String id);

    // 회원정보 변경하기
    public int updateMember(Member member);

    // 삭제, 수정, 추가 리턴은 int

}
