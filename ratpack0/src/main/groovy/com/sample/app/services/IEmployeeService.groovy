package com.sample.app.services

import com.sample.app.model.Employee
import ratpack.exec.Promise
import ratpack.exec.Operation

import javax.inject.Inject

interface IEmployeeService {
    Promise<List<Employee>> AsyncBlocklist()

    Promise<List<Employee>> syncList()

    Promise<List<Employee>> asyncList()

    Promise<List<Employee>> valueList()

    Operation background1()
}