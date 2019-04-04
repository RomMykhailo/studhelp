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
    $('#orderId').on('click', function(){
        $('.pageOrder').show();
        
    });
    $('#orderButtonCreateId').on('click', function(){
       createOrder(); 
    });
    $('#showOrdersButtonId').on('click', function(){
        showAllMyOrders();
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
                url: SERVER_URL+'customer/getByToken',
                method: 'GET',
                contentType: 'application/json',
                complete: function(serverResponse){
                    console.log(serverResponse);
                    let customer = serverResponse.responseJSON;
                     let myprof = "<tr> <td> Id </td> <td>" + customer.id + "</td> </tr> <tr> <td> Email </td>  <td>" + customer.email + "</td> </tr> <tr> <td> Ім'я </td> <td>" + customer.nickName + "</td> </tr> <tr> <td> Номер телефону </td> <td>" + customer.phoneNumber + "</td> </tr>"; 
                    $('#tableMyProfId').html(myprof);
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

function createOrder(){
    let orderDescription = $('#orderDescriptionId').val();
    let order = {
       description: orderDescription
    }                
    $.ajax({
                url: SERVER_URL+'order/add',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(order),
                complete: function(serverResponse){
                    console.log(serverResponse);
                    if(serverResponse.status == 201){
                        $('.signupWorker').hide();
                        $('#massageOrderId').text('Ваше замовлення прийняте. Очікуйте відповіді від адміністратора.');
                    }
                }
            }); 
}

function showAllMyOrders(){
     $.ajax({
                url: SERVER_URL+'order/getAllMyOrder',
                method: 'GET',
                contentType: 'application/json',
                complete: function(serverResponse){
                    console.log(serverResponse);
                    let orders =serverResponse.responseJSON;
                    let list= "<tr> <td> Id </td> <td> Дата створення </td> <td> Опис </td> </tr>";
                    var i;
                    for (i=0; i<orders.length; i++){
                       list=list + "<tr> <td> " + orders[i].id + "</td> <td>" + orders[i].dateCreate + "</td> <td>" + orders[i].description + "</td> </tr>";
                    }
                     $(".showMyOrders").html(list);
                }
        });
};
 