package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.PermissionDAO;
import com.sapient.healthyreps.dao.QuestionDAO;
import com.sapient.healthyreps.entity.Question;
import com.sapient.healthyreps.exception.InvalidId;

@RestController

public class QuestionController {

	@Autowired
	QuestionDAO questionDAO;
	@Autowired
	PermissionDAO permissionDAO;

	@GetMapping("/api/allquestions")
	public List<Question> getAllQuestions() {
		return questionDAO.getAllQuestion();
	}

	@GetMapping("/api/questionidlastrow")
	public int getQuestionIDOfLastRow() {
		int a = questionDAO.getquestionidoflastRow();
		return a;
	}

	@GetMapping("/api/question/{qid}")
	public Question getQuestionFromQuestionID(@PathVariable int qid) {
		try {
			permissionDAO.isIdPresent(qid, "question");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}
		return questionDAO.getquestionfromquestionId(qid);
	}

	@GetMapping("/api/user/{uid}/questions")
	public List<Question> getAllQuestionFromUserID(@PathVariable int uid) {
		try {
			permissionDAO.isIdPresent(uid, "user");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}
		return questionDAO.getallquestionsfromuserId(uid);
	}

	@GetMapping("/api/user/{uid}/questions/order-votes/{ord}")
	public List<Question> getAllQuestionFromUserIDOrderByVotes(@PathVariable int uid, @PathVariable String ord) {
		try {
			permissionDAO.isIdPresent(uid, "user");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}
		return questionDAO.getallquestionsfromuseridbyvotesASC(uid, ord);
	}

	@GetMapping("/api/category/{cid}/questions")
	public List<Question> getAllQuestionFromCategoryID(@PathVariable int cid) {
		try {
			permissionDAO.isIdPresent(cid, "category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}
		return questionDAO.getallquestionsfromcategoryId(cid);
	}

	@GetMapping("/api/category/{cid}/questions/order-votes/{ord}")
	public List<Question> getAllQuestionFromCategoryIDOrderByVotes(@PathVariable int cid, @PathVariable String ord) {
		try {
			permissionDAO.isIdPresent(cid, "category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}
		return questionDAO.getallquestionsfromcategoryidorderbyvotesASC(cid, ord);
	}

	@GetMapping("/api/category/{cid}/questions/order-reputation/{ord}")
	public List<Question> getAllQuestionFromCategoryIDOrderByReputation(@PathVariable int cid,
			@PathVariable String ord) {
		try {
			permissionDAO.isIdPresent(cid, "category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}
		return questionDAO.getallquestionsfromcategoryidorderbyreputationASC(cid, ord);
	}

	@PostMapping("/api/user/{uid}/question-post")
	public String insertAnswer(@RequestBody Question question, @PathVariable int uid) {
		int flag = 0;
		try {
			permissionDAO.isIdPresent(uid, "user");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			flag = 1;
		}
		if (flag == 1) {
			return "Not Updated due to invalid id";
		} else {
			return questionDAO.insertQuestion(question) ? "Inserted" : "Not Inserted";
		}
	}

	@PutMapping("/api/user/{uid}/question-update/{qid}")
	public String updateQuestion(@RequestBody Question question, @PathVariable int uid, @PathVariable int qid) {
		int flag = 0;
		try {
			permissionDAO.isIdPresent(uid, "user");
			permissionDAO.isIdPresent(qid, "question");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			flag = 1;
		}
		if (flag == 1) {
			return "Not Updated due to invalid id";
		} else {
			return questionDAO.updatequestionfromquestionid(question, qid) ? "Updated" : "NotUpdated";
		}

	}

	@DeleteMapping("/api/user/{uid}/question-delete(allanswers)/{qid}")
	public String deleteAnswerByAnswerId(@PathVariable int uid, @PathVariable int qid) {
		int flag = 0;
		try {
			permissionDAO.isIdPresent(uid, "user");
			permissionDAO.isIdPresent(qid, "question");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			flag = 1;
		}
		if (flag == 1) {
			return "NotDeleted due to invalid ID";
		} else {
			return questionDAO.deletequestionsfromquestionid(qid) ? "Deleted" : "NotDeleted";
		}

	}

}
