package com.laptrinhjavaweb.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.AssignmentStaffDTO;
import com.laptrinhjavaweb.service.IAssignmentStaffService;
import com.laptrinhjavaweb.service.impl.AssignmentStaffService;
import com.laptrinhjavaweb.utils.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-assignmentstaff"})
public class AssignmentStaffApi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        AssignmentStaffDTO assignmentStaffDTO =  HttpUtil.of(request.getReader()).toModel(AssignmentStaffDTO.class);

        IAssignmentStaffService assignmentStaffService = new AssignmentStaffService();
        assignmentStaffService.assignBuilding(assignmentStaffDTO.getBuildingId(),assignmentStaffDTO.getStaffs());
    }
}
