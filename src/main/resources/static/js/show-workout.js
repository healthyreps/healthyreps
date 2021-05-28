function fnGetUser() {
    let userId = document.querySelector("#user-id").value;
    console.log(userId);

    fetch("http://localhost:8080/api/workout/"+ userId)
        .then((resp) => resp.json())
        .then(resp => {
            document.getElementById("sets").innerHTML = resp.sets;
            document.getElementById("repsSet").innerHTML = resp.reps_per_set;

        });
}



