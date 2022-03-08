<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서목록</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>도서목록</h3>
        <hr />
        <a href="/">홈으로</a>
        <a th:href="@{/admin/insertbatch}">일괄등록</a>
        
        <form th:action="@{/admin/selectlist}" method="get">
            <input type="hidden" name="page" value="1" />
            <input type="text" name="text" placeholder="검색어"  />
            <input type="submit" value="검색" />
        </form>
        
        <hr />
        
        <form th:action="@{/admin/action}" method="post">
            <input type="submit" name="btn" value="일괄삭제" />
            <input type="submit" name="btn" value="일괄수정" />
            
            <hr />
            
            <table class="table table-sm">
                <tr>
                    <th>체크</th>
                    <th>코드</th>
                    <th>도서명</th>
                    <th>도서가격</th>
                    <th>저자</th>
                    <th>분류</th>
                    <th>등록일</th>
                    <th>버튼</th>
                </tr>
                <tr th:each="tmp : ${list}">
                    <td><input type="checkbox" name="chk" th:value="${tmp.code}" /> </td>
                    <td th:text="${tmp.code}"></td>
                    <td th:text="${tmp.title}"></td>
                    <td th:text="${tmp.price}"></td>
                    <td th:text="${tmp.writer}"></td>
                    <td th:text="${tmp.category}"></td>
                    <td th:text="${tmp.regdate}"></td>
                    <td><a th:href="@{/admin/delete(code=${tmp.code})}">삭제</a></td>
            </tr>
            </table>
        </form>
        
        <th:block th:each="i : ${#numbers.sequence(1,pages)}">
            <a th:href="@{/admin/selectlist(page=${i}, text=${param.text})}" th:text="${i}"></a>
        </th:block>
        
    </div>
</body>
</html>