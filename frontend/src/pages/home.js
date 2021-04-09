import React from "react";
import "../css/home.css";
import "../css/bootstrapStyle/bootstrap.css";
import "../css/bootstrapStyle/bootstrap-grid.css";
import logo from "../img/Logo.svg";
import sobre from "../img/imagem-sobre.svg";
import { Link } from "react-router-dom";


function home() {
  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
        <div className="container">
          <a className="navbar-brand" href="#">
            <img src={logo}></img>
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarResponsive"
            aria-controls="navbarResponsive"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarResponsive">
            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to="/login">
                  <a className="nav-link">Entrar</a>
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/cadastro">
                  <a className="nav-link">Cadastrar-se</a>
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <header className="masthead text-center text-white">
        <div className="masthead-content">
          <div className="container">
            <h1 className="masthead-heading mb-0">Feito por músicos</h1>
            <h2 className="masthead-subheading mb-0">para músicos :D</h2>
            <Link to="/cadastro">
              <a className="btn btn-primary btn-xl rounded-pill mt-5">Começar</a>
            </Link>
          </div>
        </div>
      </header>

      <div className="container">
        <div className="row align-items-center">
          <div className="col-lg-6 order-lg-2">
            <div className="p-5">
              <img className="img-fluid rounded-circle" src={sobre} alt="" />
            </div>
          </div>
          <div className="col-lg-6 order-lg-1">
            <div className="p-5">
              <h2 className="display-4">O que é MusicAll?</h2>
              <p>
                Uma plataforma feita para qualquer tipo de músico afim de
                encontrar alguém para fazer um som, juntos!
              </p>
            </div>
          </div>
        </div>
      </div>

      <div className="container">
        <div className="row align-items-center">
          <div className="col-lg-6 order-lg-2">
            <div className="tutorial-back">
              <div id="page-landing-t">
                <div className=" div-fluxo">
                  <h2 className="display-4">Como usar o MusicAll</h2>




                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="equipe">

      </div>
    </>
  );
}

export default home;
