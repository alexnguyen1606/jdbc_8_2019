function assignmentBuilding(buildingId){
    var x ={};
    x['id'] = buildingId;
    findUser(buildingId);
    $('#buildingId').val(buildingId);
    openModalAssignmentBuilding();
    console.log($('#buildingId').val());
}

function openModalAssignmentBuilding(){
    $('#myModal').modal();
}
$('#btnAssignBuilding').click(function (e) {
    e.preventDefault();
    var data = {};
    var staffs = [];
    data['buildingId'] = $('#buildingId').val();
    var staffs =$('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
        return $(this).val();
    }).get();
    data['staffs'] = staffs;
    assignStaff(data);
});
function assignStaff(data){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api-assignmentstaff",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
           // alert("Giao tòa nhà thành công");
        },error: function(response){
            console.log("fail");
           // alert("Giao tòa nhà không thành công");
        }
    });
}

$('#btnDeleteBuilding').click(function (e) {
    e.preventDefault();

    if (confirm("Bạn có muỗn xóa sản phẩm không")){
        var data  = {};
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['idDelete'] = buildingIds;
        deleteBuilding(data);
    }

});
function deleteBuilding(data){
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api-building",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            alert("Delete Success")
            console.log("success");
            window.location.href="http://localhost:8080/admin-building?action=LIST";
        },error: function(response){
            console.log("fail");
            alert("Delete fail");
            window.location.href="http://localhost:8080/admin-building?action=LIST";
        }
    });
}

$('#btnSearchBuilding').click(function (e) {
    e.preventDefault();
    $('#formSearchBuilding').submit();
});
function findUser(data){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api-user?type=SHOW_STAFF"+"&id="+data,
        //data: data,
        dataType: "json",
        contentType:"application/json",
        success: function (data) {
            $('#staffList').empty();
           var staffs = showUser(data);
            $('#staffList').append(staffs);
            console.log(data);
        },error: function(response){
            console.log("fail");
            console.log(response);
        }
    });
}
function showUser(data){
    var staffs ='';
    $.each(data,function (key,value) {
        staffs +='<tr>';
        staffs +='<td><input type="checkbox" value="'+value.id+'" id="check_'+value.id+'" '+value.checked+' </td>';
        staffs +='<td>'+value.fullName+'</td>'
        staffs +='</tr>'
    });
    return staffs;
}