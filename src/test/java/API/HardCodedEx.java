package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class HardCodedEx {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTcwNjA0MjAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5NzEwMzYyMCwidXNlcklkIjoiNTYyNyJ9.5kOK70N4qluCOg2AY9y9ErKYVuGOZNaRD_IFk9PquWw";
    static String employee_id;

    @Test
    public void a_createEmployee() {
        //preparing the request
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Abel\",\n" +
                        "  \"emp_lastname\": \"Abrams\",\n" +
                        "  \"emp_middle_name\": \"Van\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1990-03-20\",\n" +
                        "  \"emp_status\": \"employed\",\n" +
                        "  \"emp_job_title\": \"QA Manager\"\n" +
                        "}");
        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint();

        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        String tempEmpId = response.jsonPath().getString("Employee.employee_id");
        Assert.assertEquals(employee_id, tempEmpId);
    }
}