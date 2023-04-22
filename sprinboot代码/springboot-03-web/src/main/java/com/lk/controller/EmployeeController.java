package com.lk.controller;

import com.lk.dao.DepartmentDao;
import com.lk.dao.EmployeeDao;
import com.lk.pojo.Department;
import com.lk.pojo.Employee;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "/emp/list";

    }
    @GetMapping("/emp")
    public String toAddpage(Model model){
        //查出所有部门信息
        Collection<Department> depatments = departmentDao.getDepatments();
        model.addAttribute("departments",depatments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("save =>" + employee);

        //添加的操作 forward
        employeeDao.save(employee); //调用底层业务方法保存员工信息
        return "redirect:/emps";
    }

    //去员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model){
        //查出原来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);
        //查出所有部门的信息
        Collection<Department> depatments = departmentDao.getDepatments();
        model.addAttribute("departments",depatments);
        return "emp/update";

    }
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        System.out.println("update==>"+ employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") int id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    //注销
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
