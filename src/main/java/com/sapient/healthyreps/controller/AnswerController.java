package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.AnswerDAO;
import com.sapient.healthyreps.dao.PermissionDAO;
import com.sapient.healthyreps.entity.Answer;
import com.sapient.healthyreps.exception.InvalidId;
@CrossOrigin
@RestController

public class AnswerController {

	@Autowired
	AnswerDAO answerDAO;

	@Autowired
	PermissionDAO permissionDAO;

	@PostMapping("/api/question/{qid}/answer")
	public boolean insertAnswer(@RequestBody Answer answer, @PathVariable int qid) {
		try {

			permissionDAO.isIdPresent(qid, "question");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return false;
		}

		return answerDAO.insertAnswer(answer, qid);
	}

	@GetMapping("/api/question/{qid}/answer/{aid}")
	public Answer getAnswerbyID(@PathVariable int aid) {

		try {
			permissionDAO.isIdPresent(aid, "answer");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}

		return answerDAO.getAnswerByAnswerID(aid);

	}

	@GetMapping("/api/question/{qid}/answer/order/{ord}")
	public List<Answer> getAllAnswersByQuestionID(@PathVariable int qid, @PathVariable String ord) {

		try {
			permissionDAO.isIdPresent(qid, "question");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}

		return answerDAO.getAllAnswersByQuestionID(qid, ord);

	}

	@PutMapping("/api/question/{qid}/answer/{aid}")
	public boolean updateAnswer(@RequestBody Answer answer, @PathVariable int aid) {

		try {
			permissionDAO.isIdPresent(aid, "answer");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return false;
		}

		return answerDAO.updateAnswerByAnswerID(answer, aid);

	}

	@DeleteMapping("/api/question/{qid}/answer/{aid}")
	public boolean deleteAnswerByAnswerId(@PathVariable int aid) {
		try {
			permissionDAO.isIdPresent(aid, "answer");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return false;
		}
		return answerDAO.deleteAnswer(aid);
	}
}