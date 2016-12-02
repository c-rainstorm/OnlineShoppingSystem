jQuery(document).ready(function() {

    $("input:not(:submit)").val("");
    /*
        Fullscreen background
    */
    $.backstretch([
        "/images/backgrounds/2.jpg", "/images/backgrounds/3.jpg", "/images/backgrounds/1.jpg"
    ], {
        duration: 3000,
        fade: 750
    });

    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
        $(this).removeClass('input-error');
        $help = $(".help-block");
        $help.parent().removeClass("has-error");
        $help.html("");
    });

    $("#username").change(function() {
        var login = $(this).val();
        if(login.match("^[0-9]")) {
            $(this).attr("name", "phone");
        } else if(login.match("^[a-zA-Z]")) {
            $(this).attr("name", "username");
        }
    });

    $('.login-form').on('submit', function(e) {

        $(this).find('input[type="text"], input[type="password"], textarea').each(function() {
            if($(this).val() == "") {
                e.preventDefault();
                $(this).addClass('input-error');
            } else {
                $(this).removeClass('input-error');
            }
        });
    });
});