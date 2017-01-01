$(document).ready(function() {
    setTimeout(function() {
        var result = JSON.parse($.cookie("payResult"));
        var showString;
        if (result.fail == "0") {
            showString = result.success + " 个订单付款成功";
        } else {
            showString = result.success + " 个订单付款成功，" + result.fail + "个订单付款失败。失败的订单可能因为库存不足，若有需要请稍后到个人中心重新付款";
        }
        $(".tip").html(showString);
        // $.removeCookie("payResult");
    }, 500);

})