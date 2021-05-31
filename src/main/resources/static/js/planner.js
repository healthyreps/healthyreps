function addPlanner() {
  document.getElementById("addData").style.display = "block";
}
function addPlannerUtil() {
  let obj = {};
  let _userId = document.querySelector("#user-id").value;
  obj.userId = parseInt(_userId);
  obj.date = document.getElementById("dateEntryId").value;
  obj.timing = document.getElementById("timeEntryId").value;
  obj.exercisePerform = document.getElementById("exercisesPerformId").value;
  obj.target = document.getElementById("exercisesTargetId").value;
  obj.dietGoal = document.getElementById("dietGoalId").value;
  if (formValidation(obj)) {
    console.log(obj);
    fetch("http://localhost:8080/api/planner/", {
      method: "POST",
      body: JSON.stringify(obj),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((resp) => resp.json())
      .then(() => {
        alert("Planner Added!");
        return true;
      })
      .catch((err) => console.log("Error : ", err));
  }
  return false;
}

function formValidation(obj) {
  if (obj.userId == NaN) {
    alert("give your user id");
    return false;
  }
  if (obj.date == "") {
    alert("Select a date!");
    return false;
  }
  if (obj.timing == "") {
    alert("Select the timing!");
    return false;
  }
  if (obj.exercisePerform == "") {
    alert("Enter the excercise to be performed!");
    return false;
  }
  if (obj.target == "") {
    alert("Enter excercise target!");
    return false;
  }
  if (obj.dietGoal == "") {
    alert("Enter the diet goal!");
    return false;
  }
  return true;
}
function getPlanner() {
  document.getElementById("addData").style.display = "none";
  let userId = document.querySelector("#user-id").value;
  console.log(userId);
  var table = document.getElementById("planner-table");
  // console.log(table.children);
  for(var i = 1;i<table.children[1].children.length;++i)
  {
      table.children[1].children[i].remove();
  }
  var row = table.insertRow(table.rows.length);
  var date = row.insertCell(0);
  var time = row.insertCell(1);
  var exce = row.insertCell(2);
  var targ = row.insertCell(3);
  var diet = row.insertCell(4);

  fetch("http://localhost:8080/api/planner/" + userId)
    .then((resp) => resp.json())
    .then((resp) => {
      console.log(resp);
      date.innerHTML = resp.date;
      time.innerHTML = resp.timing;
      exce.innerHTML = resp.exercisePerform;
      targ.innerHTML = resp.target;
      diet.innerHTML = resp.dietGoal;
    })
    .catch((err) => console.log(err));
}
