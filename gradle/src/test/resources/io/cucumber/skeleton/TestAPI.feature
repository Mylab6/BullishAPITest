Feature: TestAPI
    #All students are deleted in the before step for each scenario

  Scenario: Add 3 students,validate the number of students after each add ,then delete 1 of them
    Given 0 students exist
    When Add a student named "John" "Smith" with id 1, nationality "US", classroom "1A"
    Then 1 students exist
    When Add a student named "Jane" "Doe" with id 2, nationality "CA", classroom "1B"
    Then 2 students exist
    When Add a student named "Amy" "Matthews" with id 3, nationality "US", classroom "1A"
    Then 3 students exist
    When I delete a student with the id 1
    Then 2 students exist
  Scenario: Add 2 students, and verify the first one's , id, nationality , first, last names , and classroom.
    Given 0 students exist
    When Add a student named "John" "Smith" with id 1, nationality "US", classroom "1A"
    When Add a student named "Jane" "Doe" with id 2, nationality "CA", classroom "1B"
    Then Verify a student with id 1 is named "John" "Smith", has nationality "US" and classroom "1A"
  Scenario: Add 3 students from classroom 1A, expect 3 to exist from 1A, and 1 to exist from 1B
    Given 0 students exist
    When Add a student named "John" "Smith" with id 1, nationality "US", classroom "1A"
    When Add a student named "Jane" "Doe" with id 2, nationality "CA", classroom "1B"
    When Add a student named "Amy" "Matthews" with id 3, nationality "US", classroom "1A"
    When Add a student named "Amy" "Matthews" with id 4, nationality "US", classroom "1A"
    Then 3 students exist from classroom "1A"
    Then 1 students exist from classroom "1B"

  Scenario: Add 2 students with the same id, verify the status code is 500 for the second add
    Given 0 students exist
    When Add a student named "John" "Smith" with id 1, nationality "US", classroom "1A"
    When Add a student named "Jane" "Doe" with id 1, nationality "CA", classroom "1B", expect status code 500
  Scenario: A student moves to France, and changes their name's spelling and classroom
    # Keep the ID , but change names and classroom, PUT test
    Given 0 students exist
    When Add a student named "John" "Smith" with id 1, nationality "US", classroom "1A"
    Then A student with id 1, changes their name to "Jances" "Qunce", nationality "FR", classroom "2C", expect status code 200
    Then Verify a student with id 1 is named "Jances" "Qunce", has nationality "FR" and classroom "2C"
  Scenario: Try to update a student that doesn't exist, expect a 500 status code
    Given 0 students exist
    Then A student with id 1, changes their name to "Jances" "Qunce", nationality "FR", classroom "2C", expect status code 500
  @broken_api
  #Skip this scenario using a custom before hook
  # This should return a 404 according to the Swagger docs
  Scenario: Fetch a student that doesn't exist, expect a 404
    Given 0 students exist
    # This test case will always fail , according to the swagger docs this should return a 404.
    #But instead returns a 200
    Then Verify a student with id 400 exist, expect status code 404


