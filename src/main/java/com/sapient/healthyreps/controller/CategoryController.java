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

import com.sapient.healthyreps.dao.CategoryDAO;
import com.sapient.healthyreps.dao.PermissionDAO;
import com.sapient.healthyreps.entity.Category;
import com.sapient.healthyreps.exception.InvalidId;

@RestController
public class CategoryController {

	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	PermissionDAO permissionDAO;

	@GetMapping("/api/category/{cid}")
	public String getCategoryFromID(@PathVariable int cid) {
		try {
			permissionDAO.isIdPresent(cid, "category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}
		return categoryDAO.getCategoryById(cid).getCategoryName();
	}

	@GetMapping("/api/category")
	public List<Category> getAllCategories() {
		return categoryDAO.getAllCategory();
	}

	@PostMapping("/api/category")
	public String insertCategory(@RequestBody Category Cat) {
		return categoryDAO.insertCategory(Cat) ? "Inserted" : "Not Inserted";
	}

	@DeleteMapping("/api/category/{cid}")
	public String deleteCategory(@PathVariable int cid) {
		try {
			permissionDAO.isIdPresent(cid, "category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}
		return categoryDAO.removeCategory(cid) ? "Removed" : "Not Removed";
	}

	@PutMapping("/api/category")
	public String updateCategory(@RequestBody Category category) {
		try {
			permissionDAO.isIdPresent(category.getCategoryID(), "category");
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return null;
		}

		return categoryDAO.updateCategory(category) ? "Updated" : "Not Updated";
	}
}