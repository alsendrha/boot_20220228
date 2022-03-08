<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>책등록</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>책등록</h3>
        <a href="/">홈으로</a>
        <hr />
        <form th:action="@{/book/insert}" method="post">
            제목 : <input type="text" name="title" /><br />
            내용 : <input type="text" name="content" /><br />
            작성자 : <input type="text" name="writer" /><br />
            가격 : <input type="text" name="price" /><br />
            <select name="category">
                <option>A</option>
                <option>B</option>
            </select><br />
            <input type="submit" value="책등록" />
        </form>
    </div>
</body>
</html>