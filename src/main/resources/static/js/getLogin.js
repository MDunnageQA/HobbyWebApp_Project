const REQ = new XMLHttpRequest();
let userLoginButton = document.querySelector("#userLoginButton");

function getTest() {

    const userName = document.getElementById("username").value;
    const password = document.getElementById("password").value;

  REQ.onload = () => {
    if (REQ.status === 200) {
      console.dir(REQ);
      let responseObject = REQ.response;
      console.log(responseObject);
      sessionStorage.setItem('currentUser',  JSON.stringify(responseObject));
      let currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
      sessionStorage.setItem('currentUserID', currentUser.id);
      let currentUserID = sessionStorage.getItem('currentUserID');
      console.log(currentUserID);
      console.log(currentUser.id);
      window.alert(`login successful`);
    } else {
      console.log(`something went wrong`);
      window.alert(`Oh no! Something went wrong :(`);
    }
  };

  let userOBJ = REQ.open("GET", `http://localhost:8181/getUserByUserName/${userName}`);
  REQ.setRequestHeader("Content-type", "Application/json");
  REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
  REQ.responseType = "json";
  console.log(userOBJ);
  REQ.send();

}

userLoginButton.addEventListener("click", getTest);