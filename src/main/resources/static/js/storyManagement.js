
let deleteUser = document.querySelector("#buttDeleteStory");

function deleteSelectedStory() {

    console.log(sessionStorage.selectedStoryID);
    
    axios({
        method : 'delete',
        url: `http://localhost:8181/deleteUser/${sessionStorage.selectedStoryID}`,
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

deleteUser.addEventListener("click", deleteSelectedStory);