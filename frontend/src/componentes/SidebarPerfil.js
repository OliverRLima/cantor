import React from "react";
import api from '../api';
import { logout } from "../auth";
import "../css/SidebarPerfil.css";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

class SidebarPerfil extends React.Component {

  logoff = () => {
    logout();
    api.delete("/usuarios/logoff")
      .then(response => {
      }).catch((error) => {
        console.error("Error -" + error);
      });
  }

  render() {
    const { id } = this.props;

    const sideBar = (
      <div className="sidebar-container">
        <nav class="menu" tabindex="0">
          <ul className="ul-perfil">
            <li tabindex="0" className="icon-inicio">
              <Link to={"/feed/" + id}>
                <a className="li-a">
                  <span>Inicio</span>
                </a>
              </Link>
            </li>
            <li tabindex="0" className="icon-invite">
              <Link to={"/convite/" + id}>
                <a className="li-a">
                  <span>Convites</span>
                </a>
              </Link>
            </li>
            <li tabindex="0" className="icon-medal">
              <Link to={"/medalhas/" + id}>
                <a className="li-a">
                  <span>Medalhas</span>
                </a>
              </Link>
            </li>
          </ul>
          <Link to={"/login"}>
            <a className="link-exit-perfil" onClick={this.logoff.bind(this)}>
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

SidebarPerfil.propTypes = {
  id: PropTypes.number
};
export default SidebarPerfil;
