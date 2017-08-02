# amazon1
#Feature to automate amazon website
Feature: Shop online at Amazon.co.uk 

@test
Scenario: Sign into Amazon.co.uk
Given I have opened 'amazon.co.uk' on my browser
When I click Sign-in
  And Enter valid 'user name' and 'password' and click signin
Then I am logged in
     
@test
Scenario: Search for product and verify the first result is related to your product
Given 'amazon.co.uk' is open and I am logged in with 'user name' and 'password'
When I search for "####(pick a product of your choice)" 
Then The first result has the word  "####" in it 
    
@test 
Scenario: verify that the original price of the product matches the price seen in the basket
Given 'amazon.co.uk' is open and I am logged in with 'user name' and 'password'
  And I add "####(pick a product of your choice)" to my basket
When I check my basket total
Then It should match the price of "####(pick a product of your choice)"
