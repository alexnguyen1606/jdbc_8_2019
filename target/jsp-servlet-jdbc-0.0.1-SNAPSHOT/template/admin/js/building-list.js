function assignmentBuilding(buildingId){
    openModalAssignmentBuilding();
    var x ={};
    x['id'] = buildingId;
    findUser(x);
    $('#buildingId').val(buildingId);
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
        url: "/api-assignmentstaff",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            console.log("success");
        },error: function(response){
            console.log("fail");
        }
    });
}
$('#btnDeleteBuilding').click(function (e) {
    e.preventDefault();
    var data  = {};
    var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function(){
        return $(this).val();
    }).get();
    data['idDelete'] = buildingIds;
    deleteBuilding(data);
});
function deleteBuilding(data){
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api-building",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            console.log("success");
        },error: function(response){
            console.log("fail");
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
        url: "http://localhost:8080/api-user",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
        $.each(response, function (i, v) { 
             $('#staffList').find('tbody').append('<tr>')
             .append('<td> <input type="checkbox" value="'+v.id+'"  '+v.checked+'" id="'+'checkbox_'+v.id+'" ></td>')
             .append('<td>'+v.fullName+'</td>')
             .append('</tr>');
        });
        },error: function(response){
            console.log("fail");
        }
    });
}