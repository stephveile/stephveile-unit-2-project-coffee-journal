import '../styles/Journal.css';
import Coffee3 from '../assets/coffee_3.jpg';
import {useState, useEffect} from 'react';


const Journal = () => {


    // Set state for the array that will be accessed from session storage
    const [entries, setEntries] = useState([]);
    
    useEffect(() => {
        console.log(entries);
        const storedEntries = JSON.parse(sessionStorage.getItem("entries") || "[]");
        console.log(storedEntries);
        if(Array.isArray(storedEntries)) {
            setEntries(storedEntries);
        }
    }, []);

    return (
        <div className="journal" style={{backgroundImage: `url(${Coffee3})` }} alt="Coffee on a table with succulent">
            <header>
                <title>The Coffee Journal - Journal</title> 
            </header>
            <h1 className="heading">Journal Entries:</h1>
            <div className="entries">
                {entries.map((entry, index) => (
                <p key={index}>
                    <strong>~~~~ {entry.name} ~ {entry.date} ~~~~</strong>
                    <br/><br/>
                    I went to {entry.name} on {entry.date} and ordered a {entry.order}.
                    I gave it {entry.rating}/5 stars. {entry.review}
                    <br/><br/><br/>
                </p>
            )) || "Create a journal entry to start filling your journal!"}
            </div>
        </div>
    );
}

export default Journal;
