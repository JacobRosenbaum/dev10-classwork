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
                <Link to="/agencies">
                    <a className="nav-link" href="/agencies">Agencies</a>
                </Link>
            </li>
            <li className="nav-item">
                <Link to="/missions">
                    <a className="nav-link" href="/missions">Missions</a>
                </Link>
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