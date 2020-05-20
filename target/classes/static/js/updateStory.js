const updateStory = () => {
    let title = document.getElementById("titleTBox").value;
    let genre = document.getElementById("genreTBox").value;
    let content = document.getElementById("contentTBox").value;
    console.log(sessionStorage.selectedStoryID);
    axios({
      method: 'put',
      url: `http://localhost:8181/updateStories/${sessionStorage.selectedStoryID}`,
      data: {
        "title" : title,
        "genre" : genre,
        "content" : content
      },
      headers: {'content-Type': 'application/json'}
    })
    .then(function (response) {
      console.log(response);
      window.alert("story updated")
    })
    .catch(function (response) {
      console.log(response);
    })
  }
  
  let buttUpdateStory = document.querySelector('#buttUpdateStories');
  buttUpdateStory.addEventListener('click', updateStory);