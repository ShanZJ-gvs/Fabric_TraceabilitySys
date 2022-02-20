<%--
  Created by IntelliJ IDEA.
  User: shanzj
  Date: 2022/1/25
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>登录</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="assets/css/styles.css">


</head>

<body style="background-image: url('assets/img/avatars/img-8950fe813f67bd95f918e80b881a312c.jpg');background-repeat: no-repeat;background-size: cover;background-position: right;">
<div class="row m-auto register-form">
    <div class="col-md-8 offset-md-2">
        <form class="custom-form"  action="${pageContext.request.contextPath}/checkUserPwd" method="post">
            <h1>登 &nbsp;录</h1>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label" for="email-input-field">用户名</label>
                </div>
                <div class="col-sm-6 input-column">
                    <input class="form-control" type="text" id="username" name="username">
                    <span id="userInfo"></span>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label" for="pawssword-input-field">密码 </label>
                </div>
                <div class="col-sm-6 input-column">
                    <input class="form-control" type="password" id="pwd" name="pwd">
                    <span id="pwdInfo"></span>
                </div>
            </div>
            <%--验证码  未写验证码 只是一个空壳--%>
            <%--<div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label class="col-form-label" for="email-input-field">验证码</label>
                </div>
                <div class="col-sm-6 input-column">
                    <input class="form-control" type="text">
                </div>
            </div>--%>
            <span id="info"></span>
            <button id="loginButton" class="btn btn-primary border rounded submit-button" type="button" onclick="checkUserPwd()">登录</button>
            <a class="btn btn-primary border rounded submit-button" role="button" href="${pageContext.request.contextPath}/toregister">注册</a>
        </form>


    </div>
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script>
    <%--function exitName() {--%>
    <%--    $.post({--%>
    <%--        url:"${pageContext.request.contextPath}/checkUserPwd",--%>
    <%--        data:{"name":$("#name").val()},--%>
    <%--        success:function (data) {--%>
    <%--            console.log(data);--%>

    <%--            if (data.toString()==="UserNameOK"){--%>
    <%--                $("#userInfo").css("color","green");--%>

    <%--            }--%>
    <%--            $("#userInfo").html(data);--%>
    <%--        }--%>

    <%--    })--%>

    <%--}--%>

    <%--function exitPwd() {--%>
    <%--    $.post({--%>
    <%--        url:"${pageContext.request.contextPath}/checkUserPwd",--%>
    <%--        data:{"pwd":$("#pwd").val(),"name":$("#name").val()},--%>
    <%--        success:function (data) {--%>
    <%--            console.log(data);--%>
    <%--            if (data.toString()=="UserPwdOK"){--%>
    <%--                $("#pwdInfo").css("color","green");--%>

    <%--            }--%>
    <%--            $("#pwdInfo").html(data);--%>
    <%--        }--%>
    <%--    })--%>
    <%--}--%>

    function checkUserPwd(){
        $.post({
            url:"${pageContext.request.contextPath}/checkUserPwd",
            data:{"pwd":$("#pwd").val(),"username":$("#username").val()},
            success:function (data) {

                if (data.toString()=="UserPwdError"){
                    $("#info").css("color","red");
                    $("#info").html("用户名或密码错误");
                    console.log(data);
                    //alert(222);
                };
                if (data.toString()=="UserPwdOK"){
                    // document.getElementById("loginButton").setAttribute("type","submit");
                    $("#info").html("");
                    console.log(data);
                    //alert(111);
                    $(window).attr("location","${pageContext.request.contextPath}/tohome");
                };
            }
        })

    }


</script>
</body>

</html>
