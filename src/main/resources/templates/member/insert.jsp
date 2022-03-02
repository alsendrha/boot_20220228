<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>회원가입</h3>
        <hr />
        <a href="/">홈</a>
        <a href="/member/selectlist">회원리스트</a>
        
        <form th:action="@{/member/insert}" method="post">
            아이디 : <input type="text" name="id"/><br />
            암호 : <input type="password" name="pw" /><br />
            암호1 : <input type="password" name="pw1" /><br />
            이름 : <input type="text" name="name" /><br />
            나이 : <input type="number" name="age" /><br />
            <input type="submit" class=" btn btn-primary" value="회원가입" />
        </form>
    </div>
</body>
</html>