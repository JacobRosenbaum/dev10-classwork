# Assessment 8: Field Agent

### Goals
* [x] View all Agents
* [x] Add an Agent
* [x] Update an Agent
* [x] Delete an Agent
* [x] Design easy to use and aesthetically pleasing UI
* [x] Route using React Router

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
    * Estimated Time of Completion (ETC): 8 hours 15 minutes
    * Actual Time of Completiong (ETC): 8 hours 50 minutes

* [x] Create React App in Assessment Folder
    * ETC: 5 minutes
    * ATC: 3 minutes

* [x] Delete all unnecessary files / code
    * ETC: 10 minutes
    * ATC: 5 minutes

* [x] Update package.json to include React Router
    * ETC: 5 minutes
    * ATC: 1 minute

* [x] Create home page component and add styling
    * ETC: 15 minutes
    * ATC: 20 minutes

* [x] Update App file to route to home page upon navigation to site
    * ETC: 10 minutes
    * ATC: 10 minutes

* [x] Write all the missing HTTP requests for the agent api
    * ETC: 15 minutes
    * ATC: 10 minutes

* [ ] Test that all the endpoints are accurate
    * ETC: 10 minutes
    * ATC: 5 minutes

* [x] Create Agent component with the following functions
    * [x] useEffect to fetch all agents right away
        * fetch http get request
        * recieve json state
        * set agent state to json data
            * ETC: 25 minutes
            * ATC: 20 minutes
    * [x] handleChange for each form field to update state when the user types into the form
        * ETC: 10 minutes
        * ATC: 5 minutes   
    * [x] handleAddSubmit that posts to the api 
        * create new agent object to send through api
        * fetch http post request with proper headers and body
        * condtionally verify that we revieved correct http status (if we didn't, update the error state and notify the user)
        * reset the form and state
            * ETC: 45 minutes
            * ATC: 45 minutes
    * [x] handleAddCancel
        * reset the form, state and errors allowing user to back out of add
            * ETC: 15 minutes
            * ATC: 10 minutes
    * [x] handleUpdateModal to open the modal
        * 'edit' button will open up a bootstrap modal
        * new form with fields pre-populated with agent data will be inside modal for user to update
        * update agent state
            * ETC: 45 minutes
            * ATC: 2 hours
    * [x] handleUpdateSubmit to post the update to the api
        * submit button will trigger update
        * create new agent object to send through api
        * fetch http put request with proper headers and body
        * condtionally verify that we revieved correct http status (if we didn't, update the error state and notify the user)
        * create copy of agents array
        * get the index of the agent in the array and update that data using the updated agent state
        * close the modal and reset the state
            * ETC: 1 hour
            * ATC: 1 hour
    * [x] handleUpdateCancel
        * reset the form, state and errors allowing user to back out of update
            * ETC: 15 minutes
            * ATC: 5 minutes
    * [x] handleDelete to delete an agent from the api
        * fetch http delete request with proper headers and body
        * condtionally verify that we revieved correct http status (if we didn't, update the error state and notify the user)
        * filter out agent using their id
        * set the state to the filtered array
            * ETC: 30 minutes
            * ATC: 20 minutes
    * [x] handleErrors to show users an error
        * map through the errors using the state array and use a bootstrap alert to notify the user
            * ETC: 1 hour
            * ATC: 45 minutes

* [x] Agent component should return the following
    * [ ] form the the user can enter agent data into
        * ETC: 1 hour
        * ATC: 1.5 hours
    * [x] table that shows the current agent data
        * ETC: 1 hour
        * ATC: 1 hour


