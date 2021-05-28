function fnGetUser() {
  let userId = document.querySelector("#user-id").value;
  console.log(userId);

  fetch("https://healthyreps.herokuapp.com/api/workout/" + userId)
    .then((resp) => resp.json())
    .then((resp) => {
      document.getElementById("sets").innerHTML = resp.sets;
      document.getElementById("repsSet").innerHTML = resp.reps_per_set;
    });
}
