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
<%--    <meta charset="utf-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">--%>
<%--    <title>登录</title>--%>
<%--    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">--%>
<%--    <link rel="stylesheet" href="assets/css/Pretty-Registration-Form.css">--%>
<%--    <link rel="stylesheet" href="assets/css/styles.css">--%>

    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <title>登录 </title>


    <link rel="stylesheet" href="assets/css/style.css" />

</head>


<body>

<!-- Top Bar  -->
<section class="top-bar">

    <!-- Brand -->
    <span class="brand">茶叶溯源管理后台</span>

    <nav class="flex items-center ml-auto">

        <!-- Dark Mode -->
        <label class="switch switch_outlined" data-toggle="tooltip" data-tippy-content="Toggle Dark Mode">
            <input id="darkModeToggler" type="checkbox">
            <span></span>
        </label>

        <!-- Fullscreen -->
        <button type="button"
                class="hidden lg:inline-block btn-link ml-5 text-2xl leading-none la la-expand-arrows-alt"
                data-toggle="tooltip" data-tippy-content="Fullscreen" id="fullScreenToggler"></button>

    </nav>
</section>

<div id="root" class="container flex items-center justify-center mt-20 py-10">
    <div class="w-full md:w-1/2 xl:w-1/3">
        <div class="mx-5 md:mx-10">
            <h2 class="uppercase">很高兴见到你!</h2>

        </div>
        <form class="card mt-5 p-5 md:p-10" action="/Fabric_TraceabilitySys_war_exploded/login" method="post">
            <div class="mb-5">
                <label class="label block mb-2" for="email">账号</label>
                <!-- <input type="text" class="form-control" id="email" name="loginUser" placeholder="邮箱">-->
                <input type="text" class="form-control" id="email" name="loginUser" >
            </div>
            <div class="mb-5">
                <label class="label block mb-2" for="password">密码</label>
                <label class="form-control-addon-within">
                    <input type="password" class="form-control border-none" id="password" name="loginPwd" value="">
                    <span class="flex items-center pr-4"><button type="button"
                                                                 class="btn btn-link text-gray-600 dark:text-gray-600 la la-eye text-xl leading-none"
                                                                 data-toggle="password-visibility"></button></span>
                </label>
            </div>

            <div class="flex items-center">
                <a href="auth-forgot-password.html" class="text-sm uppercase">忘记密码?</a>
                <span>&nbsp&nbsp&nbsp</span>
                <a :href="tosearch" class="text-sm uppercase">消费者查询</a>
                <button type="submit" class="btn btn_primary ml-auto uppercase">登录</button>
            </div>
        </form>
    </div>
</div>

<!-- Scripts -->
<script src="assets/js/vendor.js"></script>
<script src="assets/js/script.js"></script>

<!-- vue -->
<script src="assets/js/vue.js"></script>
<script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>

<script>
    Vue.config.productionTip = false
    const vm = new Vue({
        el: '#root',
        data:{
        },
        computed:{
            tosearch:{
                get(){
                    return "./tosearch"
                }
            }

        }


    })
</script>

</body>

</html>
