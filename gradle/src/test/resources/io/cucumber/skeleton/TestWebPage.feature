Feature: Test Web Page
    # Test Web Page

  Scenario: Successfully login
    Given  I open a browser
    Given I go to "http://the-internet.herokuapp.com/"
    When I click the link "Form Authentication"
    When I enter the username "tomsmith"
    When I enter the password "SuperSecretPassword!"
    When I click the login button
    Then I should see a success message

  Scenario: Fail to login due to not having a username or password
    Given  I open a browser
    Given I go to "http://the-internet.herokuapp.com/"
    When I click the link "Form Authentication"
    When I click the login button
    Then I should see an error message, "Your username is invalid!"

  Scenario: Fail to login due to not having an valid username
    Given  I open a browser
    Given I go to "http://the-internet.herokuapp.com/"
    When I click the link "Form Authentication"
    When I enter the username "smith"
    When I click the login button
    Then I should see an error message, "Your username is invalid!"

  Scenario: Fail to login due to not having a valid password
    Given  I open a browser
    Given I go to "http://the-internet.herokuapp.com/"
    When I click the link "Form Authentication"
    When I enter the username "tomsmith"
    When I enter the password "wrongPass!"

    When I click the login button
    Then I should see an error message, "Your password is invalid!"



  Scenario: Fail to login due to not having a password
    Given  I open a browser
    Given I go to "http://the-internet.herokuapp.com/"
    When I click the link "Form Authentication"
    When I enter the username "tomsmith"
    When I click the login button
    Then I should see an error message, "Your password is invalid!"

