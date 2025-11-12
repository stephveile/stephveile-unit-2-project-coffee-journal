import {Link} from 'react-router-dom';
import '../styles/Nav.css';
import Logo from '../assets/coffee_cup_logo.png';

const Nav = () => {

    return (
        <div className="nav">
            <div className="left">
                <img src={Logo} />
                <h1>The Coffee Journal</h1>
            </div>
            <div className="right">
                <Link to='/home'>Home</Link>
                <Link to='/newentry'>New Entry</Link>
                <Link to='/journal'>Journal</Link>
                <Link to='/about'>About Us</Link>
            </div>
        </div>
    );
}

export default Nav;
