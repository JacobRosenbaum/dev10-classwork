import '../assets/css/Header.css';
import { Link } from "react-router-dom";

function Header() {
    return (
        <ul id='header' className="nav justify-content-center">
            <li className="nav-item">
                <Link to="/agents">
                    <a className="nav-link" href="/agents">Agents</a>
                </Link>
            </li>
            <li className="nav-item">
                <a className="nav-link">Agencies</a>
            </li>
            <li className="nav-item">
                <a className="nav-link">Missions</a>
            </li>
            <li className="nav-item">
                <Link to="/">
                    <a className="nav-link" href="/">Home Base</a>
                </Link>
            </li>
        </ul >
    );
}

export default Header;