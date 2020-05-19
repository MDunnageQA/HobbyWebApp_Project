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