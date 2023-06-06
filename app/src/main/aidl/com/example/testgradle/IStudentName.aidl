// IStudentName.aidl
package com.example.testgradle;
import com.example.testgradle.Student;

// Declare any non-default types here with import statements

interface IStudentName {

    void setName(in Student s0, out Student s1, inout Student s2);

}