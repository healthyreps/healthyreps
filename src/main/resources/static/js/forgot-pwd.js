let form = document.querySelector(".pwd-form");

async function handleForm(e) {
  e.preventDefault();
  let user = {
    email: form.elements.email.value,
    newPassword: form.elements.newPassword.value,
  };
  await fetch("/api/changePassword", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });

  window.location.href = "../html/login.html";
}

form.addEventListener("submit", handleForm);
