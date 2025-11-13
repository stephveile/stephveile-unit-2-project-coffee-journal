import '../styles/NewEntry.css';
import CoffeeBeans from '../assets/coffee_beans.jpg';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

const UpdateEntry = () => {
  const { id } = useParams(); // entry id from URL
  const navigate = useNavigate();

  const [date, setDate] = useState("");
  const [shopId, setShopId] = useState(1); // default shop
  const [order, setOrder] = useState("");
  const [rating, setRating] = useState("");
  const [review, setReview] = useState("");
  const [coffeeShops, setCoffeeShops] = useState([]);
  const [originalEntry, setOriginalEntry] = useState(null); // for reset

  // Fetch coffee shops for drop-down
  useEffect(() => {
    axios.get("http://localhost:8080/api/coffeeshops")
      .then(res => setCoffeeShops(res.data))
      .catch(err => console.error("Error fetching coffee shops:", err));
  }, []);

  // Fetch the existing entry to populate the form
  useEffect(() => {
    axios.get(`http://localhost:8080/entries/details/${id}`) // updated endpoint
      .then(res => {
        const entry = res.data;
        setDate(entry.visitDate);
        setShopId(entry.coffeeShop?.id || 1); // use the shop id from the entry
        setOrder(entry.drinkOrder);
        setRating(entry.rating.toString());
        setReview(entry.review);
        setOriginalEntry(entry);
      })
      .catch(err => console.error("Error fetching entry:", err));
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const updatedEntry = {
      shopId: parseInt(shopId),
      userId: 1, // replace with logged-in user if you have auth
      drinkOrder: order,
      rating: parseInt(rating) || 0,
      review,
      visitDate: date,
      wouldRecommend: parseInt(rating) >= 4
    };

    try {
      await axios.put(`http://localhost:8080/entries/update/${id}`, updatedEntry);
      alert("Journal entry updated!");
      navigate("/journal"); // redirect back to journal page
    } catch (err) {
      console.error(err);
      alert("Error updating entry. Check console for details.");
    }
  };

  const handleReset = () => {
    if (originalEntry) {
      setDate(originalEntry.visitDate);
      setShopId(originalEntry.coffeeShop?.id || 1);
      setOrder(originalEntry.drinkOrder);
      setRating(originalEntry.rating.toString());
      setReview(originalEntry.review);
    }
  };

  return (
    <div className="newEntry" style={{ backgroundImage: `url(${CoffeeBeans})` }}>
      <header>
        <title>The Coffee Journal - Update Entry</title>
      </header>

      <div className="leftCol">
        <div className="leftCard">
          <h2>Update journal entry</h2>
          <form className="entryForm" onSubmit={handleSubmit}>
            <label htmlFor="date"><strong>Date*: </strong></label>
            <input type="date" id="date" value={date} onChange={e => setDate(e.target.value)} required />
            <br />

            <label htmlFor="shop"><strong>Coffee Shop*: </strong></label>
            <select
              id="shop"
              value={shopId}
              onChange={e => setShopId(Number(e.target.value))}
              required
            >
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
            <input type="text" id="order" value={order} onChange={e => setOrder(e.target.value)} required />
            <br />

            <label><strong>Rating: </strong></label>
            {[1, 2, 3, 4, 5].map(n => (
              <span key={n}>
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
          <p><strong>Date:</strong> {date}</p>
          <p><strong>Coffee Shop:</strong> {coffeeShops.find(s => s.id === parseInt(shopId))?.shopName || ""}</p>
          <p><strong>Order:</strong> {order}</p>
          <p><strong>Rating:</strong> {rating || "No rating"} / 5</p>
          <p><strong>Review:</strong> {review || "No review"}</p>
        </div>
      </div>
    </div>
  );
};

export default UpdateEntry;