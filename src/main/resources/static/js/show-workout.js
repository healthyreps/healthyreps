function fnGetUser() {
  let userId = document.querySelector("#user-id").value;
  console.log(userId);

  fetch("https://healthyreps.herokuapp.com/api/workout/" + user_id)
    .then((resp) => resp.json())
    .then((resp) => {
      document.getElementById("sets").innerHTML = resp.sets;
      document.getElementById("repsSet").innerHTML = resp.reps_per_set;
      document.getElementById("time").innerHTML = resp.time;
      document.getElementById("description").innerHTML = resp.description;
    });
}
