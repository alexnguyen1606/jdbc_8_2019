function assignmentBuilding(buildingId){
    openModalAssignmentBuilding();
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