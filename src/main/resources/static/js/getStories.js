const REQ = new XMLHttpRequest();
let showStoriesButton = document.querySelector("#buttGetStories");

function getStories() {
  REQ.onload = () => {
    if (REQ.status === 200) {
      console.dir(REQ);
      let responseObject = REQ.response;
      console.log(responseObject);
      document.write("<p>" + responseObject + "</p>");
    } else {
      console.log(`something went wrong`);
      window.alert(`Oh no! Something went wrong :(`);
    }
  };

  let userOBJ = REQ.open("GET", `http://localhost:8181/getAllStories/`);
  REQ.setRequestHeader("Content-type", "Application/json");
  REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
  REQ.responseType = "json";
  console.log(userOBJ);
  REQ.send();
}

showStoriesButton.addEventListener("click", getStories);