$(document).ready(function() {
    var pageContent = "all";
    
    //店铺管理
    $.get("../../GetShopInfo.action", {}, function(shop) {
        shop = $.parseJSON(shop);

        var curShop = $("#shopManagePage");

        curShop.find("#shopName").attr("value", shop.shopName);
        curShop.find("#phone").attr("value", shop.phone);
        curShop.find("#address").attr("value", shop.address);
        curShop.find("#announcement").attr("value", shop.announcement);
        curShop.find("#shopDescribe").attr("value", shop.shopDescribe);
    })             

    $("#shopManage").click(function() {
        $("#shopManagePage").css("display", "inline");
        $("#goodsManagePage").css("display", "none");
        $("#ordersManagePage").css("display", "none");
        $("#saleStatisticsPage").css("display", "none");
    })

    $("#shopInfoModify").click(function() {
        $(this).css("display", "none");
        $("#shopManageButton").css("display", "inline");
        $("#shopName").removeAttr("readonly");
        $("#phone").removeAttr("readonly");
        $("#address").removeAttr("readonly");
        $("#announcement").removeAttr("readonly");
        $("#shopDescribe").removeAttr("readonly");
    })

    $("#phone").on('keyup blur change', function() {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");

        if(phone.match(/^\d{11}$/g) == null) {
            addError(parent, "请输入11位手机号码！");
            return;
        }
        removeError(parent);
    })

    $("#shopName").on('keyup blur change', function() {
        var parent = $(this).parent();
        var shopName = $(this).val().replace(/^\s+|\s+$/g, "");
        if(shopName == "") {
            addError(parent, "请输入店铺名！");
            return;
        }
        removeError(parent);
    })

    $("#address").on('keyup blur change', function() {
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

    $('.form-group input[type="text"],  .form-group textarea').on('focus', function() {
        removeError($(this).parent());
    });

    $("form").on('submit', function() {
        var flag = true;
        var tip = "请完善您的信息!";
        var len = $(".form-group").length;

        for(var i = 0; i < len; ++i) {
            var fg = $(".form-group:eq(" + i + ")");
            if(flag && fg.hasClass("has-error")) {
                flag = false;
            }

            if(i < len - 3 && fg.find("input").val() == "") {
                addError(fg, tip);
                flag = false;
            }
        }
        return flag;
    })

    //商品管理

    $("#goodsManage").click(function() {
        $("#shopManagePage").css("display", "none");
        $("#goodsManagePage").css("display", "inline");
        $("#ordersManagePage").css("display", "none");
        $("#saleStatisticsPage").css("display", "none");
    })
    
    $("#goodsAddBtn").click(function(){
       $("#goodsIntro").css("display", "none");
        $("#goodsAddPage").css("display", "inline");
        
    })
    //订单管理

    $("#ordersManage").click(function() {
        $("#shopManagePage").css("display", "none");
        $("#goodsManagePage").css("display", "none");
        $("#ordersManagePage").css("display", "inline");
        $("#saleStatisticsPage").css("display", "none");
    })

    //销售统计

    $("#saleStatistics").click(function() {
        $("#shopManagePage").css("display", "none");
        $("#goodsManagePage").css("display", "none");
        $("#ordersManagePage").css("display", "none");
        $("#saleStatisticsPage").css("display", "inline");
    })

})