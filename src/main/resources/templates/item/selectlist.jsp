<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이템목록</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>아이템목록</h3>
        <a href="/">홈으로</a>
        <a href="/item/insert">물품등록</a>
        <hr />
      <table class="table">
          <tr>
              <th>번호</th>
              <th>물품번호</th>
              <th>물품명</th>
              <th>물품가격</th>
              <th>물품수량</th>
              <th>등록일</th>
              <th>이미지</th>
              <th>버튼</th>
          </tr>
          <tr th:each="tmp, idx : ${list}">
              <td th:text="${idx.count}"></td>
              <td th:text="${tmp.code}"></td>
              <td th:text="${tmp.name}"></td>
              <td th:text="${tmp.price}"></td>
              <td th:text="${tmp.quantity}"></td>
              <td th:text="${tmp.regdate}"></td>
              <td><img th:src="@{/item/image(code=${tmp.code})}" style="width: 50px;height: 50px;"></td>
              <td>
                <a th:href="@{/item/update(code=${tmp.code})}">수정</a>
                <a th:href="@{/item/delete(code=${tmp.code})}">삭제</a>

                <!-- <form th:action="@{/item/delete}" method="get">
                    <input type="hidden" name="id" th:value="${tmp.code}"/>
                    <input type="submit" value="삭제1" />
                </form> -->
            </td>
          </tr>
      </table>

      <th:block th:each="i : ${#numbers.sequence(1,pages)}">
          <a th:href="@{/item/selectlist(page=${i})}" th:text="${i}"></a>
      </th:block>

    </div>
</body>
</html>