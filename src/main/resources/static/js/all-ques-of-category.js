

//Initial function call when the DOM is loaded
  async function getAllQuestions() {
    orderVotes = "DESC";
    orderDate = "DESC";
    container = document.querySelector(".question-container");
    form = document.querySelector("#searchForm");
    document.querySelector(".votes").addEventListener("click", getQuestionsByVotes);
    document.querySelector(".date").addEventListener("click", getQuestionsByDate);
  
    let category_id = localStorage.getItem("category_id");
    let questions = await fetch(
    "http://localhost:8080/api/category/" +
      category_id +
      "/questions"
  );
  let arr = await questions.json();
  // console.log(arr);
  renderQuestions(arr);
  container.addEventListener("click", goToAnswers);
  let upButtons = document.querySelectorAll(".up");
  let downButtons = document.querySelectorAll(".down");

  for (let i = 0; i < upButtons.length; i++) {
    upButtons[i].addEventListener("click", increaseVotes);
    downButtons[i].addEventListener("click", decreaseVotes);
  }
}

function renderQuestions(questions) {
  let container = document.querySelector(".question-container");
  container.innerHTML = "";
  questions.forEach((q) => {
    let question = `<div class="home-article" id="${q.questionId}">
                      <div class="home-article-img">
                        <img src="${q.imageLink}" alt="" />
                        <button class="btn delete-question">Delete</button>
                      </div>

                      <div class="home-article-content font1">
                        <a href=""  class="question-link">
                        <h3>${q.title}</h3>
                        </a>

                        <span >Votes <span id="v${q.questionId}">${q.votes}</span></span><br>
                        <button class="up btn">up</button>
                        <button class="down btn">down</button><br>
                        <span>Date ${q.modifiedAt} </span>
                      </div>
                    </div>`;
    container.innerHTML += question;
  });
}


async function increaseVotes(e) {
  //console.log("up pressed");
  let qid = e.target.parentElement.parentElement.id;
  //console.log(qid);
  let question = await fetch("http://localhost:8080/api/question/" + qid);

  question = await question.json();
  question.votes += 1;
  //console.log(question);
  await fetch(`http://localhost:8080/api/user/1/question-update/${qid}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(question),
  });

  getAllQuestions();
}

async function decreaseVotes(e) {
  //console.log("down pressed");
  let qid = e.target.parentElement.parentElement.id;
  //console.log(qid);
  let question = await fetch("http://localhost:8080/api/question/" + qid);

  question = await question.json();
  question.votes -= 1;
  //console.log(question);
  await fetch(`http://localhost:8080/api/user/1/question-update/${qid}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(question),
  });

  getAllQuestions();
}

//Function to get clicked QuestionId and redirect to QandA page
function goToAnswers(e) {
  // //console.log(e.target);

  if (e.target.parentElement.classList.contains("question-link")) {
    e.preventDefault();
    const id = e.target.parentElement.parentElement.parentElement.id;
    localStorage.setItem("questionId", id);
    // //console.log(e.target.parentElement.parentElement.parentElement.id);
    window.location.href = "QandA.html";
  } else if (e.target.classList.contains("delete-question")) {
    //console.log("delete pressed");
    // //console.log(e.target.parentElement.parentElement.id)
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
  //console.log(res);

  let questions = await fetch("http://localhost:8080/api/allquestions");
  let arr = await questions.json();
  //console.log(arr);
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
  // //console.log(arr);
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
  // //console.log(arr);
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

document.addEventListener("DOMContentLoaded", getAllQuestions);
