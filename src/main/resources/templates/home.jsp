<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>

<body>
    <div style="padding: 20px;" >
        <h3>홈화면</h3>
        <hr />
        <div th:unless="${session.USERID}" style="display: inline-block;">
            <a th:href="@{/member/insert}">회원가입</a>
        </div>
        <div th:unless="${session.USERID}" style="display: inline-block;">
            <a th:href="@{/member/login}">로그인</a>
        </div>
        <div th:if="${session.USERID}" style="display: inline-block;">
            <a th:href="@{/member/logout}">로그아웃</a>
            <a th:href="@{/member/mypage}">마이페이지</a>
        </div>
        <a th:href="@{/member/selectlist}">회원목록</a>
        <a th:href="@{/item/insert}">물품등록</a>
        <a th:href="@{/item/selectlist}">물품목록</a>
        <a th:href="@{/admin/insertbatch}">일괄등록</a>
        <a th:href="@{/admin/selectlist}">도서목록</a>
        <a th:href="@{/board/insert}">게시물등록</a>
        <a th:href="@{/board/selectlist}">게시물목록</a>
        
    </div>
    
    
</body>
</html>