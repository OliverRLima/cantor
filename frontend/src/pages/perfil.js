import React from "react";
import api from "../api";
import axios from "axios";
import { Form } from "react-bootstrap";
import MyToast from '../componentes/MyToast';
import "../css/Perfil.css";
import SidePerfil from "../componentes/SidebarPerfil.js";
import PublicPerfil from "../componentes/PublicacaoPerfil.js";
import Avatar from "../img/avatar.svg";
import Edita from "../img/perfil/edita-info.svg";
import Add from "../img/perfil/addPublic.svg";
import Undo from "../img/Desfazer.svg";
import { Link } from "react-router-dom";

class Perfil extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      publicacoes: [],
      usuario: [],
      texto: '',
      titulo: '',
      show: false
    };
  }

  render() {
    const {
      idUsuario,
      nome,
      instrumento,
      genero,
      localizacao,
      idade,
      facebook,
      instagram,
      telefone,
    } = this.state.usuario;

    let texto = this.state.texto;
    let titulo = this.state.titulo;

    return (
      <div className="perfil-body">
        <div className="sidebar">
          <SidePerfil id={idUsuario} data-commentid="2" />
        </div>
        <div className="perfil-header">
          <div id={texto} style={{ "display": this.state.show ? "block" : "none" }}>
            <MyToast titulo={titulo} mensagem={texto} children={{ show: this.state.show }} />
          </div>
          <header className="avatar-perfil">
            <img className="img-perfil" src={Avatar} />
            <h2>
              {nome}
              <Link to={"/informacoes/alterar/" + idUsuario}>
                <a>
                  <img id="plus" className="img-edita fa fa-lg" src={Edita} />
                </a>
              </Link>
            </h2>
          </header>
        </div>
        <div className="perfil-content">
          <div className="publicacao-container">
            <div className="lista">
              <h2>Minhas Informações</h2>
              <ul className="user-info">
                <li className="icon-instrumento">
                  <span>{instrumento}</span>
                </li>
                <li className="icon-estilo">
                  <span>{genero}</span>
                </li>
                <li className="icon-local">
                  <span>{localizacao}</span>
                </li>
                <li className="icon-birthday">
                  <span>{idade}</span>
                </li>
                <li className="icon-music">
                  <span>{facebook}</span>
                </li>
                <li className="icon-music">
                  <span>{instagram}</span>
                </li>
                <li className="icon-music">
                  <span>{telefone}</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div className="container-nova">
          <div className="nova-public">
            <h2>
              Adicionar publicação
              <Link to={"/publicacao/" + idUsuario}>
                <a>
                  <img className="add-public fa fa-lg" src={Add} />
                </a>
              </Link>
            </h2>
            {this.state.publicacoes.length === 0 ? (
              <></>
            ) : (
                <Form
                  className="contact100Publi-form validate-form a"
                  onSubmit={this.desfazerUltima}
                  id="publicacaoFormId"
                >
                  <div className="f">
                    <p>Desfazer ultima postagem
                  <button
                        className="btn-perfil"
                        size="lg"
                        variant="success"
                        type="submit"
                      >
                        <img src={Undo} />
                      </button></p>
                  </div>
                  {/* <Button className=" button1" size="lg" variant="success" type="button" onClick={this.exportarCsv}>
                  Baixar publicações em csv 
              </Button>
              <Button className=" button1" size="lg" variant="success" type="button" onClick={this.exportarTxt}>
                  Baixar publicações em txt 
              </Button>
              <Button className=" button1" size="lg" variant="success" type="button" onClick={this.exportarLayout}>
                  Baixar arquivo de layout 
              </Button> */}
                </Form>
              )}

            {this.state.publicacoes.length === 0 ? (
              <h2> Não há registros </h2>
            ) : (
                this.state.publicacoes.map((i) => (
                  <PublicPerfil
                    key={i.idPublicacao}
                    isLoading={false}
                    id={i.idPublicacao}
                    nome={i.usuario.nome}
                    texto={i.texto}
                  />
                ))
              )}
          </div>
        </div>
      </div>
    );
  }

  desfazerUltima = (event) => {
    event.preventDefault();

    api.delete("/publicacoes").then((response) => {
      if (response.data != null) {
        this.setState({
          show: true,
          texto: 'Última publicação sendo desfeita!',
          titulo: 'Aceito'
        });
        setTimeout(() => this.setState({ show: false }), 5000);
        // alert("Última publicação sendo desfeita!");
      }
    });
  };

  // exportarCsv = event => {
  //   event.preventDefault();

  //   axios.get("http://localhost:8080/publicacoes/exportar/csv")
  //     .then(response => {
  //       if (response.data != null) {
  //         alert("Download começará em breve!");
  //       }
  //     })
  // }

  // exportarTxt = event => {
  //   event.preventDefault();

  //   axios.get("http://localhost:8080/publicacoes/exportar/txt")
  //     .then(response => {
  //       if (response.data != null) {
  //         alert("Download começará em breve!");
  //       }
  //     })
  // }

  // exportarLayout = event => {
  //   event.preventDefault();

  //   axios.get("http://localhost:8080/publicacoes/exportar/layout")
  //     .then(response => {
  //       if (response.data != null) {
  //         alert("Download começará em breve!");
  //       }
  //     })
  // }

  async componentDidMount() {
    await api
      .get("/usuarios/completo")
      .then((response) => response.data)
      .then((data) => {
        if (data === undefined) {
          console.log("genero undefined");
        } else if (data === []) {
          alert("Não foi encontrado resultados! Tente de outra forma");
        } else {
          this.setState({ usuario: [] });
          this.setState({ usuario: data });
        }
      })
      .catch((error) => {
        if (error.response.status === 204) {
        } else if (error.response.status === 400) {
        } else if (error.response.status === 404) {
        } else {
        }
      });

    await api
      .get("/publicacoes")
      .then((response) => {
        if (response.data === undefined) {
          console.log("genero undefined");
        } else if (response.data === []) {
          alert("Não foi encontrado resultados! Tente de outra forma");
        } else {
          this.setState({ publicacoes: [] });
          this.setState({ publicacoes: response.data });
        }
      })
    // .catch((error) => {
    //   if (error.response.status === 204) {
    //   } else if (error.response.status === 400) {
    //   } else if (error.response.status === 404) {
    //   } else {
    //   }
    // });
  }
}

export default Perfil;
