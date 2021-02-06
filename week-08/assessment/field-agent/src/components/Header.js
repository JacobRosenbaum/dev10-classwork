import '../assets/css/Header.css';
import {Link} from "react-router-dom";

function Header() {
    return (
        <ul id = 'header' class="nav justify-content-center">
            <Link to="/agents" className={window.location.pathname === "/agents"}>
                <li class="nav-item">
                    <a className="nav-link" href="/agents">Agents</a>
                </li>
            </Link>
            <li class="nav-item">
                <a className="nav-link">Agencies</a>
            </li>
            <li class="nav-item">
                <a className="nav-link">Missions</a>
            </li>
            <Link to="/" className={window.location.pathname === "/"}>
                <li class="nav-item">
                    <a className="nav-link" href="/">Home Base</a>
                </li>
            </Link>
        </ul >
    );
}

export default Header;