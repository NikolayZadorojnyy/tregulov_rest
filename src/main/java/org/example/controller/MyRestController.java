package org.example.controller;

import org.example.entity.Employee;
import org.example.exception_handling.EmployeeIncorrectData;
import org.example.exception_handling.NoSuchEmployeeException;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // управляет REST запросами и ответами
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) { // @PathVariable исп для того чтобы читать переменную из юрл
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {  // если в url введен id которого нет в базе
            throw new RuntimeException("There is no employee with ID= " + id + " in Database");
        }
        return employee;
   }

   @PostMapping("/employees") //анотация связывает http запрос, исп-й http метод post с методом контроллера
   public Employee addNewEmployee(@RequestBody Employee employee) { // антотация связывает тело http метода с параметром метода контроллера
        employeeService.saveEmployee(employee);
        return employee;           //метод нельзя использвать в браузере, только в postman
   }

   @PutMapping("/employees") // обновляем работника
      //PutMapping антотация связывает http запрос, использующий http метод put с методом контроллера
   public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;  //метод нельзя использвать в браузере, только в postman
   }

   @DeleteMapping("/employees/{id}") //DeleteMapping связывает http запрос, исп-й http етод delete c методом контроллера
   public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in Database");
        }

        employeeService.deleteEmployee(id);
        return "Employee with ID = " + id + " was deleted"; //метод нельзя использвать в браузере, только в postman

   }



}
