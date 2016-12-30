$(document).ready(function() {

    $.ajax({
        type: "get",
        url: "getCategory.action",
        async: false,
        success: function(response) {
            fillCategory(response);

            // 随机的一个大类
            var randomNam = Math.floor($(".levelOne").length * Math.random());
            var name = $(".levelOne").eq(randomNam).find("a").html();
            $("h3 .levelOneName").html(name);
            $(".levelOneName").attr("href", $(".levelOne").eq(randomNam).find("a").attr("href"));
            $.ajax({
                type: "get",
                url: "getGoodsImagesByLevelOne.action",
                data: {
                    levelOne: name,
                    imageNum: "12"
                },
                async: false,
                success: function(response) {
                    fillImages(response);
                }
            });
        }
    });

    function fillCategory(categorys) {
        var categoryRoot = $(".category-root");
        for (var i = 0; i < categorys.length; ++i) {
            categoryRoot.append(createLevelOne(categorys[i].name));
            var levelOneRoot = categoryRoot.find(".levelOne:last").find(".levelOneRoot");
            for (var j = 0; j < categorys[i].levelTwo.length; ++j) {
                levelOneRoot.append(createLevelTwo(levelOneRoot, categorys[i].levelTwo[j]));
            }
        }
    }

    function createLevelOne(levelOneName) {
        var levelOne = '<div class="levelOne">' +
                            '<div><a class="name" href="pages/core/goodsList.jsp?levelOne='+ levelOneName +'" target="_blank">'+ levelOneName +'</a><i class="glyphicon glyphicon-menu-right"></i></div>' +
                            '<div name="xz" class="levelOneRoot">' +
                            '</div>' +
                        '</div>';
        return levelOne;
    }

    function createLevelTwo(levelOneRoot, levelTwoName) {
        // alert(levelOneRoot.prev("a").attr("href"));
        var levelTwo =  '<div>' +
                            '<div><a class="levelTwo" href="' +
                            levelOneRoot.parents(".levelOne").find(".name").attr("href") + '&levelTwo=' + levelTwoName + '" target="_blank">'+
                            levelTwoName + '</a></div>' +
                        '</div>';
        // alert(levelTwo);
        return levelTwo;
    }

    function fillImages(goods) {
        if (goods.length > 0) {
            addImageToCarousel(1, goods[0]);
            if (goods.length > 1) {
                addImageToCarousel(2, goods[1]);
                if (goods.length > 2) {
                    addImageToCarousel(3, goods[2]);

                    var num = goods.length > 8 ? 8 : goods.length;
                    for (var i = 3; i < num; ++i) {
                        addBigImageToShow(goods[i]);
                    }

                    for (var i = 8; i < goods.length; ++i) {
                        addSmallImageToShow(goods[i]);
                    }
                }
            }
        }
    }
    function addImageToCarousel(slide, goods) {
        if (slide == 1) {
            slide = "first-slide";
        } else if (slide == 2) {
            slide = "second-slide";
        } else if (slide == 3) {
            slide = "third-slide";
        }

        // 设置轮播图
        $("." + slide).attr("src", goods.imageAddr);
        // 设置查看详情按钮
        $("." + slide).next().find("a").attr("href", "pages/core/goods.jsp?goodsId=" + goods.goodsId);
    }

    function addBigImageToShow(goods) {
        $(".show").append(createBigImage());
        var bigImage = $(".show bigImage:last");

        bigImage.find("a").attr("href", "pages/core/goods.jsp?goodsId=" + goods.goodsId)
        bigImage.find("img").attr("src", goos.imageAddr);
    }

    function createBigImage() {
        var bigImage = '<div class="col-lg-2 bigImage">' +
            '<a target="_blank"  href="">' +
            '<img alt="a picture" src="" class="thumbnail" style="width: 90%; height: 100px;" /></a>' +
            '</div>';
        return bigImage;
    }

    function addSmallImageToShow(goods) {
        $(".show").append(createSmallImage());
        var smallImage = $(".show smallImage:last");

        smallImage.find("a").attr("href", "pages/core/goods.jsp?goodsId=" + goods.goodsId)
        smallImage.find("img").attr("src", goos.imageAddr);
    }


    function createSmallImage() {
        var smallImage = '<div class="col-lg-1 smallImage">' +
            '<a target="_blank"  href="">' +
            '<img alt="a picture" src="" class="thumbnail" style="width: 90%; height: 45px;" /></a>' +
            '</div>';
        return smallImage;
    }
})
