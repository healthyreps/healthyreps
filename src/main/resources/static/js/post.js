//fix: if we increase the comment size a lot, the box will overflow

let signedIn = true;
console.log("Signed in: " + signedIn);
const signupDiv = document.querySelector(".signup").parentNode;
const addCommentDiv = document.querySelector(".addComment").parentNode;

const hidableButtons = document.querySelector("#hidable");
const editBtn = document.querySelectorAll("#editBtn");
const deleteBtn = document.querySelectorAll("#deleteBtn");

const commentContent = document.querySelectorAll(".commentContent");

const rating = document.querySelectorAll("#rating");
const scores = document.querySelectorAll("#score");
const upvoteBtn = document.querySelectorAll("#upvoteBtn");
const downvoteBtn = document.querySelectorAll("#downvoteBtn");
const reportBtn = document.querySelectorAll("#reportBtn");

if (signedIn) {
  console.log(signupDiv);
  signupDiv.style.display = "none";
  addCommentDiv.style.display = "block";
} else {
  console.log(addCommentDiv);
  signupDiv.style.display = "block";
  addCommentDiv.style.display = "none";
}

for (let i = 0; i < editBtn.length; i++) {
  editBtn[i].addEventListener("click", (e) => {
    console.log(commentContent[i].children[0].contentEditable);
    if (commentContent[i].children[0].contentEditable == "inherit") {
      commentContent[i].children[0].contentEditable = true;
      commentContent[i].children[0].style.border = "1px solid black";
      console.log(commentContent[i].children[0].style.paddingLeft);
      commentContent[i].children[0].style.padding = "10px";
    } else {
      commentContent[i].children[0].contentEditable = "inherit";
      commentContent[i].children[0].style.border = "none";
      commentContent[i].children[0].style.padding = "2px";
    }
  });
}

for (let i = 0; i < deleteBtn.length; i++) {
  deleteBtn[i].addEventListener("click", (e) => {
    console.log(commentContent[i].parentNode.parentNode);
    const parent = commentContent[i].parentNode.parentNode;
    if (confirm("are you sure?")) {
      parent.style.display = "none";
    }
  });
}

for (let i = 0; i < upvoteBtn.length; i++) {
  upvoteBtn[i].addEventListener("click", (e) => {
    console.log(scores[i].innerText);
    scores[i].innerText = parseInt(scores[i].innerText) + 1;
  });
}

for (let i = 0; i < downvoteBtn.length; i++) {
  downvoteBtn[i].addEventListener("click", (e) => {
    console.log(scores[i].innerText);
    scores[i].innerText = parseInt(scores[i].innerText) - 1;
  });
}
// for(let i=0;i<upvoteBtn.length;i++){

// }

// for(let i=0;i<upvoteBtn.length;i++){

// }
