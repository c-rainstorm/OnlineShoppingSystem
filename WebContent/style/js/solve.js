$(document).ready(function() {
    
    function() {
        $.get(.. / .. / getUnsolvedTransaction.action, function(gsontodotransactions) {

            var todotransactions = $.parseJSON(todotransactions);

        })
        for(var i = 1; i <= todotransactions.length; i++) {
            $(#todo_list).append(onetr_todo);

            todotransactions[i].find("#todolist #todo_id:eq(i)") html(todotransactions.transactionId);
            todotransactions[i].find("#todolist #todo_comment:eq(i)") html(todotransactions.comment);

        }

    }
    
    
    
    
    
    
    
    
    
});