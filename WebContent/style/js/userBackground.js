$(document).ready(function() {
    var pageContent = "*";

    showOrderPageOf(pageContent, 1);
    setTimeout(checkOrderPage, 500);

    $(".orderManage").click(function() {
        if (pageContent.match("Info")) {
            pageContent = "*";
            $(".userInfo ,.newReceiver, .receiverInfo").remove();
            $(".title").html("订单简介");
            $(".titleHead , .pager, .nav-tabs").show();
            $(".pageNum").html("1");

            showOrderPageOf(pageContent, 1);
            setTimeout(checkOrderPage, 500);
        }
    })

    $(".userInfoManage").click(function() {
        if (pageContent != "userInfo") {
            pageContent = "userInfo";
            $(".title").html("个人信息");
            $(".titleHead , .pager, .nav-tabs").hide();
            $(".summary , .newReceiver, .receiverInfo").remove();

            $(".main").append(createUserInfoForm());

            $.get("../../getUserInfo.action", {}, function(userInfo) {

                $(".userInfo").find("img").attr("src", $(".avatarAddr").html());
                $(".username").val(userInfo.username);
                $(".nickname").val(userInfo.nickname);
                $(".phone").val(userInfo.phone);
                $(".sex").val(userInfo.sex);
                $("#datapicker").datepicker({ dateFormat: "yy-mm-dd" });

                $(".birthday").val(userInfo.birthday);
            })
        }
    })

    $(".receiverManage").click(function() {
        if (pageContent != "receiverInfo") {
            pageContent = "receiverInfo";
            $(".title").html("收获地址管理<button class=\" navbar-right addNewAddr btn btn-success\">添加新地址</button>");
            $(".titleHead , .pager, .nav-tabs").hide();
            $(".summary , .userInfo ,.newReceiver").remove();

            showReceivers();
        }
    })


    $(".nav-tabs li").click(function() {
        $(".nav-tabs .active").removeClass("active");
        $(this).addClass("active");
        pageContent = $(this).find("a").html();
        if (pageContent.match("全部订单")) {
            pageContent = "*";
        }
        $(".pageNum").html("1");

        showOrderPageOf(pageContent, 1);
        setTimeout(checkOrderPage, 500);
    })


    $(".main").delegate(".showDetail", "click", function() {
        var len = $(".summary").length;

        for (var i = 0; i < len; ++i) {
            var order = $(".summary:eq(" + i + ")");
            var brief = order.find(".brief");
            var detail = order.find(".detail");
            //已经获取订单并显示出来
            if (detail.length > 0 && !detail.get(0).style.display.match("none")) {
                detail.slideToggle();
                brief.slideToggle();
                break;
            }
        }

        var summary = $(this).parent().parent().parent();

        var brief = summary.find(".brief");
        var detail = summary.find(".detail");
        if (pageContent.match("代付款")) {
            var orderOpeBtn = detail.find(".finishOrder");
            orderOpeBtn.removeClass("finishOrder");
            orderOpeBtn.html("去付款");
            //TODO orderOpeBtn href 该为支付页的 url
        }
        if (detail.length == 0) {
            summary.append(createOrderDetail());
            var orderId = summary.find(".brief .orderId").html();
            $.get("../../getOrderById.action", { orderId: orderId }, function(order) {
                addDataToDetail(summary.find(".detail"), order);
            })
        }
        summary.find(".detail").slideToggle();
        brief.slideToggle();
    })

    $(".main").delegate(".finishOrder", "click", function() {
        var summary = $(this).parents(".summary");
        var brief = summary.find(".brief");
        var detail = summary.find(".detail");

        detail.slideToggle();
        detail.remove();

        $.get("../../updateOrderStatus.action", {
            orderId: brief.find(".orderId").html(),
            orderStatus: '已完成'
        }, function(response) {
            if (response.result.match("true"))
                brief.find(".").html("已完成");
            else
                alert("操作失败！请稍后再试...")
        })
        brief.slideToggle();
    })

    $(".previous").click(function() {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        $(".summary").remove();
        showOrderPageOf(pageContent, curPage - 1);
        setTimeout(checkOrderPage, 500);
        $(".pageNum").html(curPage - 1);
    })

    $(".next").click(function() {
        if ($(this).hasClass("disabled"))
            return;

        var curPage = parseInt($(".pageNum").html());

        $(".summary").remove();
        showOrderPageOf(pageContent, curPage + 1);
        setTimeout(checkOrderPage, 500);

        $(".pageNum").html(curPage + 1);
    })

    $(".main").delegate(".update", "click", function() {
        var receiverInfo = $(this).parents(".receiverInfo");

        $.get("../../modifyReceiver.action", {
            receiverId: receiverInfo.find(".receiverId").val(),
            receiverAddress: receiverInfo.find(".receiverAddress").val(),
            receiverName: receiverInfo.find(".receiverName").val(),
            receiverPhone: receiverInfo.find(".receiverPhone").val()
        }, function(response) {
            if (response.result.match("true")) {
                $(".receiverInfo").remove();
                setTimeout(showReceivers, 100);
            } else {
                alert("操作失败！请稍后再试...")
            }
        })
    })

    $(".main").delegate(".delete", "click", function() {
        var receiverInfo = $(this).parents(".receiverInfo");

        $.get("../../deleteReceiver.action", {
            receiverId: receiverInfo.find(".receiverId").val()
        }, function(response) {
            if (response.result.match("true")) {
                $(".receiverInfo").remove();
                showReceivers();
            } else
                alert("操作失败！请稍后再试...")
        })
    })


    $(".main").delegate(".addNewAddr", "click", function() {
        if ($(".main .newReceiver").length > 0)
            return;

        $(".receiverInfo").slideToggle();
        $(".receiverInfo").remove();
        $(".pageNum").html("1");
        $(".previous, .next").addClass("disabled");

        $(".main").append(createNewReceiver());
        $(".newReceiver").slideToggle();
    })

    $(".main").delegate(".newReceiver .receiverPhone", 'keyup blur change', function() {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if (phone.match(/^\d{11}$/g) == null) {
            addError(parent, "请输入11位手机号码！");
            return;
        }

        $.get("../../checkPhone.action", { phone: phone }, function(response) {
            if (response.result.match("false")) {
                addError(parent, "该手机号已注册！");
                return;
            }
        })

        removeError(parent);
    })

    $(".main").delegate(".addNewReceiver", "click", function() {
        var tip = "请完善您的信息!";

        var emptyInput = $(".newReceiver :text").filter(function() {
            return !this.value;
        });

        if (emptyInput.length > 0 || $('.newReceiver .form-group').hasClass("has-error")) {
            addError(emptyInput.parent(), tip);
            return;
        }

        var receiver = $(".newReceiver");
        $.get("../../addReceiver.action", {
            receiverAddress: receiver.find(".receiverAddress").val(),
            receiverName: receiver.find(".receiverName").val(),
            receiverPhone: receiver.find(".receiverPhone").val()
        }, function(response) {
            if (response.result.match("true")) {
                var help1 = $(".newReceiver .help-block:last");
                var help2 = $(".newReceiver .help-block").last();
                addError($(".newReceiver .help-block").last().parent(), "添加成功！2s 后刷新...");
                setTimeout(function() {
                    $(".newReceiver").remove();
                    showReceivers();
                }, 2000);
            } else
                alert("操作失败！请稍后再试...")
        })
    })

    $(".main").delegate(".cancel", "click", function() {
        var receiverForm = $(".newReceiver");
        receiverForm.slideToggle();
        showReceivers();
        receiverForm.remove();
    })

    function checkOrderPage() {
        var curPage = $(".pageNum").html();
        if (curPage == "1")
            $(".previous").addClass("disabled");
        else
            $(".previous").removeClass("disabled");

        var summary = $(".summary");
        var hasNext = (summary.length == 10);
        if (hasNext)
            $(".next").removeClass("disabled");
        else
            $(".next").addClass("disabled");
    }

    function showOrderPageOf(pageContent, pageNum) {
        $.get("../../getOrderByStatus.action", {
                orderStatus: pageContent,
                maxNumInOnPage: 10,
                pageNum: pageNum
            },
            function(briefs) {
                $.each(briefs, function(i, order) {
                    $(".main").append(createOrderBrief());
                    var brief = $(".main .summary:last");

                    brief.find(".orderStatus").html(orderStatus);
                    brief.find(".orderId").html(order.orderId);

                    brief.slideToggle();
                })
            })
    }

    function showReceivers() {
        $.get("../../getReceivers.action", {}, function(receivers) {
            $.each(receivers, function(i, receiver) {
                var newinfo = createReceiverInfo();
                $(".main").append(newinfo);

                var info = $(".main .receiverInfo:last");

                info.find(".receiverId").val(receiver.receiverId);
                info.find(".receiverName").val(receiver.receiverName);
                info.find(".receiverPhone").val(receiver.receiverPhone);
                info.find(".receiverAddress").val(receiver.receiverAddress);
            })
        })
    }


    function createOrderBrief() {
        var newBrief = "<div class=\"summary\" style=\"display: none;\"><div class=\"row brief\"><div class=\"col-xs-4 col-sm-4 col-md-4\"><h4 class=\"orderId\"></h4>" +
            "</div><div class=\"col-xs-4 col-sm-4 col-md-4\"><h4 class=\"orderStatus\"></h4></div><div class=\"col-xs-4 col-sm-4 col-md-4\">" +
            "<a href=\"javascript:;\" class=\"showDetail form-control btn btn-success\" style=\"margin: 3px 12px;\">查看详情</a></div></div></div>";

        return newBrief;
    }

    function createOrderDetail() {
        var newDetail = "<div class=\"row detail\" style=\"display: none;\"><h3>订单详情</h3><hr><p><strong>订单状态：" +
            "<span class=\"status\"></span></strong></p><br><p>订单号码：<span class=\"orderId\"></span></p>" +
            "<p>下单日期：<span class=\"trackingNumber\"></span></p>" +
            "<p>下单日期：<span class=\"orderTime\"></span></p><p>付款方式：<span class=\"payway\"></span></p><br>" +
            "<p>收货地址：<span class=\"receiverAddress\"></p><p>收货人名：<span class=\"receiverName\"></span></p>" +
            "<p>收货日期：<span class=\"finishTime\"></span></p><br><p>买家备注：<span class=\"commit\"></span></p><hr><div class=\"row\"><div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p>食品</p></div><div class=\"col-xs-6 col-sm-6 col-md-6\"><p>购买信息</p></div><div class=\"col-xs-3 col-sm-3 col-md-3\">" +
            "<p>小计</p></div></div><hr><div class=\"goods\"></div>" +
            "<div class=\"row\"><div class=\"col-xs-3 col-sm-3 col-md-3\"></div><div class=\"col-xs-6 col-sm-6 col-md-6\"></div>" +
            "<div class=\"col-xs-3 col-sm-3 col-md-3\"><p>订单总额： ￥<span class=\"orderTotal\"></span></p><p>配送费用： ￥<span class=\"deliveryFee\"></span></p>" +
            "<p><strong>总 金 额：￥<span class=\"realPay\"></span></strong></p></div></div><div class=\"row\">" +
            "<div class=\"col-xs-10 col-sm-10 col-md-10 col-xs-offset-1 col-sm-offset-1 col-md-offset-1\"><a href=\"javascript:;\"" +
            "class=\"finishOrder form-control btn btn-success\" style=\"display: none;\">完成订单</a></div></div></div>";
        return newDetail;
    }

    function createUserInfoForm() {
        var newUserInfoForm = '<form role="form" class="userInfo"><hr class="colorgraph"><div class="form-group has-info"><img src="" style="height: 50px; width: auto;" alt="头像"><input type="file" class="form-control avatar input-lg" placeholder="头像" tabindex="1"></div><div class="form-group has-info"><input type="text" name="username" class="username form-control input-lg" placeholder="用户名" readonly></div><div class="form-group has-info"><input type="text" name="nickname" class="nickname form-control input-lg" placeholder="昵称" tabindex="2"></div><div class="form-group has-info"><input type="text" name="phone" class="phone form-control input-lg" placeholder="手机号" tabindex="3"></div><div class="form-group has-info"><input type="text" name="sex" class="sex form-control input-lg" placeholder="性别" tabindex="4"></div><div class="form-group has-info"><input type="text" name="birthday" class="birthday form-control input-lg" id="datapicker" placeholder="生日" tabindex="5"></div><input type="submit" class="btn btn-success form-control" value="提交"  tabindex="6"/></form>';
        return newUserInfoForm;
    }

    function createReceiverInfo() {
        var newReceiver = "<div class=\"receiverInfo col-sm-8 col-md-5\"><hr><div class=\"form-group has-info\" style=\"display: none;\"><p>当前用户手机号</p>" +
            "<input type=\"text\" name=\"receiverId\" class=\"form-control receiverId\" tabindex=\"1\" readonly><span class=\"help-block\">" +
            "</span></div><div class=\"form-group has-info\"><p>收货人名称：</p><input type=\"text\" name=\"receiverName\" class=\"form-control receiverName\"" +
            " tabindex=\"2\"><span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人联系方式：</p>" +
            "<input type=\"text\" name=\"receiverPhone\" class=\"form-control receiverPhone\"  tabindex=\"3\" readonly>" +
            "<span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人地址</p><input type=\"text\" name=\"receiverAddress\"" +
            "class=\"form-control receiverAddress\" tabindex=\"4\"><span class=\"help-block\"></span></div><hr><div class=\"row\">" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"更新地址\" class=\"update btn btn-warning btn-block\" tabindex=\"6\"></div>" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"删除地址\" class=\"delete btn btn-danger btn-block\" tabindex=\"7\"></div></div><hr></div>";
        return newReceiver;
    }

    function createNewReceiver() {
        var newReceiver = "<div class=\"newReceiver col-sm-8 col-md-5\" style=\"display: none;\"><hr><div class=\"form-group has-info\"><p>收货人名称：</p><input type=\"text\" name=\"receiverName\" class=\"form-control receiverName\"" +
            "placeholder=\"姓名\" tabindex=\"1\"><span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人联系方式：</p>" +
            "<input type=\"text\" name=\"receiverPhone\" class=\"form-control receiverPhone\" placeholder=\"联系方式\" tabindex=\"2\"> " +
            "<span class=\"help-block\"></span></div><div class=\"form-group has-info\"><p>收货人地址</p><input type=\"text\" name=\"receiverAddress\"" +
            "class=\"form-control receiverAddress\" placeholder=\"地址\" tabindex=\"3\"><span class=\"help-block\"></span></div><hr><div class=\"row\">" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"提交\" class=\"addNewReceiver btn btn-success btn-block\" tabindex=\"4\"></div>" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"取消\" class=\"cancel btn btn-primary btn-block\" tabindex=\"5\"></div>" +
            "<div class=\"col-xs-12 col-md-4\"><input type=\"button\" value=\"清空\" class=\"clear btn btn-warning btn-block\" tabindex=\"6\"></div></div><div class=\"row has-info\" style=\"padding: 15px\"><span class=\"help-block\"></span></div><hr></div>";
        return newReceiver;
    }

    function addDataToDetail(detail, order) {
        if (order.trackingNumber == undefined || order.trackingNumber == null) {
            order.trackingNumber = "";
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
            var goods = "<div class=\"row\"><div class=\"col-xs-3 col-sm-3 col-md-3\">" +
                "<img style='height:100px; width:100px;' src=\"" + goodsInOrder.goods.imageAddr + "\" alt=\"该图片离家出走了..\" class=\"img-thumbnail img-responsive\"></div><div class=\"col-xs-6 col-sm-6 col-md-6\">" +
                "<p><span class=\"foodName\">" + goodsInOrder.goods.goodsName + "<br>" + goodsInOrder.goods.goodsDescribe + "<br>" + goodsInOrder.goods.attributeValue + "</span></p><p>售价： ￥<span class=\"price\">" +
                goodsInOrder.goods.actualPrice +
                "</span> x<span class=\"soldNum\">" + goodsInOrder.goodsNum + "</span></p>" +
                "</div><div class=\"col-xs-3 col-sm-3 col-md-3\"><p>￥<span class=\"subTotal\">" + (goodsInOrder.goodsNum * goodsInOrder.goods.actualPrice) + "</span></p></div></div><hr>"

            detail.find(".goods").append(goods);
        })

        detail.find(".orderTotal").html(order.total);
        detail.find(".deliveryFee").html(0);
        detail.find(".realPay").html(order.total);
    }


    $(".main").delegate(".clear", "click", function() {
        $('input[type="password"]').val("");
        removeError($('input[type="password"]').parent());

        var newReceiverInfo = $(".newReceiver :text");
        newReceiverInfo.val("");
        removeError(newReceiverInfo.parent());
    })

    function addError(object, tip) {
        if (object.hasClass("has-info")) {
            object.removeClass("has-info");
            object.addClass("has-error");
            object.find(".help-block").html(tip);
        }
        if (object.hasClass("has-success")) {
            object.removeClass("has-success");
            object.addClass("has-error");
            object.find(".help-block").html(tip);
        }
    }

    function removeError(object) {
        if (object.hasClass("has-error")) {
            object.removeClass("has-error");
            object.addClass("has-success");
            object.find(".help-block").html("");
        }
        if (object.hasClass("has-info")) {
            object.removeClass("has-info");
            object.addClass("has-success");
            object.find(".help-block").html("");
        }
    }

    // 退出登录
    $(".logout").click(function() {
        $.get("../../userLogout.action", {}, function(response) {
            if (response.result == "true") {
                window.location.href = window.location.href;
            }
        })
    })

    $(".main").delegate(".userInfo", "submit", function(e) {
        e.preventDefault();
        if ($(".avatar").val() != "") {
            $.get("../../updateAvatar.action", new FormData(this), function() {
                $(".avatar").val("");
            });
        }
        $.get("../../updateNickname.action", {
            nickname: $(".nickname").val()
        })
        $.get("../../updatePhone.action", {
            phone: $(".phone").val()
        })
        $.get("../../updateSex.action", {
            sex: $(".sex").val()
        })
        $.get("../../updateBirthday.action", {
            birthday: $(".birthday").val()
        })
    })
})