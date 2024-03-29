import "../../assets/css/Agents.css";
import { useState, useEffect } from 'react';
import Modal from 'react-modal';
import Errors from '../Errors';

function Agents() {
    const [agents, setAgents] = useState([]);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [aliasModalIsOpen, setAliasModalIsOpen] = useState(false);
    const [editAgentId, setEditAgentId] = useState(0);
    const [firstName, setFirstName] = useState('');
    const [middleName, setMiddleName] = useState('');
    const [lastName, setLastName] = useState('');
    const [dob, setDob] = useState('');
    const [heightInInches, setHeightInInches] = useState(0);
    const [errors, setErrors] = useState([]);
    const [isAdd, setIsAdd] = useState(false);
    const [aliases, setAliases] = useState([]);

    const customStyles = {
        content: {
            top: '50%',
            left: '50%',
            right: 'auto',
            bottom: 'auto',
            marginRight: '-50%',
            transform: 'translate(-50%, -50%)',
            width: "550px",
            height: "450px",
            fontSize: "24px",
            marginTop: "20px",
            backgroundColor: "lightslategray",
            color: "white",
            borderColor: "lightcoral",
            borderRadius: "6px",
            border: ".5px solid white",
            padding: 5
        }
    };

    function closeModal() {
        setModalIsOpen(false);
    }

    function closeAliasModal() {
        setAliasModalIsOpen(false);
    }

    useEffect(() => {
        const getData = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/agent');
                const data = await response.json();
                setAgents(data);
            } catch (error) {
                console.log(error);
            }
        };
        getData();
    }, []);

    const handleAddSubmit = async (event) => {
        event.preventDefault();
        const newAgent = {
            firstName,
            middleName,
            lastName,
            dob,
            heightInInches
        };
        const body = JSON.stringify(newAgent);

        try {
            const response = await fetch('http://localhost:8080/api/agent', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body
            });

            if (response.status === 200 || response.status === 400) {
                const data = await response.json();
                if (data.agentId) {
                    console.log(...agents, data)
                    setAgents([...agents, data]);
                    setFirstName(' ');
                    setMiddleName(' ');
                    setLastName(' ');
                    setDob(' ');
                    setHeightInInches(' ');
                    setErrors([]);
                    setModalIsOpen(false);
                } else {
                    setModalIsOpen(false);
                    setErrors(data.messages);
                    console.log(errors)
                }
            } else {
                setModalIsOpen(false);
                console.log(response.status)
                let message = 'Error Error! Sorry:(';
                setErrors(message)
                throw new Error(message);
            }
        } catch (e) {
            setModalIsOpen(false);
            console.log(e);
        };
    }

    const handleUpdate = (id) => {
        setErrors([]);
        const agentToEdit = agents.find(agent => agent.agentId === id);

        setEditAgentId(agentToEdit.agentId);
        setFirstName(agentToEdit.firstName);
        setMiddleName(agentToEdit.middleName);
        setLastName(agentToEdit.lastName);
        setDob(agentToEdit.dob);
        setHeightInInches(agentToEdit.heightInInches);
        setIsAdd(false);
        setModalIsOpen(true)
    };

    const handleUpdateSubmit = async (event) => {
        event.preventDefault();
        const updatedAgent = {
            agentId: editAgentId,
            firstName,
            middleName,
            lastName,
            dob,
            heightInInches
        };

        const body = JSON.stringify(updatedAgent);
        console.log(body + ' updated');
        try {
            const response = await fetch(`http://localhost:8080/api/agent/${editAgentId}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body
            });
            if (response.status === 204) {
                const newAgents = [...agents];
                const agentIndexToEdit = agents.findIndex(agent => agent.agentId === editAgentId);
                console.log(agentIndexToEdit + ' new');

                newAgents[agentIndexToEdit] = {
                    agentId: editAgentId,
                    firstName,
                    middleName,
                    lastName,
                    dob,
                    heightInInches
                };
                setAgents(newAgents);
                setFirstName(' ');
                setMiddleName(' ');
                setLastName(' ');
                setDob(' ');
                setHeightInInches(' ');
                setErrors([]);
                setModalIsOpen(false);
            } else if (response.status === 400) {
                const data = await response.json();
                console.log(data.messages);
                setModalIsOpen(false);
                setErrors(data.messages);
                console.log(errors)
            } else {
                setModalIsOpen(false);
                console.log(response.status)
                let message = 'Error Error! Sorry:(';
                setErrors(message)
                throw new Error(message);
            }
        } catch (e) {
            setModalIsOpen(false);
            console.log(e);
        }
    };

    const handleDelete = async (agentId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/agent/${agentId}`, {
                method: "DELETE"
            });

            if (response.status === 204) {
                const newAgents = agents.filter(agent => agent.agentId !== agentId);
                setAgents(newAgents);
                setErrors([]);
            } else if (response.status === 404) {
                throw new Error(`Agent ID #${agentId} not found.`);
            } else {
                throw new Error('Error Error! Sorry:(');
            }
        } catch (error) {
            console.log(error);
        }
    };

    const handleViewAlias = async (agentId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/alias/${agentId}`);
            if (response.status === 200) {
                const data = await response.json();
                setAliases(data);
                setErrors([]);
                setAliasModalIsOpen(true);
                console.log(aliases);
            } else if (response.status === 404) {
                setErrors(["Agent has no aliases"])
            }
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <>
            <div className='container'>
                <div className="jumbotron">
                    <h1 className="display-4">Agents</h1>
                    <p className="lead"></p>
                </div>
                <div>
                    <Errors errors={errors} />
                </div>
                <div id='table'>
                    <table className="table table-dark">
                        <thead id='head'>
                            <tr>
                                <th scope="col">First</th>
                                <th scope="col">Middle</th>
                                <th scope="col">Last</th>
                                <th scope="col">DOB</th>
                                <th scope="col">Height</th>
                                <th scope="col">
                                    <button onClick={() => {
                                        setModalIsOpen(true);
                                        setIsAdd(true);
                                        setErrors([])
                                    }} id='addButton' className="btn btn-primary btn-sm">
                                        Add Agent
                            </button>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {agents.map(agent => (
                                <tr key={agent.agentId}>
                                    <td id='firstName'
                                        onClick={() => handleViewAlias(agent.agentId)}
                                    >{agent.firstName}</td>
                                    <td>{agent.middleName}</td>
                                    <td>{agent.lastName}</td>
                                    <td> {agent.dob ? agent.dob : 'unknown'}</td>
                                    <td> {agent.heightInInches ? agent.heightInInches + ' inches' : 'unknown'}</td>
                                    <td>
                                        <div className="float-right">
                                            <button id='editButton' className="btn btn-primary btn-sm"
                                                onClick={() => handleUpdate(agent.agentId)}>Edit
                                    </button>
                                            <button id='deleteButton' className="btn btn-danger btn-sm ml-3"
                                                onClick={() => handleDelete(agent.agentId)}>Delete
                                        </button>
                                        </div>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
                <div>
                    <Modal
                        isOpen={modalIsOpen}
                        onRequestClose={closeModal}
                        style={customStyles}
                        ariaHideApp={false}
                    >
                        <i id="close" className="fas fa-times" onClick={closeModal}></i>
                        {isAdd ?
                            <h1 className='modalTitle'>
                                Add Agent
                    </h1> :
                            <h1 className='modalTitle'>
                                Edit Agent
                    </h1>}
                        <form>
                            <div className="form-group">
                                {isAdd ?
                                    <input onChange={e => { setFirstName(e.target.value); console.log('first ' + e.target.value) }}
                                        type="text" className="form-control" placeholder="First name" /> :
                                    <input onChange={e => { setFirstName(e.target.value); console.log('first ' + e.target.value) }}
                                        type="text" className="form-control" defaultValue={firstName} />
                                }
                            </div>
                            <div className="form-group">
                                {isAdd ?
                                    <input onChange={e => { setMiddleName(e.target.value); console.log('middle ' + e.target.value) }}
                                        type="text" className="form-control" placeholder="Middle name" /> :
                                    <input onChange={e => { setMiddleName(e.target.value); console.log('middle ' + e.target.value) }}
                                        type="text" className="form-control" defaultValue={middleName} />
                                }
                            </div>
                            <div className="form-group">
                                {isAdd ?
                                    <input onChange={e => { setLastName(e.target.value); console.log('last ' + e.target.value) }}
                                        type="text" className="form-control" placeholder="Last name" /> :
                                    <input onChange={e => { setLastName(e.target.value); console.log('last ' + e.target.value) }}
                                        type="text" className="form-control" defaultValue={lastName} />
                                }
                            </div>
                            <div className="form-group">
                                {isAdd ?
                                    <input onChange={e => { setDob(e.target.value); console.log('dob ' + e.target.value) }}
                                        type="date" className="form-control" placeholder="Date of birth" /> :
                                    <input onChange={e => { setDob(e.target.value); console.log('dob ' + e.target.value) }}
                                        type="date" className="form-control" defaultValue={dob} />
                                }
                            </div>
                            <div className="form-group">
                                {isAdd ?
                                    <input onChange={e => { setHeightInInches(e.target.value); console.log('height ' + e.target.value) }}
                                        type="number" className="form-control" placeholder="Height in inches" /> :
                                    <input onChange={e => { setHeightInInches(e.target.value); console.log('height ' + e.target.value) }}
                                        type="number" className="form-control" defaultValue={heightInInches} />
                                }
                            </div>
                            {
                                isAdd ?
                                    <button onClick={handleAddSubmit} type="submit" className="btn btn-primary justify-content-center submitButton">Submit</button>
                                    :
                                    <button onClick={handleUpdateSubmit} type="submit" className="btn btn-primary justify-content-center submitButton">Submit</button>
                            }
                        </form>
                    </Modal>
                    <Modal
                        isOpen={aliasModalIsOpen}
                        onRequestClose={closeAliasModal}
                        style={customStyles}
                        ariaHideApp={false}
                    >
                        <i id="closeAlias" className="fas fa-times" onClick={closeAliasModal}></i>
                        <h1 className='modalTitle'>
                            Aliases
                        </h1>
                        <div>
                            <table id='aliasTable' className="table">
                                <thead>
                                    <tr>
                                        <th className='aliasHeader' scope="col">Name</th>
                                        <th className='aliasHeader' scope="col">Persona</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {aliases.map(alias => (
                                        <tr key={alias.aliasId}>
                                            <td>{alias.name}</td>
                                            <td>{alias.persona}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </Modal>
                </div>
            </div>
        </>
    )
}

export default Agents;