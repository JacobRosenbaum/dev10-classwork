# Assessment 8: Field Agent

### Goals
* [ ] View all Agents
* [ ] Add an Agent
* [ ] Update an Agent
* [ ] Delete an Agent
* [ ] Design easy to use and aesthetically pleasing UI
* [ ] Route using React Router

### UI Design
    * Landing page with cool background image and 'Jacob's Secret Agency' displayed prominently in middle
    * Either a nav bar on top with a button to navigate to the agents page or just a button displayed in the center of the screen
    * The agent screen will pre-populate with all the agents in a table upon navigation
        * Each table row will have a delete and edit button
    * The agents page will display a form with several input boxes near the top center where the user can add an agent
    * The edit button will either transform the original input box into update functionality or it will populate a modal where the user can update the agent info

### File Structure
    * App (file)
        * Contains routing for initially just the landing page and the agents page
    * Components (folder)
        * Initially contains one component for the landing page and one component for the agent page
            * Hopefully, after I get the CRUD working, I'll be able to seperate out the agent component into multiple components that only handle one function
        * Each file will get an accompanying css file

### Task List
    * Estimated Time of Completion (ETC): 7 hours 45 minutes
    * Actual Time of Completiong (ETC): 

* [ ] Create React App in Assessment Folder
    * ETC: 5 minutes
    * ATC: 3 minutes

* [ ] Delete all unnecessary files / code
    * ETC: 10 minutes
    * ATC: 

* [ ] Update package.json to include React Router
    * ETC: 5 minutes
    * ATC: 

* [ ] Create home page component and add styling
    * ETC: 15 minutes
    * ATC: 

* [ ] Update App file to route to home page upon navigation to site
    * ETC: 10 minutes
    * ATC: 

* [ ] Write all the missing HTTP requests for the agent api
    * ETC: 15 minutes
    * ATC: 

* [ ] Test that all the endpoints are accurate
    * ETC: 10 minutes
    * ATC: 

* [ ] Create Agent component with the following functions
    * [ ] useEffect to fetch all agents right away
            * ETC: 25 minutes
            * ATC: 
    * [ ] handleChange for each form field to update state when the user types into the form
            * ETC: 10 minutes
            * ATC:     
    * [ ] handleAddSubmit that posts to the api 
            * ETC: 45 minutes
            * ATC: 
    * [ ] handleUpdateModal to populate to handle the modal
            * ETC: 45 minutes
            * ATC: 
    * [ ] handleUpdateSubmit to post the update to the api
            * ETC: 1 hour
            * ATC: 
    * [ ] handleDelete to delete an agent from the api
            * ETC: 30 minutes
            * ATC: 
    * [ ] handleErrors to show user an error
            * ETC: 1 hour
            * ATC: 

* [ ] Agent component should return the following
    * [ ] form the the user can enter agent data into
            * ETC: 1 hour
            * ATC: 
    * [ ] table that shows the current agent data
            * ETC: 1 hour
            * ATC: 


