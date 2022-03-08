<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물목록2</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>게시물목록2</h3>
        <a href="/">홈으로</a>
        <hr />
        <h4>1. 정확하게 일치하는 항목 가져오기</h4>
        <form th:action="@{/board/selectfind}" method="get">
            <select name="type">
                <option value="title" >제목</option>
                <option value="writer" >작성자</option>
                <option value="hit" >조회수</option>
            </select>
            <input type="text" name="text" placeholder="검색어" />
            <input type="submit" value="검색" />
        </form>
        <hr />
        <h4>2. 조회수가 이상, 미만 조회하기</h4>
        <form th:action="@{/board/selectfind}" method="get">
            <select name="type1">
                <option value="1" >이상</option>
                <option value="2" >미만</option>
            </select>

            <input type="text" name="hit" placeholder="검색어" />
            <input type="submit" value="검색" />
        </form>
        <hr />
        <h4>3. 포함, 포함하지 않음</h4>
        <form th:action="@{/board/selectfind}" method="get">
            <select name="type2">
                <option value="1" >포함</option>
                <option value="2" >포함하지않음</option>
            </select>
            <input type="text" name="no" placeholder="글번호1" />
            <input type="text" name="no" placeholder="글번호2" />
            <input type="text" name="no" placeholder="글번호3" />
            <input type="submit" value="검색" />
        </form>
        <hr />
            <table class="table">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>등록일</th>
                </tr>
                <tr th:each="tmp: ${list}">
                        <td th:text="${tmp.no}"></td>
                        <td th:text="${tmp.title}"></td>
                        <td th:text="${tmp.writer}"></td>
                        <td th:text="${tmp.hit}"></td>
                        <td th:text="${tmp.regdate}"></td>
                </tr>
            </table>
    </div>
</body>
</html>