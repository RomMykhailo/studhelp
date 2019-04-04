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
}