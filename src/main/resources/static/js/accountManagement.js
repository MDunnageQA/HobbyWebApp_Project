const REQ = new XMLHttpRequest();
let deleteUser = document.querySelector("#buttDeletUser");

function getTest() {

    const idToDelete = document.getElementById(deleteIDBox).nodeValue;
    
    axios.delete('http://localhost:8181/delete/${idToDelete}', {
        headers: { 
         'content-Type': 'application/json'
        },
        data: {
        }
      });
}

deleteUser.addEventListener("click", getTest);