import React from "react";
import api from '../api';
import PropTypes from "prop-types";
import { logout } from "../auth";
import { Link } from "react-router-dom";

import Avatar from "../img/avatar.svg";
import "../css/Sidebar.css";

class SidebarMedalha extends React.Component {

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

    const sideBar = (
      <div className="sidebar-container">
        <nav className="menu-medalha" tabindex="0">
          <header className="avatar" >
            <img src={Avatar} />
            <h2>{nome}</h2>
          </header>
          <ul className="ul-medalhas">
            <li tabIndex="0" className="icon-inicio">
              <Link to={"/feed/" + id}>
                <a className="a-medalha">
                  <span>Inicio</span>
                </a>
              </Link>
            </li>
            <li tabIndex="0" className="icon-profile">
              <Link to={"/perfil/" + id}>
                <a className="a-medalha">
                  <span>Perfil</span>
                </a>
              </Link>
            </li>
            <li tabIndex="0" className="icon-invite">
              <Link to={"/convite/" + id}>
                <a className="a-medalha">
                  <span>Convites</span>
                </a>
              </Link>
            </li>
          </ul>
          <Link to={"/login"}>
            <a className="link-exit-medalhas" onClick={this.logoff.bind(this)}>
              Sair
          </a>
          </Link>
        </nav>
      </div>
    );

    return (
      <div>
        {sideBar}
      </div>
    );
  }
}

SidebarMedalha.propTypes = {
  id: PropTypes.number,
  nome: PropTypes.string
};

export default SidebarMedalha;
