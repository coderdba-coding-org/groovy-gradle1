package com.sample.app.model

import io.netty.util.internal.EmptyPriorityQueue

class Employee {
    String FirstName
    String LastName

    Employee(String fname,String lname)
    {
        this.FirstName=fname
        this.LastName=lname
    }
}