/**
 *
 * @author Akhil
 * @returns
 */
async function updateQuestion(e) {
  //   console.log(qid);

  localStorage.setItem("operationQuestion", "updateQuestion");

  console.log(localStorage.getItem("operationQuestion"));

  window.location.href = "newQuestion.html";
}
