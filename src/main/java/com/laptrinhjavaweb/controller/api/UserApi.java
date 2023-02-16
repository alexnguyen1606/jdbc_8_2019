package com.laptrinhjavaweb.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.contant.SystemContant;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-user"})
public class UserApi extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    IUserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);

        objectMapper.writeValue(response.getOutputStream(), userDTO);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        BuildingDTO buildingDTO = FormUtil.toModel(BuildingDTO.class, request);

        List<UserDTO> users = null;
        if (buildingDTO.getType() != null && buildingDTO.getType().equals("SHOW_STAFF")) {
            users = userService.findByStatusAndRoleIdAndBuildingId
                    (SystemContant.USER_ENABLE, SystemContant.USER_ROLE, buildingDTO.getId());
        }
        if (buildingDTO.getType() != null && buildingDTO.getType().equals("SHOW_USER")) {
            users = userService.findByStatusAndRoleAndCustomerId
                    (SystemContant.USER_ENABLE, SystemContant.USER_ROLE, buildingDTO.getId());
        }
        objectMapper.writeValue(response.getOutputStream(), users);

    }
}

