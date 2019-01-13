package com.sagarandcompany.transactiondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/")
    public ModelAndView getEmpPage(@RequestParam(value = "id", required = false) Long id) {

        ModelAndView modelAndView = new ModelAndView("index");
        if (Objects.nonNull(id)) {
            modelAndView.addObject("employee", employeeService.get(id).getData());
        } else
            modelAndView.addObject("employee", new Employee());

        return modelAndView;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseDTO get(@PathVariable Long id) {
        return employeeService.get(id);
    }

    @GetMapping("/findByName/{name}")
    public Employee get(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("/findBySalary")
    public List<Employee> findBySalary(@RequestParam Integer min, @RequestParam Integer max) {
        return employeeService.findBySalary(min, max);
    }

    @GetMapping("/get")
    public ModelAndView getALl() {
        ModelAndView modelAndView = new ModelAndView("empList");
//        return employeeService.getAll();
        modelAndView.addObject("employees", employeeService.getAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/emp/get";
    }
//    @GetMapping("/get")
//    public Employee get(@RequestParam("salary") Integer salary, @RequestParam("name") String name, @RequestParam(value = "id", required = false) Long id) {
//        return new Employee(salary, name, id);
//    }

//    @PostMapping(value = "/save", consumes = "application/json")
//    public Employee save(@RequestBody Employee employee) {
//        return employee;
//    }

    @PostMapping(value = "/save")
    public ModelAndView save(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @PutMapping(value = "/update", produces = "application/json")
    public Employee update(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }
}
