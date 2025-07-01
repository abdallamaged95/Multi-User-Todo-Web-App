import React, { useState } from "react";
import { Eye, EyeOff, Mail, Lock, User } from "lucide-react";
import "./Signup.css";
import { ApiService } from "../api.js";

export default function Signup({ onSignedUp }) {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async () => {
    setIsLoading(true);
    let signupRes = await ApiService.signup(username, email, password);
    if (signupRes.status !== 201) {
      setIsLoading(false);
      alert("Signup failed. Please try again later.");
      console.log("Error during signup:", signupRes);
      return;
    }
    setIsLoading(false);
    onSignedUp();
  };

  return (
    <div className="signup-container">
      <div className="signup-card">
        <div className="signup-header">
          <h2 className="signup-title">Create Account</h2>
          <p className="signup-subtitle">Join us today and get started</p>
        </div>

        <div className="signup-form">
          <div className="input-group">
            <label htmlFor="username" className="input-label">
              Username
            </label>
            <div className="input-wrapper">
              <User className="input-icon" />
              <input
                id="username"
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                className="input-field"
                placeholder="Choose a username"
                required
              />
            </div>
          </div>

          <div className="input-group">
            <label htmlFor="email" className="input-label">
              Email Address
            </label>
            <div className="input-wrapper">
              <Mail className="input-icon" />
              <input
                id="email"
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="input-field"
                placeholder="Enter your email"
                required
              />
            </div>
          </div>

          <div className="input-group">
            <label htmlFor="password" className="input-label">
              Password
            </label>
            <div className="input-wrapper">
              <Lock className="input-icon" />
              <input
                id="password"
                type={showPassword ? "text" : "password"}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="input-field input-field-password"
                placeholder="Create a password"
                required
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="password-toggle"
              >
                {showPassword ? (
                  <EyeOff className="toggle-icon" />
                ) : (
                  <Eye className="toggle-icon" />
                )}
              </button>
            </div>
          </div>

          <button
            onClick={handleSubmit}
            disabled={isLoading}
            className={`signup-button ${
              isLoading ? "signup-button-loading" : ""
            }`}
          >
            {isLoading ? "Creating Account..." : "Create Account"}
          </button>
        </div>

        <div className="login-link">
          <p className="login-text">
            Already have an account?{" "}
            <a href="#" className="login-link-text">
              Sign in
            </a>
          </p>
        </div>
      </div>
    </div>
  );
}
