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
    $('#adminId').on('click', function(){
        $('.pageAdmin').show();
        showAllAdmins();
    });
    $('#customerId').on('click', function(){
        $('.pageCustomer').show();
        showAllCustomers();
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
    $('.pageAdmin').hide();
    $('.pageRespond').hide();
}

function showMe(){
      $.ajax({
                url: SERVER_URL+'worker/getByToken',
                method: 'GET',
                contentType: 'application/json',
                complete: function(serverResponse){
                    console.log(serverResponse);
                    let worker = serverResponse.responseJSON;
                     let myprof = "<tr> <td> Id </td> <td>" + worker.id + "</td> </tr> <tr> <td> Email </td>  <td>" + worker.email + "</td> </tr> <tr> <td> Ім'я </td> <td>" + worker.nickName + "</td> </tr> <tr> <td> Номер телефону </td> <td>" + worker.phoneNumber + "</td> </tr> <tr> <td> Опис </td> <td>" + worker.description + "</td> </tr>"; 
                    $('#tableMyProfId').html(myprof);
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

 