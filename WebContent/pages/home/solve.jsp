<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

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
       
        <nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">

                    <a class="navbar-brand" href="#">OSS</a>
                </div>

            </div>
        </nav>

     
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">事务处理</h1>

                    <div class="row">
                        <div class="col-md-9">

      
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">annotation</h3>
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="../../SolveTransaction.action" method="post">
<textarea rows="10" cols="90" id="annotation" name="annotation" class="form-control" placeholder="annotation" ></textarea>
                                        
                                        <br>
                                        <p>
                                        <!--<select name="cars" name="transactionstatus" id="transactionstatus">
                                            <option value="volvo">未处理</option>
                                            <option value="saab">已通过</option>
                                            <option value="fiat">已拒绝</option>

                                        </select> -->
                                        

                                        <input type="text" name="transactionstatus" placeholder="transaction status" id="transactionstatus">
                                        
                                        <input type="text" name="transaction_id" placeholder="transaction_id" id="transaction_id">
                                        <input type="text" name="admin_id" placeholder="admin_id" id="admin_id">
                                        </p>
                                        
                                        <br><br>
                                        <button type="submit" class="btn btn-lg" >完成</button>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <script src="../../style/js/jquery.min.js"></script>
        <script src="../../style/js/bootstrap.min.js"></script>

    </body>

</html>