<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../../style/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../style/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="../..style/css/jquery-ui.css">
        <link rel="stylesheet" href="../..style/css/style.css">

        <style>
            body {
                padding-top: 50px;
                padding-bottom: 40px;
                color: #5a5a5a;
            }
            
            .right {
                position: absolute;
                right: 0px;
                width: 300px;
                background-color: #b0e0e6;
            }
            
            .sidebar {
                position: fixed;
                top: 51px;
                bottom: 0;
                left: 0;
                z-index: 1000;
                display: block;
                padding: 20px;
                overflow-x: hidden;
                overflow-y: auto;
                background-color: #ddd;
                border-right: 1px solid #eee;
            }
            
            .nav-sidebar {
                margin-right: -21px;
                margin-bottom: 20px;
                margin-left: -20px;
            }
            
            .nav-sidebar> li> a {
                padding-right: 20px;
                padding-left: 20px;
            }
            
            .nav-sidebar> .active> a,
            .nav-sidebar> .active> a:hover,
            .nav-sidebar> .active> a:focus {
                color: #fff;
                background-color: #428bca;
            }
            
            .main {
                padding: 20px;
            }
            
            .main .page-header {
                margin-top: 0;
            }
        </style>

    </head>

    <body>

        <div class="container-fluid">

            <div class="row">

                <div class="col-sm-3 col-md-2 sidebar">
                    <h1>OSS</h1>
                    <h2>卖家中心</h2>
                    <ul class="nav nav-sidebar">
                        <li>
                            <a href="#" id="shopManage">店铺管理</a>
                        </li>
                        <li>
                            <a href="#" id="goodsManage">商品管理</a>
                        </li>
                        <li>
                            <a href="#" id="ordersManage">订单管理</a>
                        </li>
                        <li>
                            <a href="#" id="saleStatistics">销售统计</a>
                        </li>
                    </ul>
                </div>

                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <!--店铺管理模块-->

                    <div class="row" id="shopManagePage" style="display:inline">

                        <div id="shopManageForm1" style="display:inline">

                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

                                <h2 class="page-header">店铺信息</h2>
                                
                                <form role="form">

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺名称：</h4><br />
                                        <input type="text" name="shopName1" id="shopName1" class="form-control input-sm" readonly="readonly">
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>联系方式：</h4><br />
                                        <input type="text" name="phone1" id="phone1" class="form-control input-sm" readonly="readonly">
                                    </div>

                                    <div class="row">

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>店铺评分</h4><br />
                                                <input type="text" name="evaluateSum1" id="evaluateSum1" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>评分人数</h4><br />
                                                <input type="text" name="evaluateNum1" id="evaluateNum1" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>

                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺地址：</h4><br />
                                        <input type="text" name="address1" id="address1" class="form-control input-sm" readonly="readonly">
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺描述：</h4><br />
                                        <textarea class="form-control" name="shopDescribe1" id="shopDescribe1" cols="20" rows="10" readonly="readonly"></textarea>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺公告：</h4><br />
                                        <textarea class="form-control" name="announcement1" id="announcement1" cols="20" rows="10" readonly="readonly"></textarea>
                                    </div>

                                </form>

                                <div class="col-xs-12 col-md-12" id="shopInfoModify" style="display:inline"><button class="btn btn-primary btn-block btn-sm">修改</button></div>

                            </div>

                        </div>

                        <div id="shopManageForm2" style="display:none">

                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

                                <h2 class="page-header">店铺信息</h2>
                                
                                <form role="form" id="shopInfoForm" action="${pageContext.request.contextPath }/updateShopInfo.action" method="post">

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺名称：</h4><br />
                                        <input type="text" name="shopName2" id="shopName2" class="form-control input-sm">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>联系方式：</h4><br />
                                        <input type="text" name="phone2" id="phone2" class="form-control input-sm">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="row">

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>店铺评分</h4><br />
                                                <input type="text" name="evaluateSum2" id="evaluateSum2" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>评分人数</h4><br />
                                                <input type="text" name="evaluateNum2" id="evaluateNum2" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>

                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺地址：</h4><br />
                                        <input type="text" name="address2" id="address2" class="form-control input-sm">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺描述：</h4><br />
                                        <textarea class="form-control" name="shopDescribe2" id="shopDescribe2" cols="20" rows="10"></textarea>
                                    </div>
                                    
                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺公告：</h4><br />
                                        <textarea class="form-control" name="announcement2" id="announcement2" cols="20" rows="10"></textarea>
                                    </div>

                                    <div class="row" id="shopManageButton">
                                        <div class="col-xs-12 col-md-6" id="shopInfoGoback">
                                            <a href="javascript:;" class="btn btn-warning btn-block btn-lg">取消</a>
                                        </div>
                                        <div class="col-xs-12 col-md-6" id="shopInfoSave"><input type="submit" value="保存" class="btn btn-primary btn-block btn-lg"></div>
                                    </div>

                                </form>

                            </div>

                        </div>

                    </div>

                    <!--商品管理模块-->

                    <div class="row" id="goodsManagePage" style="display:none">

                        <div id="goodsIntro" style="display: inline">

                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

                                <h2 class="page-header">商品简介</h2>

                                <button type="button" id="goodsAddBtn" class="btn-primary right">添加商品</button>

                                <div class="row tableHead goodsBriefs" style="display:inline">
                                    <hr>
                                    <div class="row">
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>商品ID</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>商品名称</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>商品销量</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>商品详情</p>
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

                            </div>

                        </div>

                        <div id="goodsAddPage" style="display: none">

                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

                                <h2 class="page-header">添加商品</h2>

                                <form role="form" id="goodsForm">

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>商品名称：</h4><br />
                                        <input type="text" name="goodsName" id="goodsName" class="form-control input-sm">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="row" id="category">

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>一级分类</h4><br />
                                                <select name="Category" id="firstCategoryOption" class="form-control">
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>二级分类</h4><br />
                                                <select name="Category" id="secondCategoryOption" class="form-control">
                                                </select>
                                            </div>
                                        </div>

                                    </div>

                                    <div style="display:none">
                                        <div class="form-group has-info">
                                            <input type="text" name="firstCategory" id="firstCategory" class="form-control input-sm">
                                        </div>
                                    </div>

                                    <div style="display:none">
                                        <div class="form-group has-info">
                                            <input type="text" name="secondCategory" id="secondCategory" class="form-control input-sm">
                                        </div>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>商品描述：</h4><br />
                                        <input type="text" name="goodsDescribe" id="goodsDescribe" class="form-control input-sm">
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>商品图片(至多保存6张)：</h4><br />
                                        <input type="file" name="addImage" accept="image/*" class="form-control input-sm" multiple="multiple">
                                    </div>

                                </form>

                                <form role="form" class="goodsAttrForm">

                                    <div class="row tableHead">
                                        <hr>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>属性内容</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>商品进价</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>商品售价</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>商品库存</p>
                                        </div>
                                    </div>

                                </form>

                                <div class="row">
                                    <div class="goodsAttrDelete">
                                        <div class="col-xs-12 col-md-6">
                                            <a href="javascript:;" class="btn btn-warning btn-block btn-sm">删除属性</a>
                                        </div>
                                    </div>
                                    <div class="goodsAttrAdd">
                                        <div class="col-xs-12 col-md-6 goodsAttrAdd">
                                            <a href="javascript:;" class="btn btn-primary btn-block btn-sm">添加属性</a>
                                        </div>
                                    </div>
                                </div>

                                <br /><br /><br />

                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <a href="javascript:;" id="goodsAddGoback" class="btn btn-warning btn-block btn-lg">取消</a>
                                    </div>
                                    <div class="col-xs-12 col-md-6">
                                        <a href="javascript:;" id="goodsInfoSave" class="btn btn-primary btn-block btn-lg">保存</a>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div id="goodsDetailPage" style="display: none;">

                            <div id="goodsManageForm1" style="display:none">

                                <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

                                    <h2 class="page-header">商品详情</h2>
                                    
                                    <form role="form" id="goodsBasicInfo1">

                                        <div class="form-group has-info">
                                            <br />
                                            <h4>商品ID：</h4><br />
                                            <input type="text" name="goodsId1" id="goodsId1" class="form-control input-sm" readonly="readonly">
                                        </div>

                                        <div class="form-group has-info">
                                            <br />
                                            <h4>商品名称：</h4><br />
                                            <input type="text" name="goodsName1" id="goodsName1" class="form-control input-sm" readonly="readonly">
                                        </div>

                                        <div class="row">

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group has-info">
                                                    <br />
                                                    <h4>一级分类</h4><br />
                                                    <input type="text" name="firstCategory1" id="firstCategory1" class="form-control input-sm" readonly="readonly">
                                                </div>
                                            </div>

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group has-info">
                                                    <br />
                                                    <h4>二级分类</h4><br />
                                                    <input type="text" name="secondCategory1" id="secondCategory1" class="form-control input-sm" readonly="readonly">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="form-group has-info">
                                            <br />
                                            <h4>商品描述：</h4><br />
                                            <input type="text" name="goodsDescribe1" id="goodsDescribe1" class="form-control input-sm" readonly="readonly">
                                        </div>

                                        <div class="row">

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group has-info">
                                                    <br />
                                                    <h4>打折截止日期：</h4><br />
                                                    <input type="text" name="discountDeadline1" id="discountDeadline1" readonly="readonly">
                                                </div>
                                            </div>

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group has-info">
                                                    <br />
                                                    <h4>打折比例：</h4><br />
                                                    <input type="text" name="discountRate1" id="discountRate1" readonly="readonly">
                                                </div>
                                            </div>

                                        </div>

                                        <div id="goodsImages1">
                                            <br />
                                            <h4>商品图片(至多保存6张)：</h4><br />
                                            <div class="goodsImages"></div>
                                        </div>

                                    </form>

                                    <form role="form" class="goodsAttrForm">

                                        <div class="row tableHead" style="display:inline">
                                            <hr>
                                            <div class="col-xs-3 col-sm-3 col-md-3">
                                                <p>属性内容</p>
                                            </div>
                                            <div class="col-xs-3 col-sm-3 col-md-3">
                                                <p>商品进价</p>
                                            </div>
                                            <div class="col-xs-3 col-sm-3 col-md-3">
                                                <p>商品售价</p>
                                            </div>
                                            <div class="col-xs-3 col-sm-3 col-md-3">
                                                <p>商品库存</p>
                                            </div>
                                        </div>

                                    </form>

                                    <div class="row">
                                        <div class="col-xs-12 col-md-6" id="goodsDelete">
                                            <a href="javascript:;" class="btn btn-warning btn-block btn-lg">删除商品</a>
                                        </div>
                                        <div class="col-xs-12 col-md-6" id="goodsInfoModify">
                                            <a href="javascript:;" class="btn btn-success btn-block btn-lg">修改商品</a>
                                        </div>
                                    </div>

                                </div>

                            </div>

                            <div id="goodsManageForm2" style="display:none">

                                <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

                                    <h2 class="page-header">商品详情</h2>
                                    
                                    <form role="form" id="goodsBasicInfo2">

                                        <div class="form-group has-info">
                                            <br />
                                            <h4>商品ID：</h4><br />
                                            <input type="text" name="goodsId2" id="goodsId2" class="form-control input-sm" readonly="readonly">
                                        </div>

                                        <div class="form-group has-info">
                                            <br />
                                            <h4>商品名称：</h4><br />
                                            <input type="text" name="goodsName2" id="goodsName2" class="form-control input-sm">
                                            <p class="help-block"></p>
                                        </div>

                                        <div class="row">

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group has-info">
                                                    <br />
                                                    <h4>一级分类</h4><br />
                                                    <input type="text" name="firstCategory2" id="firstCategory2" class="form-control input-sm" readonly="readonly">
                                                </div>
                                            </div>

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group has-info">
                                                    <br />
                                                    <h4>二级分类</h4><br />
                                                    <input type="text" name="secondCategory2" id="secondCategory2" class="form-control input-sm" readonly="readonly">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="form-group has-info">
                                            <br />
                                            <h4>商品描述：</h4><br />
                                            <input type="text" name="goodsDescribe2" id="goodsDescribe2" class="form-control input-sm">
                                        </div>

                                        <div class="row">

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group has-info">
                                                    <br />
                                                    <h4>打折截止日期：</h4><br />
                                                    <input type="text" name="discountDeadline2" id="discountDeadline2">
                                                </div>
                                            </div>

                                            <div class="col-xs-6 col-sm-6 col-md-6">
                                                <div class="form-group has-info">
                                                    <br />
                                                    <h4>打折比例：</h4><br />
                                                    <input type="text" name="discountRate2" id="discountRate2">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="form-group has-info">
                                            <div id="goodsImage2"></div>
                                            <input type="file" name="goodsImage2" accept="image/*" class="form-control input-sm" multiple="multiple">
                                        </div>

                                    </form>

                                    <div class="row">
                                        <div class="goodsAttrDelete">
                                            <div class="col-xs-12 col-md-6">
                                                <a href="javascript:;" class="btn btn-warning btn-block btn-sm">删除属性</a>
                                            </div>
                                        </div>
                                        <div class="goodsAttrAdd">
                                            <div class="col-xs-12 col-md-6 goodsAttrAdd">
                                                <a href="javascript:;" class="btn btn-primary btn-block btn-sm">添加属性</a>
                                            </div>
                                        </div>
                                    </div>

                                    <br /><br /><br />

                                    <div class="row">
                                        <div class="col-xs-12 col-md-6" id="goodsDetailGoback">
                                            <a href="javascript:;" class="btn btn-warning btn-block btn-lg">取消</a>
                                        </div>
                                        <div class="col-xs-12 col-md-6" id="goodsModifySave">
                                            <a href="javascript:;" class="btn btn-success btn-block btn-lg">保存</a>
                                        </div>
                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>

                    <!--订单管理模块-->

                    <div class="row" id="ordersManagePage" style="display:none">

                        <div id="ordersIntro" style="display: inline">

                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                                
                                <h2 class="page-header">订单简介</h2>
                                
                                <div class="row">
                                    <div class="col-xs-12 col-md-6" id="unfinishButton">
                                        <a href="javascript:;" class="btn btn-warning btn-block btn-lg">未发货</a>
                                    </div>
                                    <div class="col-xs-12 col-md-6" id="historyButton">
                                        <a href="javascript:;" class="btn btn-primary btn-block btn-lg">已完成</a>
                                    </div>
                                </div>

                                <div class="row tableHead unfinishBriefs" style="display:inline">
                                    <hr>
                                    <div class="row">
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>订单ID</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>下单时间</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>订单总价</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>订单详情</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="row tableHead historyBriefs" style="display:none">
                                    <hr>
                                    <div class="row">
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>订单ID</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>完成时间</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>订单总价</p>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <p>订单详情</p>
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
                            </div>
                        
                        </div>

                        <div id="orderDetailPage" style="display: none">

                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

                                <h2 class="page-header">订单详情</h2>
                                
                                <form role="form" id="orderForm">

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>订单编号：</h4><br />
                                        <input type="text" name="orderId" id="orderId" class="form-control input-sm" readonly="readonly">
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>用户ID：</h4><br />
                                                <input type="text" name="userId" id="userId" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>订单状态：</h4><br />
                                                <input type="text" name="orderStatus" id="orderStatus" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>收件人姓名：</h4><br />
                                                <input type="text" name="receiverName" id="receiverName" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>联系方式：</h4><br />
                                                <input type="text" name="receiverPhone" id="receiverPhone" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>收货地址：</h4><br />
                                        <input type="text" name="receiverAddress" id="receiverAddress" class="form-control input-sm" readonly="readonly">
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>运单号：</h4><br />
                                        <input type="text" name="trackNum" id="trackNum" class="form-control input-sm">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="row tableHead goodsInOrder">
                                        <hr>
                                        <div class="row">
                                            <div class="col-xs-2 col-sm-2 col-md-2">
                                                <p>商品ID</p>
                                            </div>
                                            <div class="col-xs-3 col-sm-3 col-md-3">
                                                <p>商品名称</p>
                                            </div>
                                            <div class="col-xs-3 col-sm-3 col-md-3">
                                                <p>属性</p>
                                            </div>
                                            <div class="col-xs-2 col-sm-2 col-md-2">
                                                <p>数量</p>
                                            </div>
                                            <div class="col-xs-2 col-sm-2 col-md-2">
                                                <p>单价</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>备注：</h4><br />
                                        <input type="text" name="annotation" id="annotation" class="form-control input-sm" readonly="readonly">
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>下单时间：</h4><br />
                                                <input type="text" name="orderTime" id="orderTime" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>完成时间：</h4><br />
                                                <input type="text" name="completeTime" id="completeTime" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>支付方式：</h4><br />
                                                <input type="text" name="payMethod" id="payMethod" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group has-info">
                                                <br />
                                                <h4>总金额：</h4><br />
                                                <input type="text" name="total" id="total" class="form-control input-sm" readonly="readonly">
                                            </div>
                                        </div>
                                    </div>

                                </form>

                                <div class="row">
                                    <div class="col-xs-12 col-md-6" id="orderDetailGoback">
                                        <a href="javascript:;" class="btn btn-warning btn-block btn-lg">返回</a>
                                    </div>
                                    <div class="col-xs-12 col-md-6" id="sendGoods" style="display: inline;">
                                        <a href="javascript:;" class="btn btn-success btn-block btn-lg">发货</a>
                                    </div>
                                </div>

                            </div>

                        </div>
