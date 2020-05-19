const postUser = () => {
  let userName = document.getElementById("usernameTBox").value;
  let password = document.getElementById("passwordTBox").value;
  let firstName = document.getElementById("firstnameTBox").value;
  let surname = document.getElementById("surnameTBox").value;
  let dateOfBirth = document.getElementById("dateofbirthTBox").value;
  let email = document.getElementById("emailTBox").value;
  axios({
    method: 'post',
    url: 'http://localhost:8181/createUser',
    data: {
      "userName" : userName,
      "password" : password,
      "firstName" : firstName,
      "surname" : surname,
      "dateOfBirth" : dateOfBirth,
      "email" : email
    },
    headers: {'content-Type': 'application/json'}
  })
  .then(function (response) {
    console.log(response);
    window.alert("User created");
  })
  .catch(function (response) {
    console.log(response);
  })
}

let buttPostUser = document.querySelector('#buttCreateUser');
buttPostUser.addEventListener('click', postUser);

/*const REQ = new XMLHttpRequest();
function createUser() {

      let userData = {
        "userName" : document.getElementById("usernameTBox").value,
        "password" : document.getElementById("passwordTBox").value,
        "firstName" : document.getElementById("firstnameTBox").value,
        "surname" : document.getElementById("surnameTBox").value,
        "dateOfBirth" : document.getElementById("dateofbirthTBox").value,
        "email" : document.getElementById("emailTBox").value
      }

      JSON.stringify(userData);

      console.log(userData);

      REQ.onload = () => {
        if (REQ.status === 201) {
            console.log(REQ.response);
            console.log("user created");
            window.location.href = "http://localhost:8181/js/UserRegistration.html";
        } else {
            console.log('handle error');
        }
    }
    REQ.open('POST', 'http://localhost:8181/createUser/');
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
   
    REQ.send(userData);
}

let buttCreateUser = document.querySelector('#buttCreateUser');
    buttCreateUser.addEventListener('click', createUser);

    userNameData = document.getElementById('usernameTBox').value;
    passwordData = document.getElementById('passwordTBox').value;
    firstNameData = document.getElementById('firstnameTBox').value;
    surname = document.getElementById('surnameTBox').value;
    dateOfBirthData = document.getElementById('dateofbirthTBox').value;
    emailData = document.getElementById('emailTBox').value;
    
    axios.defaults.headers.post["Content-Type"] =
      "application/json;charset=utf-8";
    axios.defaults.headers.post["Access-Control-Allow-Origin"] = "*";
    axios.post('localhost:8181/createUser', {
        userName : userNameData,
        password : passwordData,
        firstName : firstNameData,
        surname : surnameData,
        dateOfBirth : dateOfBirthData,
        email : emailData
    })
    .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });

      let userData = `[{"userName" : "${document.getElementById("usernameTBox").value}", 
      "password": "${document.getElementById("passwordTBox").value}", 
      "firstName": "${document.getElementById("firstnameTBox").value}",
      "surname": "${document.getElementById("surnameTBox").value}",
      "dateOfBirth": "${document.getElementById("dateofbirthTBox").value}",
      "email": "${document.getElementById("emailTBox").value}"}]`;*/