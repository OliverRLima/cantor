import React from "react";
import api from "../api";
import MyToast from '../componentes/MyToast';
import { logout } from '../auth';
import "../css/convite.css";
import Avatar from "../img/avatar.svg";
import { Link } from "react-router-dom";

class Convite extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      enviados: [],
      recebidos: [],
      nome: '',
      mensagem: '',
      titulo: '',
      show: false
    };
  }
  componentDidMount() {

    api
      .get("/usuarios")
      .then((response) => response.data)
      .then((data) => {
        this.setState({ nome: data.nome });
      });

    api
      .get("/home/convites/enviados")
      .then((response) => response.data)
      .then((data) => {
        this.setState({ enviados: data });
      });

    api
      .get("/home/convites/recebidos")
      .then((response) => response.data)
      .then((data) => {
        console.log(data);
        this.setState({ recebidos: data });
      });
  }

  negarConvite = (id) => {
    api
      .put("/home/convites/negar/ " + id)
      .then((response) => {
        if (response.data != null) {
          this.setState({
            show: true,
            mensagem: 'Negando acesso a informações!',
            titulo: 'Aceito'
          });
          setTimeout(() => this.setState({ show: false }), 5000);
        }
      });
  };

  permitirConvite = (id) => {
    api
      .put("/home/convites/permitir/ " + id)
      .then((response) => {
        if (response.data != null) {
          this.setState({
            show: true,
            mensagem: 'Permitindo acesso a informações!',
            titulo: 'Aceito'
          });
          setTimeout(() => this.setState({ show: false }), 5000);
        }
      });
  };

  logoff = () => {
    logout();
    api
      .delete("/usuarios/logoff")
      .then((response) => { })
      .catch((error) => {
        console.error("Error -" + error);
      });
  };

  render() {

    let id = this.props.match.params.id;
    let nome = this.state.nome;
    let titulo = this.state.titulo;
    let mensagem = this.state.mensagem;

    return (
      <div className="as">
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
                <Link to={"/medalhas/" + id}>
                  <a className="a-medalha">
                    <span>Medalhas</span>
                  </a>
                </Link>
              </li>
            </ul>
            <Link to={"/login"}>
              <a className="link-exit-medalhas" onClick={this.logoff.bind(this)}>Sair</a>
            </Link>
          </nav>
        </div>
        <div className="convite-content">
          <div style={{ "display": this.state.show ? "block" : "none" }}>
            <MyToast titulo={titulo} mensagem={mensagem} children={{ show: this.state.show }} />
          </div>
          <div className="solicitacoes">
            <h2>Solicitações Enviadas</h2>

            {this.state.enviados.length === 0 ? (
              <h2>Não há solicitações</h2>
            ) : (
                this.state.enviados.map((i) => (
                  <div className="z">
                    <div className="convite-item blog">
                      <div className="text-holder col-3-5">
                        <div className="convite-title">
                          {i.nome}, {i.instrumento}, {i.genero}, {i.estado} -{" "}
                          {i.cidade}
                        </div>
                        <div className="convite-description">
                          Facebook: {i.visualizar ? i.facebook : "Não Permitido"}{" "}
                          <br />
                        Instagram:{" "}
                          {i.visualizar ? i.instagram : "Não Permitido"} <br />
                        Telefone: {i.visualizar
                            ? i.telefone
                            : "Não Permitido"}{" "}
                          <br />
                        </div>
                      </div>
                    </div>
                  </div>
                ))
              )}
          </div>

          <div className="recebidas">
            <h2>Solicitações Recebidas</h2>

            {this.state.recebidos.length === 0 ? (
              <h2>Não há solicitações</h2>
            ) : (
                this.state.recebidos.map((i) => (
                  <div className="z">
                    <div className="convite-item blog">
                      <div className="text-holder col-3-5">
                        <div className="convite-title">
                          {i.nome}, {i.instrumento}, {i.genero}, {i.estado} -{" "}
                          {i.cidade}
                        </div>
                        <div className="convite-description">
                          {i.visualizar ? (
                            <p>Permitido</p>
                          ) : (
                              <p>Este usuário não tem permissão para visualizar seus dados de contato</p>
                            )}
                          {i.visualizar ? (
                            <button
                              onClick={this.negarConvite.bind(this, i.idConvite)}
                            >
                              negar
                            </button>
                          ) : (
                              <button
                                onClick={this.permitirConvite.bind(
                                  this,
                                  i.idConvite
                                )}
                              >
                                permitir
                              </button>
                            )}
                        </div>
                      </div>
                    </div>
                  </div>
                ))
              )}
          </div>
        </div>
      </div>
    );
  }
}

export default Convite;
