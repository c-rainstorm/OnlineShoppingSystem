$(document).ready(function () {
    $("body").delegate(".VerticalMenu>div>div:first-child","click",function () {
        $xz = $(".VerticalMenu>div>div:first-child");
        $($xz).not(this).children("i.glyphicon-menu-right").css({ "transform": "rotate(0deg)", "color": "#000000" }).attr("leng", "")
        if ($(this).children("i.glyphicon-menu-right").attr("leng") != "s") {
            $(this).children("i.glyphicon-menu-right").attr("leng", "s")
            $(this).children("i.glyphicon-menu-right").css({ "transform": "rotate(90deg)", "color": "#f9579e" })
        } else {
            $(this).children("i.glyphicon-menu-right").attr("leng", "")
            $(this).children("i.glyphicon-menu-right").css({ "transform": "rotate(0deg)", "color": "#000000" })
        }
        $($xz).not($(this)).siblings("[name='xz']").stop().slideUp()
        $(this).siblings("[name='xz']").slideToggle()

    })
    $VerticalMenu_scdj = null;
    $("body").delegate(".VerticalMenu>div>div:last-child>div","click",function () {
        $($VerticalMenu_scdj).css("background-color", "white");
        $(this).css("background-color","#c0c0c0");
        $VerticalMenu_scdj=$(this)
    })
})