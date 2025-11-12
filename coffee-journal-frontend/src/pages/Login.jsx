import '../styles/Login.css';
import Coffee1 from '../assets/coffee_1.png'
import { useState } from "react";
import { useNavigate } from 'react-router-dom';

const Login = () => {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    setError("");

    if (email && password) {
      alert("Login successful!");
      navigate("/home");
    } else {
      setError("Invalid email or password");
    }
  };

  return (
    <div className="login" style={{backgroundImage: `url(${Coffee1})` }} alt="Coffee on a table with spoon">
      <form
        onSubmit={handleSubmit}
        className="form"
      >
        <h2 className="loginButton">Log in to your journal</h2>

        {error && <p className="error">{error}</p>}

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="input"
          required
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="input"
          required
        />

        <button type="submit" className="submit">
          Login
        </button>
      </form>
    </div>
  );
}

export default Login;
