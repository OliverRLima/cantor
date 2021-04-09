import React, { Component } from 'react';
import "../css/login.css";
import { Form, Button } from 'react-bootstrap';
import Logo from '../img/Logo.svg';
import { Link } from 'react-router-dom';
import { login } from '../auth';
import { number } from 'prop-types';
import MyToast from '../componentes/MyToast';

import axios from 'axios';
import api from '../api';

export default class LoginClass extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.changeLogin = this.changeLogin.bind(this);
        this.submitLogin = this.submitLogin.bind(this);
    }

    initialState = {
        email: '', senha: '', show: false, texto: '', titulo: ''
    }

    submitLogin = event => {
        event.preventDefault();

        const usuario = {
            email: this.state.email,
            senha: this.state.senha
        }

        api.post("/usuarios/login", usuario)
            .then(response => {
                if (response.data != null) {
                    if (response.status === 404) {
                        this.setState({
                            show: true,
                            texto: 'Email e/ou senha não encontrados',
                            titulo: 'Negado'
                        });
                        setTimeout(() => this.setState({ show: false }), 5000);
                    } else if (response.status === 400) {
                        this.setState({
                            show: true,
                            texto: 'Já possui usuário logado!',
                            titulo: 'Negado'
                        });
                        setTimeout(() => this.setState({ show: false }), 5000);
                    } else if (response.status === 204) {
                        this.setState({
                            show: true,
                            texto: 'Campos preenchidos de forma errada',
                            titulo: 'Negado'
                        });
                        setTimeout(() => this.setState({ show: false }), 5000);
                    } else if (response.status === 500) {
                        this.setState({
                            show: true,
                            texto: 'Erro desconhecido',
                            titulo: 'Erro'
                        });
                        setTimeout(() => this.setState({ show: false }), 5000);
                    } else {

                        this.setState({
                            show: true,
                            texto: 'Login realizado!',
                            titulo: 'Aceito'
                        });
                        setTimeout(() => this.setState({ show: false }), 5000);

                        login(response.data.token);
                        
                        if (response.data.idInfoUsuario === null) {
                            this.props.history.push("/informacoes/" + response.data.idUsuario);
                        } else {
                            this.props.history.push("/perfil/" + response.data.idUsuario);
                        }
                    }
                }
            }).catch((error) => {
                if (error.response.status === 404) {
                    this.setState({
                        show: true,
                        texto: 'Email e/ou senha não encontrados',
                        titulo: 'Negado'
                    });
                    setTimeout(() => this.setState({ show: false }), 5000);
                } else if (error.response.status === 400) {
                    this.setState({
                        show: true,
                        texto: 'Já possui usuário logado!',
                        titulo: 'Negado'
                    });
                    setTimeout(() => this.setState({ show: false }), 5000);
                } else if (error.response.status === 204) {
                    this.setState({
                        show: true,
                        texto: 'Campos preenchidos de forma errada',
                        titulo: 'Negado'
                    });
                    setTimeout(() => this.setState({ show: false }), 5000);
                } else {
                    this.setState({
                        show: true,
                        texto: 'Erro desconhecido',
                        titulo: 'Erro'
                    });
                    setTimeout(() => this.setState({ show: false }), 5000);
                }
            });

    }

    changeLogin = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {

        const { email, senha, texto, titulo } = this.state;

        return (
            <div className="login-all">
                <div className="img-logo-login">
                    <Link to="/#">
                        <img src={Logo} />
                    </Link>
                </div>

                <div id={texto} style={{ "display": this.state.show ? "block" : "none" }}>
                    <MyToast titulo={titulo} mensagem={texto} children={{ show: this.state.show }} />
                </div>
                <div className="login-secundary">


                    <span className="login-form1">
                        Logar
                    </span>

                    <span className="login-form2">
                        Entre no ritmo da sua música
                    </span>

                    <hr className="login-linha"></hr>

                    <Form onSubmit={this.submitLogin} id="loginFormId">
                        <Form.Group controlId="formGridEmail">
                            <div className="login-div1">
                                <Form.Label><h2 className="info-login">E-mail</h2></Form.Label>
                                <Form.Control required autoComplete="off"
                                    className="login-input1"
                                    type="text" name="email"
                                    value={email}
                                    onChange={this.changeLogin} />
                            </div>
                        </Form.Group>
                        <Form.Group>
                            <div className="login-div2">
                                <Form.Label><h2 className="info-login">Senha</h2></Form.Label>
                                <Form.Control required autoComplete="off"
                                    className="login-input1"
                                    type="password" name="senha"
                                    value={senha}
                                    onChange={this.changeLogin} />
                            </div>
                        </Form.Group>
                        <div className="login-btn">
                            <Button className="login-btn-style" type="submit">
                                Login
                            </Button>
                        </div>
                    </Form>

                    <Link to="/cadastro">
                        <a className="cadastro-link">Não possuo conta</a>
                    </Link>

                </div>
            </div>
        );
    }
}
