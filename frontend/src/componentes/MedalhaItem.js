import React from "react";
import PropTypes from "prop-types";

import "../css/MedalhaItem.css";

import rock from "../img/medalha/rock-icon.png";
import ini from "../img/medalha/ini-icon.png";
import amad from "../img/medalha/amad-icon.png";
import profi from "../img/medalha/profi-icon.png";

import musi from "../img/medalha/musicista.png";
import chama from "../img/medalha/chama.png";
import eng from "../img/medalha/engajado.png";
import find from "../img/medalha/find.png";
import har from "../img/Publicacao/picture1.svg";
import { getDefaultNormalizer } from "@testing-library/react";

class MedalhaItem extends React.Component {

  render() {

    const { titulo, texto, emblema } = this.props;
    var iconEmblema;
    var iconTitulo;
    switch (emblema) {
      case "Iniciante": {
        iconEmblema = ini
        break;
      }
      case "Amador": {
        iconEmblema = amad
        break;
      }
      case "Profissional": {
        iconEmblema = profi
        break;
      }
      case "Rockstar": {
        iconEmblema = rock
        break;
      }
    };
    switch (titulo) {
      case "Engajado": {
        iconTitulo = eng;
        break;
      }
      case "Harmonia": {
        iconTitulo = har;
        break;
      }
      case "Procurando": {
        iconTitulo = find;
        break;
      }
      case "Musicista": {
        iconTitulo = musi;
        break;
      }
      case "Chama": {
        iconTitulo = chama
        break;
      }
    };
    return (
      <div className="conteudo">
        <div id="a">
          <div className="medal-item blog">
            <div className="icon-hlder"></div>
            <div className="icon">
              <img className="icon-img" src={iconTitulo} />
            </div>
            <div className="text-holder col-3-5">
              <div className="medal-title">{titulo}</div>
              <div className="medal-description">{texto}</div>
              <div className="medal-status"><img className="icon-desc" src={iconEmblema} />{emblema}</div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

MedalhaItem.propTypes = {
  titulo: PropTypes.string,
  texto: PropTypes.string,
  emblema: PropTypes.string
};

export default MedalhaItem;