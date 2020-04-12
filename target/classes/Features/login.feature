Feature: HubSpot Login Feature 

#without Examples Keyword
#Scenario: HubSpot Login Test Scenario	 
#	Given user is already on Login Page 
#	When title of login page is HubSpot Login 
#	Then  user enters "srv321@gmail.com" and "usabI009@@#" 
#	Then  user clicks on login button 
#	Then user is on home page  

#with Examples Keyword
Scenario Outline: HubSpot Login Test Scenario
	Given user is already on Login Page 
	When title of login page is HubSpot Login 
	Then  user enters "<username>" and "<password>" 
	Then  user clicks on login button 
	Then user is on home page 
	Then close the browser 
	
	Examples: 
		| username         | password |
		| srv321@gmail.com | usabI009@@# |
		| tom              | test456 |
		
