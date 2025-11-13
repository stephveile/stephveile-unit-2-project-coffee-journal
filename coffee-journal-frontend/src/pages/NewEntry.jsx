import '../styles/NewEntry.css';
import CoffeeBeans from '../assets/coffee_beans.jpg';
import {useState, useEffect} from 'react';
import axios from 'axios';

const DEFAULT_SHOP_ID = 1;

const NewEntry = () => {

    // Initialize states for each of the form entries
    const [visitDate, setVisitDate] = useState("");
    const [shopId, setShopId] = useState(DEFAULT_SHOP_ID);
    const [drinkOrder, setDrinkOrder] = useState("");
    const [rating, setRating] = useState("");
    const [review, setReview] = useState("");
    const [coffeeShops, setCoffeeShops] = useState([]);
    const [wouldRecommend, setWouldRecommend] = useState("");

    // Retrieve the coffee shop data from the api
    useEffect(() => {
    axios.get("http://localhost:8080/api/coffeeshops")
      .then(res => {
        setCoffeeShops(res.data);
        if (res.data.length > 0) setShopId(res.data[0].id);
      })
      .catch(err => console.error("Error fetching coffee shops:", err));
  }, []);

    // Function to save the data to the api after clicking the submit button
    const handleSubmit = async (e) => {
    e.preventDefault();

    if (drinkOrder.length < 5 || drinkOrder.length > 50) {
      alert("Order must be 5-50 characters long.");
      return;
    }

    const newEntry = {
      shopId: parseInt(shopId),
      userId: 1, 
      drinkOrder: drinkOrder,
      rating: parseInt(rating) || 0,
      review,
      visitDate: visitDate,
      wouldRecommend: parseInt(rating) >= 4
    };

    try {
        console.log(newEntry);
      await axios.post("http://localhost:8080/entries/add", newEntry);
      alert("Journal entry saved!");
      handleReset();
    } catch (err) {
      console.error(err);
      alert("Error saving entry. Check console for details.");
    }
  };

    // Function for the reset button
    const handleReset = () => {
        setVisitDate("");
        setShopId(coffeeShops.length > 0 ? coffeeShops[0].id : DEFAULT_SHOP_ID);
        setDrinkOrder("");
        setRating("");
        setReview("");
        setWouldRecommend("");
    };

    // Return that prints the HTML and calls the event handler functions
    return (
    <div className="newEntry" style={{ backgroundImage: `url(${CoffeeBeans})` }}>
      <header>
        <title>The Coffee Journal - New Entry</title>
      </header>

      <div className="leftCol">
        <div className="leftCard">
          <h2>Create a new journal entry</h2>
          <form className="entryForm" onSubmit={handleSubmit}>
            <label htmlFor="date"><strong>Date*: </strong></label>
            <input type="date" id="date" value={visitDate} onChange={e => setVisitDate(e.target.value)} required />
            <br />

            <label htmlFor="shop"><strong>Coffee Shop*: </strong></label>
            <select id="shop" value={shopId} onChange={e => setShopId(e.target.value)} required>
              {coffeeShops.length === 0 ? (
                <option>Loading...</option>
              ) : (
                coffeeShops.map(shop => (
                  <option key={shop.id} value={shop.id}>{shop.shopName}</option>
                ))
              )}
            </select>
            <br />

            <label htmlFor="order"><strong>Order*: </strong></label>
            <input type="text" id="order" value={drinkOrder} onChange={e => setDrinkOrder(e.target.value)} required />
            <br />

            <label><strong>Rating: </strong></label>
            <div className="radiogroup">
            {[1, 2, 3, 4, 5].map(n => (
              <span key={n} className="radiobuttons">
                <input
                  type="radio"
                  id={`rating${n}`}
                  name="rating"
                  value={n}
                  checked={parseInt(rating) === n}
                  onChange={e => setRating(e.target.value)}
                />
                <label htmlFor={`rating${n}`}>{n}</label>
              </span>
            ))}
            </div>
            <br />

            <label htmlFor="review"><strong>Review: </strong></label>
            <textarea id="review" value={review} onChange={e => setReview(e.target.value)} />
            <br /><br />

            <div className="formButtons">
              <input type="submit" className="submit" />
              <input type="reset" className="reset" onClick={handleReset} />
            </div>
          </form>
        </div>
      </div>

      <div className="rightCol">
        <div className="rightCard">
          <h2>Current journal entry preview</h2>
          <p><strong>Date:</strong> {visitDate}</p>
          <p><strong>Coffee Shop:</strong> {coffeeShops.find(s => s.id === parseInt(shopId))?.shopName || ""}</p>
          <p><strong>Order:</strong> {drinkOrder}</p>
          <p><strong>Rating:</strong> {rating || "No rating"} / 5</p>
          <p><strong>Review:</strong> {review || "No review"}</p>
        </div>
      </div>
    </div>
  );
};

export default NewEntry;
