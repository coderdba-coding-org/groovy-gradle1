package com.sample.app.services.internal

import com.sample.app.model.Employee
import com.sample.app.services.IEmployeeService

import ratpack.exec.Blocking
import ratpack.exec.Promise
import ratpack.exec.Operation
import ratpack.exec.Downstream
import ratpack.exec.Execution

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

    Promise<List<Employee>> asyncListThisHangs() {
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

    Promise<List<Employee>> asyncList() {
        Promise.async { Downstream downstream ->
            println("before"+Thread.currentThread().name)
            List<Employee> employees = new ArrayList<>()
            // ask for an execution to be scheduled on another compute thread
            Execution.fork().start({ forkedExec ->
                employees.add(new Employee("Adam","Jhon"))
                employees.add(new Employee("Yashu","JSDSD"))
                employees.add(new Employee("XYZ","ADA"))
                employees.add(new Employee("HXS","HJK"))
                println("promise method:"+Thread.currentThread().name)
                downstream.success(employees)
            })
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