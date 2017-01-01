$(document).ready(function() {
    $(".url").html(url("?"));

    var para = url('?');
    var levelOne = para.levelOne;
    var levelTwo = para.levelTwo;
    var keyword = para.keyword;
    if (keyword != undefined && keyword != "") {
        // 通过关键词
        $(".byCategory").css("display", "none");
        $(".keyword").html(keyword);
        getGoodsBriefByKeyword(keyword, 1);
    } else {
        if (levelTwo == undefined || levelTwo == "") {
            if (levelOne != undefined && levelOne != "") {
                // 通过 levelOne
                $(".levelOne").html(levelOne);
                $(".levelOne").attr("href", "goodsList.jsp?levelOne=" + levelOne);
                $(".levelTwo").remove();
                $(".bykeyword").remove();
                $.get("../../getCategory.action", {}, function(response) {
                    fillCategory(response);
                })
                getGoodsBriefByCategory(levelOne, levelTwo, 1);
            }
        } else if (levelOne != undefined && levelOne != "") {
            // 通过 levelOne && levelTwo
            $(".levelOne").html(levelOne);
            $(".levelOne").attr("href", "goodsList.jsp?levelOne=" + levelOne);
            $(".levelTwo").html(levelTwo);
            $(".levelTwo").attr("href", $(".url").html());
            $(".bykeyword").remove();
            $.get("../../getCategory.action", {}, function(response) {
                fillCategory(response);
            })
            getGoodsBriefByCategory(levelOne, levelTwo, 1);
        }
    }


    function fillCategory(categorys) {
        var levelOne = url('?', $(".url").html()).levelOne;
        for (var i = 0; i < categorys.length; ++i) {
            if (levelOne == categorys[i].name) {
                for (var j = 0; j < categorys[i].levelTwo.length; ++j) {
                    $(".levelTwoList").append(createLevelTwo(levelOne, categorys[i].levelTwo[j]));
                }
            }
        }
    }

    function createLevelTwo(levelOneName, levelTwoName) {
        var levelTwo =
            '<div><a class="levelTwo" href="goodsList.jsp?levelOne=' + levelOneName + '&levelTwo=' + levelTwoName + '">' +
            levelTwoName + '</a></div>';
        return levelTwo;
    }

    function getGoodsBriefByKeyword(keyword, pageNum, sortByPrice, priceUp) {
        if (sortByPrice == undefined) {
            sortByPrice = "false"; //未定义时为 false
            priceUp = "false";
        } else if (priceUp == undefined) {
            priceUp = "true"; //未定义时升序
        }

        $.get("../../getGoodsBriefByKeyword.action", {
            keyword: keyword,
            pageNum: pageNum,
            maxNumInOnePage: "20",
            sortByPrice: sortByPrice,
            priceUp: priceUp
        }, function(response) {
            fillGoods(response);
            checkPage(20);
        })
    }

    function fillGoods(goods) {
        $(".brief").remove();
        for (var i = 0; i < goods.length; ++i) {
            $(".goodsList").append(createGoods(goods[i]));
        }
    }

    function createGoods(goods) {

        if (goods.goodsName.length > 12) {
            goods.goodsName = goods.goodsName.substr(0, 12) + "...";
        }
        if (goods.goodsDescribe.length > 12) {
            goods.goodsDescribe = goods.goodsDescribe.substr(0, 12) + "...";
        }

        var newGoods = '<div class="brief col-sm-3 col-md-3">' +
            '<div class="thumbnail">' +
            '<img class="img-responsive" src="' + goods.imageAddr + '" style="height: 200px; width:auto;" alt="图片离家出走了...">' +
            '<div class="caption">' +
            '<span style="display: none;" class="goodsId">' + goods.goodsId + '</span>' +
            '<span style="display: none;" class="attributeId">' + goods.attributeId + '</span>' +
            '<h3>￥<span class="price">' + goods.price + '</span></h3>' +
            '<p>已售 <span class="sales">' + goods.sales + '</span></p>' +
            '<p class="goodsName">' + goods.goodsName + '</p>' +
            '<p class="goodsDescribe">' + goods.goodsDescribe + '</p>' +
            '<div class="btn-group" style="margin-left: 20px">' +
            '<a class="btn btn-danger addToFavorite" href="javascript:;"><span class="glyphicon glyphicon-heart-empty"></span></a>' +
            '<a class="btn btn-default" href="goods.jsp?goodsId=' + goods.goodsId + '" target="_blank">查看详情</a>' +
            '<a class="btn btn-success addToshoppingCart" href="javascript:;"><span class="glyphicon glyphicon-shopping-cart"></span></a>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        return newGoods;
    }

    $("body").delegate(".addToFavorite", "click", function() {
        $.get("../../addGoodsToFavorite.action", {
            goodsId: $(this).parents(".brief").find(".goodsId").html()
        })
        $(this).find("span").removeClass("glyphicon-heart-empty");
        $(this).find("span").addClass("glyphicon-heart");
    })

    $("body").delegate(".addToshoppingCart", "click", function() {
        $.get("../../addToShoppingCart.action", {
            goodsId: $(this).parents(".brief").find(".goodsId").html(),
            attributeId: $(this).parents(".brief").find(".attributeId").html(),
            goodsNum: "1"
        }, function(response) {
            if (response.result == "true") {
                var curNum = parseInt($(".badge").html());
                $(".badge").html(curNum + 1);
            }
        })
    })

    function getGoodsBriefByCategory(levelOne, levelTwo, pageNum, sortByPrice, priceUp) {

        if (sortByPrice == undefined) {
            sortByPrice = "false";
            priceUp = "false";
        }
        if (priceUp == undefined) {
            priceUp = "true";
        }
        if (levelTwo == undefined) {
            $.get("../../getGoodsBriefByCategory.action", {
                levelOne: levelOne,
                pageNum: pageNum,
                maxNumInOnePage: "20",
                sortByPrice: sortByPrice,
                priceUp: priceUp
            }, function(response) {
                fillGoods(response);
                checkPage(20);
            })
            return;
        }
        $.get("../../getGoodsBriefByCategory.action", {
            levelOne: levelOne,
            levelTwo: levelTwo,
            pageNum: pageNum,
            maxNumInOnePage: "20",
            sortByPrice: sortByPrice,
            priceUp: priceUp
        }, function(response) {
            fillGoods(response);
            checkPage(20);
        })
    }

    $(".sales").click(function() {
        $(".sortByPrice").removeClass("active");
        $(".pageNum").html("1");
        getPage(1);
    })

    $(".sortByPrice").click(function() {
        $(this).addClass("active");
        var arrow = $(this).find("span");
        if (arrow.hasClass("glyphicon-arrow-down")) {
            arrow.removeClass("glyphicon-arrow-down");
            arrow.addClass("glyphicon-arrow-up");
            $(".pageNum").html("1");
            getPage(1);
        } else if (arrow.hasClass("glyphicon-arrow-up")) {
            arrow.removeClass("glyphicon-arrow-up");
            arrow.addClass("glyphicon-arrow-down");
            $(".pageNum").html("1");
            getPage(1);
        }
    })

    $(".previous").click(function() {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        $(".brief").slideToggle();
        $(".brief").remove();

        getPage(curPage - 1);
        $(".pageNum").html(curPage - 1);
    })

    $(".next").click(function() {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        $(".brief").slideToggle();
        $(".brief").remove();

        getPage(curPage + 1);
        $(".pageNum").html(curPage + 1);
    })

    function getPage(pageNum) {
        var para = url("?", $(".url").html());
        var levelOne = para.levelOne;
        var levelTwo = para.levelTwo;
        var keyword = para.keyword;
        var pageContent = -1;
        if (keyword != undefined && keyword != "") {
            var sort = $(".sortByPrice");
            if (sort.hasClass("active")) {
                if (sort.find("span").hasClass("glyphicon-arrow-down")) {
                    // 关键词 价格降序
                    getGoodsBriefByKeyword(keyword, pageNum, "true", "false")
                } else {
                    //关键词 价格升序
                    getGoodsBriefByKeyword(keyword, pageNum, "true", "true")
                }
            } else {
                //关键词销量降序
                getGoodsBriefByKeyword(keyword, pageNum);
            }
        } else {
            var sort = $(".sortByPrice");
            if (sort.hasClass("active")) {
                if (sort.find("span").hasClass("glyphicon-arrow-down")) {
                    //分类 价格降序
                    getGoodsBriefByCategory(levelOne, levelTwo, pageNum, "true", "false");
                } else {
                    //分类 价格升序
                    getGoodsBriefByCategory(levelOne, levelTwo, pageNum, "true", "true");
                }
            } else {
                //分类 销量降序
                getGoodsBriefByCategory(levelOne, levelTwo, pageNum);
            }
        }
    }

    function checkPage(maxNumInOnPage) {
        var curPage = $(".pageNum").html();
        if (curPage == "1")
            $(".previous").addClass("disabled");
        else
            $(".previous").removeClass("disabled");

        var brief = $(".brief");
        var hasNext = (brief.length == maxNumInOnPage);
        if (hasNext)
            $(".next").removeClass("disabled");
        else
            $(".next").addClass("disabled");
    }
});