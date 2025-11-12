import '../styles/NewEntry.css';
import CoffeeBeans from '../assets/coffee_beans.jpg';
import {useState} from 'react';


const NewEntry = () => {

    // Initialize states for each of the form entries
    const [date, setDate] = useState("");
    const [name, setName] = useState("");
    const [order, setOrder] = useState("");
    const [rating, setRating] = useState("");
    const [review, setReview] = useState("");

    // Function to save the data to session storage after clicking the submit button
    const handleSubmit = (event) => {
        // Prevent the form from clearing right away
        event.preventDefault();
        // Set up the object to store the new form data
        const newEntry = {date, name, order, rating, review};
        // Check for existing stored form data or start with an empty array
        const storedEntries = JSON.parse(sessionStorage.getItem("entries") || "[]");
        // Add the new form data to the existing (or to the empty array)
        storedEntries.push(newEntry);
        // Save the array to session storage
        sessionStorage.setItem("entries", JSON.stringify(storedEntries));
        // Pop up a message stating the data was saved
        alert("Journal entry saved!");
        // Reset the form after saving (and clearing the success message)
        handleReset();

    };

    // Function for the reset button
    const handleReset = () => {
        setDate("");
        setName("");
        setOrder("");
        setRating("");
        setReview("");
    };

    // Return that prints the HTML and calls the event handler functions
    return (
        <div className="newEntry" style={{backgroundImage: `url(${CoffeeBeans})` }} alt="Coffee beans in a roaster">
            <header>
                <title>The Coffee Journal - New Entry</title>
            </header>
            <div className="leftCol">
                <div className= "leftCard">
                    <h2>Create a new journal entry</h2>
                    <form className="entryForm" onSubmit={handleSubmit}>
                        <label htmlFor="date"><strong>Date*: </strong></label>
                        <input type="date" id="date" name="date" value={date} onChange={(event) => setDate(event.target.value)} required />
                        <br/>
                        <label htmlFor="name"><strong>Coffee Shop*: </strong></label>
                        <input type="text" id="name" name="name" value={name} onChange={(event) => setName(event.target.value)} required />
                        <br/>
                        <label htmlFor="order"><strong>Order*: </strong></label>
                        <input type="text" id="order" name="order" value={order} onChange={(event) => setOrder(event.target.value)} required />
                        <br/>
                        <label><strong>Rating: </strong>
                            <input type="radio" id="rating1" name="rating" value="1" onChange={(event) => setRating(event.target.value)} />
                            <label htmlFor="rating1" >1</label>
                            <input type="radio" id="rating2" name="rating" value="2" onChange={(event) => setRating(event.target.value)} />
                            <label htmlFor="rating2">2</label>
                            <input type="radio" id="rating3" name="rating" value="3" onChange={(event) => setRating(event.target.value)} />
                            <label htmlFor="rating3">3</label>
                            <input type="radio" id="rating4" name="rating" value="4" onChange={(event) => setRating(event.target.value)} />
                            <label htmlFor="rating4">4</label>
                            <input type="radio" id="rating5" name="rating" value="5" onChange={(event) => setRating(event.target.value)} />
                            <label htmlFor="rating5">5</label>
                        </label>
                        <br/>
                        <label htmlFor="review"><strong>Review: </strong></label>
                            <textarea id="review" name="review" value={review} onChange={(event) => setReview(event.target.value)} />
                        <br/><br/>
                        <div className="formButtons">
                            <input type="submit" className="submit" />
                            <input type="reset" className="reset" onClick={handleReset}></input>
                        </div>
                    </form>
                </div>
            </div>
            <div className="rightCol">
                <div className="rightCard">
                    <h2>Current journal entry preview</h2>
                    <p><strong>Date:</strong> {date}</p>
                    <p><strong>Coffee Shop:</strong> {name}</p>
                    <p><strong>Order:</strong> {order}</p>
                    <p><strong>Rating:</strong> {rating || ("No rating")} / 5</p>
                    <p><strong>Review:</strong> {review || ("No review")}</p>
                </div>
            </div>
        </div>
    );
}

export default NewEntry;
