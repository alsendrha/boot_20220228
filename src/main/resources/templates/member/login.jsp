<!DOCTYPE html>

<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head th:replace="~{/member/header :: headerFragment}"></head>

<body>
    <div style="padding: 20px;">
    <h3>로그인</h3>
    <a href="/">홈</a>
    <hr />
    <form th:action="@{/member/login}" method="post">
        아이디 : <input type="text" name="id" /><br />
        암호 : <input type="password" name="pw" /><br />
        <input type="submit" class=" btn btn-primary" value="로그인" />
    </form>
    <hr />
    <div th:replace="~{/member/footer::footerFragment}"></div>
    <div th:replace="~{/member/footer::footerFragment1}"></div>
</div> 

</body>
</html>

  