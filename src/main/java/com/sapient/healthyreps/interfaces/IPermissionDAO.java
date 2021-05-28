package com.sapient.healthyreps.interfaces;

import com.sapient.healthyreps.exception.InvalidId;

public interface IPermissionDAO {
	public boolean isIdPresent(int id, String tableName) throws InvalidId;

	public int getLastID(String tableName);
}
