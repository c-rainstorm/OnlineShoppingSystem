$(document).ready(function() {
    $("input:not(:submit)").val("");
    
    $("input:not(:submit)").on('focus',function(){
        var parent = $(this).parent();
        var help = parent.find('.help-block');
        removeError(parent, help);
    })
    
    $("#username").on('change', function() {
        var parent = $(this).parent();
        var name = $(this).val().replace(/^\s+|\s+$/g, "");
       
        var help = parent.find('.help-block');
        if(name.length > 25 || name.match(/^[A-Za-z][-_a-zA-Z0-9]*$/g) == null) {
            addError(parent, "用户名只允许由英文字母，数字，'-'，'_' 组成且首位必须为字母，不超过 25 个字符");
            return;
        }

        $.post("../checkUsername.action", {
            "username" : name.toString()
        }, function(result) {
            if(result.result == "false") {
                addError(parent, "该用户名已注册");
                return;
            }
        })

        removeError(parent, help);
    })

    $("#phone").on('change', function() {
        var parent = $(this).parent();
        var phone = $(this).val().replace(/^\s+|\s+$/g, "");
        var help = parent.find(".help-block");
        if(phone.match(/^1\d{10}$/g) == null) {
            addError(parent, "请输入11位手机号码！");
            return;
        }

        $.post("../checkPhone.action", {
            "phone" : phone.toString()
        }, function(result) {
            if(result.result == "false") {
                addError(parent, "该手机号已注册！");
                return;
            }
        })

        removeError(parent);
    })

    $("#password").on('blur', function() {
        var parent = $(this).parent();
        var help = parent.find(".help-block");

        var password = $(this).val();
        var confirm_password = $("#password_confirmation").val();
        if(confirm_password != null || confirm_password != "")
            password_confirmation($("#password_confirmation"));

        if(password.length > 20 || password.match(/^[-_a-zA-Z0-9]{6,}$/g) == null) {
            addError(parent, "密码只允许由英文字母，数字，'-'，'_' 组成，最小长度为6个字符，不超过 20 个字符");
            return;
        }

        removeError(parent);
    })

    $("#password_confirmation").on('keyup blur change', function() {
        password_confirmation($(this));
    })

    function password_confirmation(object) {
        var parent = object.parent();
        var help = parent.find(".help-block");

        var password1 = $("#password").val();
        var password2 = object.val();

        if(password1 != password2) {
            addError(parent, "两次密码不一致!");
            return;
        }

        removeError(parent);

    }

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

    $("form").on('submit', function(e) {
        var tip = "请完善您的信息!";
        var len = $(".form-group").length;

        for(var i = 0; i < len; ++i) {
            var fg = $(".form-group:eq(" + i + ")");
            if(flag && fg.hasClass("has-error")) {
                e.preventDefault();
            }
            if(fg.find("input").val() == "") {
                addError(fg, tip);
                e.preventDefault();
            }
        }
    })
})