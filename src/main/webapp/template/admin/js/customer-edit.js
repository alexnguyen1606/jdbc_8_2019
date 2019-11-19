$('#btnAddCustomer').click(function () {
    var data = {};
    var formData= $('#formEdit').serializeArray();
    $.each(formData,function (i,v) {
        data[""+v.name+""]=v.value;
    })
    var id = $('#id').val();
    if (id==""){
        addCustomer(data);
    } else {
        editCustomer(data);
    }
})
function addCustomer(data) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api-customer",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            console.log("add success");
            alert('Add Success !');
            console.log(response);
            window.location.href="http://localhost:8080/admin-customer?action=LIST";
        },
        error: function(response){
            console.log("fail");
            alert("Add fail !");
            console.log(response);
        }
    });
}
function editCustomer(data) {
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api-customer",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
            console.log("add success");
            alert('Update Success !');
            console.log(response);
            window.location.href="http://localhost:8080/admin-customer?action=LIST";
        },
        error: function(response){
            console.log("fail");
            alert("Update fail !");
            console.log(response);
        }
    });
}