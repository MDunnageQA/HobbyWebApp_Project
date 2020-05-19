const REQ = new XMLHttpRequest();
let deleteUser = document.querySelector("#buttDeleteUser");

function deleteThisUser() {

    console.log(sessionStorage.currentUserID);
    
    axios({
        method : 'delete',
        url: `http://localhost:8181/deleteUser/${sessionStorage.currentUserID}`,
        data: {},
        headers: 
        {'content-Type': 'application/json'}
  })
  .then(function (response) {
    console.log(response);
    window.alert("User successfully deleted");
  })
  .catch(function (response) {
    console.log(response);
  })
    
}

deleteUser.addEventListener("click", deleteThisUser);