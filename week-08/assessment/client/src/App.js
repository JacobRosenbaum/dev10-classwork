import { BrowserRouter as Router, Route } from 'react-router-dom';
import HomePage from "./components/pages/HomePage";
import Mission from './components/pages/Mission';
import Agencies from './components/pages/Agencies';
import Agents from "./components/pages/Agents";
import './assets/css/App.css';
import Header from './components/Header';


function App() {
  return (
    <Router>
       <Header />
        <div>
        <Route exact path="/" component={HomePage} />
        <Route exact path="/agents" component={Agents} />
        <Route exact path="/missions" component={Mission} />
        <Route exact path="/agencies" component={Agencies} />

        </div>
    </Router>
  );
}

export default App;
