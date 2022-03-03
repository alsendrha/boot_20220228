<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품수정</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>물품수정</h3>
        <hr />
        <form th:action="@{/item/update}" method="post" enctype="multipart/form-data">
            물품번호 : <input type="text" th:value="${item.code}" name="code" readonly><br />
            물품명 : <input type="text" th:value="${item.name}" name="name"  ><br />
            물품가격 : <input type="text" th:value="${item.price}" name="price" ><br />
            물품수량 : <input type="text" th:value="${item.quantity}" name="quantity" ><br />
            이미지 : <img th:src="@{/item/image(code=${item.code})}" style="width: 100px;" />
            <input type="file" name="image" /><br />
            <hr />
            <input type="submit" class="btn btn-primary" value="수정하기" />
            <a th:href="@{/item/selectlist}" class="btn btn-primary">물품목록</a>
        </form>
    </div>
</body>
</html>