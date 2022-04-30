Feature: Coupon functionalities like Add, Edit and delete coupon
  User can add, edit & delete the coupon from Marketing menu

  Background: Login to Portal
    Given User is navigated to Login Page
    And User logs in with "admin" & "Admin@123"
    Then User should be on dashboard page
    Given User is navigated to Coupons page

  Scenario: User adds new coupon,edits & deletes coupon
    Given User adds New coupon
    Then Coupon should be visible on Coupons List page
   	Given User edits the coupon
  	Given User deletes the coupon
    Then Coupon should be deleted from Coupons List
    Given user clicks Logout
    Then User should be logged out
