package com.sapient.healthyreps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sapient.healthyreps.dao.AnswerDAO;
import com.sapient.healthyreps.dao.PermissionDAO;
import com.sapient.healthyreps.entity.Answer;

class AnswerDAOTest {

	AnswerDAO answerDAO;
	PermissionDAO permissionDAO;

	@BeforeEach
	void initialize() {
		this.answerDAO = new AnswerDAO();
		this.permissionDAO = new PermissionDAO();
	}

	@Test
	void CRUDTestPass() {
		// given
		Answer answer = new Answer(1, "Test Answer", 4, "2020-01-01 10:10:10", 2, 1, 10);

		// Create Test

		boolean inserted = answerDAO.insertAnswer(answer, 2);

		assertTrue(inserted);

		// Read Test

		int id = permissionDAO.getLastID("answer");

		answer = answerDAO.getAnswerByAnswerID(id);

		assertEquals(answer.getDescription(), "Test Answer");

		// Update test

		answer.setDescription("Updated Test answer");

		assertTrue(answerDAO.updateAnswerByAnswerID(answer, 1));

		// Delete test

		assertTrue(answerDAO.deleteAnswer(id));

	}

}
