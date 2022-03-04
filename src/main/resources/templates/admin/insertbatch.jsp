<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일괄등록</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>도서 일괄 등록</h3>
        <a href="/">홈으로</a>
        <hr />
        <form th:action="@{/admin/insertbatch}" method="post">
            <table class="table">
                <tr>
                    <th>제목</th>
                    <th>가격</th>
                    <th>저자</th>
                    <th>분류</th>
                </tr>
                <tr th:each="i : ${#numbers.sequence(1,2)}">
                    <td><input type="text" value="1" name="title" /></td>
                    <td><input type="text" value="1" name="price" /></td>
                    <td><input type="text" value="44" name="writer" /></td>
                    <td>
                        <select name="category">
                            <option>A</option>
                            <option>B</option>
                            <option>C</option>
                        </select>
                    </td>
                </tr>
            </table>
                <input type="submit" class=" btn btn-primary" value="도서일괄등록" />
            </form>
    </div>
</body>
</html>