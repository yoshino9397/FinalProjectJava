package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Controller
public class SampleController {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  private SampleService sampleService;

  @ModelAttribute
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model) {
    LoginForm loginForm = new LoginForm();
    loginForm.setId("");
    loginForm.setName("");
    model.addAttribute("loginForm", loginForm);
    model.addAttribute("message", "Please enter the administrator information");

    return "login";
  }

  @ModelAttribute
  @RequestMapping(value = "/login_result", method = RequestMethod.POST)
  public String login_result(LoginForm loginForm, Model model) {
    List<Map<String, Object>> list;
    list = jdbcTemplate.queryForList("select * from user");

    for (int i = 1; i <= list.size(); i++) {
      // System.out.println(list.get(0).values().toString());
      // System.out.println("[" + loginForm.getId() + ", " + loginForm.getName() +
      // "]");
      // System.out.println(("[" + loginForm.getId() + ", " + loginForm.getName() +
      // "]")
      // .compareTo((list.get(i - 1).values().toString())));

      if (("[" + loginForm.getId() + ", " + loginForm.getName() + "]")
          .compareTo((list.get(i - 1).values().toString())) == 0) {
        model.addAttribute("message", "Login was successful");
        // System.out.println("dcdcdcdcdcd");
      }

      else if (("[" + loginForm.getId() + ", " + loginForm.getName() + "]")
          .compareTo((list.get(i - 1).values().toString())) == 1) {
        model.addAttribute("message", "Login was jfghfhgfhg");
        System.out.println("jfhgfhjgfhg");
      }

      // else {
      // model.addAttribute("message", "Failed to login");
      // }
    }

    return "login_result";
  }

  @ModelAttribute
  @RequestMapping(value = "/admin_menu", method = RequestMethod.GET)
  public String admin_menu(Model model) {
    model.addAttribute("message", "Please select a menu.");
    return "admin_menu";
  }

  @ModelAttribute
  @RequestMapping(value = "/show_product", method = RequestMethod.GET)
  public String show_product(Model model) {
    List<String> products = null;
    products = sampleService.selectAll();
    model.addAttribute("products", products);
    model.addAttribute("message", "Displayed the product.");

    return "show_product";
  }

  @ModelAttribute
  @RequestMapping(value = "/register_product", method = RequestMethod.GET)
  public String register_product(Model model) {
    ProductForm productForm = new ProductForm();
    model.addAttribute("productForm", productForm);
    model.addAttribute("message", "Please register the product.");

    return "register_product";
  }

  @ModelAttribute
  @RequestMapping(value = "/update_product", method = RequestMethod.GET)
  public String update_product(Model model) {
    ProductForm productForm = new ProductForm();
    model.addAttribute("productForm", productForm);
    model.addAttribute("message", "Please update the product.");

    return "update_product";
  }

  @ModelAttribute
  @RequestMapping(value = "/delete_product", method = RequestMethod.GET)
  public String delete_product(Model model) {
    ProductForm productForm = new ProductForm();
    model.addAttribute("productForm", productForm);
    model.addAttribute("message", "Please delete the product");

    return "delete_product";
  }

  @ModelAttribute
  @RequestMapping(value = "/after_delete_product", method = RequestMethod.POST)
  public String afeter_delete_product(ProductForm productForm, Model model) {
    sampleService.delete(productForm);
    model.addAttribute("message", "Deleting is complete.");
    return "after_delete_product";
  }

  @ModelAttribute
  @RequestMapping(value = "/show_result", method = RequestMethod.POST)
  public String show_result(ProductForm productForm, Model model) {
    sampleService.insert(productForm);
    model.addAttribute("message", "Processing is complete.");

    return "show_result";
  }
}