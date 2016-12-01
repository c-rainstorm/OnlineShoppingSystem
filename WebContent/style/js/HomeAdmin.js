$(document).ready(function() {
    
//点击未处理事务，获取id改写 链接。到处理事务界面
        var id=$(this).val();
       $("#todo_ida").attr('href','solve.jsp?id='+id); 



    $("#todo_ida").click(function(){
       
    })

    
    $("#searchTransaction").click(function() {
        $("#todolist").css("display","none");
        $("#donelist").css("display","none");
        $("#search").css("display","inline");
        });
 
    $("#left_transaction").click(function(){
        $("#right_transaction").css("display","inline");
        $("#right_usermanage").css("display","none");
        $("#right_shopmanage").css("display","none");
    })
    $("#left_usermanage").click(function(){
        $("#right_transaction").css("display","none");
        $("#right_usermanage").css("display","inline");
        $("#right_shopmanage").css("display","none");
    })
    $("#left_shopmanage").click(function(){
        $("#right_transaction").css("display","none");
        $("#right_usermanage").css("display","none");
        $("#right_shopmanage").css("display","inline");
    })
    


$.get("../../GetUnsolvedTransaction.action",function(gsontodotransactions) {
            
            
            var todotransactions =JSON.parse(gsontodotransactions);


            for(var i = 0; i <= todotransactions.length-1; i++) {
                $("#todo_list").append('<tr><td id="transaction_id">'+todotransactions[i].transactionId+'</td><td id="transaction_comment">'+todotransactions[i].comment+'</td><td id="commitTime">'+todotransactions[i].commitTime+'</td></tr>');
                //$("#todo_list").append(onetr_todo);
                
                //$("#todolist #todo_id:eq(" + i + ")").html(todotransactions.transactionId);
                //$("#todolist #todo_comment:eq(" + i + ")").html(todotransactions.comment);
                

            }
        })
    //默认加载未处理事务
/*
        $.get("../../getUnsolvedTransaction.action",{}, function(gsontodotransactions) {
            
            var onetr_todo = '<tr><td id="todo_id"><a href="solve.jsp"></a></td><td id="todocomment "></td></tr>';
            var onetr_done = '<tr><td id="done_id "></td><td id="donecomment "></td></tr>';
            todotransactions = (gsontodotransactions);


            for(var i = 1; i <= todotransactions.length; i++) {
                $("#todo_list").append(onetr_todo);
                
                $("#todolist #todo_id:eq(" + i + ")").html(todotransactions.transactionId);
                $("#todolist #todo_comment:eq(" + i + ")").html(todotransactions.comment);
                

            }
        })
   */
    //加载未处理事务
    $("#todotransaction").click(function() {
        $("#todolist").css("display","inline");
        $("#donelist").css("display","none");
        $("#search").css("display","none");
        
       $.get("../../GetUnsolvedTransaction.action",function(gsontodotransactions) {
            
            
            var todotransactions =JSON.parse(gsontodotransactions);


            for(var i = 0; i <= todotransactions.length-1; i++) {
                $("#todo_list").append('<tr><td id="transaction_id">'+todotransactions[i].transactionId+'</td><td id="transaction_comment">'+todotransactions[i].comment+'</td><td id="commitTime">'+todotransactions[i].commitTime+'</td></tr>');
                //$("#todo_list").append(onetr_todo);
                
                //$("#todolist #todo_id:eq(" + i + ")").html(todotransactions.transactionId);
                //$("#todolist #todo_comment:eq(" + i + ")").html(todotransactions.comment);
                

            }
        })
    });


    //加载已处理事务
    $("#donetransaction").click(function() {
        
        $("#todolist").css("display","none");
        $("#donelist").css("display","inline");
        $("#search").css("display","none");
       
    
        $.get("../../GetDoneTransaction.action", function(gsondonetransactions) {
          
            
            //document.write(gsondonetransactions);
            var donetransactions = JSON.parse(gsondonetransactions);


            for(var i = 0; i <= donetransactions.length-1; i++) {
                                
                                //$("#done_list").append(gsondonetransaction);

                $("#done_list").append('<tr><td id="transaction_id">'+donetransactions[i].transactionId+'</td><td id="transaction_comment">'+donetransactions[i].comment+'</td><td id="commitTime">'+donetransactions[i].commitTime+'</td></tr>');
                
                

            } 
        })
    });
   
        
        
        
        
    $("#search_user").on("submit", function(e){
        e.preventDefault();
        $.get("../../GetUserInfo.action",$(this).serialize(), function(gsonUsers) {
            
            var users =JSON.parse(gsonUsers);
            


            for(var i = 0; i <= users.length-1; i++) {
            	$("#user_list").append('<tr><td id="user_id">'+users[i].userId+'</td><td id="user_name">'+users[i].username+'</td><td id="user_phone">'+users[i].phone+'</td></tr>');
            	
            	
            	
                

            }
        })
    })
    
    $("#search_tran").on("submit", function(e){
       e.preventDefault();
        $.get("../../searchTransaction.action",$(this).serialize(),function(gsontransaction) {
            
            var transaction =JSON.parse(gsontransaction);
            


            for(var i = 0; i <=transaction.length-1; i++) {
                $("#tran_list").append('<tr><td id="transaction_id">'+transaction[i].transactionId+'</td><td id="transaction_comment">'+transaction[i].comment+'</td><td id="commitTime">'+transaction[i].commitTime+'</td></tr>');


            }
        })
    })
    
    
    $("#search_shop").on("submit", function(e){
        e.preventDefault();
        $.get("../../GetShopInfo.action",$(this).serialize(),function(gsonShops) {
            //var onetr_user = '<tr><td id="user_id"></td><td id="user_name"></td></tr>';
            var shops =JSON.parse(gsonShops);
            


            for(var i = 0; i <= shops.length-1; i++) {
            	$("#shop_list").append('<tr><td id="shop_id">'+shops[i].shopId+'</td><td id="shop_name">'+shops[i].shopName+'</td><td id="shop_phone">'+shops[i].phone+'</td></tr>');
            	
            	
            	

            }
        })
    })
  
    
     $("#delete_user").click(function(){
        $.get("../../DeleteUser",{},function(){
            
        })
    })
    
    $("#delete_shop").click(function(){
        $.get("../../DeleteShop",{},function(){
            
        })
    })
    
    
    

})