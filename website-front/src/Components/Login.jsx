import "../CSS/Login.css";
import { Link } from "react-router-dom";
import React from "react";
import A from "../CSS/App.module.css"


const Login = (props) => {
  return (
    <div className={A.Login} >
      <form className="labelStyle">
        <div>
          <h6>E-mail:
          <input className="inputStyle" type="email" /></h6>
          <h7>Parola:
          <input className="inputStyle" type="password" /></h7>
          <br />
          <Link
            className="buttonsLoginStyle"
            to="/"
            onClick={() => props.setIsLoggedIn(true)}
          >
            Login
          </Link>
          <input
            className="buttonsLoginStyle2"
            type="submit"
            value="Parola uitata"
            onClick={() =>
              alert(
                "O parola noua va fi trimisa pe mail dupa ce vom lua legatura cu tine"
              )
            }
          />
        </div>
      </form>
        <div>
        <input
          className="termsStyle"
          type="submit"
          value="Termeni si Conditii"
          onClick={() => alert("termeni si conditii")}
        />
        <input
          className="contactStyle"
          type="submit"
          value="Contact"
          onClick={() => alert("date de contact")}
        />
      </div>
    </div>
  );
};

export default Login;
