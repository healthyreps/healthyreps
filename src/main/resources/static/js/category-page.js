/**
 *
 * @author Shivam
 * @returns
 */

console.log("index.js connected!! by shivam");

async function getAllCategory() {
  let categories = await fetch(
    "https://healthyreps.herokuapp.com/api/category"
  );
  let container = document.querySelector(".category-content");
  container.innerHTML = "";
  let arr = await categories.json();
  // console.log(arr );
  renderCategory(arr, container);
}

function renderCategory(categories, container) {
  categories.forEach((cat) => {
    let category = `<div class="category-element" >
            <span class="text font1" class="text"> 
                <a href="all-ques-of-category.html" id="${cat.categoryID}">${cat.categoryName}</a>
            </span>
            <img src="https://source.unsplash.com/1600x900/?health">
        </div>`;
    container.innerHTML += category;
  });
  container.childNodes.forEach((category) => {
    category.addEventListener("click", goToCategoryQuestions);
  });
}

function goToCategoryQuestions(event) {
  event.preventDefault();
  console.log(event.target.id);
  const id = event.target.id;
  localStorage.setItem("category_id", id);
  window.location.href = "all-ques-of-category.html";
}

document.addEventListener("DOMContentLoaded", getAllCategory);
