<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>OSS</title>
        <link href="../../style/css/bootstrap.min.css" rel="stylesheet">
        <link href="../../style/css/HomeAdmin.css" rel="stylesheet">

    </head>

    <body>
        <!--下面是顶部导航栏的代码-->
        <nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">

                    <a class="navbar-brand" href="#">OSS</a>
                </div>

            </div>
        </nav>

        <!—自适应布局-->
        <div class="container-fluid">
            <div class="row">
                <!—左侧导航栏-->
                <div class="col-sm-3 col-md-2 sidebar">

                    <ul class="nav nav-sidebar">
                        <li>
                            <a href="#" id="left_transaction">事务处理</a>
                        </li>
                        <li>
                            <a href="#" id="left_usermanage">用户管理</a>
                        </li>
                        <li>
                            <a href="#" id="left_shopmanage">店铺管理</a>
                        </li>
                    </ul>
                </div>

                <!—右侧管理控制台-->
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">管理控制台</h1>

                    <div class="row" id="right_transaction" style="display: inline;">

                        <div class="col-md-6">
                            <p>

                                <button type="button" class="btn btn-lg btn-primary" id="todotransaction">未处理事务</button>
                                <button type="button" class="btn btn-lg btn-success" id="donetransaction">已处理事务</button>
                                <button type="button" class="btn btn-lg btn-info" id="searchTransaction">查找事务</button>

                            </p>

                            <!--显示未处理事务-->

                            <div class="panel panel-primary" id="todolist" style="display: inline;">
                                <div class="panel-heading">
                                    <h3 class="panel-title">未处理事务</h3>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事务编号</th>
                                                <th>事务描述</th>
                                                <th>提交时间</th>
                                                <th>处理时间</th>
                                            </tr>
                                        </thead>
                                        <tbody id="todo_list">

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <!--显示已处理事务-->

                            <div class="panel panel-primary" id="donelist" style="display: none;">
                                <div class="panel-heading">
                                    <h3 class="panel-title">已处理事务</h3>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事务编号</th>
                                                <th>事务描述</th>
                                                <th>提交时间</th>
                                                <th>处理时间</th>
                                            </tr>
                                        </thead>
                                        <tbody id="done_list">

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <!--显示查找事务-->

                            <div class="panel-primary" id="search" style="display: none;">
                                <form role="form" id="search_tran" action="../../searchTransaction.action" method="post">

                                    <p><input type="text" name="searchtran" placeholder="comment或id" class="searchtran " id="searchtran">

                                        <button type="submit" class="btn btn-lg btn-info btn-link">查找</a></button></p>
                                </form>

                                <div class="panel-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事务编号</th>
                                                <th>事务描述</th>
                                                <th>提交时间</th>
                                                <th>处理时间</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tran_list">

                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>

                    </div>

                    <div class="row" id="right_usermanage" style="display: none;">
                        <div class="col-md-6">
                            <a href="../user-signup.jsp"><button type="button" class="btn btn-lg btn-primary">添加用户</button></a>
                            <button type="button" class="btn btn-lg btn-success">查找用户</button>

                            <div id="aboutuser">
                                <form role="form" id="search_user" action="../../GetUserInfo.action" method="post">

                                    <p><input type="text" name="searchuser" placeholder="phone or username" class="searchuser" id="searchuser">

                                        <button type="submit" class="btn btn-lg btn-info btn-link">查找</button></p>

                                </form>
                                <form role="form" action="../../DeleteUser.action" method="post">

                                    <p><input type="text" name="deleteuser" placeholder="userId" class="deleteuser" id="deleteuser">

                                        <button type="submit" class="btn btn-lg btn-info btn-link" id="delete_user">删除</button></p>

                                </form>
                            </div>

                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>userId</th>
                                        <th>username</th>
                                        <th>phone</th>
                                    </tr>
                                </thead>
                                <tbody id="user_list">

                                </tbody>
                            </table>

                        </div>
                    </div>

                    <div class="row" id="right_shopmanage" style="display: none;">
                        <div class="col-md-6">

                            <!—一组按钮控件-->
                            <a href="../home/sellerBehind.jsp"><button type="button" class="btn btn-lg btn-primary">添加店铺</button></a>
                            <button type="button" class="btn btn-lg btn-success">查找店铺</button>

                            <div id="aboutshop">
                                <form role="form" id="search_shop" action="../../GetShopInfo.action" method="post">

                                    <p><input type="text" name="searchshop" placeholder="phone or shopname" class="searchshop" id="searchshop">

                                        <button type="submit" class="btn btn-lg btn-info btn-link">查找</button></p>

                                </form>
                                <form role="form" action="../../DeleteShop.action" method="post">

                                    <p><input type="text" name="deleteshop" placeholder="phone" class="deleteshop" id="deleteshop">

                                        <button type="submit" class="btn btn-lg btn-info btn-link" id="delete_shop">删除</button></p>

                                </form>
                            </div>

                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>shopId</th>
                                        <th>shopName</th>
                                        <th>phone</th>
                                    </tr>
                                </thead>
                                <tbody id="shop_list">

                                </tbody>
                            </table>

                        </div>

                    </div>

                </div>
            </div>
        </div>
        <script src="../../style/js/jquery.min.js"></script>
        <script src="../../style/js/bootstrap.min.js"></script>
        <script src="../../style/js/HomeAdmin.js"></script>

    </body>

</html>