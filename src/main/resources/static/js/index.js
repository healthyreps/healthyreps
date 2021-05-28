/**
 *
 * @author Akhil
 * @returns
 */

console.log("index.js connected!");
let orderVotes = "DESC";
let orderDate = "DESC";
let container = document.querySelector(".question-container");
console.log(container);
let votes = document.querySelector(".votes");
let date = document.querySelector(".date");
let form = document.querySelector("#searchForm");
// console.log(form.elements.query.value);

// console.log(votes,date);

// console.log(container);

//Initial function call when the DOM is loaded
async function getAllQuestion() {
  let questions = await fetch("http://localhost:8080/api/allquestions");
  let arr = await questions.json();
  console.log(arr);
  renderQuestions(arr);
  container.addEventListener("click", goToAnswers);
}

//Funnction for rendering the list of questions
function renderQuestions(questions) {
  container.innerHTML = "";
  questions.forEach((q) => {
    let question = `<div class="home-article" id="${q.questionId}">
        <div class="home-article-img">
          <img src="../img/11.svg" alt="" />
          <button class="btn delete-question">Delete</button>
        </div>

        <div class="home-article-content font1">
          <a href="/"  class="question-link">
          <h3>${q.title}</h3>
          </a>

          <span>Votes ${q.votes}</span><br>
         <span>Date ${q.modifiedAt} </span>
        </div>
      </div>`;
    container.innerHTML += question;
  });
}
//Function to get clicked QuestionId and redirect to QandA page
function goToAnswers(e) {
  // console.log(e.target);

  if (e.target.parentElement.classList.contains("question-link")) {
    e.preventDefault();
    const id = e.target.parentElement.parentElement.parentElement.id;
    localStorage.setItem("questionId", id);
    // console.log(e.target.parentElement.parentElement.parentElement.id);
    window.location.href = "/src/main/resources/static/html/QandA.html";
  } else if (e.target.classList.contains("delete-question")) {
    console.log("delete pressed");
    // console.log(e.target.parentElement.parentElement.id)
    deleteQuestion(e.target.parentElement.parentElement.id);
  }
}

//Function to delete a question
async function deleteQuestion(id) {
  let url = `http://localhost:8080/api/user/1/question-delete(allanswers)/${id}`;
  let res = await fetch(url, {
    method: "DELETE",
  });

  res = await res.text();
  console.log(res);

  let questions = await fetch("http://localhost:8080/api/allquestions");
  let arr = await questions.json();
  console.log(arr);
  renderQuestions(arr);

  // location.reload();
}

// Function to sort By votes
function compVotesDec(a, b) {
  if (a.votes < b.votes) return 1;
  if (a.votes > b.votes) return -1;
  return 0;
}
function compVotesAsc(a, b) {
  if (a.votes < b.votes) return -1;
  if (a.votes > b.votes) return +1;
  return 0;
}

async function getQuestionsByVotes() {
  let questions = await fetch("http://localhost:8080/api/allquestions");
  let arr = await questions.json();
  // console.log(arr);
  // renderQuestions(arr);

  if (orderVotes === "DESC") {
    arr.sort(compVotesDec);
    orderVotes = "ASC";
  } else {
    arr.sort(compVotesAsc);
    orderVotes = "DESC";
  }

  renderQuestions(arr);
}

// Functions to sortBy Date

function compDateDec(a, b) {
  if (a.modifiedAt < b.modifiedAt) return 1;
  if (a.modifiedAt > b.modifiedAt) return -1;
  return 0;
}
function compDateAsc(a, b) {
  if (a.modifiedAt < b.modifiedAt) return -1;
  if (a.modifiedAt > b.modifiedAt) return +1;
  return 0;
}

async function getQuestionsByDate() {
  let questions = await fetch("http://localhost:8080/api/allquestions");
  let arr = await questions.json();
  // console.log(arr);
  // renderQuestions(arr);

  if (orderDate === "DESC") {
    arr.sort(compDateDec);
    orderDate = "ASC";
  } else {
    arr.sort(compDateAsc);
    orderDate = "DESC";
  }

  renderQuestions(arr);
}

votes.addEventListener("click", getQuestionsByVotes);
date.addEventListener("click", getQuestionsByDate);

document.addEventListener("DOMContentLoaded", getAllQuestion);
