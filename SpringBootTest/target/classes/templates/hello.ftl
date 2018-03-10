<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="/css/index.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css"/>
    <script src="/js/jquery-1.12.3.min.js"></script>
    <script src="/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <h2 th:text="${msg}"></h2>
    <h2 th:text="${msg}"></h2>
    <h2 th:text="${msg}"></h2>
    <button id="send">添加</button>
    <button id="delete">删除</button>
</div>
<script type="text/javascript">
    $(function() {

        function add(e){
            var person = {name:"老宋",address:"邯郸",age:15};
            $.ajax({ url: "http://localhost:8080/thymeleaf/person",type:"post",data:person,context: document.body, success: function(){
                alert("添加成功");
              }});
        }
        function delete(e){
            $.ajax({
                url:"http://localhost:8080/thymeleaf/person"
            })
        }
        $('#send').bind('click', function() {
            add();
        });
    })



</script>
</body>
</html>