import '../styles/About.css';
import CoffeeJournal from '../assets/coffee_journal.jpg'


const About = () => {
    return (
        <div className="about" style={{backgroundImage: `url(${CoffeeJournal})` }} alt="Coffee on a table with notebook">
            <header>
                <title>The Coffee Journal - About Us</title> 
            </header>
            <div className="content">
                <h1 className="heading">About The Coffee Journal</h1>
                <p>
                    The Coffee Journal is your ultimate guide to discovering the best coffee experiences around you.
                    Whether you're searching for a cozy corner café or a hidden gem serving the perfect latte, The Coffee Journal helps you find it.
                    Enter and track your coffee adventures to help discover your favorite cup of coffee in your area.
                </p>
            </div>
        </div>
    )
}

export default About;
