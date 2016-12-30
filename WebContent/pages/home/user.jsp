<%@page contentType="text/html"%>
    <%@page pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>用户后台</title>
            <link rel="icon" href="/images/icon.jpg" />
            <link rel="stylesheet" href="../../style/css/bootstrap.min.css">
            <link rel="stylesheet" href="../../style/css/sticky-footer.css">
            <link rel="stylesheet" href="../../style/css/summary.css">
            <link rel="stylesheet" href="../../style/css/dashboard.css">
            <link rel="stylesheet" href="../../style/css/jquery-ui.css" />
        </head>

        <body>
            <p class="avatarAddr"></p>
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
					</button>
                        <a class="navbar-brand" href="../../index.jsp">OSS</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <ul id="login" class="nav navbar-nav">
                                <li class="dropdown">
                                    <a href="javascript:;" id="nickname" class="dropdown-toggle" data-toggle="dropdown"></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="javascript:;"> Profile</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;"> Inbox</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;"> Settings</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li class="logout">
                                            <a href=""> Log Out</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <li>
                                <a href="javascript:;">About</a>
                            </li>
                            <li>
                                <a href="javascript:;">Contaxt</a>
                            </li>
                            <li>
                                <a href="javascript:;">Help</a>
                            </li>
                        </ul>
                        <form class="navbar-form navbar-right">
                            <input type="text" class="form-control" placeholder="Search...">
                        </form>
                    </div>
                </div>
            </nav>

            <div class="container-fluid">
                <div class="container" style="padding-bottom: 35px">
                    <div class="row">
                        <div class="col-sm-3 col-md-2 sidebar">
                            <ul class="nav nav-sidebar">
                                <li>
                                    <a href="javascript:;" class="active orderManage">订单管理</a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="userInfoManage">更改个人信息</a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="receiverManage">收获地址管理</a>
                                </li>
                                <li>
                                    <a href="javascript:;" class="shop" target="_blank">我的店铺</a>
                                </li>
                            </ul>
                        </div>

                        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
                            <ul class="nav nav-tabs ">
                                <li class="active"><a href="javascript:;">全部订单</a></li>
                                <li><a href="javascript:;">代付款</a></li>
                                <li><a href="javascript:;">待收货</a></li>
                                <li><a href="javascript:;">待评价</a></li>
                                <li><a href="javascript:;">已完成</a></li>
                            </ul>
                            <div class="row main">
                                <div class="page-header">
                                    <h3 class="title">订单简介</h3>
                                    <div class="row titleHead">
                                        <hr>
                                        <div class="col-xs-4 col-sm-4 col-md-4">
                                            <p>订单号码</p>
                                        </div>
                                        <div class="col-xs-4 col-sm-4 col-md-4">
                                            <p>订单状态</p>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <ul class="pager">
                                <li class="previous">
                                    <a href="javascript:;">Previous</a>
                                </li>
                                <li class="pageNum">1</li>
                                <li class="next">
                                    <a href="javascript:;">Next</a>
                                </li>
                            </ul>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="footer" style="text-align: center; padding-left: 233px; left : 0px">
                <div class="container">
                    <p class="text-muted">
                        <a href="http://kaidian.ele.me">我要开店</a><span> | </span>
                        <a href="contact.html">联系我们</a><span> | </span>
                        <a href="agreement.html">服务条款和协议</a><span> | </span>
                        <a href="sitemaps.html">站点地图</a><span> | </span>
                        <a href="http://jobs.ele.me">加入我们</a> <br> 增值电信业务许可证：
                        <a class=-link-zero " target="_blank " rel="nofollow "
					href="http://www.shca.gov.cn ">沪B2-20150033</a><span> | </span> <a
					target="_blank " rel="nofollow " href="http://www.miibeian.gov.cn ">沪ICP备
					09007032</a> <span> | </span> <a target="_blank " rel="nofollow "
					href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120305173227823 ">上海工商行政管理</a>
				<br> Copyright &copy;2008-2015 ele.me, All Rights Reserved.
			</p>
		</div>
		</footer>
	</div>

	<script src="../../style/js/jquery.min.js "></script>
	<script src="../../style/js/bootstrap.min.js "></script>
	<script src="../../style/js/jquery-ui.min.js " ></script>
	<script src="../../style/js/userBackground.js "></script>
	<script>
		$("#nickname ").html("${sessionScope.nickname}<b class='caret'></b>"); var shophref = ""; if("${sessionScope.shopHasOpend}" == "true"){ shophref = "seller.jsp"; }else{ shophref = "../"; } $(".shop").attr("href",shophref); $(".avatarAddr").html("${sessionScope.userAvatarAddr}");

                            </script>
        </body>

        </html>