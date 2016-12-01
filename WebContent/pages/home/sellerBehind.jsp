<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../../style/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../style/css/bootstrap-theme.min.css">

        <link rel="stylesheet" href="../../style/css/HomeAdmin.css">
    </head>

    <body>
        

        <!—自适应布局-->
        <div class="container-fluid">
            <div class="row">
                <!—左侧导航栏-->
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
                <!—右侧管理控制台-->
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <div class="row" id="shopManagePage" style="display:inline">
                        <div id="shopManageForm" style="display:inline">
                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                                <h2 class="page-header">店铺信息</h2>
                                <form role="form" action="${pageContext.request.contextPath }/UpdateShopInfo.action" method="post">

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺名称：</h4><br />
                                        <input type="text" name="shopName" id="shopName" class="form-control input-lg" tabindex="5" readonly="readonly">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>联系方式：</h4><br />
                                        <input type="text" name="phone" id="phone" class="form-control input-lg" tabindex="2" readonly="readonly">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺地址：</h4><br />
                                        <input type="text" name="address" id="address" class="form-control input-lg" tabindex="6" readonly="readonly">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺公告：</h4><br />
                                        <textarea class="form-control" name="announcement" id="announcement" cols="30" rows="10" readonly="readonly"></textarea>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>店铺描述：</h4><br />
                                        <textarea class="form-control" name="shopDescribe" id="shopDescribe" cols="30" rows="10" readonly="readonly"></textarea>
                                    </div>

                                    </hr class="colorgraph">

                                    <div class="row" id="shopManageButton" style="display:none">
                                        <div class="col-xs-12 col-md-6" id="goback">
                                            <a href="" class="btn btn-success btn-block btn-lg">取消</a>
                                        </div>
                                        <div class="col-xs-12 col-md-6" id="shopInfoSave"><input type="submit" value="保存" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
                                    </div>
                                </form>
                                <div class="col-xs-12 col-md-12" id="shopInfoModify" style="display:inline"><button class="btn btn-primary btn-block btn-lg">修改</button></div>
                            </div>
                        </div>
                    </div>

                    <div class="row" id="goodsManagePage" style="display:none">
                        <div id="goodsIntro" style="display: inline">
                            <h2 class="page-header">商品简介</h2>
                            <button type="button" id="goodsAddBtn" class="right">添加商品</button>
                            <div class="row tableHead" style="display:inline">
                                <hr>
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

                        <div id="goodsAddPage" style="display: none">

                            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                                <h2 class="page-header">添加商品</h2>
                                <form role="form" action="${pageContext.request.contextPath }/AddGoodsInfo.action" method="post">

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>商品名称：</h4><br />
                                        <input type="text" name="goodsName" id="goodsName" class="form-control input-lg" tabindex="5">
                                        <p class="help-block"></p>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>分类编号</h4><br />
                                        <select name="category" id="category" class="form-control">
                                            <option selected="selected">一级分类</option>
                                        </select>
                                    </div>

                                    <div class="form-group has-info">
                                        <br />
                                        <h4>商品描述：</h4><br />
                                        <textarea class="form-control" name="shopDescibe" id="shopDescribe" cols="30" rows="10" .></textarea>
                                    </div>

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

                                    <div class="row" id="goodsAttr1" style="display:inline">
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="goodsAtrrVal-1" id="goodsAtrrVal-1" class="form-control input-lg" placeholder="属性值" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="cost-1" id="cost-1" class="form-control input-lg" placeholder="进价" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="price-1" id="price-1" class="form-control input-lg" placeholder="售价" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="inventory-1" id="inventory-1" class="form-control input-lg" placeholder="库存" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row" id="goodsAttr2" style="display:none">
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="goodsAtrrVal-2" id="goodsAtrrVal-2" class="form-control input-lg" placeholder="属性值" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="cost-2" id="cost-2" class="form-control input-lg" placeholder="进价" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="price-2" id="price-2" class="form-control input-lg" placeholder="售价" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="inventory-2" id="inventory-2" class="form-control input-lg" placeholder="库存" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row" id="goodsAttr3" style="display:none">
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="goodsAtrrVal-3" id="goodsAtrrVal-3" class="form-control input-lg" placeholder="属性值" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="cost-3" id="cost-3" class="form-control input-lg" placeholder="进价" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="price-3" id="price-3" class="form-control input-lg" placeholder="售价" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3">
                                            <div class="form-group has-info">
                                                <input type="text" name="inventory-3" id="inventory-3" class="form-control input-lg" placeholder="库存" tabindex="3">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                    </div>

                                    </hr class="colorgraph">

                                    <div class="row" id="goodsAdd" style="display:none">
                                        <div class="col-xs-12 col-md-6" id="goback">
                                            <a href="" class="btn btn-success btn-block btn-lg">取消</a>
                                        </div>
                                        <div class="col-xs-12 col-md-6" id="save"><input type="submit" value="保存" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </div>

                    <div class="row" id="ordersManagePage" style="display:none"></div>

                    <div class="row" id="saleStatisticsPage" style="display:none"></div>

                </div>
            </div>
        </div>

        <script src="../../style/js/jquery.min.js"></script>
        <script src="../../style/js/bootstrap.min.js"></script>
        <script src="../../style/js/sellerBehind.js"></script>
    </body>

</html>