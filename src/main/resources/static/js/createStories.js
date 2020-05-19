const postStories = () => {
    let title = document.getElementById("titleTBox").value;
    let genre = document.getElementById("genreTBox").value;
    let content = document.getElementById("contentTBox").value;

    console.log(content);

    axios({
      method: 'post',
      url: 'http://localhost:8181/createStories',
      data: {
        "title" : title,
        "genre" : genre,
        "content" : content,
        "user" : {
            "id" : sessionStorage.currentUserID
        }
      },
      headers: {'content-Type': 'application/json'}
    })
    .then(function (response) {
      console.log(response);
      window.alert("Story created");
    })
    .catch(function (response) {
      console.log(response);
    })
  }
  
  let buttPostUser = document.querySelector('#buttCreateStories');
  buttPostUser.addEventListener('click', postStories);