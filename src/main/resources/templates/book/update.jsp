<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수정하기</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>수정하기</h3>
        <a href="/">홈으로</a>
        <hr />
        <form th:action="@{/book/update}" method="Post">
            <input type="hidden" name="code" th:value="${book.code}" />
            제목 : <input type="text" th:value="${book.title}" name="title" /><br />
            가격 : <input type="text" th:value="${book.price}" name="price" /><br />
            작성자 : <input type="text" th:value="${book.writer}" name="writer" /><br />
            <input type="submit" value="수정하기" />
        </form>
    </div>
</body>
</html>