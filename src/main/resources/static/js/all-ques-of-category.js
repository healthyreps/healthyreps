async function getAllQuestions() {
  let category_id = localStorage.getItem("category_id");
  let questions = await fetch(
    "https://healthyreps.herokuapp.com/api/category/" +
      category_id +
      "/questions"
  );
  let arr = await questions.json();
  // console.log(arr);
  renderQuestions(arr);
}

function renderQuestions(questions) {
  let container = document.querySelector(".category-content");
  questions.forEach((q) => {
    let question = `<div class="home-article">
        <div class="home-article-img">
          <img src="../img/11.svg" alt="" />
        </div>

        <div class="home-article-content font1">
          <a href="" id=${q.questionId}>
          <h3>${q.title}</h3>
          </a>

          <span>Votes ${q.votes}</span><br>
         <span>Date ${q.modifiedAt} </span>
        </div>
      </div>`;
    container.innerHTML += question;
  });
  container.addEventListener("click", goToAnswers);
}

function goToAnswers(e) {
  e.preventDefault();
  const id = e.target.parentElement.id;
  localStorage.setItem("questionId", id);
  window.location.href = "QandA.html";
}

document.addEventListener("DOMContentLoaded", getAllQuestions);
