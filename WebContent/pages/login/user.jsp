<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户登录</title>
        <link rel="icon" href="/images/icon.jpg" />
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="../../style/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../style/css/sticky-footer.css">
        <link rel="stylesheet" href="../../style/css/font-awesome.min.css">
        <link rel="stylesheet" href="../../style/css/login-form-elements-admin.css">
        <link rel="stylesheet" href="../../style/css/login-form-elements-user.css">
        <link rel="stylesheet" href="../../style/css/login-style-user.css" />
    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                            <div class="form-top">
                                <div class="form-top-left">
                                    <h3>登录到我们的网站</h3>
                                    <p>请输入你的用户名或手机号和密码：</p>
                                </div>
                                <div class="form-top-right">
                                    <i class="fa fa-key"></i>
                                </div>
                            </div>
                            <div class="form-bottom">
                                <form role="form" class="login-form" action="../../checkUserLogin.action" method="post">
                                    <div class="form-group">
                                        <label class="sr-only" for="form-phone">Phone number</label>
                                        <input type="text" name="username" placeholder="用户名 / 手机号..." class="form-username form-control" id="username">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-password">Password</label>
                                        <input type="password" name="password" placeholder="密码..." class="form-password form-control" id="password">
                                    </div>
                                    <button type="submit" class="btn">登录</button>
                                    <a href="../user-signup.jsp" class="btn btn-primary btn-block btn-lg" style="margin-top: 10px;">注册</a>
                                    <p class="help-block"></p>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 social-login">

                            <div class="social-login-buttons">
                                <a class="btn btn-link-1 btn-link-1-facebook" href="#">
                                    <i class="fa fa-facebook"></i> Facebook
                                </a>
                                <a class="btn btn-link-1 btn-link-1-twitter" href="#">
                                    <i class="fa fa-twitter"></i> Twitter
                                </a>
                                <a class="btn btn-link-1 btn-link-1-google-plus" href="#">
                                    <i class="fa fa-google-plus"></i> Google Plus
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <footer class="footer" style="text-align: center">
            <div class="container">
                <p class="text-muted">
                    <a href="http://kaidian.ele.me">我要开店</a><span> | </span>
                    <a href="contact.html">联系我们</a><span> | </span>
                    <a href="agreement.html">服务条款和协议</a><span> | </span>
                    <a href="sitemaps.html">站点地图</a><span> | </span>
                    <a href="http://jobs.ele.me">加入我们</a>
                    <br> 增值电信业务许可证：
                    <a class=-link-zero " target="_blank " rel="nofollow " href="http://www.shca.gov.cn ">沪B2-20150033</a><span> | </span>
        <a target="_blank " rel="nofollow " href="http://www.miibeian.gov.cn ">沪ICP备 09007032</a> <span> | </span>
        <a target="_blank " rel="nofollow " href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120305173227823 ">上海工商行政管理</a>
        <br> Copyright &copy;2008-2015 ele.me, All Rights Reserved.
      </p>
    </div>
  </footer>

  <script src="../../style/js/jquery.min.js "></script>
  <script src="../../style/js/bootstrap.min.js "></script>
  <script src="../../style/js/jquery.backstretch.min.js "></script>
  <script src="../../style/js/login-scripts-user.js "></script>
   <script>
    var status = "".concat(${sessionScope.userLoginStatus});
    if( status == "false" ){
      $("form ").addClass("has-error");
      $(".help-block ").html("手机号或密码错误！ ");
    }
  </script>
</body>
</html>