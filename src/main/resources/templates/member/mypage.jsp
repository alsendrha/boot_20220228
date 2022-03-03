<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
    <head th:replace="~{/member/header :: headerFragment}"></head>

<body>
    <div style="padding: 20px;">
        <h3>마이페이지</h3>
        <a th:href="@{/home}">홈으로</a>
        <hr />

        <div th:replace="~{/member/footer::footerFragment}"></div>
    </div>
    
</body>
</html>