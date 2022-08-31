package by.htp.ex.controller;

import java.io.IOException;

import by.htp.ex.dao.DaoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {

	void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException;

}
