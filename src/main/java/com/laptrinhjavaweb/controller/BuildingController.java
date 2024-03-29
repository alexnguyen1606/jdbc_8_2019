package com.laptrinhjavaweb.controller;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.contant.SystemContant;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@ManagedBean
@WebServlet(urlPatterns = {"/admin-building"})
public class BuildingController extends HttpServlet {

    @Inject
    private IBuildingService buildingService;
    @Inject
    private IUserService userService;

    protected void doPost (HttpServletRequest request, HttpServletResponse response)throws SecurityException, IOException {

    }
    protected void doPut (HttpServletRequest request, HttpServletResponse response){

    }
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        BuildingDTO buildingDTO = FormUtil.toModel(BuildingDTO.class,request);
        String url = "";

        buildingService.setType(buildingDTO);
        request.setAttribute("districts",buildingService.getDistricts());
        request.setAttribute("buildingTypes",buildingService.getBuildingTypes());
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
            //request.setAttribute("districts",districts);
            request.setAttribute("users",userService.findByStatusAndRole(SystemContant.USER_ENABLE,SystemContant.USER_ROLE));
            request.setAttribute("buildings",buildings);
            request.setAttribute("model",buildingDTO);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
        }
        else if (action!=null && action.equals("EDIT")){
            url = "/views/admin/building/edit.jsp";
            if (buildingDTO.getId()!=null){
                request.setAttribute("model",buildingService.findById(buildingDTO.getId()));
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
        }

    }

	
}
