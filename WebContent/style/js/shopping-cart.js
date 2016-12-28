$(document).ready(function() {
    $.get("../../getShoppingCart.action", {}, function(shops) {
        $.each(shops, function(i, shopInfo) {
            $(".root").append(createShop());
            var shop = $(".shop:last");
            shop.find(".shopId").html(shopInfo.shopId);
            shop.find(".shopName").html(shopInfo.shopName);
            for (var i = 0; i < shopInfo.goodsInThisShop.length; ++i) {
                shop.append(createGoods());
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
                checkNum(goods);
                goods.find(".attributeValue").html(goodsAttr.attributeValue);
                goods.find(".price").html(parseFloat(goodsAttr.price).toFixed(2));
                goods.find(".inventory").html(goodsAttr.inventory);
                if (goodsAttr.inventory == "0") {
                    var checkbox = goods.find("input");
                    checkbox.addClass("disabled");
                    checkbox.css("display", "none");
                    goods.find(".tips").html("无货");
                }
            }
        })
        calculateTotal();
    })

    function createShop() {
        var shop = '<div class="shop"><span class="shopId" style="display: none;"></span><div class="row" style="padding: 0px 0px 13px 3px; margin: 0px 0px 5px 0px;"><div class="col-lg-offset-1 col-md-offset-1" style="border-bottom: 1px solid; "><input type="checkbox" class="checkShop" /> <span class="shopName">万道数码专营店</span></div></div><hr />';
        return shop;
    }

    function createGoods() {
        var goods = '<div class="goods row" style="margin-bottom: 20px">  <span class="id" style="display: none;"></span> <div class="col-lg-1 col-md-1 col-lg-offset-1 col-md-offset-1" style="padding:0px 0px 0px 25px;"> <input type="checkbox" class="checkGoods" /><span class="tips"></span><span class="inventory" style="display:none;"></span> <img class="" src="/images/avatars/default.jpg" style="height: 49px; width: 49px;" /> </div> <div class="col-lg-2 col-md-2"> <p><a class="goodsDesc">联想（lenovo）SL700 固态硬盘 128G M.2-2242笔记本 固态NGFF</a></p> </div> <div class="col-lg-2 col-md-2"> <p class="attributeValue">颜色：Green系列●最大读速540MB/s<br>尺码：『『 120G 』』</p> </div> <div class="col-lg-1 col-md-1"> <p>￥<span class="price">319.00</span></p>  </div> <div class="col-lg-2 col-md-2" align="center"> <div class="btn-group" align="center"> <button type="button" class="btn btn-default dec">-</button> <button type="button" class="btn btn-default"><span class="num">1</span></button> <button type="button" class="btn btn-default inc">+</button> </div> </div> <div class="col-lg-1 col-md-1"> <p>￥<span class="subTotal">319.00</span></p> </div> <div class="col-lg-1 col-md-1"> <button type="button" class="btn btn-danger delete">删除</button> </div> </div>';
        return goods;
    }

    function checkNum(goods) {
        if (goods.find(".num") == "1") {
            goods.find(".dec").addClass("disabled");
        } else {
            goods.find(".dec").removeClass("disabled");
        }
    }

    $(":checkbox").prop("checked", false);

    // 对应商品数量减一
    $(".root").delegate(".dec", "click", function() {
        updateGoodsNum(this, -1);
    });

    //对应商品数量加一
    $(".root").delegate(".inc", "click", function() {
        updateGoodsNum(this, 1);
    });

    function updateGoodsNum(btn, num) {
        if ($(btn).hasClass("disabled")) {
            return;
        }
        var goods = $(btn).parents(".goods");
        var newNum = parseInt(goods.find(".num").html()) + num;
        $.get("../../updateGoodsNumInShoppingCart.action", {
            id: goods.find(".id").html(),
            goodsNum: newNum
        }, function(response) {
            if (response.result.match("true")) {
                goods.find(".num").html(newNum);
                calculateTotal();
            }
        })
    }

    //删除对应商品
    $(".root").delegate(".delete", "click", function() {
        deleteGoods($(this).parents(".goods"));
    });

    function deleteGoods(goods) {
        $.get("../../deleteFromShoppingCart.action", {
            id: goods.find(".id").html()
        }, function(response) {
            if (response.result == "true") {
                window.location.href = window.location.href;
            }
        })
    }

    // 删除选中商品
    $(".deleteChecked").click(function() {
        $(".goods").each(function(i, goods) {
            goods = $(goods);
            if (goods.find("input").prop("checked")) {
                deleteGoods(goods);
            }
        })
    })

    //全选
    $(".checkAll").click(function() {
        if ($(this).prop("checked")) {
            $(":checkbox").prop("checked", true);
            $(".goods").find("input").find(":disabled").prop("checked", false);
        } else {
            $(":checkbox").prop("checked", false);
        }
        calculateTotal();
    })

    // 全选店铺
    $(".root").delegate(".checkShop", "click", function() {
        if ($(this).prop("checked")) {
            var checkboxs = $(this).parents(".shop").find(":checkbox");
            for (var i = 0; i < checkboxs.length; ++i) {
                var checkbox = checkboxs.eq(i);
                if (!checkbox.prop("disabled")) {
                    checkbox.prop("checked", true);
                }
            }
        } else {
            $(this).parents(".shop").find(":checkbox").prop("checked", false);
        }
        calculateTotal();
    })

    //选中单个商品
    $(".root").delegate(".checkGoods", "click", calculateTotal);

    function calculateTotal() {
        $(".total").html("0");
        $(".goods").each(function(i, goods) {
            goods = $(goods);

            var price = parseFloat(goods.find(".price").html());
            var num = parseFloat(goods.find(".num").html());
            var subTotal = price * num;
            goods.find(".subTotal").html(subTotal.toFixed(2));

            if (goods.find("input").prop("checked")) {
                var money = $(".total");
                var total = parseFloat(money.html());

                total = total + subTotal;
                money.html(total.toFixed(2));
            }
        })
    }

    $(".checkout").click(function() {
        var orders = new Array();

        var shops = $(".shop");
        for (var i = 0; i < shops.length; ++i) {
            var shop = shops.eq(i);
            var goodsInThisShop = shop.find(".goods");
            var checkedGoods = new Array();
            for (var j = 0; j < goodsInThisShop.length; ++j) {
                var goods = goodsInThisShop.eq(j);
                if (goods.find("input").prop("checked")) {
                    checkedGoods[checkedGoods.length] = goods.find(".id").html();
                }
            }
            if (checkedGoods.length > 0) {
                orders[orders.length] = order(shop.find(".shopId").html(), checkedGoods);
            }
        }
        $.cookie('localOrders', JSON.stringify(orders));
    })

    function order(shopId, checkedGoods) {
        var order = new Object();
        order.shopId = shopId;
        order.id = checkedGoods;
        return order;
    }
})