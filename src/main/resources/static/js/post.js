//fix: if we increase the comment size a lot, the box will overflow
// add author, timestamp, category, votes(upvote, downvote post), 

let signedIn = true;
const postId = 2;



console.log("Signed in: " + signedIn);
const signupDiv = document.querySelector(".signup").parentNode;
const addCommentDiv = document.querySelector(".addComment").parentNode;


let hidableButtons = document.querySelector("#hidable");
let editBtn = document.querySelectorAll("#editBtn");
let deleteBtn = document.querySelectorAll("#deleteBtn");

const commentContent = document.querySelectorAll(".commentContent");

let rating = document.querySelectorAll("#rating");
let scores = document.querySelectorAll("#score");
let upvoteBtn = document.querySelectorAll("#upvoteBtn");
let downvoteBtn = document.querySelectorAll("#downvoteBtn");
let reportBtn = document.querySelectorAll("#reportBtn");

const blogTitle = document.querySelector(".blogTitle");
const blogImage = document.querySelector(".blogImage");
const blogContent = document.querySelector(".main");


const authorName = document.querySelector("#authorName");
const postTimestamp = document.querySelector("#postTimestamp");


const upvotePost = document.querySelector("#upvotePost");
const downvotePost = document.querySelector("#downvotePost");
const postRating = document.querySelector("#postRating");


const commentsDiv = document.querySelector(".commentsCenter");

if (signedIn){
    console.log(signupDiv)
    signupDiv.style.display = "none";
    addCommentDiv.style.display = "block";
}
else{
    console.log(addCommentDiv)
    signupDiv.style.display = "block";
    addCommentDiv.style.display = "none";
}



fetchAllData();
addCommentListeners();


upvotePost.addEventListener("click", (e)=>{
    console.log(postRating.innerText);
    postRating.innerText = parseInt(postRating.innerText) + 1;
});



downvotePost.addEventListener("click", (e)=>{
    console.log(postRating.innerText);
    postRating.innerText = parseInt(postRating.innerText) - 1;
});


