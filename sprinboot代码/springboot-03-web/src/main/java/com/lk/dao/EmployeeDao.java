package com.lk.dao;

import com.lk.pojo.Department;
import com.lk.pojo.Employee;
import com.lk.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//员工dao
@Repository
public class EmployeeDao {


    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    // 员工有所属部门
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<Integer, Employee>();  //创建一个部门表

        employees.put(1,new Employee(1,"lk",null,0,new Department(105,"后勤部"),null));
        employees.put(2,new Employee(2,"hxf",null,0,new Department(104,"运营部"),null));
        employees.put(3,new Employee(3,"zxm",null,0,new Department(103,"教研部"),null));
        employees.put(4,new Employee(4,"myn",null,0,new Department(102,"市场部"),null));
        employees.put(5,new Employee(5,"gpx",null,0,new Department(101,"教学部"),null));
    }


    //数据库的操作
    //主键自增
    private static Integer initId = 6;

    //增加员工
    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }

    //查看全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //删除员工通过id
    public void delete(Integer id){
        employees.remove(id);
    }
}

