package com.sample.app.services.internal

import com.sample.app.model.Employee
import com.sample.app.services.IEmployeeService

import ratpack.exec.Blocking
import ratpack.exec.Promise
import ratpack.exec.Operation


import javax.inject.Inject

class DefaultEmployeeService implements IEmployeeService{

    @Override
    Promise<List<Employee>> AsyncBlocklist() {
        Blocking.get {
            List<Employee> employees = new ArrayList<>()
            employees.add(new Employee("Adam","Jhon"))
            employees.add(new Employee("Yashu","JSDSD"))
            employees.add(new Employee("XYZ","ADA"))
            employees.add(new Employee("HXS","HJK"))
            println(Thread.currentThread().name)
            return employees
        }
    }

    Promise<List<Employee>> syncList() {
        Promise.sync {
            List<Employee> employees = new ArrayList<>()
            employees.add(new Employee("Adam","Jhon"))
            employees.add(new Employee("Yashu","JSDSD"))
            employees.add(new Employee("XYZ","ADA"))
            employees.add(new Employee("HXS","HJK"))
            println(Thread.currentThread().name)
            return employees
        }
    }

    Promise<List<Employee>> asyncList() {
        Promise.async {
            List<Employee> employees = new ArrayList<>()
            employees.add(new Employee("Adam","Jhon"))
            employees.add(new Employee("Yashu","JSDSD"))
            employees.add(new Employee("XYZ","ADA"))
            employees.add(new Employee("HXS","HJK"))
            println(Thread.currentThread().name)
            return employees
        } as Promise<List<Employee>>
    }

    Promise<List<Employee>> valueList() {
        Blocking.get {
            List<Employee> employees = new ArrayList<>()
            employees.add(new Employee("Adam","Jhon"))
            employees.add(new Employee("Yashu","JSDSD"))
            employees.add(new Employee("XYZ","ADA"))
            employees.add(new Employee("HXS","HJK"))
            return employees
        }
    }
}