function fetchAllData(){
    // fetch the post
    // TO DO: ADD AUTHOR DETAILS FROM USER TABLE
    fetch('http://localhost:8080/post/' + postId)
    .then(res => res.json())
    .then(json => {
        console.log(json);
        let postDate = new Date(json.timeStamp);
        // console.log(postDate.getDate() + "/" + postDate.getMonth() + "/" + postDate.getYear())
        console.log(postDate.toDateString())
        blogTitle.innerHTML = "<h1>" + json.title + "</h1>";
        blogContent.innerHTML = "<p>" + json.content + "</p>";
        postTimestamp.innerText = postDate.toDateString();
        postRating.innerText = json.votes;
    }
    ).catch(err => {
        console.log(err);
    });

    // fetch all comments for this post
    fetch('http://localhost:8080/CommentsPost/post/' + postId)
    .then(res => res.json())
    .then(json => {
        console.log(json)
        for(let i=0;i<json.length;i++){
            let createCommentCardview = document.createElement("div");
            let createComment = document.createElement("div");
            let createCommentImg = document.createElement("div");
            let createCommentContent = document.createElement("div");
            let createCommentRight = document.createElement("div");
            let createCommentText = document.createElement("p");
            let hidableDiv = document.createElement("div");
            let commentRatingDiv = document.createElement("div");
            let commentActionButtons = document.createElement("div");
            let commentTimestamp = document.createElement("div");

            createCommentCardview.className = "cardview";
            createCommentCardview.id = json[i].id;
            createComment.className = "comment";
            createCommentImg.className = "commentImg";
            createCommentContent.className = "commentContent";
            createCommentText.className = "commentText";
            createCommentRight.className = "commentRight";

            hidableDiv.id = "hidable";
            commentRatingDiv.id = "rating";
            commentActionButtons.className = "actionbuttons";
            commentTimestamp.id = "timestamp";

            createCommentCardview.appendChild(createComment);

            createComment.appendChild(createCommentImg);
            createComment.appendChild(createCommentContent);
            createComment.appendChild(createCommentRight);

            createCommentImg.innerHTML = "<img src=\"http://source.unsplash.com/1600x900/?health"; alt="Avatar\">" + json[i].userId;
            createCommentContent.appendChild(createCommentText);
            createCommentText.innerHTML = json[i].content;

            createCommentRight.appendChild(hidableDiv);
            createCommentRight.appendChild(commentRatingDiv);
            createCommentRight.appendChild(commentActionButtons);
            createCommentRight.appendChild(commentTimestamp);

            hidableDiv.innerHTML = "<button id=\"deleteBtn\">Delete</button><button id=\"editBtn\">Edit</button>";
            commentRatingDiv.innerHTML = "Rating: <span id=\"score\">" + json[i].votes + "</span>";
            commentActionButtons.innerHTML = "<button id=\"upvoteBtn\">Upvote</button><button id=\"downvoteBtn\">Downvote</button><button id=\"reportBtn\">Report</button>";
            commentTimestamp.innerText = new Date(json[i].timestamp).toDateString();

            commentsDiv.appendChild(createCommentCardview);
            
            let tempScore;
            for(let i=0;i<commentRatingDiv.childNodes.length;i++){
                if(commentRatingDiv.childNodes[i].id == "score"){
                    tempScore = commentRatingDiv.childNodes[i];
                    break;
                }
            }

            for(let i=0;i<commentActionButtons.childNodes.length;i++){
                //upvote button
                if(commentActionButtons.childNodes[i].id == "upvoteBtn"){
                    commentActionButtons.childNodes[i].addEventListener("click", (e)=>{
                        console.log(tempScore.innerText);
                        tempScore.innerText = parseInt(tempScore.innerText) + 1;
                    });
                }

                //downvote button
                else if(commentActionButtons.childNodes[i].id == "downvoteBtn"){
                    commentActionButtons.childNodes[i].addEventListener("click", (e)=>{
                        console.log(tempScore.innerText);
                        tempScore.innerText = parseInt(tempScore.innerText) - 1;
                    });
                }
                // report button
                
            }

            //hidable buttons
            for(let i=0;i<hidableDiv.childNodes.length;i++){
                if(hidableDiv.childNodes[i].id == "deleteBtn"){
                    hidableDiv.childNodes[i].addEventListener("click", (e)=>{
                        // console.log(commentContent[i].parentNode.parentNode)
                        const parent = createCommentCardview;
                        console.log(parent)
                        if(confirm("are you sure?")){
                            // parent.style.display = "none";
                            parent.remove();
                            //add api endpoint for delete
                        }
                    });
                }
                else if(hidableDiv.childNodes[i].id == "editBtn"){
                    hidableDiv.childNodes[i].addEventListener("click", (e)=>{
                        console.log(createCommentText.contentEditable);
                        if(createCommentText.contentEditable == "inherit"){
                            createCommentText.contentEditable = true;
                            createCommentText.style.border = "1px solid black";
                            console.log(createCommentText.style.paddingLeft);
                            createCommentText.style.padding = "10px";
                        }
                        else{
                            createCommentText.contentEditable = "inherit";
                            createCommentText.style.border = "none";
                            createCommentText.style.padding = "2px";
                        }

                        //add the api end point for edit
                    });
                }
            }

        }
    }).catch(err =>{
        console.log(err);
        const errorLoadingComments = document.querySelector(".commentsError");
        console.log(errorLoadingComments)
        errorLoadingComments.parentNode.style.display = "block"
    }).finally(()=>{
        rating = document.querySelectorAll("#rating");
        scores = document.querySelectorAll("#score");
        upvoteBtn = document.querySelectorAll("#upvoteBtn");
        downvoteBtn = document.querySelectorAll("#downvoteBtn");
        reportBtn = document.querySelectorAll("#reportBtn");

        hidableButtons = document.querySelector("#hidable");
        editBtn = document.querySelectorAll("#editBtn");
        deleteBtn = document.querySelectorAll("#deleteBtn");
        
        
    });


    //fetch media
}

// not gonna be there in the final version
function addCommentListeners(){
    for(let i=0;i<editBtn.length;i++){
        editBtn[i].addEventListener("click", (e)=>{
            console.log(createCommentText.contentEditable);
            if(createCommentText.contentEditable == "inherit"){
                createCommentText.contentEditable = true;
                createCommentText.style.border = "1px solid black";
                console.log(createCommentText.style.paddingLeft);
                createCommentText.style.padding = "10px";
            }
            else{
                createCommentText.contentEditable = "inherit";
                createCommentText.style.border = "none";
                createCommentText.style.padding = "2px";
            }
        });
    }
    
    
    for(let i=0;i<deleteBtn.length;i++){
        deleteBtn[i].addEventListener("click", (e)=>{
            console.log(commentContent[i].parentNode.parentNode)
            const parent = commentContent[i].parentNode.parentNode;
            if(confirm("are you sure?")){
                parent.style.display = "none";
            }
        });
    }
    
    
    for(let i=0;i<upvoteBtn.length;i++){
        upvoteBtn[i].addEventListener("click", (e)=>{
            console.log(scores[i].innerText);
            scores[i].innerText = parseInt(scores[i].innerText) + 1;
        });
    }
    
    
    for(let i=0;i<downvoteBtn.length;i++){
        downvoteBtn[i].addEventListener("click", (e)=>{
            console.log(scores[i].innerText);
            scores[i].innerText = parseInt(scores[i].innerText) - 1;
        });
    }
}


