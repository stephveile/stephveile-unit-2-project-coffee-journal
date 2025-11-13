import '../styles/Journal.css';
import Coffee3 from '../assets/coffee_3.jpg';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Journal = () => {
    const [entries, setEntries] = useState([]);
    const navigate = useNavigate();

    // Fetch all entries from the API
    useEffect(() => {
        fetch("http://localhost:8080/entries")
            .then(response => {
                if (!response.ok) throw new Error("Network response was not ok");
                return response.json();
            })
            .then(data => setEntries(data))
            .catch(error => console.error("Fetch error:", error));
    }, []);

    // Delete an entry
    const handleDelete = (id) => {
        fetch(`http://localhost:8080/entries/delete/${id}`, { method: "DELETE" })
            .then(response => {
                if (response.ok) {
                    setEntries(entries.filter(entry => entry.id !== id));
                } else {
                    console.error("Delete failed");
                }
            })
            .catch(error => console.error("Delete error:", error));
    };

    // Navigate to the UpdateEntry page
    const handleUpdate = (id) => {
        navigate(`/updateentry/${id}`);
    };

    return (
        <div className="journal" style={{ backgroundImage: `url(${Coffee3})` }}>
            <header>
                <title>The Coffee Journal - Journal</title>
            </header>
            <h1 className="heading">Journal Entries:</h1>
            <div className="entries">
                {entries.length === 0 ? (
                    <p>Create a journal entry to start filling your journal!</p>
                ) : (
                    entries.map((entry) => (
                        <div key={entry.id} className="entryCard">
                            <p>
                                <strong>~~~~ {entry.coffeeShop?.shopName} ~ {entry.visitDate} ~~~~</strong>
                                <br /><br />
                                I went to {entry.coffeeShop?.shopName} on {entry.visitDate} and ordered a {entry.drinkOrder}.
                                I gave it {entry.rating}/5 stars. {entry.review}
                                <br /><br />
                            </p>
                            <button onClick={() => handleUpdate(entry.id)}>Update</button>
                            <button onClick={() => handleDelete(entry.id)}>Delete</button>
                            <hr />
                        </div>
                    ))
                )}
            </div>
        </div>
    );
};

export default Journal;
