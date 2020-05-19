const REQ2 = new XMLHttpRequest();
let userLoginButton = document.querySelector("#buttGetOneStory");

function getOneStory() {

    let storyID = document.getElementById("storyidTBox").value;

  REQ2.onload = () => {
    if (REQ2.status === 200) {
      console.dir(REQ2);
      let responseObject = REQ2.response;
      console.log(responseObject);
      sessionStorage.setItem('selectedStory',  JSON.stringify(responseObject));
      let selectedStory = JSON.parse(sessionStorage.getItem('selectedStory'));
      sessionStorage.setItem('selectedStoryID', selectedStory.id);
      let selectedStoryID = sessionStorage.getItem('selectedStoryID');
      window.alert(`story has been selected`);
    } else {
      console.log(`something went wrong`);
      window.alert(`Oh no! Something went wrong :(`);
    }
  };

  let userOBJ = REQ2.open("GET", `http://localhost:8181/getStoriesByID/${storyID}`);
  REQ2.setRequestHeader("Content-type", "Application/json");
  REQ2.setRequestHeader('Access-Control-Allow-Origin', '*');
  REQ2.responseType = "json";
  console.log(userOBJ);
  REQ2.send();

}

userLoginButton.addEventListener("click", getOneStory);