
let deleteStory = document.querySelector("#buttDeleteStory");

function deleteSelectedStory() {

    console.log(sessionStorage.selectedStoryID);
    
    axios({
        method : 'delete',
        url: `http://localhost:8181/deleteStories/${sessionStorage.selectedStoryID}`,
        data: {},
        headers: 
        {'content-Type': 'application/json'}
  })
  .then(function (response) {
    console.log(response);
    window.alert("story successfully deleted");
  })
  .catch(function (response) {
    console.log(response);
  })
    
}

deleteStory.addEventListener("click", deleteSelectedStory);