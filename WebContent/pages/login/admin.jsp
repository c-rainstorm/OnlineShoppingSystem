  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>OSS</title>
    <link rel="stylesheet"  href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet"  href="../../style/css/bootstrap.min.css" >
    <link rel="stylesheet"  href="../../style/css/sticky-footer.css" >
  
    <link rel="stylesheet"  href="../../style/css/font-awesome.min.css">
    <link rel="stylesheet"  href="../../style/css/form-elements.css">
    <link rel="stylesheet"  href="../../style/css/style.css">
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
                  <h3>Login to our site</h3>
                  <p>Enter your AdminId and password to log on:</p>
                </div>
              </div>
              <div class="form-bottom">
                <form role="form" action="../../AdminLogin.action" method="post" class="login-form">
                  <div class="form-group">
                    
                    <input type="text" name="form-AdminId" placeholder="AdminId..." class="form-AdminId form-control" id="form-AdminId">
                  </div>
                  <div class="form-group">
                    
                    <input type="password" name="form-password" placeholder="Password..." class="form-password form-control" id="form-password">
                  </div>
                  <button type="submit" class="btn">Sign in!</button>
                   <p class="help-block"></p>
                </form>
              </div>
            </div>
          </div>
          
        </div>
      </div>

    </div>

  <script src="../../style/js/jquery.min.js "></script>
  <script src="../../style/js/bootstrap.min.js "></script>
  <script src="../../style/js/jquery.backstretch.min.js "></script>
  <script src="../../style/js/scripts.js "></script>
  <script src="../../style/js/signup.js "></script>
   <script>
    var status = ".concat(${sessionScope.adminLoginStatus})";
    if( status == "ture" ){
    	 window.location.href="../home/admin.jsp";
      }
   
    if( status == "false" ){
      $("form").addClass("has-error");
      $(".help-block").html("用户名或密码错误！");
    }
  </script>
</body>
</html>