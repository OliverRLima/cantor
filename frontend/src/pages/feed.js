import React from "react";
import api from '../api';

import "../css/Publicacao.css";
import "../css/Feed.css";
import Nav from "../componentes/Navbar.js";
import Publicacao from "../componentes/Publicacao.js";


async function pesquisar(event) {
  if (event.key === 'Enter') {

    let texto = this.state.texto;
    let valorTexto = texto.toLocaleLowerCase();

    if (valorTexto === "rock" || valorTexto === "pop" || valorTexto === "indie" || valorTexto === "mpb" ||
      valorTexto === "sertanejo" || valorTexto === "rap" || valorTexto === "samba" || valorTexto === "pagode") {

      await api.get("/home/publicacoes/genero/" + valorTexto)
        .then(response => response.data)
        .then((data) => {
          if (data === undefined) {
            console.log('genero undefined');
          } else if (data === []) {
            alert("Não foi encontrado resultados! Tente de outra forma")
          } else {
            this.setState({ publicacoes: [] });
            this.setState({ publicacoes: data });
          }
        }).catch((error) => {
          if (error.response.status === 204) {
            alert("Não foi encontrado resultados! Tente de outra forma")
          } else if (error.response.status === 400) {
            alert("Não foi encontrado resultados! Tente de outra forma")
          } else {
            alert("Erro desconhecido")
          }
        });


    } else if (valorTexto === "violão" || valorTexto === "guitarra" || valorTexto === "baixo" || valorTexto === "vocal" ||
      valorTexto === "teclado" || valorTexto === "bateria" || valorTexto === "cavaco" || valorTexto === "pandeiro") {

      if (valorTexto === "violão") {
        valorTexto = "violao";
      }

      await api.get("/home/publicacoes/instrumento/" + valorTexto)
        .then(response => response.data)
        .then((data) => {
          if (data === undefined) {
            console.log("instrumento undefined");
          } else if (data === []) {
            alert("Não foi encontrado resultados! Tente de outra forma")
          } else {
            this.setState({ publicacoes: [] });
            this.setState({ publicacoes: data });
          }

        }).catch((error) => {
          if (error.response.status === 204) {
            alert("Não foi encontrado resultados! Tente de outra forma")
          } else if (error.response.status === 400) {
            alert("Não foi encontrado resultados! Tente de outra forma")
          } else {
            alert("Erro desconhecido")
          }
        });

    } else if (valorTexto === "ac" || valorTexto === "al" || valorTexto === "ap" || valorTexto === "am" || valorTexto === "ba" ||
      valorTexto === "ce" || valorTexto === "es" || valorTexto === "go" || valorTexto === "ma" || valorTexto === "mt" ||
      valorTexto === "ms" || valorTexto === "mg" || valorTexto === "pa" || valorTexto === "pb" || valorTexto === "pr" ||
      valorTexto === "pe" || valorTexto === "pi" || valorTexto === "rj" || valorTexto === "rn" || valorTexto === "rs" ||
      valorTexto === "ro" || valorTexto === "sc" || valorTexto === "sp" || valorTexto === "se" || valorTexto === "to" || valorTexto === "df") {

      await api.get("/home/publicacoes/estado/" + valorTexto)
        .then(response => response.data)
        .then((data) => {
          if (data === undefined) {
            console.log("estado undefined");
          } else if (data === []) {
            alert("Não foi encontrado resultados! Tente de outra forma");
          } else {
            this.setState({ publicacoes: [] });
            this.setState({ publicacoes: data });
          }
        }).catch((error) => {
          if (error.response.status === 204) {
            alert("Não foi encontrado resultados! Tente de outra forma");
          } else if (error.response.status === 400) {
            alert("Não foi encontrado resultados! Tente de outra forma");
          } else if (error.response.status === 404) {
            alert("Não foi encontrado resultados! Tente de outra forma");
          } else {
            alert("Erro desconhecido");
          }
        });

    } else {

      if (valorTexto === "são paulo") {
        valorTexto = "saopaulo";
      }

      await api.get("/home/publicacoes/cidade/" + valorTexto)
        .then(response => response.data)
        .then((data) => {
          if (data === undefined) {
            console.log("cidade undefined");
          } else if (data === []) {
            alert("Não foi encontrado resultados! Tente de outra forma");
          } else {
            this.setState({ publicacoes: [] });
            this.setState({ publicacoes: data });
          }
        }).catch((error) => {
          if (error.response.status === 204) {
            alert("Não foi encontrado resultados! Tente de outra forma");
          } else if (error.response.status === 400) {
            alert("Não foi encontrado resultados! Tente de outra forma");
          } else if (error.response.status === 404) {
            alert("Não foi encontrado resultados! Tente de outra forma");
          } else {
            alert("Erro desconhecido");
          }
        });

    }

  }
}


class Feed extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      publicacoes: ['a'],
      texto: '',
      nome: ''
    };
  }

  async componentDidMount() {

    await api.get("/usuarios")
      .then(response => response.data)
      .then((data) => {
        if (data === undefined) {
        } else if (data === []) {
        } else {
          this.setState({ nome: data });
        }
      }).catch((error) => {
        if (error.response.status === 400) {

        } else {

        }
      });
  }

  render() {

    let id = this.props.match.params.id;
    const { nome } = this.state.nome;
    return (
      <div className="corpo">
        <div>
          <Nav id={id} nome={nome} />
        </div>
        <input class="search__input" type="text" id="nome" onInput={(e) => this.setState({ texto: e.target.value })} onKeyPress={pesquisar.bind(this)} placeholder="O que você está procurando?"></input>
        { typeof this.state.publicacoes === undefined || this.state.publicacoes === null || this.state.publicacoes[0] === 'a' ?
          <h2 className="div-resultado-feed">Ache o que você procura :D <br /><br />
          Na barra de pesquisa, busque por gênero musical, instrumentos ou estado de preferência <br />
          para achar alguém que entre no seu ritmo! <br /> <br />
          Exemplo de pesquisa por instrumento: Bateria </h2> :
          this.state.publicacoes === [] || this.state.publicacoes === "" || this.state.publicacoes.length === 0 ?
            <h2 className="div-resultado-feed">Sem resultados! Tente novamente :(</h2> :
            this.state.publicacoes.map((i) => (
              <Publicacao key={i.idPublicacao} isLoading={false} id={i.idPublicacao} usuario={i.idUsuario} nome={i.nome} texto={i.publicacao} />
            ))
        }
      </div>
    );
  }

}

export default Feed;