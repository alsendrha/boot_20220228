<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원목록</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>회원목록</h3>
        <hr />
        <a href="/">홈으로</a>
        <a href="/member/insert">회원가입</a>

      <table class="table">
          <tr>
              <th>아이디</th>
              <th>이름</th>
              <th>나이</th>
              <th>버튼</th>
          </tr>
          <tr th:each="tmp, idx : ${list}">
              <td th:text="${tmp.id}"></td>
              <td th:text="${tmp.name}"></td>
              <td th:text="${tmp.age}"></td>
              <td>
                <a href="#">수정</a>
                <a th:href="@{/member/delete(id=${tmp.id})}">삭제</a>

                <form th:action="@{/member/delete}" method="get">
                    <input type="hidden" name="id" th:value="${tmp.id}"/>
                    <input type="submit" value="삭제1" />
                </form>
                
                
            </td>
             
          </tr>
      </table>
    </div>
</body>
</html>