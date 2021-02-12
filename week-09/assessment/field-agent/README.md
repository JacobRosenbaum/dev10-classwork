# Assessment 9: Thymeleaf Field Agent

### Goals
* [ ] Multi-page, server-side rendered site
* [ ] View all Agents
* [ ] Add an Agent
* [ ] Update an Agent
* [ ] Delete an Agent
* [ ] Design easy to use and aesthetically pleasing UI

### UI Design
    * Landing page with cool background image and 'Jacob's World Famous Secret Agency' displayed prominently in middle
    * Either a nav bar on top with a button to navigate to the agents page or just a button displayed in the center of the screen
    * The agent screen will pre-populate with all the agents in a table upon navigation
        * Each table row will have a delete and edit button
    * The agents page will display a form with several input boxes near the top center where the user can add an agent
    * The edit button will either transform the original input box into update functionality or it will populate a modal where the user can update the agent info

### Task List
    * Estimated Time of Completion (ETC): 7 hours 56 minutes
    * Actual Time of Completiong (ATC): 
* [ ] Read through code from assessment 7
    * ETC: 15 minutes
    * ATC: 
* [ ] Read through HTML from assessment 8 in the browser to see what can be re-used
    * ETC: 15 minutes
    * ATC:
* [ ] Update pom file to include Thymeleaf
    * ETC: 5 minutes
    * ATC:
* [ ] Move the following manual validations to annotations using the Validation API
    * [ ] First name cannot be null or empty
    * [ ] Last name cannot be null or empty

        * ETC: 15 minutes
        * ATC:
    
* [ ] Move existing controllers into new package called api

    * ETC: 10 minutes
    * ATC:
* [ ] Create new package called ui in the controllers parent package    
    * ETC: 2 minutes
    * ATC:
* [ ] Create HomeController for landing page with following methods
    * [ ] getHome 
        * GetMapping("/")
            * ETC: 10 minutes
            * ATC:
* [ ] Create AgentController with RequestMapping("/agents") and the following methods
    * [ ] index
        * GetMapping
    * [ ] create
        * GetMapping("/create")
    * [ ] create
        * PostMapping("/create)
        * Redirect back to view all agents page
    * [ ] update
      * GetMapping("/update/{id}")
    * [ ] update
      * PostMapping("/update/*)
      * Redirect back to view all agents page
    * [ ] delete
      * GetMapping("/delete/{id}")
    * [ ] delete
      * PostMapping("/delete/{id})
      * Redirect back to view all agents page
            * ETC: 1.5 hours
            * ATC:
    
* [ ] Create templates folder
    * ETC: 2 minutes
    * ATC:
* [ ] Create index.html at root of folder for homepage
    * ETC: 15 minutes
    * ATC:
* [ ] Create agents sub folder
    * ETC: 2 minutes
    * ATC:
* [ ] Create index.html inside of sub folder displaying the view all agents
    * [ ] Take the HTML from the browser and paste into template
    * [ ] Clean up code
    * [ ] Add Thymeleaf to populate the agent table
        * ETC: 45 minutes
        * ATC:
    
* [ ] Create form.html file that will either be to create an agent or to update an agent using conditional Thymeleaf rendering
    * [ ] Take the HTML from the browser and paste into template
    * [ ] Clean up code
    * [ ] Add Thymeleaf to allow user to interact with and post the form
        * ETC: 1.5 hours
        * ATC:
        

* [ ] Create delete.html file as a delete confirmation page
    * [ ] Take the HTML from the browser and paste into template
    * [ ] Clean up code
    * [ ] Add Thymeleaf to populate agent information and allow user to delete or cancel deletion agents
        * ETC: 25 minutes
        * ATC:
    
* [ ] Make sure Bootstrap CDN has been added to all templates and make sure correct bootstrap formatting in each template
  * ETC: 30 minutes
  * ATC:
* [ ] Paste custom CSS from previous assessment into css file and clean up code to match each template
  * ETC: 45 minutes
  * ATC:
* [ ] Test UI and design in browser until satisfied with result
  * ETC: 1 hour
  * ATC:
    
    




    
