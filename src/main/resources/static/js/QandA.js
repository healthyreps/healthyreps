/**
 *
 * @author Akhil
 * @returns
 */

console.log("QandA.js connected!!");

let questionContainer = document.querySelector(".post-content");
let answersContainer = document.querySelector(".home-article");

const qid = localStorage.getItem("questionId");

async function loadQuestionAndData() {
  questionContainer.innerHTML = "";
  answersContainer.innerHTML = "";

  // console.log(qid);
  let question = await fetch("http://localhost:8080/api/question/" + qid);
  question = await question.json();
  let answers = await fetch(
    `http://localhost:8080/api/question/${qid}/answer/order/desc`
  );
  let answersArr = await answers.json();

  console.log(question);
  console.log(answersArr);
  renderData(question, answersArr);
}

async function renderData(q, answers) {
  // Rendering Question
  let question = `
   <h1 class="font1">Question ${q.questionId}</h1>
   <p class="font1">
    ${q.title}
   </p>
   <div class="post-meta">
     <div class="author-info">
       <div><b> ${getUser(q.userId)}</b></div>
       <div>${q.modifiedAt}</div>
     </div>
     <div class="social">
     <button class="btn delete-question" id="update-question">UPDATE</button>
     </div>
   </div>`;
  questionContainer.innerHTML += question;
  //End of question Rendering

  // Start of Answers

  let renderedAnswers = await getAnswers(answers);

  // console.log(renderedAnswers);
  answersContainer.innerHTML += renderedAnswers;

  let upButtons = document.querySelectorAll(".up");
  let downButtons = document.querySelectorAll(".down");

  for (let i = 0; i < upButtons.length; i++) {
    upButtons[i].addEventListener("click", increaseVotes);
    downButtons[i].addEventListener("click", decreaseVotes);
  }

  let updateQuestionButton = document.querySelector("#update-question");
  console.log(updateQuestionButton);
  updateQuestionButton.addEventListener("click", updateQuestion);

  let addAnswerButton = document.querySelector(".add-answer-button");
  addAnswerButton.addEventListener("click", addAnswer);

  let deleteAnswerButtons = document.querySelectorAll(".delete-answer");
  let updateAnswerButtons = document.querySelectorAll(".update-answer");

  for (let i = 0; i < deleteAnswerButtons.length; i++) {
    deleteAnswerButtons[i].addEventListener("click", deleteAnswer);
    updateAnswerButtons[i].addEventListener("click", updateAnswer);
  }
}

async function deleteAnswer(e) {
  // console.log(e.target.parentElement.parentElement.id);
  let aid = e.target.parentElement.parentElement.id;
  let url = `http://localhost:8080/api/question/${qid}/answer/${aid}`;

  await await fetch(url, {
    method: "DELETE",
  });

  loadQuestionAndData();
}

function updateAnswer(e) {
  // console.log(e.target.parentElement.parentElement.id);
  let aid = e.target.parentElement.parentElement.id;
  localStorage.setItem("answerOperation", "update");
  localStorage.setItem("answerId", aid);

  window.location.href = "newAnswer.html";
}
function addAnswer(e) {
  window.location.href = "newAnswer.html";
}

async function increaseVotes(e) {
  let cid = e.target.parentElement.parentElement.parentElement.id;
  let comment = await fetch(
    "http://localhost:8080/api/answer/1/comment/" + cid
  );
  comment = await comment.json();

  comment.reliability += 1;

  console.log(comment);

  await fetch(`http://localhost:8080/api/answer/1/comment/${cid}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(comment),
  });

  location.reload();
}

async function decreaseVotes(e) {
  let cid = e.target.parentElement.parentElement.parentElement.id;
  let comment = await fetch(
    "http://localhost:8080/api/answer/1/comment/" + cid
  );
  comment = await comment.json();

  comment.reliability -= 1;

  console.log(comment);

  await fetch(`http://localhost:8080/api/answer/1/comment/${cid}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(comment),
  });

  location.reload();
}

async function getAnswers(answers) {
  let renderedAnswers = "";

  // forEach doesn't work with async and await  inside loop :(
  for (let ans of answers) {
    let arr = await renderComments(ans.answerID);
    // console.log(arr);
    //  let arr= await test1();
    renderedAnswers += ` <div class="home-article-content font1" id="${
      ans.answerID
    }">
       
       <h3>${ans.description}</h3>
       <span>${getUser(ans.userId)} |</span>
       <span>${ans.modifiedAt}</span>
       <div class="answer-buttons">
          <button class="btn update-answer">Update Answer</button>
          <button class="btn delete-answer">Delete Answer</button>
       </div>
       
       <div class="comment">
        
          ${arr}
       
         
       </div>
     </div> `;
  }

  return renderedAnswers;
}

async function renderComments(aid) {
  let arr = await fetch(`http://localhost:8080/api/answer/${aid}/comment`);
  let comments = await arr.json();

  console.log(comments);

  let rendComments = '<button class="btn">Add Comment</button>';

  comments.forEach((el) => {
    rendComments += `
       <div class="comment-content" id="${el.commentID}">
         <hr />
         <span> ${el.commentID} |</span>
         <span>${el.modifiedAt}</span>
         <p>
          ${el.description}
         </p>
         <div class="vote">
           <div >
               <span class="count">${el.reliability}</span>
               <button class="up">up</button>
               <button class="down">down</button>
           </div>
          
           <div>
              <button class="btn">Update Comment</button>
              <button class="btn">Delete Comment</button>
           </div>
         </div>
        
         
       </div>`;
  });
  // console.log(rendComments);
  return rendComments;
}

//testing function
async function test1() {
  return "hahaha";
}

// function to get user by User ID;
function getUser(id) {
  return "Akhil";
}

document.addEventListener("DOMContentLoaded", loadQuestionAndData);
