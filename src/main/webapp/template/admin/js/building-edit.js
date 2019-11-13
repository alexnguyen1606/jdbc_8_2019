$('#btnAddBuilding').click(function(){
    var data ={};
    var buildingTypes = [];
    var formData = $('#formEdit').serializeArray();
    $.each(formData, function (index, v) {
        if(v.name=="buildingTypes"){
            buildingTypes.push(v.value);
        }
        else{
            data[""+v.name+""] = v.value;
        }
    });
    data['buildingTypes'] = buildingTypes;
    var id = $('#id').val();
    if (id==""){
        addBuilding(data);
    }else {
        updateBuilding(data);
    }

})
function addBuilding(data){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api-building",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            console.log("add success");
            alert('Add Success !');
            console.log(response);
            window.location.href="http://localhost:8080/admin-building?action=LIST";
        },
        error: function(response){
            console.log("fail");
            alert("Add fail !");
            console.log(response);
        }
    });
}
function updateBuilding(data) {
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api-building",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            console.log("update success");
            alert("Update Success");
            console.log(response);
            window.location.href="http://localhost:8080/admin-building?action=LIST";
        },
        error: function(response){
            console.log("update fail");
            alert("update fail");
            console.log(response);
        }
    });
}