package io.cucumber.skeleton;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

// We keep all of our API calls in this class.
// Using Rest Assured
// https://github.com/rest-assured/rest-assured/wiki/Usage#static-imports
public class Student {


    public int id;

    public String firstName;
    public String lastName;
    public String nationality;
    public String studentClass;


    public static Student[] getAllStudents() {
        Response result = RestAssured.get("/fetchStudents");
        return result.body().as(Student[].class);

    }

    public static Student[] getStudentById(int id) {
        Response result = RestAssured.given().queryParam("id", String.valueOf(id)).get("/fetchStudents");
        return result.body().as(Student[].class);
    }

    public static Student[] getStudentById(int id, int expectedStatusCode) {
        Response result = RestAssured.given().queryParam("id", String.valueOf(id)).get("/fetchStudents");
        result.then().statusCode(expectedStatusCode);
        return result.body().as(Student[].class);
    }

    public static Student[] getStudentsByClassroom(String studentClass) {
        Response result = RestAssured.given().queryParam("studentClass", studentClass).get("/fetchStudents");
        return result.body().as(Student[].class);
    }

    public static void deleteStudentByID(int id) {
        Map<String, String> deleteMap = new HashMap<>();
        deleteMap.put("id", String.valueOf(id));

        Response result = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(deleteMap).delete("/deleteStudent");
    }

    public static Response createStudent(String first, String last, String nationality, String studentClass, int id, int expectedStatusCode) {

        Map<String, String> jsonPost = createStudentHashMap(first, last, nationality, studentClass, id);
        Response result = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonPost)
                .post("/addStudent");
        result.then().statusCode(expectedStatusCode);
        return result;
    }

    public static Map<String, String> createStudentHashMap(String first, String last, String nationality, String studentClass, int id) {
        Map<String, String> jsonPost = new HashMap<>();
        jsonPost.put("firstName", first);
        jsonPost.put("lastName", last);
        jsonPost.put("nationality", nationality);
        jsonPost.put("studentClass", studentClass);
        jsonPost.put("id", String.valueOf(id));
        return jsonPost;
    }

    public static void updateStudent(String first, String last, String nationality, String classroom, int id, int expectedStatusCode) {

        createStudentHashMap(first, last, nationality, classroom, id);
        Map<String, String> jsonPost = createStudentHashMap(first, last, nationality, classroom, id);
        Response result = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonPost)
                .put("/updateStudent");
        result.then().statusCode(expectedStatusCode);
        //return result;
    }


    @Override
    public String toString() {

        return firstName + " " + lastName + " " + id;

    }

}
