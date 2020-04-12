Feature: HubSpot Create Contacts with Data Table 

Scenario: HubSpot Create a new contact scenario using DT with Maps
	Given user alrdy on Login Page 
	When title login page HubSpot Login 
	Then  user fills username and passwrd
	| username         | password    |
	| srv321@gmail.com | usabI009@@# |
	 
	Then  user does click login buttn 
	Then user is locatd on home page 
	Then user fills contct details
	| email           |firstname | lastname | jobtitle  |
	| wableu@test.com | adam     | gilly    | QA Tester |
	| qableu@test.com | edam     | hilly    | QA Mgr    |
	| jableu@test.com | david    | billy    | QA Lead   |
	
	Then closed the browser 