/**
 *
 * @author Akhil
 * @returns
 */

console.log("newQuestion.js connected  !!");

let form = document.querySelector(".question-form");

// console.log(submitButton);

async function handleFormData(e) {
  e.preventDefault();

  let title = form.elements.title.value;
  //   console.log(title);
  let description = form.elements.description.value;
  //   console.log(description);
  let category = form.elements.category.value;
  //   console.log(category);
  let user = form.elements.user.value;
  //   console.log(user);
  let link = form.elements.link.value;
  //   console.log(link);

  let questionObject = {
    title: `${title}`,
    description: `${description}`,
    votes: 0,

    categoryID: `${category}`,
    userId: `${user}`,
    imageLink: `${link}`,
    reliability: 10,
  };
  let url = "";
  let methodUsed = "";
  if (localStorage.getItem("operationQuestion") === "updateQuestion") {
    const qid = localStorage.getItem("questionId");
    url = `https://healthyreps.herokuapp.com/api/user/1/question-update/${qid}`;
    methodUsed = "PUT";
  } else {
    url = `https://healthyreps.herokuapp.com/api/user/${user}/question-post`;
    methodUsed = "POST";
  }
  await fetch(url, {
    method: methodUsed,
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(questionObject),
  });

  if (localStorage.getItem("operationQuestion") === "updateQuestion") {
    localStorage.removeItem("operationQuestion");
    window.location.href = "QandA.html";
  } else window.location.href = "/";
}

async function populateLists() {
  let categorySelect = document.querySelector("#category");
  console.log(categorySelect);
  let categoryList = await fetch(
    "https://healthyreps.herokuapp.com/api/category"
  );
  categoryList = await categoryList.json();
  console.log(categoryList);

  let options = "";

  categoryList.forEach((el) => {
    options += `<option value="${el.categoryID}">${el.categoryName}</option>`;
  });

  categorySelect.innerHTML += options;
  // console.log(localStorage.getItem("operationQuestion"));

  //Populating the update form with initial data
  if (localStorage.getItem("operationQuestion") === "updateQuestion") {
    const qid = localStorage.getItem("questionId");
    let question = await fetch(
      `https://healthyreps.herokuapp.com/api/question/${qid}`
    );
    question = await question.json();
    console.log(question);
    form.elements.title.value = question.title;

    form.elements.description.value = question.description;

    form.elements.category.value = question.categoryID;

    form.elements.user.value = 2;

    form.elements.link.value = question.imageLink;

    //  localStorage.removeItem("operationQuestion")
  }
}

form.addEventListener("submit", handleFormData);

document.addEventListener("DOMContentLoaded", populateLists);
