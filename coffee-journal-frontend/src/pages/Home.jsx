import '../styles/Home.css';
import Coffee1 from '../assets/coffee_1.png'
import {Link} from 'react-router-dom';
import Button from '../components/Button';


const Home = () => {

    // Return that renders the buttons and future features note
    return (
        <div className="home" style={{backgroundImage: `url(${Coffee1})` }} alt="Coffee on a table with spoon">
            <header>
                <title>The Coffee Journal - Home</title>
            </header>
            <h1 className="intro">Welcome to your coffee journal!</h1>
            <div className="buttons">
                <div>
                    <Link to='/newentry'>
                        <Button label="Create a new journal entry"></Button>
                    </Link>
                </div>
                <br/><br/>
                <div>
                    <Link to='/journal'>
                        <Button label="View your journal"></Button>
                    </Link>
                </div>
            </div>
                <br/><br/><br/><br/>
            <div className="features">
                <ul>New features coming soon!
                    <li>Locations & hours</li>
                    <li>Personalized favorite coffees and shops</li>
                </ul>
            </div>
        </div>
    );
}

export default Home;
