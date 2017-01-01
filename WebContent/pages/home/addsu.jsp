<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OSS</title>
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="../../style/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../style/css/sticky-footer.css">
        <link rel="icon" href="F:/groupnineImage/icon-admin.png" />
        <link rel="stylesheet" href="../../style/css/font-awesome.min.css">
        <link rel="stylesheet" href="../../style/css/form-elements.css">
        <link rel="stylesheet" href="../../style/css/style.css">
    </head>

    <body>
        
        

            <button id="gotoadduser">添加用户</button>

        
   
            <button id="gotoaddshop">添加店铺</button>
    

        <div id="adduser" style="display: none;">
            <!-- Top content -->
            <div class="top-content">
                <div class="inner-bg">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3 form-box">
                                <div class="form-top">
                                    <div class="form-top-left">
                                        <h2>Add Users</h2>

                                    </div>
                                </div>
                                <div class="form-bottom">
                                    <form role="form" action="../../AdminLogin.action" method="post" class="login-form">
                                        <div class="form-group">

                                            <input type="text" name="form-userId" placeholder="UserId" class="form-AdminId form-control" id="form-username">
                                        </div>

                                        <div class="form-group">

                                            <input type="text" name="form-userphone" placeholder="PhoneNumber" class="form-AdminId form-control" id="form-username">
                                        </div>

                                        <div class="form-group">

                                            <input type="password" name="form-password" placeholder="Password" class="form-password form-control" id="form-password">
                                        </div>
                                        <button type="submit" class="btn">Finish!</button>
                                        <p class="help-block"></p>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
        <div id="addshop" style="display: none;">

            <!-- Top content -->
            <div class="top-content">
                <div class="inner-bg">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3 form-box">
                                <div class="form-top">
                                    <div class="form-top-left">
                                        <h2>Add Shops</h2>

                                    </div>
                                </div>
                                <div class="form-bottom">
                                    <form role="form" action="../../AdminLogin.action" method="post" class="login-form">
                                        <div class="form-group">

                                            <input type="text" name="form-userId" placeholder="ShopId" class="form-AdminId form-control" id="form-username">
                                        </div>

                                        <div class="form-group">

                                            <input type="text" name="form-userphone" placeholder="PhoneNumber" class="form-AdminId form-control" id="form-username">
                                        </div>

                                        <div class="form-group">

                                            <input type="password" name="form-password" placeholder="Password" class="form-password form-control" id="form-password">
                                        </div>
                                        <button type="submit" class="btn">Finish!</button>
                                        <p class="help-block"></p>
                                    </form>
                                </div>
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
        <script src="../../style/js/addsu.js "></script>
        <script src="../../style/js/addsu.js"></script>
          

    </body>

</html>