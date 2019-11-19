$('#btnDeleteCustomer').click(function (e) {
    e.preventDefault();
    if (confirm("Bạn có muỗn xóa khách hàng không ?")){
        var data  = {};
        var customerIds = $('#customerList').find('tbody input[type=checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['idDelete'] = customerIds;
        deleteCustomer(data);
    }

})
function deleteCustomer(data) {
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api-building",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            alert("Delete Success")
            console.log("success");
            window.location.href="http://localhost:8080/admin-customer?action=LIST";
        },error: function(response){
            console.log("fail");
            alert("Delete fail");
            window.location.href="http://localhost:8080/admin-customer?action=LIST";
        }
    });

}