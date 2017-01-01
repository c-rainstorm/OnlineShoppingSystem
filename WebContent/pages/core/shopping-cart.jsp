<%@page contentType="text/html"%>
    <%@page pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <title>我的购物车</title>
            <link rel="icon" href="/images/icon.jpg" />
            <link rel="stylesheet" href="../../style/css/bootstrap.min.css" />
            <link rel="stylesheet" href="../../style/css/sticky-footer.css" />
        </head>

        <body>
            <nav class="navbar navbar-default">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                        <a class="navbar-brand" href="">OSS</a>
                    </div>
                    <div id="navbar" class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <a href="">Home</a>
                            </li>
                            <li>
                                <a href="#about">关于</a>
                            </li>
                            <li>
                                <a href="#contact">联系</a>
                            </li>
                        </ul>
                        <ul id="unlogin" class="nav navbar-nav navbar-right">
                            <li>
                                <a href="../login/user.jsp" target="_blank">登录</a>
                            </li>
                            <li>
                                <a href="../user-signup.jsp" target="_blank">注册</a>
                            </li>
                        </ul>
                        <ul id="login" class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <img alt="avatar" style="height: 40px; width: 40px; margin-top: 5px;" src="/images/avatars/default.jpg" class="img-circle dropdown-toggle avatar" data-toggle="dropdown" />
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="glyphicon glyphicon-user" href="../home/user.jsp" target="_blank"> 个人中心</a>
                                    </li>
                                    <li>
                                        <a class="glyphicon glyphicon-shopping-cart" href=""> 我的购物车</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a class="glyphicon glyphicon-log-out logout" href="#"> 退出</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </nav>

            <!--
        	作者：rainstorm.me@outlook.com
        	时间：2016-12-02
        	描述：搜索框一行
        -->
            <div class="row" style="margin:0px;">
                <div class="col-md-2 col-md-offset-2">
                    <img class="logo" alt="a logo" src="/images/icon.jpg" style="height: 50px; width:auto;" />
                </div>
                <div class="col-md-4">
                    <div class="input-group" style="margin-top: 11px;">
                        <input type="text" id="searchInput" class="form-control" placeholder="笔记本电脑">
                        <span class="input-group-btn">
                        <a class="btn btn-danger search" href="#" target="_blank"><span class="glyphicon glyphicon-search"></span></a>
                        </span>
                    </div>
                </div>
            </div>
            <hr />

            <div class="container" style="padding-left: 50px; padding-right: 50px;">
                <div class="title row">

                    <div class="col-lg-1 col-md-1 col-lg-offset-1 col-md-offset-1">
                        <input type="checkbox" align="right" class="checkAll" /><span align="right"> 全选</span>
                    </div>
                    <div class="col-lg-2 col-md-2">
                        <span>商品</span>
                    </div>
                    <div class="col-lg-2 col-md-2">
                    </div>
                    <div class="col-lg-1 col-md-1">
                        <span>单价</span>
                    </div>
                    <div class="col-lg-2 col-md-2" align="center">
                        <span>数量</span>
                    </div>
                    <div class="col-lg-1 col-md-1">
                        <span>小计</span>
                    </div>
                    <div class="col-lg-1 col-md-1">
                        <span>操作</span>
                    </div>
                </div>
                <hr />
                <div class="root">
                </div>
                <hr />
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-lg-offset-1 col-md-offset-1" style="padding-top: 10px;">
                        <input type="checkbox" align="right" class="checkAll" /><span align="right"> 全选</span>
                    </div>
                    <div class="col-lg-2 col-md-2" style="padding-top: 10px;">
                        <p><a href="javascript:;" class="deleteChecked">删除选中商品</a></p>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <p align="right">总价：<span style="color: red; font-weight:bold; font-size:2em;">￥<span class="total">0</span></span>
                        </p>
                    </div>
                    <div class="col-lg-1 col-md-1 ">
                        <a href="confirmOrder.jsp" class="btn btn-danger checkout">去结算</a>
                    </div>
                </div>
            </div>
            <hr />
            <div class="row" style="margin:50px 0px 0px 0px;">
                <footer class="footer" style="text-align: center">
                    <div class="container">
                        <p class="text-muted">
                            <a href="http://kaidian.ele.me">我要开店</a><span> | </span>
                            <a href="contact.html">联系我们</a><span> | </span>
                            <a href="agreement.html">服务条款和协议</a><span> | </span>
                            <a href="sitemaps.html">站点地图</a><span> | </span>
                            <a href="http://jobs.ele.me">加入我们</a>
                            <br> 增值电信业务许可证：
                            <a class="-link-zero " target="_blank " rel="nofollow " href="http://www.shca.gov.cn ">沪B2-20150033</a><span> | </span>
                            <a target="_blank " rel="nofollow " href="http://www.miibeian.gov.cn ">沪ICP备 09007032</a> <span> | </span>
                            <a target="_blank " rel="nofollow " href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120305173227823 ">上海工商行政管理</a>
                            <br> Copyright &copy;2008-2015 ele.me, All Rights Reserved.
                        </p>
                    </div>
                </footer>
            </div>


            <script type="text/javascript " src="../../style/js/jquery.min.js "></script>
            <script type="text/javascript " src="../../style/js/bootstrap.min.js "></script>
            <script type="text/javascript " src="../../style/js/public.js "></script>
            <script type="text/javascript " src="../../style/js/jquery.cookie.js"></script>
            <script type="text/javascript " src="../../style/js/shopping-cart.js "></script>
            <script type="text/javascript ">
                var userLoginStatus = "${sessionScope.userLoginStatus} ";

                if (userLoginStatus.match("true") != null) {
                    $("#unlogin ").hide();
                    $(".avatar ").attr("src ", "${sessionScope.userAvatarAddr} ")
                    $("#login ").show();
                } else {
                    $("#login ").hide();
                    $("#unlogin ").show();
                }
            </script>
        </body>

        </html>