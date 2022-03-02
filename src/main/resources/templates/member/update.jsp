<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원수정</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>회원정보수정</h3>
        <hr />
        <form th:action="@{/member/update}" method="post">
            아이디 : <input type="text" th:value="${member.id}" name="id" readonly><br />
            이름 : <input type="text" th:value="${member.name}" name="name" ><br />
            나이 : <input type="text" th:value="${member.age}" name="age" ><br />
            <input type="submit" value="수정하기" />
        </form>
    </div>
</body>
</html>