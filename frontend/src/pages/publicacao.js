import React from "react";
import api from "../api";
import { Form, Button } from "react-bootstrap";
import MyToast from '../componentes/MyToast';
import "../css/novaPublicacao.css";
import SideBarMedalha from "../componentes/SidebarMedalhas";


class Publicacao extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      texto: '',
      nome: '',
      selectedFile: null,
      mensagem: '',
      titulo: '',
      show: false
    };
    this.mudancaPublicacao = this.mudancaPublicacao.bind(this);
    this.submitPublicacao = this.submitPublicacao.bind(this);
  }

  async componentDidMount() {
    let userData2 = await api.get("/usuarios");
    this.setState({ nome: userData2.data.nome}
    );
  }

  submitPublicacao = event => {
    event.preventDefault();

    const publicacao = {
      texto: this.state.texto
    }

    console.log(this.state.texto);
    api.post("/publicacoes", publicacao)
      .then(response => {
        if (response.data != null) {
          this.setState({
            show: true,
            mensagem: 'Postagem sendo deletada!',
            titulo: 'Aceito'
          });
          setTimeout(() => this.setState({ show: false }), 5000);
          return this.props.history.push("/perfil/" + this.props.match.params.id);
        }
      })
  }

  onFileUpload = () => {

    const formData = new FormData();

    if (this.state.selectedFile === null) {
      this.setState({
        show: true,
        mensagem: 'Para importar primeiro selecione um arquivo!',
        titulo: 'Negado'
      });
      setTimeout(() => this.setState({ show: false }), 5000);
    } else {
      formData.append(
        "arquivo",
        this.state.selectedFile,
        this.state.selectedFile.name
      );

      api.post("/publicacoes/enviar", formData)
        .then(response => {
          if (response.data != null) {
            this.setState({
              show: true,
              mensagem: 'Publicação importada e realizada!',
              titulo: 'Negado'
            });
            setTimeout(() => this.setState({ show: false }), 5000);
            return this.props.history.push("/perfil/" + this.props.match.params.id);
          }
        });
    }
  };

  onFileChange = event => {
    this.setState({ selectedFile: event.target.files[0] });
  };

  mudancaPublicacao = event => {
    this.setState({
      texto: event.target.value
    });
  }

  render() {
    let id = this.props.match.params.id;

    let mensagem = this.state.mensagem;
    let titulo = this.state.titulo;
    let nome = this.state.nome;
    const { texto } = this.state;

    return (
      <>
        <SideBarMedalha id={id} nome={nome}/>
        <div className=" container-contact100Publi">
          <div className="wrap-contact100Publi">
            <span className="contact100Publi-form-title">
              Quer fazer um som? Conta mais!
              </span>

            <div className=" wrap-input100Publi validate-input" data-validate="Message is required">
              <div id={texto} style={{ "display": this.state.show ? "block" : "none" }}>
                <MyToast titulo={titulo} mensagem={mensagem} children={{ show: this.state.show }} />
              </div>
              <Form className="contact100Publi-form validate-form" onReset={this.resetPublicacao} onSubmit={this.submitPublicacao} id="publicacaoFormId">
                <Form.Group controlId="formGridPublicacao">
                  <Form.Control className=" div-publi" as="textarea" rows="3" required autoComplete="off"
                    type="test" name="texto"
                    value={texto}
                    onChange={this.mudancaPublicacao}
                    placeholder="Diga o que procura" />
                </Form.Group>
                <span className=" focus-input100Publi"></span>
                <Form.Group>
                  <Form.File className=" file-importado" id=" arquivoImportado" label="Clique aqui para importar um arquivo" onChange={this.onFileChange} />
                </Form.Group>
                <Button className=" button1" size="lg" variant="success" type="submit">
                  Publicar
                </Button>
                <Button className=" button2" size="lg" onClick={this.onFileUpload}>
                  Importar
                  </Button>

              </Form>
            </div>
          </div>
        </div>
      </>
    );
  }

}

export default Publicacao;