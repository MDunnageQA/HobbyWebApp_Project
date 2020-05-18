const REQ = new XMLHttpRequest();
let userLoginButton = document.querySelector("#buttGetTest");

function getTest() {

    Object; userToLogin

  REQ.onload = () => {
    if (REQ.status === 200) {
      console.dir(REQ);
      let responseObject = REQ.response;
      console.log(responseObject);
    } else {
      console.log(`something went wrong`);
      window.alert(`Oh no! Something went wrong :(`);
    }
  };

  let userOBJ = REQ.open("GET", `http://localhost:8181/getAllUsers/`);
  REQ.setRequestHeader("Content-type", "Application/json");
  REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
  REQ.responseType = "json";
  console.log(userOBJ);
  REQ.send();
}

userLoginButton.addEventListener("click", getTest);