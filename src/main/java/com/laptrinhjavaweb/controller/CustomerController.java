package com.laptrinhjavaweb.controller;

import com.laptrinhjavaweb.DTO.CustomerDTO;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.CustomerService;
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

@WebServlet(urlPatterns = {"/admin-customer"})
public class CustomerController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "";
        CustomerDTO customerDTO = FormUtil.toModel(CustomerDTO.class, request);
        ICustomerService customerService = new CustomerService();
        Pageable pageable = new PageRequest(1, 10);
        if (action.equals("LIST") && action != null) {

            CustomerSearchBuilder customerSearchBuilder = new CustomerSearchBuilder.Builder()
                    .setEmail(customerDTO.getEmail())
                    .setFullName(customerDTO.getFullName())
                    .setPhoneNumber(customerDTO.getPhoneNumber())
                    .setUserId(customerDTO.getUserId())
                    .build();
            List<CustomerDTO> customers = customerService.findAll(customerSearchBuilder, pageable);
            IUserService userService = new UserService();
            request.setAttribute("customers", customers);
            request.setAttribute("users", userService.findByStatusAndRole(1, 2));
            request.setAttribute("model", customerDTO);
            url = "/views/admin/customer/list.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
        } else if (action.equals("EDIT") && action != null) {
            if (customerDTO.getId() != null) {
                request.setAttribute("model", customerService.findById(customerDTO.getId()));
            }
            url = "/views/admin/customer/edit.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
        }

    }
}
