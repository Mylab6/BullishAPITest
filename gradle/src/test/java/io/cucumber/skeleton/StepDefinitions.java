package io.cucumber.skeleton;

import dev.failsafe.internal.util.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
//import org.junit.jupiter.*;//Assume;
import org.junit.jupiter.api.Assumptions;

// https://github.com/rest-assured/rest-assured/wiki/Usage#static-imports
public class StepDefinitions {
    private Scenario scenario;

    //
    public  String baseUri = "http://localhost:9080";
    public  String basePath = "studentmgmt" ;
    @Before
    public void beforeScenario(Scenario scenario){
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath ;
        this.scenario = scenario;
        // cleanup before each test
        iFetchAndDeleteAllStudents();
    }
    @Before("@broken_api")
    public void skip_scenario(Scenario scenario){
        System.out.println("SKIP SCENARIO: " + scenario.getName());
        Assumptions.assumeTrue(false);
        //Assert.isTrue(true);
    }


    @Then("{int} students exist")
    public void IntStudentsExist(int numberOfStudents) {
      Student[] students =   Student.getAllStudents();
      var i = students.length;
        assertEquals(numberOfStudents, i );
      //assertEquals

    }


    @Given("I fetch and delete all students")
    public void iFetchAndDeleteAllStudents() {

      //  Person[] persons = given().when().get("person/").as(Person[].class);

      Student[] students =   Student.getAllStudents();
        for (var student: students
             ) {
            Student.deleteStudentByID(student.id);
        }
    }

    @When("Add a student named {string} {string} with id {int}, nationality {string}, classroom {string}")
    public void addAStudentNamedWithIdNationalityClassroom(String first, String last, int id, String nationality, String classroom) {
        Response result = Student.createStudent(first, last,nationality,classroom,id,200);
    }

    @When("I delete a student with the id {int}")
    public void iDeleteAStudentWithTheId(int id) {
        Student.deleteStudentByID(id);

    }

    @Then("Verify a student with id {int} is named {string} {string}, has nationality {string} and classroom {string}")
    public void verifyAStudentWithIdIsNamedHasNationalityAndClassroom(int id, String firstName, String lastName, String nationality, String classroom) {

    Student currentStudent = Student.getStudentById(id)[0];
    validateStudentFields(id, firstName, lastName, nationality, classroom, currentStudent);


    }

    private static void validateStudentFields(int id, String firstName, String lastName, String nationality, String classroom, Student currentStudent) {
        assertEquals( id, currentStudent.id) ;
        assertEquals( firstName, currentStudent.firstName) ;
        assertEquals( lastName, currentStudent.lastName) ;
        assertEquals(nationality, currentStudent.nationality) ;
        assertEquals(classroom, currentStudent.studentClass) ;
    }


    @Then("{int} students exist from classroom {string}")
    public void studentsExistFromClassroom(int studentCount, String classroomName) {
        Student[] currentStudents = Student.getStudentsByClassroom(classroomName);
        assertEquals(studentCount, currentStudents.length);

        for (Student student: currentStudents
             ) {
            assertEquals(classroomName,student.studentClass);
        }

    }

    @When("Add a student named {string} {string} with id {int}, nationality {string}, classroom {string}, expect status code {int}")
    public void addAStudentNamedWithIdNationalityClassroomExpectStatusCode(String first, String last, int id, String nationality, String classroom, int statusCode) {

        Response result = Student.createStudent(first, last,nationality,classroom,id,statusCode);

    }

    @Then("A student with id {int}, changes their name to {string} {string}, nationality {string}, classroom {string}, expect status code {int}")
    public void aStudentWithIdChangesTheirNameToNationalityClassroom(int id, String first, String last, String nationality, String classroom, int statusCode) {
    Student.updateStudent(first,last,nationality,classroom,id,statusCode);

    }

    @Then("Verify a student with id {int} exist, expect status code {int}")
    public void verifyAStudentWithIdExistExpectStatusCode(int id, int statusCode) {
        Student currentStudent = Student.getStudentById(id, statusCode)[0];

    }
}
