<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물수정</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>게시물수정</h3>
        <hr />
        <form th:action="@{/board/update}" method="post">
            <table>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성자</th>
                </tr>
                <tr>
                    <td><input type="text" th:value="${board.no}" name="no" readonly></td>
                    <td><input type="text" th:value="${board.title}" name="title"  ></td>
                    <td><input type="text" th:value="${board.content}" name="content" ></td>
                    <td><input type="text" th:value="${board.writer}" name="writer" ></td>
                </tr>    
            </table>
            <hr />
            <input type="submit" value="수정하기" />
        </form>
    </div>
</body>
</html>