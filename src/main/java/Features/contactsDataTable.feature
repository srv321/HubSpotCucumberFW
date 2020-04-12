Feature: HubSpot Create Contacts with Data Table 

Scenario: HubSpot Create a new contact scenario using Data Table
	Given user already on Login Page 
	When title login page is HubSpot Login 
	Then  user fills username and password
	| srv321@gmail.com | usabI009@@# |
	 
	Then  user does click login button 
	Then user is located on home page 
	Then user navigates to contact page
	Then user does click on create contact
	Then user fills contact details
	| bableu@test.com | adam | gilly | QA Tester |
	Then closes the browser 