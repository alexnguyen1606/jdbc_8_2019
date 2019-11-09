package com.laptrinhjavaweb.controller;

import com.laptrinhjavaweb.DTO.BuildingDTO;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.service.impl.UserService;
import com.laptrinhjavaweb.utils.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-building"})
public class BuildingController extends HttpServlet {
    private IBuildingService buildingService = new BuildingService();
    private IUserService userService = new UserService();
    protected void doPost (HttpServletRequest request, HttpServletResponse response)throws SecurityException, IOException {

    }
    protected void doPut (HttpServletRequest request, HttpServletResponse response){

    }
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        BuildingDTO buildingDTO = FormUtil.toModel(BuildingDTO.class,request);
        String url = "";
        if (action!=null && action.equals("LIST")){
            url = "/views/admin/building/list.jsp";
            BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
                    .setName(buildingDTO.getName())
                    .setDistrict(buildingDTO.getDistrict())
                    .setBuildingArea(buildingDTO.getBuildingArea())
                    .setNumberOfBasement(buildingDTO.getNumberOfBasement())
                    .setBuildingTypes(buildingDTO.getBuildingTypes())
                    //.setType(buildingDTO.getType())
                    .setCostRentForm(buildingDTO.getCostRentFrom())
                    .setCostRentTo(buildingDTO.getCostRentTo())
                    .setRentAreaFrom(buildingDTO.getRentAreaFrom())
                    .setRentAreaTo(buildingDTO.getRentAreaTo())
                    .setStaffId(buildingDTO.getStaffId())
                    .build();
            Pageable pageable =new PageRequest(null,null);
            List<BuildingDTO> buildings = buildingService.findAll(builder,pageable);
            request.setAttribute("users",userService.findByStatusAndRole(1,2));
            request.setAttribute("buildings",buildings);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
        }
        else if (action!=null && action.equals("EDIT")){
            url = "/views/admin/building/edit.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
        }
    }

	
}
