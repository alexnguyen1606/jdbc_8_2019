function AssignmentCustomer(customerId) {
    $('#customerId').val(customerId);
    findStaff(customerId);
    openModelAssignmentCustomer();

}
function openModelAssignmentCustomer(){
    $('#myModalCustomer').modal();
}

$('#btnAssignCustomer').click(function (e) {
    e.preventDefault();
    e.preventDefault();
    var data = {};
    var staffs = [];
    data['customerId'] = $('#customerId').val();
    var staffs =$('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
        return $(this).val();
    }).get();
    data['staffs'] = staffs;
    assignCustomer(data);
});
function assignCustomer(data){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api-assignmentcustomer",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            // alert("Giao tòa nhà thành công");
        },error: function(response){
            console.log(response);
            console.log("fail");
            // alert("Giao tòa nhà không thành công");
        }
    });
}
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
function findStaff(customerId){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api-user?type=SHOW_USER"+"&id="+customerId,
        //data: data,
        dataType: "json",
        contentType:"application/json",
        success: function (data) {
            $('#staffList').empty();
            var staffs = showStaff(data);
            $('#staffList').append(staffs);
            console.log(data);
        },error: function(response){
            console.log("fail");
            console.log(response);
        }
    });
}
function showStaff(data){
    var staffs ='';
    $.each(data,function (key,value) {
        staffs +='<tr>';
        staffs +='<td><input type="checkbox" value="'+value.id+'" id="check_'+value.id+'" '+value.checked+' </td>';
        staffs +='<td>'+value.fullName+'</td>'
        staffs +='</tr>'
    });
    return staffs;
}