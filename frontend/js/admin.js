$(document).ready(function(){
    let authToken = localStorage.getItem('auth_token');
    if(authToken){
        $.ajaxSetup({
            headers:{
                'Authorization' : 'Bearer ' + authToken
            }
        })
    }
    $('#exitId').on('click',function(){
        localStorage.removeItem('auth_token');
        authToken = "";
       window.location.href = 'auth.html';
    });
   $('.tabClass').on({
        mouseenter: function(){
          $(this).css("background-color", "gray");
        },  
        mouseleave: function(){
          $(this).css("background-color", "green");
        }, 
        click: function(){
            $('.tabClass').css("color", "black");
          $(this).css("color", "rgb(60, 60, 50)");
          hideAllPage();
        }  
    });
    $('#workerId').on('click',function(){
        $('.pageWorker').show();
    });
    $('#showAllWorkersId').on('click',function(){
        showAllWorkers();
    });
    $('#showButtonWorkerId').on('click',function(){
        let num= $('#inputWorkerId').val();
        showWorkerById(num);
    });
     $('#activateWorkerButtonId').on('click', function(){
        let num2= $('#inputActivateWorkerId').val();
         activateWorkerById(num2);
    });
     $('#deactivateWorkerButtonId').on('click', function(){
        let num3= $('#inputDeactivateWorkerId').val();
         deactivateWorkerById(num3);
    });
    
    $('#adminId').on('click', function(){
        $('.pageAdmin').show();
        showAllAdmins();
    });
    $('#customerId').on('click', function(){
        $('.pageCustomer').show();
        showAllCustomers();
    });
    $('#deactivateCustomerButtonId').on('click', function(){
        let num3= $('#inputDeactivateCustomerId').val();
         deactivateCustomerById(num3);
    });
    $('#profId').on('click',function(){
        hideAllPage();
        $('.pageProf').show();
        showMe();
    });
    $('#mainId').on('click', function(){
        $('.pageMain').show();
    });
   
});

function hideAllPage(){
    $('.pageMain').hide();
    $('.pageOrder').hide();
    $('.pageProf').hide();
    $('.pageWorker').hide();
    $('.pageAdmin').hide();
    $('.pageRespond').hide();
    $('.pageCustomer').hide();
}

function showMe(){
      $.ajax({
                url: SERVER_URL+'admin/getByToken',
                method: 'GET',
                contentType: 'application/json',
                complete: function(serverResponse){
                    console.log(serverResponse);
                    let admin = serverResponse.responseJSON;
                     let myprof = "<tr> <td> Id </td> <td>" + admin.id + "</td> </tr> <tr> <td> Email </td>  <td>" + admin.email + "</td> </tr> <tr> <td> Ім'я </td> <td>" + admin.firstName + "</td> </tr> <tr> <td> Прізвище </td> <td>" + admin.lastName + "</td> </tr> <tr> <td> Номер телефону </td> <td>" + admin.phoneNumber + "</td> </tr>"; 
                    $('#tableMyProfId').html(myprof);
                }
            });  
};


function showAllWorkers(){
    
     $.ajax({
                url: SERVER_URL+'worker/getAllForAdmin',
                method: 'GET',
                contentType: 'application/json',
                complete: function(serverResponse){
                    console.log(serverResponse);
                    let workers =serverResponse.responseJSON;
                    let list="<tr> <td> Id </td> <td> Name </td> <td> Email </td> <td> Description </td> <td> Role </td> </tr>";
                    var i;
                    for (i=0; i<workers.length; i++){
                       list=list + "<tr> <td> " + workers[i].id + "</td> <td>" + workers[i].nickName + "</td> <td>" + workers[i].email + "</td> <td>" + workers[i].description + "</td> <td>";
                        if(workers[i].roles.length!=0){
                            list = list + workers[i].roles[0].role + "</td> </tr>";
                        }else{
                            list = list + " null </td> </tr>"; 
                        }
                    }
                     $("#tableWorkersId").html(list);
                
                }
            });
};


function showWorkerById (id){
    $.ajax({
        url: SERVER_URL+'worker/id/'+id,
        success: function(data){
            console.log(data);
            let massage = "id: " + data.id + "<br> Name worker: " + data.nickName + "<br> Email: " + data.email + "<br> Description: " + data.description + "<br> Role: ";
            if(data.roles.length!=0){
              for (var i=0; i<data.roles.length; i++){
                massage = massage + data.roles[i].role + "  ";
              };
            }else{
                massage = massage + "null";   
            }
            $("#showWorkerId").html(massage);
        },
        statusCode:{ 409:function(){
            $("#showWorkerId").text("Worker with id " + id + " not exists");
        }
    }
        
   });
};

function activateWorkerById (id){
    $.ajax({
        url: SERVER_URL+'worker/activate/'+id,
        success: function(data){
            console.log(data);
            $("#massageActivateWorkerId").text("Виконавець з id = " + id + " доданий до списку виконавців.");
        },
        statusCode:{ 409:function(){
            $("#massageActivateWorkerId").text("Worker with id " + id + " not exists");
            }
        }
        
   });
};

function deactivateWorkerById (id){
    $.ajax({
        url: SERVER_URL+'worker/deactivate/'+id,
        success: function(data){
            console.log(data);
            $("#massageDeactivateWorkerId").text("Виконавець з id = " + id + " видалений зі списку виконавців.");
        },
        statusCode:{ 409:function(){
            $("#massageDeactivateWorkerId").text("Worker with id " + id + " not exists");
            }
        }
        
   });
};

function showAllAdmins(){
     $.ajax({
                url: SERVER_URL+'admin/getAll',
                method: 'GET',
                contentType: 'application/json',
                complete: function(serverResponse){
                    console.log(serverResponse);
                    let admins =serverResponse.responseJSON;
                    let list= "<tr> <td> Id </td> <td> Ім'я </td> <td> Електронна адреса </td> <td> Номер телефону  </td> </tr>";
                    var i;
                    for (i=0; i<admins.length; i++){
                       list=list + "<tr> <td> " + admins[i].id + "</td> <td>" + admins[i].firstName + "</td> <td>" + admins[i].email + "</td> <td>" + admins[i].phoneNumber + "</td> </tr>";
                    }
                     $(".pageAdmin").html(list);
                }
        });
};
function showAllCustomers(){
     $.ajax({
                url: SERVER_URL+'customer/getAll',
                method: 'GET',
                contentType: 'application/json',
                complete: function(serverResponse){
                    console.log(serverResponse);
                    let customers =serverResponse.responseJSON;
                    let list= "<tr> <td> Id </td> <td> Ім'я </td> <td> Електронна адреса </td> <td> Номер телефону  </td> <td> Роль  </td> </tr>";
                    var i;
                    for (i=0; i<customers.length; i++){
                       list=list + "<tr> <td> " + customers[i].id + "</td> <td>" + customers[i].nickName + "</td> <td>" + customers[i].email + "</td> <td>" + customers[i].phoneNumber + "</td> <td>";
                         if(customers[i].role!=null){
                            list = list + customers[i].role.role + "</td> </tr>";
                        }else{
                            list = list + " null </td> </tr>"; 
                        }
                    }
                     $(".customersTable").html(list);
                }
        });
};

function deactivateCustomerById (id){
    $.ajax({
        url: SERVER_URL+'customer/deactivate/'+id,
        success: function(data){
            console.log(data);
            $("#massageDeactivateCustomerId").text("Замовник з id = " + id + " видалений зі списку замовників.");
        },
        statusCode:{ 409:function(){
            $("#massageDeactivateCustomerId").text("Customer with id " + id + " not exists");
            }
        }
        
   });
};
