<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../../style/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../style/css/bootstrap-theme.min.css">
    </head>

    <body>

        <div class="container">

            <div class="page-header">
                <h1>OSS</h1>
                <h2>卖家中心</h2>
            </div>

            <hr class="colorgraph">

            <div class="row" id="shopSignup1" style="display: inline">
                <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                    <div class="hero-unit">
                        <h2>申请开店</h2><br />
                        <h3>开店须知：</h3><br />
                        <p>1.申请店铺完全免费;</p><br />
                        <p>2.一个身份只能开一家店;</p><br />
                        <p>3.开店后店铺无法注销; </p><br />
                        <p>4.申请到正式开通预计需1~3个工作日。</p><br />
                        <button class="btn btn-primary btn-large" id="Signup">创建个人店铺 </button>
                    </div>
                </div>
            </div>

            <div class="row" id="shopSignup2" style="display: none">
                <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                    <form role="form" action="${pageContext.request.contextPath }/registerShop.action" method="post">

                        <h2>店铺申请</h2>

                        <hr class="colorgraph">

                        <div class="form-group has-info">
                            <input type="text" name="shopName" id="shopName" class="form-control input-lg" placeholder="店铺名称" tabindex="5">
                            <p class="help-block"></p>
                        </div>

                        <div class="form-group has-info">
                            <input type="text" name="phone" id="phone" class="form-control input-lg" placeholder="联系方式" tabindex="2">
                            <p class="help-block"></p>
                        </div>

                        <div class="form-group has-info">
                            <input type="text" name="address" id="address" class="form-control input-lg" placeholder="店铺地址" tabindex="6">
                            <p class="help-block"></p>
                        </div>

                        <div class="form-group has-info">
                            <textarea class="form-control" name="shopDescribe" id="shopDescribe" placeholder="店铺描述" cols="30" rows="10"></textarea>
                        </div>

                        </hr class="colorgraph">

                        <div class="row">
                            <div class="col-xs-12 col-md-6" id="Goback">
                                <a href="" class="btn btn-success btn-block btn-lg">返回</a>
                            </div>
                            <div class="col-xs-12 col-md-6" id="submit"><input type="submit" value="注册" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
                        </div>

                    </form>
                </div>
            </div>

            <div class="row" id="shopSignup3" style="display: none">
                <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                    <br /><br /><br />
                    <h3>您的申请已提交，请耐心等候</h3>
                </div>
            </div>

        </div>

        <p id="shopStatus" style='display:none'></p>

        <script src="../../style/js/jquery.min.js"></script>
        <script src="../../style/js/bootstrap.min.js"></script>
        <script src="../../style/js/sellerSignup.js"></script>
        <script type="text/javascript">
            var login = "${sessionScope.shopHasOpend}";
            if(login.match("reviewing") != null) {
                $("#shopStatus").html("${sessionScope.shopHasOpend}");
            }
        </script>

    </body>

</html>
