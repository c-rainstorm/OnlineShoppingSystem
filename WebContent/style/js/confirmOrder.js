$(document).ready(function() {

    $.get("../../getReceivers.action", {}, function(receivers) {
        for (var i = 0; i < receivers.length; ++i) {
            $(".receiverRoot .title").append(createReceiver());
            var receiver = $(".receiver:last");
            receiver.find(".receiverId").html(receivers[i].receiverId);
            receiver.find(".receiverName").html(receivers[i].receiverName);
            receiver.find(".receiverPhone").html(receivers[i].receiverPhone);
            receiver.find(".receiverAddress").html(receivers[i].receiverAddress);
        }
        if (receivers.length == 0) {
            alert("请添加收货人")
            return;
        }
        $(".receiver:first").addClass("active");
        $(".receiver:first").css("display", "inline");
    })

    $("body").delegate(".receiver", "click", function() {
        if ($(this).hasClass("active")) {
            return;
        }
        $(".receiver").removeClass("active");
        $(this).addClass("active");
    })

    $(".showMore").click(function() {
        if ($(".receiver:hidden").length == 0) {
            $(".receiver").css("display", "none");
            for (var i = 0; i < $(".receiver").length; ++i) {
                var item = $(".receiver").eq(i);
                if (item.hasClass("active")) {
                    item.css("display", "inline");
                    $(this).html("查看更多");
                    return;
                }
            }

        } else {
            $(".receiver").css("display", "inline");
            $(this).html("收起");
        }

    })

    function createReceiver() {
        var temp = '<div class="row" style="margin-bottom: 10px; "> <div class="col-lg-offset-2 col-md-offset-2 "> <button class="btn btn-default receiver" style="display: none;"> <span class="receiverId" style="display: none;"> </span> <span class="receiverName ">张宝琛</span> <span class="receiverPhone ">17839918876</span> <span class="receiverAddress ">郑州大学</span> </button> </div> </div>';
        return temp;
    }

    $(".payMethod").click(function() {
        $(".payMethod").removeClass("active");
        $(this).addClass("active");
    })

    var orders = JSON.parse($.cookie('localOrders'));

    $.get("../../getShoppingCart.action", {}, function(shops) {
        for (var k = 0; k < orders.length; ++k) {
            //一个订单遍历所有店铺
            for (var j = 0; j < shops.length; ++j) {
                if (orders[k].shopId == shops[j].shopId) {
                    $(".ordersRoot").append(createOrder());
                    var shop = $(".order:last");
                    var shopInfo = shops[j];
                    shop.find(".shopId").html(shopInfo.shopId);
                    shop.find(".shopName").html(shopInfo.shopName);


                    for (var m = 0; m < orders[k].id.length; ++m) {
                        //订单中一个商品
                        var id = orders[k].id[m];
                        for (var i = 0; i < shopInfo.goodsInThisShop.length; ++i) {
                            // 遍历该店铺中所有商品
                            if (id == shopInfo.goodsInThisShop[i].id) {
                                shop.find(".showOrderTotal").before(createGoods());
                                var goods = shop.find(".goods:last");

                                var goodsInfo = shopInfo.goodsInThisShop[i].goods;

                                goods.find("img").attr("src", goodsInfo.imageAddr);
                                var goodsDesc = goods.find(".goodsDesc");
                                goodsDesc.html(goodsInfo.goodsName + "<br>" + goodsInfo.goodsDescribe);
                                goodsDesc.attr("href", "goods.jsp?goodsId=" + goodsInfo.goodsId);
                                var goodsAttr;
                                for (var j = 0; j < goodsInfo.goodsAttrs.length; ++j) {
                                    if (shopInfo.goodsInThisShop[i].attributeId == goodsInfo.goodsAttrs[j].attributeId) {
                                        goodsAttr = goodsInfo.goodsAttrs[j];
                                        break;
                                    }
                                }

                                goods.find(".id").html(shopInfo.goodsInThisShop[i].id)
                                goods.find(".num").html(shopInfo.goodsInThisShop[i].goodsNum);
                                goods.find(".attributeValue").html(goodsAttr.attributeValue);
                                goods.find(".price").html(parseFloat(goodsAttr.price).toFixed(2));
                                goods.find(".inventory").html(goodsAttr.inventory);
                                if (goodsAttr.inventory == "0") {
                                    var checkbox = goods.find("input");
                                    checkbox.addClass("disabled");
                                    checkbox.css("display", "none");
                                    goods.find(".tips").html("无货");
                                }
                                break;
                            }
                        }
                    }
                    break;
                }

            }
        }
        calculateTotal();
    })

    function createOrder() {
        var shop = '<div class="order"><span class="shopId" style="display: none;"></span><div class="row" style="padding: 0px 0px 13px 3px; margin: 0px 0px 5px 0px;"><div class="row"><div class="col-lg-8 col-md-8 col-lg-offset-2 col-md-offset-2" style="border-bottom: 1px solid; "><span class="shopName"></span></div></div><div class="row showOrderTotal"> <div class="col-lg-6 col-md-6 col-lg-offset-4 col-md-offset-4"> <p align="right">合计：<span style="color: red; font-weight:bold;">￥<span class="orderTotal">0</span></span> </p> </div> </div> <div class="row anno" style="padding: 0px 0px 13px 3px; margin: 0px 0px 5px 0px;"> <div class="input-group col-lg-8 col-md-8 col-lg-offset-2 col-md-offset-2"> <span class="input-group-addon">备注：</span> <input type="text" class="form-control annotation"> </div> </div></div><div class="row"> <div class="col-lg-offset-1 col-md-offset-1 col-lg-10 col-md-10"> <hr> </div> </div>';
        return shop;
    }

    function createGoods() {
        var goods = '<div class="goods row" style="margin: 10px 0px 10px 0px">  <span class="id" style="display: none;"></span> <div class="col-lg-1 col-md-1 col-lg-offset-2 col-md-offset-2" style="padding:0px 0px 0px 25px;"> <span class="tips"></span><span class="inventory" style="display:none;"></span> <img src="/images/avatars/default.jpg" style="height: 49px; width: 49px;" /> </div> <div class="col-lg-2 col-md-2"> <p><a class="goodsDesc" target="_blank"></a></p> </div> <div class="col-lg-2 col-md-2"> <p class="attributeValue"></p> </div> <div class="col-lg-1 col-md-1"> <p>￥<span class="price"></span></p>  </div> <div class="col-lg-1 col-md-1" align="center"> <div class="btn-group" align="center"><span class="num">1</span></div> </div> <div class="col-lg-1 col-md-1"> <p>￥<span class="subTotal"></span></p> </div></div>';
        return goods;
    }

    function calculateTotal() {
        $(".total").html("0");
        $(".orderTotal").html("0");
        for (var i = 0; i < $(".order").length; ++i) {
            var order = $(".order").eq(i);
            var orderTotal = 0;
            order.find(".goods").each(function(i, goods) {
                goods = $(goods);

                var price = parseFloat(goods.find(".price").html());
                var num = parseFloat(goods.find(".num").html());
                var subTotal = price * num; // 单个商品小计
                goods.find(".subTotal").html(subTotal.toFixed(2));

                //订单小计
                orderTotal = orderTotal + subTotal;
            })
            order.find(".orderTotal").html(orderTotal.toFixed(2));
            var total = parseFloat($(".total").html()) + orderTotal;
            $(".total").html(total.toFixed(2));
        }
    }

    $(".pay").click(function() {
        var shops = $(".order");

        var receiverId = $(".receiverRoot .active").find("span:first").html();
        if (receiverId == undefined) {
            alert("请填写收货人")
            return;
        }
        var payMethod = $(".payRoot .active").html();
        for (var k = 0; k < shops.length; ++k) {
            var annotation = shops.eq(k).find(".annotation").val();
            var shopId = shops.eq(k).find(".shopId").html();
            for (var i = 0; i < orders.length; ++i) {
                if (shopId == orders[i].shopId) {
                    orders[i].annotation = annotation;
                    break;
                }
            }
        }
        var temp = new Object();
        temp.orders = orders;
        temp.payMethod = payMethod;
        temp.receiverId = receiverId;
        $.post("../../confirmOrder.action", {
            orders: JSON.stringify(orders),
            receiverId: receiverId,
            payMethod: payMethod
        }, function(orderId) {
            $.cookie('orderIds', JSON.stringify(orderId));
            $.removeCookie("localOrders");
            window.location.href = "http://localhost:8080/OSS/pages/core/payOrders.jsp";
        })
    })

})