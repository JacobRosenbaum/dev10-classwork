import "../../assets/css/Agents.css";
import { useState, useEffect } from 'react';

function Agents() {
    const [agents, setAgents] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/agent')
          .then(response => response.json()) // assume that it's a 200
          .then(data => setAgents(data))
          .then(console.log('fetched data'))
          .catch(error => console.log(error));
      }, []);

    return (
        <div className='container'>
            <div className="jumbotron">
                <h1 id='title' className="display-4">Agents</h1>
                <p className="lead"></p>
            </div>
            <table id='table' className="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">First</th>
                        <th scope="col">Middle</th>
                        <th scope="col">Last</th>
                        <th scope="col">DOB</th>
                        <th scope="col">Height</th>
                        <th scope="col">
                        <button id='addButton' className="btn btn-primary btn-sm">
                                {/* onClick={() => handleEdit(agent.id)}>Edit */}
                                Add Agent
                                </button>
                        </th>
                    </tr>
                </thead>
                <tbody>
                {agents.map(agent => (
                    <tr key={agent.agentId}>
                    <td>{agent.firstName}</td>
                    <td>{agent.middleName}</td>
                    <td>{agent.lastName}</td>
                    <td>{agent.dob}</td>
                    <td>{agent.heightInInches} inches</td>
                    <td>
                        <div className="float-right">
                            <button id='editButton' className="btn btn-primary btn-sm">
                                {/* onClick={() => handleEdit(agent.id)}>Edit */}
                                Edit
                                </button>
                            <button id='deleteButton' className="btn btn-danger btn-sm ml-3">
                                Delete
                                {/* // onClick={() => handleDelete(agent.id)}>Delete */}
                                </button>
                        </div>
                    </td>              
                    </tr>
                ))}

                </tbody>
            </table>
            {/* <form>
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" />
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" />
                </div>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1" />
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form> */}
        </div>
    )
}

export default Agents;