<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일괄수정</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>도서 일괄 수정</h3>
        <hr />
        <a th:href="@{/admin/selectlist}">뒤로기기</a>
        <form th:action="@{/admin/updatebatch}" method="post">
            <table class="table table-sm">
                <tr>
                    <th>코드</th>
                    <th>제목</th>
                    <th>가격</th>
                    <th>저자</th>
                    <th>분류</th>
                    <th>날짜</th>
                </tr>
                <tr th:each="tmp : ${list}">
                    <td><input type="text" th:value="${tmp.code}" name="code" readonly /></td>
                    <td><input type="text" th:value="${tmp.title}" name="title" /></td>
                    <td><input type="text" th:value="${tmp.price}" name="price" /></td>
                    <td><input type="text" th:value="${tmp.writer}" name="writer" /></td>
                    
                    <td>
                        <p th:text="${tmp.category}"></p>
                        <select name="category">
                        <option th:selected="${#strings.equals(tmp.category, 'A')}">A</option>
                        <option th:selected="${#strings.equals(tmp.category, 'B')}">B</option>
                        <option th:selected="${#strings.equals(tmp.category, 'C')}">C</option>

                      
                        <!-- <option th:selected="${tmp.category == 'A'}">A</option>
                        <option th:selected="${tmp.category == 'B'}">B</option>
                        <option th:selected="${tmp.category == 'C'}">C</option> -->
                    </select>
                    </td>
                    
                    <td th:text="${tmp.regdate}"></td><br />
                </tr>
            </table>
            <input type="submit" value="수정하기" />
        </form>

       
    </div>
</body>
</html>