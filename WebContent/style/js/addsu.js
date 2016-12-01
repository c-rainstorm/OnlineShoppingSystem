$(document).ready(function() {
     $("#gotoadduser").click(function(){
        $("#adduser").css("display","inline");
        $("#addshop").css("display","none");
      
    })
    
    $("#gotoaddshop").click(function(){
        $("#adduser").css("display","none");
        $("#addshop").css("display","inline");
      
    })

 });