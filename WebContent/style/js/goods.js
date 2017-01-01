$(document).ready(function() {
    $(".goodsId").html(url("?goodsId"));

    var goodsId = $(".goodsId").html();

    $.get("../../getGoodsDetail.action", {
        goodsId: goodsId
    }, function(response) {
        fillGoodsDetail(response);
    })

    $.get("../../getShopInfoByGoodsId.action", {
        goodsId: goodsId
    }, function(response) {
        fillShopInfo(response);
    })


    function fillGoodsDetail(goods) {
        $(".goodsName").html(goods.goodsName);
        $(".goodsDescribe").html(goods.goodsDescribe);
        fillGoodsImage(goods.images);
        fillGoodsCategory(goods.levelOne, goods.levelTwo);
        if (Date.parse(goods.discountDeadline.substring(0, 10)) > (new Date()).getTime()) {
            goods.discountRate = parseFloat(goods.discountRate);
            $(".discount").html("商品打折中.. 可享 " + floor(goods.discountRate * 100) + " 折优惠")
            for (var i = 0; i < goods.attributes.length; ++i) {
                goods.attributes[i].price = goods.attributes[i].price * goods.discountRate;
            }
        }
        fillGoodsAttribute(goods.attributes);
        checkInventory();
        activeAttribute($(".attribute:not(.disabled):first"));
    }

    function fillGoodsImage(images) {
        if (images.length > 0) {
            $(".bigImage").attr("src", images[0]);
            var imageNum = images.length > 5 ? 5 : images.length;
            for (var i = 0; i < imageNum; ++i) {
                $(".smallImages").append(createGoodsSmallImage(images[i]));
            }
        }
    }

    function createGoodsSmallImage(imageAddr) {
        var newImage = '<div class="col-md-2" style="padding: 0px"><img class="smallImage img-responsive img-thumbnail" src="' + imageAddr + '" style="height:50px; width:50px" alt=""></div>';
        return newImage;
    }

    function fillGoodsCategory(levelOne, levelTwo) {
        var levelOneEle = $(".levelOne");
        var levelTwoEle = $(".levelTwo");

        levelOneEle.html(levelOne);
        levelTwoEle.html(levelTwo);

        levelOneEle.attr("href", "goodsList.jsp?levelOne=" + levelOne);
        levelTwoEle.attr("href", "goodsList.jsp?levelOne=" + levelOne + "&levelTwo=" + levelTwo);
    }

    function fillGoodsAttribute(attributes) {
        for (var i = 0; i < attributes.length; ++i) {
            $(".attributes").append(createGoodsAttribute(attributes[i]));
        }
    }

    function createGoodsAttribute(attribute) {
        var newAttr = '<button class="attribute btn btn-default" style="margin:5px;">' +
            '<span class="attributeId" style="display:none">' + attribute.attributeId + '</span>' +
            '<span class="attributeValue">' + attribute.attributeValue + '</span>' +
            '<span class="price" style="display:none">' + attribute.price + '</span>' +
            '<span class="inventory" style="display:none">' + attribute.inventory + '</span>' +
            '</button>';
        return newAttr;
    }

    function fillShopInfo(shopInfo) {
        $(".shop").html(shopInfo.shopName);
        $(".shop").attr("href", "shop-fore.jsp?shopId=" + shopInfo.shopId);
    }

    function checkInventory() {
        $(".attribute").each(function() {
            // 库存为 0 时，标记为 disabled
            if ($(this).find(".inventory").html() == '0') {
                $(this).addClass("disabled");
            }
        })
    }

    $("body").delegate(".attribute", "click", function() {
        activeAttribute($(this));
    })

    function activeAttribute(newAttr) {
        if (newAttr == undefined || newAttr.hasClass("disabled")) {
            return;
        }

        //目前激活的属性带有 btn-primary class
        var oldActive = $(".btn-primary");
        if (oldActive != undefined) {
            oldActive.removeClass("btn-primary");
            oldActive.addClass("btn-default");
        }

        newAttr.removeClass("btn-default");
        newAttr.addClass("btn-primary");

        $(".goodsPrice").html(newAttr.find(".price").html());
    }

    $("body").delegate(".smallImage", "mouseenter", function() {
        $(this).css("border-style", "solid");
        $(this).css("border-color", "red");
        $(".bigImage").attr("src", $(this).attr("src"));
    })

    $("body").delegate(".smallImage", "mouseleave", function() {
        $(this).css("border-style", "none");
    })

    $(".decNum").click(function() {
        var goodsNum = parseInt($(".goodsNum").html());
        if (goodsNum > 1) {
            $(".goodsNum").html(goodsNum - 1);
        }
    })

    $(".incNum").click(function() {
        var goodsNum = parseInt($(".goodsNum").html());
        $(".goodsNum").html(goodsNum + 1);
    })

    $(".addToShoppingCart").click(function() {
        var attr = $(".btn-primary");
        if (attr != undefined) {
            $.get("../../addToShoppingCart.action", {
                goodsId: $(".goodsId").html(),
                attributeId: attr.find(".attributeId").html(),
                goodsNum: $(".goodsNum").html()
            }, function(response) {
                if (response.result == "true") {
                    window.location.href = window.location.href;
                }
            })
        }
    })

})