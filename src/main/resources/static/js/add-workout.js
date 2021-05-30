function addWorkout() {
  let obj = {};
  obj.userId = 5;
  let day = document.getElementById("days").value;
  if (day == "default") obj.day = 0;
  else if (day == "monday") obj.day = 1;
  else if (day == "tuesday") obj.day = 2;
  else if (day == "wednesday") obj.day = 3;
  else if (day == "thursday") obj.day = 4;
  else if (day == "friday") obj.day = 5;
  else if (day == "saturday") obj.day = 6;
  else obj.day = 7;

  let timeInString = String(document.getElementById("time").value);
  //console.log(timeInString);
  obj.time = timeInString;
  obj.sets = document.getElementById("sets").value;
  obj.reps_per_set = document.getElementById("reps").value;
  obj.description = document.getElementById("description").value;

  if (formValidation(obj)) {
    console.log(obj);
    fetch("http://localhost:8080/api/workout/", {
      method: "POST",
      body: JSON.stringify(obj),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((resp) => resp.json())
      .then((resp) => {
        alert("Workout Added!");
        //window.location.href = "../html/add-workout.html";
        return true;
      })
      .catch((err) => console.log("Error : ", err));
  }

  return false;
}

function formValidation(obj) {
  if (obj.day == 0) {
    alert("Select a day!");
    return false;
  }
  if (obj.time == "") {
    alert("Select a time!");
    return false;
  }
  if (obj.sets == "") {
    alert("Enter number of sets!");
    return false;
  }
  if (obj.reps_per_set == "") {
    alert("Enter number of repetition per sets!");
    return false;
  }
  if (obj.description == "") {
    alert("Enter description of workout!");
    return false;
  }
  return true;
}
