function addPlanner() {
  document.getElementById("addData").style.display = "block";
}
function addPlannerUtil() {
  let obj = {};
  let _userId = document.querySelector("#user-id").value;
  obj.userId = parseInt(_userId);
  obj.date = document.getElementById("dateEntryId").value;
  obj.timing = document.getElementById("timeEntryId").value;
  obj.exercise_perform = document.getElementById("exercisesPerformId").value;
  obj.target = document.getElementById("exercisesTargetId").value;
  obj.diet_goal = document.getElementById("dietGoalId").value;
  if (formValidation(obj)) {
    console.log(obj);
    fetch("https://localhost:8080/api/planner/", {
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
  if (obj.exercise_perform == "") {
    alert("Enter the excercise to be performed!");
    return false;
  }
  if (obj.target == "") {
    alert("Enter excercise target!");
    return false;
  }
  if (obj.diet_goal == "") {
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
  var row = table.insertRow(table.rows.length);
  var date = row.insertCell(0);
  var time = row.insertCell(1);
  var exce = row.insertCell(2);
  var targ = row.insertCell(3);
  var diet = row.insertCell(4);
  fetch("https://localhost:8080/api/planner/" + userId)
    .then((resp) => resp.json())
    .then((resp) => {
      date.innerHTML = resp.date;
      time.innerHTML = resp.timing;
      exce.innerHTML = resp.exercise_perform;
      targ.innerHTML = resp.target;
      diet.innerHTML = resp.diet_goal;
    })
    .catch((err) => console.log(err));
}
