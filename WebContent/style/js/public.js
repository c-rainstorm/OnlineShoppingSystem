$(document).ready(function(){

    var shoppingCart = $(".shoppingCart");
    if(shoppingCart != undefined){
        $.get("http://localhost:8080/OSS/getGoodsNumInShoppingCart.action",{},function(response){
            $(".shoppingCart .badge").html(response.goodsNum);
        })
    }

    $(".logout").click(function(){
        $.get("http://localhost:8080/OSS/userLogout.action",{},function(response){
            if(response.result == "true"){
                window.location.href = "http://localhost:8080/OSS/index.jsp";
            }
        })
    })

    $(".search").click(function(){
        var content = $("#searchInput").val();
        if(content == null || content == ""){
            content = $("#searchInput").attr("placeholder");
        }


        $(".search").attr("href", "http://localhost:8080/OSS/pages/core/goodsList.jsp?keyword=" + content);
    })

})
