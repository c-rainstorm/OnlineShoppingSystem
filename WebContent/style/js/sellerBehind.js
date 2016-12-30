$(document).ready(function() {

    var pageContent = "all";

    //店铺管理

    $.get("../../getShopInfo.action", {}, function(shop1) {
        shop1 = $.parseJSON(shop1);
        var curShop = $("#shopManagePage");
        curShop.find("#shopName1").attr("value", shop1.shopName);
        curShop.find("#phone1").attr("value", shop1.phone);
        curShop.find("#evaluateSum1").attr("value", shop1.evaluateSum);
        curShop.find("#evaluateNum1").attr("value", shop1.evaluateNumber);
        curShop.find("#address1").attr("value", shop1.address);
        curShop.find("#shopDescribe1").val(shop1.shopDescribe);
        curShop.find("#announcement1").val(shop1.announcement);
    })

    $("#shopManage").click(function() {
        pageContent = "shop";
        $("#shopManagePage").css("display", "inline");
        $("#goodsManagePage").css("display", "none");
        $("#ordersManagePage").css("display", "none");
        $("#saleStatisticsPage").css("display", "none");
    })

    $("#shopInfoModify").click(function() {
        $("#shopManageForm1").css("display", "none");
        $("#shopManageForm2").css("display", "inline");
        var curShop = $("#shopManagePage");
        curShop.find("#shopName2").attr("value", $("#shopName1").val());
        curShop.find("#phone2").attr("value", $("#phone1").val());
        curShop.find("#address2").attr("value", $("#address1").val());
        curShop.find("#evaluateSum2").attr("value", $("#evaluateSum1").val());
        curShop.find("#evaluateNum2").attr("value", $("#evaluateNum1").val());
        curShop.find("#announcement2").val($("#announcement1").val());
        curShop.find("#shopDescribe2").val($("#shopDescribe1").val());
    })

    $("#shopInfoGoback").click(function() {
        $("#shopManageForm1").css("display", "inline");
        $("#shopManageForm2").css("display", "none");
    })

    $("#phone2").on('keyup blur change', function() {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");
        if(phone.match(/^\d{11}$/g) == null) {
            addError(parent, "请输入11位手机号码！");
            return;
        }
        removeError(parent);
    })

    $("#shopName2").on('keyup blur change', function() {
        var parent = $(this).parent();
        var shopName = $(this).val().replace(/^\s+|\s+$/g, "");
        if(shopName == "") {
            addError(parent, "请输入店铺名！");
            return;
        }
        removeError(parent);
    })

    $("#address2").on('keyup blur change', function() {
        var parent = $(this).parent();
        var address = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if(address == "") {
            addError(parent, "请输入店铺地址！");
            return;
        }
        removeError(parent);
    })

    function addError(object, tip) {
        if(object.hasClass("has-info")) {
            object.removeClass("has-info");
            object.addClass("has-error");
            object.find(".help-block").html(tip);
        }
        if(object.hasClass("has-success")) {
            object.removeClass("has-success");
            object.addClass("has-error");
            object.find(".help-block").html(tip);
        }
    }

    function removeError(object) {
        if(object.hasClass("has-error")) {
            object.removeClass("has-error");
            object.addClass("has-success");
            object.find(".help-block").html("");
        }
        if(object.hasClass("has-info")) {
            object.removeClass("has-info");
            object.addClass("has-success");
            object.find(".help-block").html("");
        }
    }

    $('.form-group input[type="text"],.form-group input[type="file"],.form-group textarea').on('focus', function() {
        removeError($(this).parent());
    });

    //商品管理

    $("#goodsManage").click(function() {
        pageContent = "goods";
        showGoodsManage();
    })

    function showGoodsManage() {

        $("#shopManagePage").css("display", "none");
        $("#goodsManagePage").css("display", "inline");

        $("#goodsIntro").css("display", "inline");
        $("#goodsAddPage").css("display", "none");
        $("#goodsDetailPage").css("display", "none");
        $("#goodsManageForm1").css("display", "none");
        $("#goodsManageForm2").css("display", "none");
        $(".goodsBriefs").find(".goodsBrief").remove();
        $(".pageNum").html("1");
        getGoodsBrief(1);

        $("#ordersManagePage").css("display", "none");
        $("#saleStatisticsPage").css("display", "none");

    }

    function getGoodsBrief(page) {

        $.get("../../getGoodsBriefs.action", {
                page: page
            },
            function(briefs) {

                briefs = $.parseJSON(briefs);

                $.each(briefs, function(i, item) {
                    var newBrief = createGoodsBrief();
                    $(".goodsBriefs").append(newBrief);
                    var brief = $(".goodsBrief:last");

                    brief.find(".goodsId").html(item.goodsId);
                    brief.find(".goodsName").html(item.goodsName);
                    brief.find(".sales").html(item.sales);

                    brief.slideDown();
                })
            })

        setTimeout(checkPage, 500);
    }

    function createGoodsBrief() {
        var newBrief = "<div class=\"row goodsBrief\" style=\"display:none\">" +
            "<hr>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"goodsId\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"goodsName\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"sales\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<a href=\"javascript:;\" class=\"showGoodsDetail form-control btn btn-success\" style=\"margin: 3px 12px;\">查看详情</a>" +
            "</div>" +
            "</div>"
        return newBrief;
    }

    $(".previous").click(function() {

        if($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        if(pageContent == "goods") {
            $(".goodsBriefs").find(".goodsBrief").remove();
            getGoodsBrief(curPage - 1);
        }

        if(pageContent == "unfinishOrders") {
            $(".unfinishBriefs").find(".unfinishBrief").remove();
            getUnfinishBrief(curPage - 1);
        }

        if(pageContent == "historyOrders") {
            $(".historyBriefs").find(".historyBrief").remove();
            getHistoryBrief(curPage - 1);
        }

        $(".pageNum").html(curPage - 1);
    })

    $(".next").click(function() {

        if($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        if(pageContent == "goods") {
            $(".goodsBriefs").find(".goodsBrief").remove();
            getGoodsBrief(curPage + 1);
        }

        if(pageContent == "unfinishOrders") {
            $(".unfinishBriefs").find(".unfinishBrief").remove();
            getUnfinishBrief(curPage + 1);
        }

        if(pageContent == "historyOrders") {
            $(".historyBriefs").find(".historyBrief").remove();
            getHistoryBrief(curPage + 1);
        }

        setTimeout(checkPage, 500);

        $(".pageNum").html(curPage + 1);
    })

    function checkPage() {

        var curPage = $(".pageNum").html();

        if(curPage == "1")
            $(".previous").addClass("disabled");
        else
            $(".previous").removeClass("disabled");

        var hasNext = true;

        if(pageContent == "goods") {
            hasNext = ($(".goodsBrief").length == 10);
        }

        if(pageContent == "unfinishOrders") {
            hasNext = ($(".unfinishBrief").length == 10);
        }

        if(pageContent == "historyOrders") {
            hasNext = ($(".historyOrderBrief").length == 10);
        }

        if(hasNext)
            $(".next").removeClass("disabled");
        else
            $(".next").addClass("disabled");

    }

    $("#goodsAddBtn").click(function() {
        $("#goodsIntro").css("display", "none");
        cleanGoodsAdd();
      //  createCategory();
        $("#goodsAddPage").css("display", "inline");
    })

    function cleanGoodsAdd() {
        $("#goodsAddPage").find('input[type="text"],input[type="file"],textarea').val("");
        $("#goodsAddPage").find(".goodsAttr").remove();
    }

    $("#goodsName").on('keyup blur change', function() {
        var parent = $(this).parent();
        var shopName = $(this).val().replace(/^\s+|\s+$/g, "");
        if(shopName == "") {
            addError(parent, "请输入商品名！");
            return;
        }
        removeError(parent);
    })

    function createCategory() {
        $.ajax({
            url: "../../getCategory.action",
            success: function(catetory) {
                var curCatetory = $.parseJSON(catetory);
                //建立一级分类
                var option = "<option>请选择一级分类</option>";
                for(var i = 0; i < curCatetory.length; i++) {
                    option += "<option value='" + curCatetory[i].name + "'>" + curCatetory[i].name + "</option>";
                }
                $("#FirstCatetoryOption").append(option);
                //建立二级分类
                $("#FirstCatetoryOption").change(function(curCatetory) {

                    var FirstId = $(this).val();

                    if(FirstId != "") {
                        $("#SecondCategoryOption").empty();
                        for(var i = 0; i < curCatetory.length; i++) {
                            if(curCatetory[i].name = FirstId) {
                                var SecondId = curCatetory[i].levelTwo;
                            }
                        }
                        for(var i = 0; i < SecondId.length; i++) {
                            option += "<option value='" + SecondId[i] + "'>" + SecondId[i] + "</option>";
                        }
                        $("#SecondCatetoryOption").append(option);
                    }

                });

            }
        });
    }

    function createGoodsAttr() {
        var newGoodsAttr = "<div class=\"row goodsAttr\" style=\"display:inline\">" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<div class=\"form-group has-info\">" +
            "<input type=\"text\" name=\"value\" class=\"form-control input-sm \" placeholder=\"属性值\">" +
            "<p class=\"help-block\"></p>" +
            "</div>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<div class=\"form-group has-info\">" +
            "<input type=\"text\" name=\"cost\" class=\"form-control input-sm\" placeholder=\"进价\">" +
            "<p class=\"help-block\"></p>" +
            "</div>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<div class=\"form-group has-info\">" +
            "<input type=\"text\" name=\"price\" class=\"form-control input-sm\" placeholder=\"售价\">" +
            "<p class=\"help-block\"></p>" +
            "</div>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<div class=\"form-group has-info\">" +
            "<input type=\"text\" name=\"inventory\" class=\"form-control input-sm\" placeholder=\"库存\">" +
            "<p class=\"help-block\"></p>" +
            "</div>" +
            "</div>" +
            "</div>"

        return newGoodsAttr;
    }

    $(".value").on('keyup blur change', function() {
        var parent = $(this).parent();
        var value = $(this).val().replace(/^\s+|\s+$/g, "");
        if(value == "") {
            addError(parent, "请输入属性！");
            return;
        }
        removeError(parent);
    })

    $(".price").on('keyup blur change', function() {
        var parent = $(this).parent();
        var price = $(this).val().replace(/^\s+|\s+$/g, "");
        if(price == "") {
            addError(parent, "请输入售价！");
            return;
        }
        removeError(parent);
    })

    $(".cost").on('keyup blur change', function() {
        var parent = $(this).parent();
        var cost = $(this).val().replace(/^\s+|\s+$/g, "");
        if(cost == "") {
            addError(parent, "请输入成本！");
            return;
        }
        removeError(parent);
    })

    $(".inventory").on('keyup blur change', function() {
        var parent = $(this).parent();
        var inventory = $(this).val().replace(/^\s+|\s+$/g, "");
        if(inventory == "") {
            addError(parent, "请输入库存！");
            return;
        }
        removeError(parent);
    })

    $(".goodsAttrDelete").click(function() {
        var parent = $(this).parent().parent();
        parent.find(".goodsAttr:last").remove();
    })

    $(".goodsAttrAdd").click(function() {
        var newGoodsAttr = createGoodsAttr();
        var parent = $(this).parent().parent();
        parent.find(".goodsAttrForm").append(newGoodsAttr);
    })

    $("#goodsAddGoback").click(function() {
        $("#goodsIntro").css("display", "inline");
        $("#goodsAddPage").css("display", "none");
    })

    function attribute(value, cost, price, inventory) {
        var attr = new Object();
        attr.value = value;
        attr.cost = cost;
        attr.price = price;
        attr.inventory = inventory;
        return attr;
    }

    $("#goodsInfoSave").click(function() {

        if($("#goodsAddPage").find(".goodsAttr").length == 0) {
            alert("请至少创建一个属性！");
        } else {
            var firstCatetoryContent = $("#firstCatetoryOption").find("[selected='selected']").val();
            $("#firstCatetory").val(firstCatetoryContent);
            var secondCatetoryContent = $("#secondCatetoryOption").find("[selected='selected']").val();
            $("#secondCatetory").val(secondCatetoryContent);

            var attributes = new Array();
            var length = $("#goodsAddPage").find(".goodsAttr").length;
            for(var i = 0; i < length; i++) {
                var curAttr = $("#goodsAddPage").find(".goodsAttr:eq(" + i + ")");
                attributes[i] = attribute(curAttr.find("input:eq(0)").val(), curAttr.find("input:eq(1)").val(), curAttr.find("input:eq(2)").val(), curAttr.find("input:eq(3)").val());
            }

            $.ajax({
                type: "post",
                async: false,
                data: new FormData($("#goodsForm")[0]),
                processData: false,
                contentType: false,
                url: "../../addGoods.action",
                success: function(goodsId) {
                    $.ajax({
                        type: "post",
                        url: "../../addGoodsAttr.action",
                        async: false,
                        data: {
                            goodsId: goodsId,
                            attributes: JSON.stringify(attributes)
                        },
                        success: function() {
                            $("#goodsIntro").css("display", "inline");
                            $("#goodsAddPage").css("display", "none");
                        },
                    });
                },
            });
        }

    })

    $("body").delegate(".showGoodsDetail", "click", function() {
        var curGoodsId = $(this).parents(".goodsBrief").find(".goodsId").html();
        $("#goodsIntro").css("display", "none");
        $("#goodsDetailPage").css("display", "inline");
        $("#goodsManageForm1").css("display", "inline");
        $("#goodsManageForm2").css("display", "none");
        cleanGoodsDetail();
        getGoodsDetail(curGoodsId);
    })

    function cleanGoodsDetail() {
        $("#goodsManageForm1").find('input[type="text"],input[type="file"]').val("");
        $("#goodsManageForm1").find(".goodsAttr").remove();
        
    }

    function getGoodsDetail(goodsId) {

        $("#goodsId1").val(goodsId);

        $.get("../../getGoodsInfo.action", {
                goodsId: goodsId
            },
            function(goodsInfo) {

                goodsInfo = $.parseJSON(goodsInfo);
                curGoodsInfo = $("#goodsManageForm1");

                curGoodsInfo.find("#goodsName1").val(goodsInfo.goodsName);
                curGoodsInfo.find("#firstCategory1").val(goodsInfo.firstCategory);
                curGoodsInfo.find("#secondCategory1").val(goodsInfo.secondCategory);
                curGoodsInfo.find("#discountDeadline1").val((goodsInfo.discountDeadline).substring(0,10));
                curGoodsInfo.find("#discountRate1").val(goodsInfo.discountRate);
                curGoodsInfo.find("#goodsDescribe1").val(goodsInfo.goodsDescribe);

                for(var i = 0; i < goodsInfo.goodsImagesUrl.length; i++) {
                    var img = "<img src'" + goodsInfo.goodsImagesUrl[i] + "' alt='商品图片' />";
                    $("#goodsManageForm1").find(".goodsImages").append(img);
                }

            });

        $.get("../../getGoodsAttrs.action", {
                goodsId: goodsId
            },
            function(attributes) {

                attributes = $.parseJSON(attributes);
                
                for(var i=0;i<attributes.length;i++){
                	
                	var newAttr = createGoodsAttr();
                    var goodsAttrForm = $("#goodsManageForm1").find(".goodsAttrForm");
                    goodsAttrForm.find(".row").append(newAttr);
                    var attribute = goodsAttrForm.find(".goodsAttr:last");

                    attribute.find("input:eq(0)").val(attributes[i].attributeValue);
                    attribute.find("input:eq(1)").val(attributes[i].cost);
                    attribute.find("input:eq(2)").val(attributes[i].price);
                    attribute.find("input:eq(3)").val(attributes[i].inventory);
                }
                
                var goodsAttrForm = $("#goodsManageForm1").find(".goodsAttrForm");
                goodsAttrForm.find("input").attr("readonly", "readonly");

            });
    }

    $("#goodsDelete").click(function() {

        var curGoodsId = $("#goodsId1").val();

        $.ajax({
            type: "post",
            url: "../../deleteGoods.action",
            data: {
                goodsId: curGoodsId
            },
            success: function() {
                alert("商品已删除！");
                showGoodsManage();
            },
        });

    })

    $("#goodsInfoModify").click(function() {
        
        $("#goodsManageForm1").css("display", "none");
        cleanGoodsModify();
        $("#goodsManageForm2").css("display", "inline");
        $("#goodsId2").val($("#goodsId1").val());
        $("#goodsName2").val($("#goodsName1").val());
        $("#goodsDescribe2").val($("#goodsDescribe1").val());
        $("#firstCategory2").val($("#firstCategory1").val());
        $("#secondCategory2").val($("#secondCategory1").val());
        $("#discountDeadline2").val($("#discountDeanline1").val());
        $("#discountRate2").val($("#discountRate1").val());
        
        $("#goodsImage1").clone().appendTo("#goodsImage2");
        
        $("#goodsManageForm1").find(".goodsAttrForm").clone().insertAfter("#goodsBasicInfo2");
        
        var goodsAttrForm = $("#goodsManageForm2").find(".goodsAttrForm");
        goodsAttrForm.find("input").removeAttr("readonly");
        
        var altFormat = $("#discountDeadline2").datepicker("option", "altFormat");
        $("#discountDeadline2").datepicker({dateFormat: "yy-mm-dd"});
    })

    function cleanGoodsModify() {
        $("#goodsManageForm2").find('input[type="text"],input[type="file"]').val("");
        $("#goodsManageForm2").find(".goodsAttrForm").remove();
        $("#goodsManageForm2").find("#goodsImage2").empty();
    }

    $("#goodsDetailGoback").click(function() {
        $("#goodsManageForm1").css("display", "inline");
        $("#goodsManageForm2").css("display", "none");
    })

    $("#discountRate2").on('keyup blur change', function() {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");
        if(phone.match(/^(0(\.\d+)?|1(\.0+)?)$/)) {
            addError(parent, "请输入0~1的数字！");
            return;
        }
        removeError(parent);
    })

    $("#goodsModifySave").click(function() {

        if($("#goodsManageForm2").find(".goodsAttr").length == 0) {
            alert("请至少创建一个属性！");
        } else {
        	var curGoodsId = $("#goodsId2").val();
        	
            var attributes = new Array();
            var length = $("#goodsManageForm2").find(".goodsAttr").length;
            for(var i = 0; i < length; i++) {
                var curAttr = $("#goodsManageForm2").find(".goodsAttr:eq(" + i + ")");
                attributes[i] = attribute(curAttr.find("input:eq(0)").val(), curAttr.find("input:eq(1)").val(), curAttr.find("input:eq(2)").val(), curAttr.find("input:eq(3)").val());
            }

            $.ajax({
                type: "post",
                url: "../../updateGoodsAttr.action",
                async: false,
                data: {
                    goodsId: curGoodsId,
                    attributes: JSON.stringify(attributes)
                },
                success: function() {
                	alert("已更新商品信息！");
                	
                }
            });
            
            $.ajax({
                type: "post",
                async: false,
                processData: false,
                contentType: false,
                data: new FormData($("#goodsBasicInfo2")[0]),
                url: "../../updateGoodsInfo.action",
                success: function() {
                	showGoodsManage();
                	
                }
            });
        }

        

    })

    //订单管理

    $("#ordersManage").click(function() {
    	showOrderManage(); 
    })
    
    function showOrderManage(){
    	$("#shopManagePage").css("display", "none");
        $("#goodsManagePage").css("display", "none");

        $("#ordersManagePage").css("display", "inline");
        $("#ordersIntro").css("display","inline");
        $("#orderDetailPage").css("display","none");
        $(".unfinishBriefs").css("display", "inline");
        $(".unfinishBrief").remove();
        $(".historyBrief").remove();
        pageContent = "unfinishOrders";
        $(".pageNum").html("1");
        getUnfinishBrief(1);

        $("#saleStatisticsPage").css("display", "none");
    }
    
    $("#unfinishButton").click(function() {
        pageContent = "unfinishOrders";
        $(".unfinishBriefs").css("display", "inline");
        $(".unfinishBrief").remove();
        $(".historyBriefs").css("display", "none");
        $(".historyBrief").remove();
        $(".pageNum").html("1");
        getUnfinishBrief(1);
    })

    function getUnfinishBrief(page) {

        $.get("../../getUnfinishedOrder.action", {
                page: page
            },
            function(briefs) {

                briefs = $.parseJSON(briefs);

                $.each(briefs, function(i, item) {
                    var newBrief = createUnfinishBrief();
                    $(".unfinishBriefs").append(newBrief);
                    var brief = $(".unfinishBrief:last");

                    brief.find(".orderId").html(item.orderId);
                    brief.find(".orderTime").html(item.orderTime);
                    brief.find(".total").html(item.total);

                    brief.slideDown();
                })
            })

        setTimeout(checkPage, 500);
    }

    function createUnfinishBrief() {
        var newBrief = "<div class=\"row unfinishBrief\" style=\"display:none\">" +
            "<hr>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"orderId\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"orderTime\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"total\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<a href=\"javascript:;\" class=\"showUnfinishDetail form-control btn btn-success\" style=\"margin: 3px 12px;\">查看详情</a>" +
            "</div>" +
            "</div>"
        return newBrief;
    }

    $("#historyButton").click(function() {
        pageContent = "historyOrders";
        $(".unfinishBriefs").css("display", "none");
        $(".historyBriefs").css("display", "inline");
        $(".unfinishBrief").remove();
        $(".historyBrief").remove();
        $(".pageNum").html("1");
        getHistoryBrief(1);
    })

    function getHistoryBrief(page) {

        $.get("../../getHistoryOrder.action", {
                page: page
            },
            function(briefs) {

                briefs = $.parseJSON(briefs);

                $.each(briefs, function(i, item) {
                    var newBrief = createHistoryBrief();
                    $(".historyBriefs").append(newBrief);
                    var brief = $(".historyBrief:last");

                    brief.find(".orderId").html(item.orderId);
                    brief.find(".completeTime").html(item.completeTime);
                    brief.find(".total").html(item.total);

                    brief.slideDown();
                })
            })

        setTimeout(checkPage, 500);
    }

    function createHistoryBrief() {
        var newBrief = "<div class=\"row historyBrief\" style=\"display:none\">" +
            "<hr>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"orderId\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"completeTime\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p class=\"total\"></p>" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<a href=\"javascript:;\" class=\"showHistoryDetail form-control btn btn-success\" style=\"margin: 3px 12px;\">查看详情</a>" +
            "</div>" +
            "</div>"
        return newBrief;
    }

    $("body").delegate(".showUnfinishDetail", "click", function() {
    	var curOrderId = $(this).parents(".unfinishBrief").find(".orderId").html();
    	
    	$("#ordersIntro").css("display","none");
    	$("#orderDetailPage").css("display", "inline");
        
        cleanOrderDetail();
        getOrderDetail(curOrderId);
    })
    
    $("body").delegate(".showHistoryDetail", "click", function() {
    	var curOrderId = $(this).parents(".historyBrief").find(".orderId").html();
    	$("#ordersIntro").css("display","none");
        $("#orderDetailPage").css("display", "inline");
        
        cleanOrderDetail();
        getOrderDetail(curOrderId);
    })

    function cleanOrderDetail() {
        $("#orderDetailPage").find('input[type="text"]').val("");
        $("#orderDetailPage").find(".goodsOrder").remove();
        if(pageContent == "historyOrders") {
            $("#trackNum").attr("readonly", "readonly");
            $("#sendGoods").css("display", "none");
        }
        if(pageContent == "unfinishOrders") {
            $("#trackNum").removeAttr("readonly");
            $("#sendGoods").css("display", "inline");
        }
    }

    function getOrderDetail(orderId) {

        $.get("../../getOrderDetail.action", {
                orderId: orderId
            },
            function(orderInfo) {

                orderInfo = $.parseJSON(orderInfo);
                curOrderInfo = $("#orderDetailPage");

                curOrderInfo.find("#orderId").val(orderInfo.orderId);
                curOrderInfo.find("#userId").val(orderInfo.userId);
                curOrderInfo.find("#orderStatus").val(orderInfo.orderStatus);
                curOrderInfo.find("#receiverName").val(orderInfo.receiver.receiverName);
                curOrderInfo.find("#receiverPhone").val(orderInfo.receiver.receiverPhone);
                curOrderInfo.find("#receiverAddress").val(orderInfo.receiver.receiverAddress);
                curOrderInfo.find("#trackNum").val(orderInfo.trackingNumber);
                curOrderInfo.find("#annotation").val(orderInfo.annotation);
                curOrderInfo.find("#orderTime").val(orderInfo.orderTime);
                curOrderInfo.find("#completeTime").val(orderInfo.completeTime);
                curOrderInfo.find("#payMethod").val(orderInfo.payMethod);
                curOrderInfo.find("#total").val(orderInfo.total);

                for(var i=0;i<orderInfo.goodsInOrder.length;i++) {
                    
                	var order = createGoodsOrder();
                    $(".goodsInOrder").append(order);
                    var brief = $(".goodsOrder:last");

                    brief.find("input:eq(0)").val(orderInfo.goodsInOrder[i].goods.goodsId);
                    brief.find("input:eq(1)").val(orderInfo.goodsInOrder[i].goods.goodsName);
                    brief.find("input:eq(2)").val(orderInfo.goodsInOrder[i].attributeValue);
                    brief.find("input:eq(3)").val(orderInfo.goodsInOrder[i].goodsNum);
                    brief.find("input:eq(4)").val(orderInfo.goodsInOrder[i].cost);

                    brief.slideDown();
                }
            });

    }

    function createGoodsOrder() {
        var GoodsOrder = "<br><div class=\"row goodsOrder\">" +
            "<div class=\"col-xs-2 col-sm-2 col-md-2\">" +
            "<input type=\"text\" name=\"goodsId\" class=\"form-control input-sm\">" +
            "</div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<input type=\"text\" name=\"goodsName\" class=\"form-control input-sm\">" +
            "</div>"+
        "<div class=\"col-xs-3 col-sm-3 col-md-3\">" +
        "<input type=\"text\" name=\"attribute\" class=\"form-control input-sm\">" +
        "</div>"+
        "<div class=\"col-xs-2 col-sm-2 col-md-2\">" +
        "<input type=\"text\" name=\"goodsNum\" class=\"form-control input-sm\">" +
        "</div>"+
        "<div class=\"col-xs-2 col-sm-2 col-md-2\">" +
        "<input type=\"text\" name=\"cost\" class=\"form-control input-sm\">" +
        "</div>" +
        "</div>"

        return GoodsOrder;
    }

    $("#orderDetailGoback").click(function() {
        $("#ordersIntro").css("display", "inline");
        $("#orderDetailPage").css("display", "none");
    })

    $("#trackNum").on('keyup blur change', function() {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");
        if(phone.match(/^\d{12}$/g) == null) {
            addError(parent, "请输入12位运单号！");
            return;
        }
        removeError(parent);
    })

    $("#sendGoods").click(function() {
        $.ajax({
            type: "post",
            url: "../../sendGoods.action",
            async: false,
            data: {
                orderId: $("#orderId").val(),
                trackingNumber: $("#trackNum").val()
            },
            success: function() {
                alert("已发货！");
            	showOrderManage();
            }
        });

    })

    //销售统计

    var searchContent = "All";

    $("#saleStatistics").click(function() {
        $("#shopManagePage").css("display", "none");
        $("#goodsManagePage").css("display", "none");
        $("#ordersManagePage").css("display", "none");
        $("#saleStatisticsPage").css("display", "inline");
        
        searchContent = "Total";
        $(".totalSales").css("display","inline");
        $(".singleSales").css("display","none");
        $("#searchId").css("display", "none");
        $("#flotContainer").empty();
        $("#searchFoodsId").val("");
        $("#searchDays").val("");
    })

    $("#totalSalesBtn").click(function() {
        searchContent = "Total";
        $("#searchId").css("display", "none");
        $(".totalSales").css("display","inline");
        $(".singleSales").css("display","none");
        $("#flotContainer").empty();
        $("#searchDays").val("");
    })

    $("#singleSalesBtn").click(function() {
        searchContent = "Single";
        $("#searchId").css("display", "inline");
        $(".totalSales").css("display","none");
        $(".singleSales").css("display","inline");
        $("#flotContainer").empty();
        $("#searchFoodsId").val("");
        $("#searchDays").val("");
    })

    function data(date,sale) {
        var data = new Object();
        data.date = date;
        data.sale = sale;
        return data;
    }
    
    $("#salesBtn").click(function() {
        
    	var datas = new Array();
    	
    	if(searchContent == "Total") {
            $.ajax({
                type: "post",
                url: "../../getTotalSales.action",
                async: false,
                data: {
                    days: $("#searchDays").val(),
                },
                success: function(datal) {
                    datal=JSON.parse(datal);
                	var length=datal.length;
                	for(var i=0;i<length;i++){
                		var temp = new Array();
                		temp[0] = parseInt(datal[i].date);
                		temp[1] = parseInt(datal[i].value);
                		datas[i]= temp;
                	}
                	
                	$.plot($("#flotContainer"), [{
                        data: datas,
                        lines: {
                            show: true
                        }
                    }]);
                }
            });
        }
        
    	if(searchContent == "Single") {
           $.ajax({
                type: "post",
                url: "../../getSingleSales.action",
                async: false,
                data: {
                    goodsId:$("#searchGoodsId").val(),
                    days: $("#searchDays").val(),
                },
                success: function(datal) {
                    
                	datal=JSON.parse(datal);
                	var length=datal.length;
                	for(var i=0;i<length;i++){
                		var temp = new Array();
                		temp[0] = parseInt(datal[i].date);
                		temp[1] = parseInt(datal[i].value);
                		datas[i]= temp;
                	}
                	
                	$.plot($("#flotContainer"), [{
                        data: datas,
                        lines: {
                            show: true
                        }
                    }]);
                }
            });
        }
    })

})
