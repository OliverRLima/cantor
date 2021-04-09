import React from "react";
import api from '../api';
import { logout } from '../auth';
import { Link } from "react-router-dom";
import "../css/Navbar.css";
import Avatar from "../img/avatar.svg";
import PropTypes from "prop-types";

class NavBarFeed extends React.Component {

  logoff = () => {
    logout();
    api.delete("/usuarios/logoff")
      .then(response => {
      }).catch((error) => {
        console.error("Error -" + error);
      });
  }

  render() {

    const { id, nome } = this.props;

    const nav = (
      <nav className="navbar">
        <div className="navbar-container">
          <nav class="menu" tabindex="0" >
            <header className="avatar" >
              <img src={Avatar} />
              <h2>{nome}</h2>
            </header>
            <ul>
              <li tabindex="0" className="icon-profile">
                <Link to={"/perfil/" + id}>
                  <a className="li-a"><span>Perfil</span></a>
                </Link>
              </li>
              <li tabindex="0" className="icon-invite">
                <Link to={"/convite/" + id}>
                  <a className="li-a"><span>Convites</span></a>
                </Link>
              </li>
              <li tabindex="0" className="icon-medal">
                <Link to={"/medalhas/" + id}>
                  <a className="li-a"><span>Medalhas</span></a>
                </Link>
              </li>
            </ul>
            <Link to={"/login"}>
              <a className="link-exit" onClick={this.logoff.bind(this)} >Sair</a>
            </Link>
          </nav>
        </div>
      </nav>
    );

    return (
      <div>
        {nav}
      </div>
    );

  }
}

NavBarFeed.propTypes = {
  id: PropTypes.number,
  nome: PropTypes.string
};

export default NavBarFeed;