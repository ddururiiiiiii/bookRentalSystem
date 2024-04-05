
function bookStateEditYn(){
    let selectedValue = document.getElementById("bookStateCode").value;
    if (selectedValue == 'UNABLE'){
        document.getElementById("bookStateCode").disabled = 'true';
        document.getElementById('bookStateSpan').style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', function (){
    bookStateEditYn();
})