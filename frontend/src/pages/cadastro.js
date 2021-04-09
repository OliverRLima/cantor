import React, { Component } from "react";
import "../css/cadastro.css";
import { Form, Button } from "react-bootstrap";
import Logo from "../img/Logo.svg";
import { Link } from "react-router-dom";
import api from "../api";
import MyToast from "../componentes/MyToast";

const initialState = {
  nome: "",
  email: "",
  senha: "",
  confirmSenha: "",
  nameError: "",
  emailError: "",
  senhaError: "",
  confirmSenhaError: "",
  mensagem: "",
  titulo: "",
  show: false,
};

export default class Cadastro extends Component {
  constructor(props) {
    super(props);
    this.state = this.stateInicial;
    this.submitCadastro = this.submitCadastro.bind(this);
    this.changeCadastro = this.changeCadastro.bind(this);
  }

  stateInicial = initialState;

  validate = () => {
    let nameError = "";
    let emailError = "";
    let senha = "";
    let nome = "";
    let email = "";
    let confirmSenha = "";
    let senhaError = "";
    let confirmSenhaError = "";
    let confirmSenhaCorreta = "";

    if (!this.state.nome) {
      nameError = "Nome não pode ficar vazio";
    }

    if (!this.state.senha) {
      senhaError = "A senha não pode ficar vazia";
    }else if (this.state.senha < 6) {
      senhaError = "A senha deve ter no minimo 6 caracteres";
    }

    if (!this.state.email) {
      emailError = "O Email não pode ficar vazio";
    }else if (!this.state.email.includes("@")) {
        emailError = "Email invalido";
      }

    if (!this.state.confirmSenha) {
      confirmSenhaError = "Confirme a senha";
      confirmSenhaCorreta = "Senha não esta igual";
    }else if (senha !== confirmSenha) {
      this.setState({ confirmSenhaCorreta });
      return false;
    }

    if (emailError || nameError) {
      this.setState({ emailError, nameError });
      return false;
    }

    if (senhaError || confirmSenhaError) {
      this.setState({ senhaError, confirmSenhaError });
      return false;
    }
    return true;
  };

  submitCadastro = (event) => {
    event.preventDefault();

    const usuario = {
      nome: this.state.nome,
      email: this.state.email,
      senha: this.state.senha,
    };

    const isValid = this.validate();
    if (isValid) {
      api
        .post("/usuarios", usuario)
        .then((response) => {
          if (response.status === 204) {
            this.setState({
              show: true,
              mensagem: "Erro ao completar os campos!",
              titulo: "Negado",
            });
            setTimeout(() => this.setState({ show: false }), 5000);
          } else {
            this.setState({
              show: true,
              mensagem: "Cadastrado realizado com sucesso!",
              titulo: "Aceito",
            });
            setTimeout(() => this.setState({ show: false }), 5000);
            this.setState(this.initialState);
            return this.props.history.push("/login");
          }
        })
        .catch((error) => {
          if (error.response.status === 400) {
            this.setState({
              show: true,
              mensagem: "Erro ao realizar o cadastro",
              titulo: "Negado",
            });
            setTimeout(() => this.setState({ show: false }), 5000);
          } else if (error.response.status === 204) {
            this.setState({
              show: true,
              mensagem: "Erro ao completar os campos",
              titulo: "Negado",
            });
            setTimeout(() => this.setState({ show: false }), 5000);
          } else {
            this.setState({
              show: true,
              mensagem: "Erro desconhecido",
              titulo: "Erro",
            });
            setTimeout(() => this.setState({ show: false }), 5000);
          }
        });
    } else {
      this.setState({
        show: true,
        mensagem: "Erro ao realizar o cadastro",
        titulo: "Negado",
      });
      setTimeout(() => this.setState({ show: false }), 5000);
    }
  };

  changeCadastro = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  render() {
    const { nome, email, senha, confirmSenha } = this.state;
    let titulo = this.state.titulo;
    let mensagem = this.state.mensagem;
    return (
      <div className="register-all">
        <div className="img-logo-cadastro">
          <Link to="/#">
            <img src={Logo} />
          </Link>
        </div>
        <div style={{ display: this.state.show ? "block" : "none" }}>
          <MyToast
            titulo={titulo}
            mensagem={mensagem}
            children={{ show: this.state.show }}
          />
        </div>
        <div className="register-secundary">
          <span className="register-form1">Cadastre-se</span>

          <span className="register-form2">
            Para encontrar a sintonia perfeita
          </span>

          <hr className="register-linha"></hr>
          <Form onSubmit={this.submitCadastro} id="cadastroId">
            <Form.Group controlId="formGridNome">
              <div className="register-div1">
                <Form.Label>
                  <h2 className="info">Nome Completo</h2>
                </Form.Label>
                <Form.Control
                  autoComplete="off"
                  className="register-input1"
                  type="text"
                  name="nome"
                  value={nome}
                  onChange={this.changeCadastro}
                />
                <div className="alerta">
                  {this.state.nameError}
                </div>
              </div>
            </Form.Group>
            <Form.Group controlId="formGridEmail">
              <div className="register-div2">
                <Form.Label>
                  <h2 className="info">E-mail</h2>
                </Form.Label>
                <Form.Control
                  autoComplete="off"
                  placeholder="musicall@gmail.com"
                  className="register-input1"
                  type="text"
                  name="email"
                  value={email}
                  onChange={this.changeCadastro}
                 />
                 <div className="alerta">
                  {this.state.emailError}
                </div>
              </div>
            </Form.Group>
            <Form.Group>
              <div className="register-div3">
                <Form.Label>
                  <h2 className="info">Senha</h2>
                </Form.Label>
                <Form.Control
                  autoComplete="off"
                  className="register-input3"
                  type="password"
                  name="senha"
                  value={senha}
                  onChange={this.changeCadastro}
                />
                <div className="alerta">
                  {this.state.senhaError}
                </div>
              </div>
            </Form.Group>
            <Form.Group>
              <div className="register-div4">
                <Form.Label>
                  <h2 className="info">Confirme sua senha</h2>
                </Form.Label>
                <Form.Control
                  autoComplete="off"
                  className="register-input4"
                  type="password"
                  name="confirmSenha"
                  value={confirmSenha}
                  onChange={this.changeCadastro}
                />
                <div className="alerta">
                  {this.state.confirmSenhaError}
                </div>
              </div>
            </Form.Group>
            <div className="register-btn">
              <Button type="submit" className="register-btn-style">
                Cadastre-se
              </Button>
            </div>
          </Form>

          <Link to="/login">
            <a className="login-link">Já tenho conta</a>
          </Link>
        </div>
      </div>
    );
  }
}
