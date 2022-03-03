<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
    <head th:replace="~{/member/header :: headerFragment}"></head>

<body>
    <div style="padding: 20px;">
        <h3>마이페이지</h3>
        <a th:href="@{/home}">홈으로</a>
        <hr />
        <a th:href="@{/member/mypage?menu=1}">정보수정</a>
        <a th:href="@{/member/mypage?menu=2}">암호변경</a>
        <a th:href="@{/member/mypage?menu=3}">회원탈퇴</a>
        <hr />
        <div th:if="${param.menu.toString().equals('1')}">
            <h3>정보수정</h3>
            <hr />
                <form th:action="@{/member/update}" method="post">
                    이름 : <input type="text" name="name" ><br />
                    나이 : <input type="text" name="age" ><br />
                    <input type="submit" value="수정하기" />
                </form>
              
            
        </div>
        <div th:if="${param.menu.toString().equals('2')}">
            <h3>암호변경</h3>
            <hr />
                <form th:action="@{/member/update}" method="post">
                    암호 : <input type="password" name="pw" ><br />
                    새암호 : <input type="password" name="pw1" ><br />
                    <input type="submit" value="수정하기" />
                </form>
        </div>
        <div th:if="${param.menu.toString().equals('3')}">
            <h3>회원탈퇴</h3>
            <hr />
                <form th:action="@{/member/update}" method="post">
                    암호 : <input type="password" name="pw" ><br />
                    <input type="submit" value="탈퇴하기" />
                </form>
        </div>
        <div th:replace="~{/member/footer::footerFragment}"></div>
    </div>
    
</body>
</html>