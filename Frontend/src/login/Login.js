import React, { useState } from "react";
import { Eye, EyeOff, Mail, Lock } from "lucide-react";
import "./Login.css";
import { ApiService } from "../api.js";
import Signup from "../sign_up/Signup.js";

export default function Login({ onLoggedIn }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [signingUp, setSigningUp] = useState(false);

  const handleLogin = async () => {
    setIsLoading(true);

    let res = await ApiService.login(username, password);
    if (res.status !== 200) {
      setIsLoading(false);
      alert("Login failed. Please check your credentials.");
      console.log("Error during login:", res);
      return;
    }

    console.log(res.data);
    setIsLoading(false);
    ApiService.setUserJWTToken(res.data.token);

    onLoggedIn(res.data, username);
  };

  const handleSignedUp = () => {
    setSigningUp(false);
    alert("Account created successfully! You can now log in.");
  };

  if (signingUp) {
    return <Signup onSignedUp={handleSignedUp} />;
  }

  return (
    <div className="login-container">
      <div className="login-card">
        <div className="login-header">
          <h2 className="login-title">Todo App</h2>
        </div>

        <div className="login-form">
          <div className="input-group">
            <label htmlFor="email" className="input-label">
              Username
            </label>
            <div className="input-wrapper">
              <Mail className="input-icon" />
              <input
                id="email"
                type="email"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                className="input-field"
                placeholder="Enter your username"
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
                placeholder="Enter your password"
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
            onClick={handleLogin}
            disabled={isLoading}
            className={`login-button ${
              isLoading ? "login-button-loading" : ""
            }`}
          >
            {isLoading ? "Signing in..." : "Sign In"}
          </button>
        </div>

        <div className="signup-link">
          <p className="signup-text">
            Don't have an account?{" "}
            <button
              className={"signup-button-ref"}
              onClick={() => setSigningUp(true)}
            >
              Sign Up
            </button>
          </p>
        </div>
      </div>
    </div>
  );
}
