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
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api-building",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            console.log("success");
        },
        error: function(response){
            console.log("fail");
            console.log(response);
        }
    });
})
