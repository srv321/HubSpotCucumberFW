Feature: HubSpot Create Contacts

Scenario Outline: HubSpot Create a new contact scenario
	Given user is already on Login Page 
	When title of login page is HubSpot Login 
	Then  user enters "<username>" and "<password>" 
	Then  user clicks on login button 
	Then user is on home page 
	Then user moves to contact page
	Then user clicks on create contact
	Then user enters contact details "<email>" and "<firstname>" and "<lastname>" and "<jobtitle>"
	Then close the browser 
	
	Examples: 
		| username         | password 	 |   email         | firstname   | lastname | jobtitle   |
		| srv321@gmail.com | usabI009@@# |   xyz@test.com  | tom		 | sage     | QA Manager |
		| srv321@gmail.com | usabI009@@# |   deb@test.com  | julie       | rogers   | QA Lead    |
		
	
		