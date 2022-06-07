Feature: be able to login successfully to the Modulr Customer Portal


 Scenario: be able to login securely to our customer portal
 
 Given the Customer launches the required browser
    And the Customer opens URL "https://secure-sandbox.modulrfinance.com/"
    When Customer enters the Username as vijay.inamdar11 and Password as Training$123
    Then Click on Signin Button
    And Successful login should take the user to the account overview page
    Then assert the correct Usename and Signin button is disabled/signout button is Enabled
    Then reset the Password through view profile
    And Enter current Password and New Password and Re enter New Password and Click Save
    Then Click on Signout from profile
    When Click on Forgotten Password from Login page view
    Then Enter Username on Reset Access Page
    And Click on Reset acess
    Then assert the Email sent notification
    And Close the browser