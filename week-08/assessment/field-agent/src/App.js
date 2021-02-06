import { BrowserRouter as Router, Route } from 'react-router-dom';
import HomePage from "./components/pages/HomePage";
import Agents from "./components/pages/Agents";
import './assets/css/App.css';
import Header from './components/Header'

function App() {
  return (
    <Router>
       <Header />
        <div>
        <Route exact path="/" component={HomePage} />
        <Route exact path="/agents" component={Agents} />
        </div>
    </Router>
  );
}

export default App;
