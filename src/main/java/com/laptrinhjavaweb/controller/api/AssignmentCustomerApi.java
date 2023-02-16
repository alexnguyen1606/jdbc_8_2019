package com.laptrinhjavaweb.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import com.laptrinhjavaweb.service.impl.AssignmentCustomerService;
import com.laptrinhjavaweb.utils.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api-assignmentcustomer")
public class AssignmentCustomerApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        AssignmentCustomerDTO assignmentCustomerDTO = HttpUtil.of(request.getReader()).toModel(AssignmentCustomerDTO.class);
        IAssignmentCustomerService assignmentCustomerService = new AssignmentCustomerService();
        assignmentCustomerService.assignCustomer(assignmentCustomerDTO.getCustomerId(),assignmentCustomerDTO.getStaffs());

    }
}
