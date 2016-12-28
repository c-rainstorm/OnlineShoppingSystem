$(document).ready(function() {
    var a = $.cookie("orderIds");
    var orderIds = JSON.parse($.cookie("orderIds"));

    var main = $(".main");
    for (var i = 0; i < orderIds.length; ++i) {
        $.post("../../getOrderById.action", { orderId: orderIds[i] }, function(order) {
            main.append(createOrderDetail());
            var detail = $(".detail:last");
            addDataToDetail(detail, order);
        })
    }

    function createOrderDetail() {
        var newDetail = '<div class="row detail"><div class="col-md-7 col-lg-7 col-md-offset-2 col-lg-offset-2"><h3>订单详情</h3><hr><p><strong>订单状态： <span class="status"></span></strong></p><br><p>订单号码：<span class="orderId"></span></p> <p>下单日期：<span class="trackingNumber"></span></p> <p>下单日期：<span class="orderTime"></span></p><p>付款方式：<span class="payway"></span></p><br> <p>收货地址：<span class="receiverAddress"></p><p>收货人名：<span class="receiverName"></span></p> <p>收货日期：<span class="finishTime"></span></p><br><p>买家备注：<span class="commit"></span></p><hr><div class="row"><div class="col-xs-6 col-sm-6 col-md-6"> <p>商品</p></div><div class="col-xs-3 col-sm-3 col-md-3"><p>商品属性</p></div><div class="col-xs-3 col-sm-3 col-md-3"> <p>小计</p></div></div><hr><div class="goods"></div> <div class="row"><div class="col-xs-3 col-sm-3 col-md-3"></div><div class="col-xs-6 col-sm-6 col-md-6"></div> <div class="col-xs-3 col-sm-3 col-md-3"><p>订单总额： ￥<span class="orderTotal"></span></p><p>配送费用： ￥<span class="deliveryFee"></span></p> <p><strong>总 金 额：￥<span class="realPay"></span></strong></p></div></div><div class="row"> <div class="col-md-offset-9 col-md-offset-9"> <a href="pay-result.jsp" class="payOrder form-control btn btn-success" target="_blank">支付订单</a> </div> </div></div></div>';
        return newDetail;
    }

    function addDataToDetail(detail, order) {
        if (order.trackingNumber == undefined || order.trackingNumber == null) {
            order.trackingNumber = "";
        }
        if (order.completeTime == undefined || order.completeTime == null) {
            order.completeTime = "";
        }
        if (order.annotation == undefined || order.annotation == null) {
            order.annotation = "";
        }

        detail.find(".status").html(order.orderStatus);
        detail.find(".orderId").html(order.orderId);
        detail.find(".trackingNumber").html(order.trackingNumber);
        detail.find(".orderTime").html(order.orderTime);
        detail.find(".payway").html(order.payMethod);
        detail.find(".receiverAddress").html(order.receiver.address);
        detail.find(".receiverName").html(order.receiver.name + " " + order.receiver.phone);
        detail.find(".finishTime").html(order.completeTime);
        detail.find(".commit").html(order.annotation);

        $.each(order.goodsInOrder, function(i, goodsInOrder) {
            var goods = '<div class="row"><div class="col-xs-2 col-sm-2 col-md-2"> <img style="height:100px; width:100px;" src="' + goodsInOrder.goods.imageAddr + '"alt="该图片离家出走了.. " class="img-thumbnail img-responsive"></div><div class=" col-xs-4 col-sm-4 col-md-4 "> <p> <span class="foodName" > ' + goodsInOrder.goods.goodsName + '<br> ' + goodsInOrder.goods.goodsDescribe + ' </div><div class=" col-xs-3 col-sm-3 col-md-3 "> ' + goodsInOrder.attributeValue + '</span></p><p>售价： ￥<span class=" price "> ' + goodsInOrder.actualPrice + '</span> x<span class="soldNum"> ' + goodsInOrder.goodsNum + '</span > </p> </div><div class="col-xs-3 col-sm-3 col-md-3"><p>￥<span class="subTotal"> ' + (goodsInOrder.goodsNum * goodsInOrder.actualPrice) + '</span > </p></div > </div><hr>';

            detail.find(".goods").append(goods);
        })

        detail.find(".orderTotal").html(order.total);
        detail.find(".deliveryFee").html(0);
        detail.find(".realPay").html(order.total);
    }

    $("body").delegate(".payOrder", "click", function() {
        var orderId = $(this).parents(".detail").find(".orderId").html();
        var temp = new Array();
        temp[0] = orderId;
        $.post("../../payOrder.action", {
            orderId: JSON.stringify(temp)
        }, function(response) {
            temp = new Array();
            var count = 0;
            for (var i = 0; i < orderIds.length; ++i) {
                if (orderIds[i] != orderId) {
                    temp[count] = orderIds[i];
                    ++count;
                }
            }
            $.cookie("orderIds", JSON.stringify(temp));
            $.cookie("payResult", JSON.stringify(response));
        })
    })

    $(".payAll").click(function() {
        $.post("../../payOrder.action", {
            orderId: $.cookie("orderIds")
        }, function(response) {
            // var resultPage = "http://localhost:8080/OSS/pages/core/pay-result.jsp?success=" + response.success + "&fail=" + response.fail;
            // window.location.href = resultPage;
            $.cookie("payResult", JSON.stringify(response));
            $.removeCookie("orderIds");
        })
    })

})