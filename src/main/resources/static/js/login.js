let form = document.querySelector(".login-form");

async function handleForm(e) {
  e.preventDefault();
  let user = {
    userEmail: form.elements.userEmail.value,
    password: form.elements.password.value,
  };
  await fetch("/api/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });

  window.location.href = "../html/allPostsByUser.html";
}

form.addEventListener("submit", handleForm);