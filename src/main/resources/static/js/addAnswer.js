/**
 *
 * @author Akhil
 * @returns
 */

console.log("add Answer connected");

let form = document.querySelector(".answer-form");

// console.log(form);

async function handleForm(e) {
  e.preventDefault();
  //   console.log("submitted");
  let descriptionInp = form.elements.description.value;
  //   console.log(description);

  let answer = {
    description: descriptionInp,
    userID: 1,
  };

  let qid = localStorage.getItem("questionId");

  let url = "";
  let method = "";
  if (localStorage.getItem("answerOperation") === "update") {
    let aid = localStorage.getItem("answerId");
    url = `http://localhost:8080/api/question/${qid}/answer/${aid}`;
    method = "PUT";
  } else {
    url = `http://localhost:8080/api/question/${qid}/answer`;
    method = "POST";
  }
  await fetch(url, {
    method: method,
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(answer),
  });

  localStorage.removeItem("answerOperation");
  localStorage.removeItem("answerId");
  window.location.href = "QandA.html";
}

async function populateAnswer() {
  if (localStorage.getItem("answerOperation") === "update") {
    let aid = localStorage.getItem("answerId");
    let ans = await fetch(
      `http://localhost:8080/api/question/1/answer/${aid}`
    );
    ans = await ans.json();

    form.elements.description.value = ans.description;
  }
}

form.addEventListener("submit", handleForm);
populateAnswer();
