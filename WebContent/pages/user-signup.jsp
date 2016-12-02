<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户注册</title>
        <link rel="icon" href="/images/icon.jpg" />
        <link rel="stylesheet" href="../style/css/signup.css">
        <link rel="stylesheet" href="../style/css/bootstrap.min.css">
        <link rel="stylesheet" href="../style/css/sticky-footer.css">

    </head>

    <body>
        <div class="container">

            <div class="row">
                <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                    <form role="form" action="../addNewUser.action" method="post">

                        <h2>新用户 ? 请注册！</h2>
                        <hr class="colorgraph">
                        <div class="form-group has-info">
                            <input type="text" name="username" id="username" class="form-control input-lg" placeholder="* 用户名" tabindex="1">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group has-info">
                            <input type="text" name="phone" id="phone" class="form-control input-lg" placeholder="* 手机号" tabindex="2">
                            <span class="help-block"></span>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group has-info">
                                    <input type="password" name="password" id="password" class="form-control input-lg" placeholder="* 密码" tabindex="3">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group has-info">
                                    <input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" placeholder="* 确认密码" tabindex="4">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>

                        <hr class="colorgraph">
                        <div class="row">
                            <div class="col-xs-12 col-md-6"><input type="submit" id="submit" value="注册" class="btn btn-primary btn-block btn-lg" tabindex="5"></div>
                            <div class="col-xs-12 col-md-6">
                                <a href="login/user.jsp" class="btn btn-success btn-block btn-lg">登录</a>
                            </div>
                        </div>

                    </form>
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

  <script src="../style/js/jquery.min.js "></script>
  <script src="../style/js/bootstrap.min.js "></script>
  <script src="../style/js/signup.js "></script>
  <script src="../style/js/userSignup.js "></script>
</body>
</html>