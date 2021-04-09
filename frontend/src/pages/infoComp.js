import React from "react";
import api from '../api';

import { Form, Button } from 'react-bootstrap';
import InputMask from 'react-input-mask';
import MyToast from '../componentes/MyToast';
import '../css/infoComp.css';

class infoComp extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      dataAniversario: '', cidade: '', instagram: '', facebook: '', telefone: '', genero: '', instrumento: '', estado: '', mensagem: '', titulo: '', show: false
    };
    this.submitInfos = this.submitInfos.bind(this);
  }

  submitInfos = event => {
    event.preventDefault();

    let cidade = this.state.cidade;
    let valorCidade = cidade.toLocaleLowerCase();

    const infos = {
      dataAniversario: this.state.dataAniversario,
      descricao: 'padrão',
      cidade: valorCidade,
      estado: this.state.estado
    }

    const instrumentos = {
      tipoInstrumento: 'CORDA',
      instrumento: this.state.instrumento,
      nvDominio: 'Alto'
    }

    const generos = {
      preferencia: 'Alta',
      generoMusical: this.state.genero
    }

    const sociais = {
      facebook: this.state.facebook,
      instagram: this.state.instagram,
      twitter: this.state.telefone
    }

    api.post("/usuarios/descricao", infos)
      .then(response => {
        console.log(response);
        if (response.data != null) {
        }
      });

    api.post("/usuarios/instrumentos", instrumentos)
      .then(response => {
        console.log(response);
        if (response.data != null) {
        }
      });

    api.post("/usuarios/generos", generos)
      .then(response => {
        console.log(response);
        if (response.data != null) {
        }
      });

    api.post("/usuarios/sociais", sociais)
      .then(response => {
        console.log(response);
        if (response.data != null) {
        }
      });

    this.setState({
      show: true,
      mensagem: 'informações cadastradas com sucesso',
      titulo: 'Aceito'
    });
    setTimeout(() => this.setState({ show: false }), 5000);
    return this.props.history.push("/feed/" + this.props.match.params.id);

  }

  mudancaDataAniversario = event => {
    this.setState({ dataAniversario: event.target.value })
  }

  mudancaCidade = event => {
    this.setState({ cidade: event.target.value })
  }

  mudancaInstagram = event => {
    this.setState({ instagram: event.target.value })
  }

  mudancaFacebook = event => {
    this.setState({ facebook: event.target.value })
  }

  mudancaTelefone = event => {
    this.setState({ telefone: event.target.value })
  }

  mudancaGenero = event => {
    this.setState({ genero: event.target.value })
  }

  mudancaInstrumento = event => {
    this.setState({ instrumento: event.target.value })
  }

  mudancaEstado = event => {
    this.setState({ estado: event.target.value })
  }

  render() {

    const { dataAniversario, cidade, instagram, facebook, telefone } = this.state;

    let titulo = this.state.titulo;
    let mensagem = this.state.mensagem;

    return (
      <div class="container-contact100">
        <div class="wrap-contact100">
          <div style={{ "display": this.state.show ? "block" : "none" }}>
            <MyToast titulo={titulo} mensagem={mensagem} children={{ show: this.state.show }} />
          </div>
          <Form onSubmit={this.submitInfos} id="infosFormId" className="contact100-form validate-form">
            <span className="contact100-form-title">
              Vamos enriquecer seu perfil? :D
            </span>

            <div className="wrap-input100 validate-input" data-validate="Name is required">
              <Form.Group controlId="formGridDataAniversario">
                <Form.Label className="label-input100">Informe sua data de nascimento</Form.Label>
                <Form.Control required autoComplete="off"
                  type="date" name="dataAniversario"
                  value={dataAniversario}
                  onChange={this.mudancaDataAniversario}
                  className="input100data" />
              </Form.Group>
              <span className="focus-input100"></span>
            </div>

            <div className="wrap-input100 input100-select">
              <Form.Group controlId="formGridGenero">
                <Form.Label className="label-input100">Qual o seu gênero musical preferido?</Form.Label>
                <div>
                  <Form.Control as="select" className="selection-2" custom onChange={this.mudancaGenero.bind(this)}>
                    <option value="***">--- Gênero ---</option>
                    <option value="rock">Rock</option>
                    <option value="pop">Pop</option>
                    <option value="indie">Indie</option>
                    <option value="mpb">MPB</option>
                    <option value="sertanejo">Sertanejo</option>
                    <option value="rap">Rap</option>
                    <option value="samba">Samba</option>
                    <option value="pagode">Pagode</option>
                  </Form.Control>
                </div>
              </Form.Group>
              <span className="focus-input100"></span>
            </div>

            <div className="wrap-input100 input100-select">
              <Form.Group controlId="formGridInstrumento">
                <Form.Label className="label-input100">Qual instrumento você é o rockstar?</Form.Label>
                <div>
                  <Form.Control as="select" className="selection-2" custom onChange={this.mudancaInstrumento.bind(this)}>
                    <option value="***">--- Instrumento ---</option>
                    <option value="violao">Violão</option>
                    <option value="guitarra">Guitarra</option>
                    <option value="baixo">Baixo</option>
                    <option value="vocal">Vocal</option>
                    <option value="teclado">Teclado</option>
                    <option value="bateria">Bateria</option>
                    <option value="cavaco">Cavaco</option>
                    <option value="pandeiro">Pandeiro</option>
                  </Form.Control>
                </div>
              </Form.Group>
              <span className="focus-input100"></span>
            </div>

            <div className="wrap-input100 input100-select">
              <Form.Group controlId="formGridEstado">
                <Form.Label className="label-input100">Selecione o seu estado</Form.Label>
                <div>
                  <Form.Control as="select" className="selection-2" custom onChange={this.mudancaEstado.bind(this)}>
                    <option value="***">--- Estado ---</option>
                    <option value="ac">Acre</option>
                    <option value="al">Alagoas</option>
                    <option value="ap">Amapá</option>
                    <option value="am">Amazonas</option>
                    <option value="ba">Bahia</option>
                    <option value="ce">Ceará</option>
                    <option value="es">Espírito Santo</option>
                    <option value="go">Goiás</option>
                    <option value="ma">Maranhão</option>
                    <option value="mt">Mato Grosso</option>
                    <option value="ms">Mato Grosso do Sul</option>
                    <option value="mg">Minas Gerais</option>
                    <option value="pa">Pará</option>
                    <option value="pb">Paraíba</option>
                    <option value="pr">Paraná</option>
                    <option value="pe">Pernambuco</option>
                    <option value="pi">Piauí</option>
                    <option value="rj">Rio de Janeiro</option>
                    <option value="rn">Rio Grande do Norte</option>
                    <option value="rs">Rio Grande do Sul</option>
                    <option value="ro">Roraima</option>
                    <option value="sc">Santa Catarina</option>
                    <option value="sp">São Paulo</option>
                    <option value="se">Sergipe</option>
                    <option value="to">Tocantins</option>
                    <option value="df">Distrito Federal</option>
                  </Form.Control>
                </div>
              </Form.Group>
              <span className="focus-input100"></span>
            </div>

            <div className="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
              <Form.Group controlId="formGridDataAniversario">
                <Form.Label className="label-input100">Informe sua cidade</Form.Label>
                <Form.Control className=" div-input" required autoComplete="off"
                  type="text" name="cidade"
                  value={cidade}
                  onChange={this.mudancaCidade}
                  className="input100data" />
              </Form.Group>
              <span className="focus-input100"></span>
            </div>

            <div className="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
              <Form.Group controlId="formGridDataAniversario">
                <Form.Label className="label-input100">Informe seu Instagram</Form.Label>
                <Form.Control required autoComplete="off"
                  type="text" name="instagram"
                  value={instagram}
                  onChange={this.mudancaInstagram}
                  className="input100data" />
              </Form.Group>
              <span className="focus-input100"></span>
            </div>

            <div className="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
              <Form.Group controlId="formGridDataAniversario">
                <Form.Label className="label-input100">Informe seu Facebook</Form.Label>
                <Form.Control required autoComplete="off"
                  type="text" name="facebook"
                  value={facebook}
                  onChange={this.mudancaFacebook}
                  className="input100data" />
              </Form.Group>
              <span className="focus-input100"></span>
            </div>

            <div className="wrap-input100 validate-input" >
              <Form.Group controlId="formGridDataAniversario">
                <Form.Label className="label-input100">Informe seu número de telefone</Form.Label>
                <Form.Control required autoComplete="off"
                  type="text" name="telefone"
                  value={telefone}
                  onChange={this.mudancaTelefone}
                  className="input100data" />
              </Form.Group>
              <span className="focus-input100"></span>
            </div>
            <div className="container-contact100-form-btn">

              <Button className="contact100-form-btn" size="sm" variant="success" type="submit">
                Cadastrar
                </Button>

              {/* // mask="(xx) xxxx-xxxx" */}

            </div>
          </Form>
        </div>
      </div>
    );
  }
}

export default infoComp;