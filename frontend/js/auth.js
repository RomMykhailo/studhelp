$(document).ready(function(){
    $('#registerId').on('click',function(){
        $('.signin').hide();
        $('.signupWorker').hide();
        $('.signupCustomer').hide();
        $('.signupRole').show();
    });
    $('#signinId').on('click',function(){
        $('.signupWorker').hide();
        $('.signupCustomer').hide();
        $('.signupRole').hide();
        $('.signin').show();
    });
    $('#signupRoleButtonId').on('click',function(){
        $('.signupRole').hide();
        $('.signin').hide();
        let roleRegister = $('#signupRoleId').val();
        if(roleRegister === 'CUSTOMER'){
            $('.signupWorker').hide();
            $('.signupCustomer').show();
        }else{
            $('.signupCustomer').hide();
            $('.signupWorker').show();
        }   
    });
    
    $('#customerRegisterId').on('click',function(){
        signupCustomer();
    })
    
    $('#workerRegisterId').on('click',function(){
        signupWorker();
    })
     $('#signinButtonId').on('click',function(){
        signin();
    })
    
});

function signupCustomer(){
    let customerName = $('#customerNameId').val();
    let customerEmail = $('#customerEmailId').val();
    let customerPassword = $('#customerPasswordId').val();
    let customerConfirm = $('#customerConfirmId').val();
    let customerPhoneNumber = $('#customerPhoneId').val();
    
    let customer = {
        nickName: customerName,
        email: customerEmail,
        password: customerPassword,
        passwordConfirm: customerConfirm,
        phoneNumber: customerPhoneNumber
    }
    console.log(JSON.stringify(customer));
                
    $.ajax({
                url: SERVER_URL+'auth/signupcustomer',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(customer),
                complete: function(serverResponse){
                    console.log(serverResponse)
                    if(serverResponse.status == 201){
                        $('.signupCustomer').hide();
                        $('#massageId').text('Вітаємо! Ви успішно зареєструвалися.');
                        $('#massageId').css({backgroundColor:'green'})
                    }
                }
            });
    
}

function signupWorker(){
    let workerName = $('#workerNameId').val();
    let workerEmail = $('#workerEmailId').val();
    let workerPassword = $('#workerPasswordId').val();
    let workerConfirm = $('#workerConfirmId').val();
    let workerPhoneNumber = $('#workerPhoneId').val();
    let workerDeskription = $('#workerDescriptionId').val();
    
    let worker = {
        nickName: workerName,
        email: workerEmail,
        password: workerPassword,
        passwordConfirm: workerConfirm,
        phoneNumber: workerPhoneNumber,
        description: workerDeskription
    }
    console.log(JSON.stringify(worker));
                
    $.ajax({
                url: SERVER_URL+'auth/signupworker',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(worker),
                complete: function(serverResponse){
                    console.log(serverResponse);
                    if(serverResponse.status == 201){
                        $('.signupWorker').hide();
                        $('#massageId').text('Вітаємо! Ваша заявка прийнята. Очікуйте коли адміністратор додасть Вас до списку виконавців.');
                        $('#massageId').css({backgroundColor:'green'}) 
                    }
                }
            });
    
}

function signin(){
    let userEmail = $('#signinEmailId').val();
    let userPassword = $('#signinPasswordId').val();
   
    let user = {
        email: userEmail,
        password: userPassword
    }
    let url1;
    console.log(JSON.stringify(user));
        let role = $('#roleId').val();
        let page;
           if(role==="ADMIN"){
              url1 = 'auth/signinadmin';
                page = "pageAdmin.html";
            }else if(role==="WORKER"){
                     url1 = 'auth/signinworker';
                        page = "pageWorker.html";
                     }else{
                         url1 = 'auth/signincustomer';
                            page = "pageCustomer.html";
                     }
    $.ajax({
                url: SERVER_URL+url1,
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(user),
                complete: function(serverResponse){
                    console.log(serverResponse);
                    if(serverResponse.status==200){                       localStorage.setItem('auth_token',serverResponse.responseText);
                        window.location.href = page;
                    }
                }
            });
    
}
      