</div>
                        <!--销售统计模块-->

                                           <div class="row" id="saleStatisticsPage" style="display:none">

                        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

                            <h2 class="page-header">销量统计</h2>

                            <div class="row">
                                <div class="col-xs-12 col-md-6" id="totalSalesBtn">
                                    <a href="javascript:;" class="btn btn-warning btn-block btn-lg">总体销量</a>
                                </div>
                                <div class="col-xs-12 col-md-6" id="singleSalesBtn">
                                    <a href="javascript:;" class="btn btn-success btn-block btn-lg">单品销量</a>
                                </div>
                            </div>

                            
                                <div class="col-xs-12 col-md-6" class="totalSales" style="display:none">
                                    <h3>总体销量统计</h3>
                                </div>
                                <div class="col-xs-12 col-md-6" class="singleSales" style="display:none">
                                    <h3>单品销量统计</h3>
                                </div>
                           
                            
                            <div class="row">
                                <div class="col-xs-12 col-md-6" id="searchId" style="display: none;">
                                    <br />
                                    <h4>商品ID：</h4><br />
                                    <input type="text" name="searchGoodsId" id="searchGoodsId" class="form-control input-sm">
                                </div>
                                <div class="col-xs-12 col-md-6" id="singleSalesButton">
                                    <br />
                                    <h4>查询天数：</h4><br />
                                    <input type="text" name="searchDays" id="searchDays" class="form-control input-sm">
                                </div>
                            </div>

                             <br/><br/><br/>
                            
                            <div class="row">
                                <div id="flotContainer" style="width: 600px;height:400px; text-align: center; margin:0 auto;">
                                </div>
                            </div>
                            
                            <br/><br/><br/>

                            <button type="button" id="salesBtn" class="btn-primary right">查询</button>

                        </div>

                    </div>

                    

                </div>

            </div>

            <script src="../../style/js/jquery.min.js"></script>
            <script src="../../style/js/bootstrap.min.js"></script>
            <script src="../../style/js/sellerBehind.js"></script>
            <script src="../../style/js/jquery-1.12.4.js"></script>
            <script src="../../style/js/jquery-ui.js"></script>
            <script src="../../style/js/excanvas.min.js"></script>
            <script src="../../style/js/jquery.flot.min.js"></script>
    </body>

</html>
