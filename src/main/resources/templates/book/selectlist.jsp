<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>책목록</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>책목록</h3>
        <a href="/">홈으로</a>
        <a th:href="@{/book/insert}">책등록</a>
        <form th:action="@{/admin/selectlist}" method="get">
            <input type="hidden" name="page" value="1" />
            <input type="text" name="text" placeholder="검색어"  />
            <input type="submit" value="검색" />
        </form>
        <hr />
            <table class="table">
                    <th>번호</th>
                    <th>책제목</th>
                    <th>작성자</th>
                    <th>가격</th>
                    <th>등록일</th>
                    <th>버튼</th>
                </tr>
                <tr th:each="tmp: ${list}">
                        <td th:text="${tmp.code}"></td>
                        <td th:text="${tmp.title}"></td>
                        <td th:text="${tmp.writer}"></td>
                        <td th:text="${tmp.price}"></td>
                        <td th:text="${tmp.regdate}"></td>
                        <td><a th:href="@{/book/delete(id=${tmp.code})}">삭제</a>
                            <a th:href="@{/book/update(id=${tmp.code})}">수정</a>
                            <form th:action="@{/book/delete}" method="get">
                                <input type="hidden" name="id" th:value="${tmp.code}"/>
                                <input type="submit" value="삭제1" />
                            </form>
                        </td>
                </tr>
                
            </table>
      

        <th:block th:each="i : ${#numbers.sequence(1, pages)}">
            <a th:href="@{/book/selectlist(page=${i}, text=${param.text})}" th:text="${i}"></a>
        </th:block>
    </div>
</body>
</html>