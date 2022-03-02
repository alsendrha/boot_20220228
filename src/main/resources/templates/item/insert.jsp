<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>물품등록</title>
    <link rel="stylesheet" type="text/css"
        th:href="@{/css/bootstrap.css}" />
        <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
</head>
<body>
    <div style="padding: 20px;">
        <h3>물품등록</h3>
        <a href="/">홈으로</a>
        <hr />
        <form th:action="@{/item/insert}" method="post" enctype="multipart/form-data">
            물품코드 등록시간은 자동으로 입력 <br />
            물품명 : <input type="rext" name="name" /><br />
            물품가격 : <input type="rext" name="price" /><br />
            물품수량 : <input type="rext" name="quantity" /><br />
            대표 이미지 : <input type="file" name="image" /><br /> 
            <input type="submit" class=" btn btn-primary" value="물품등록" />
        </form>
    </div>
</body>
</html>