import React from "react";
import api from '../api';

import "../css/Medalhas.css";
import Side from "../componentes/SidebarMedalhas.js";

import Medal from "../componentes/MedalhaItem.js";
import trofeu from "../img/medalha/trofeu.png";
import ini from "../img/medalha/ini-icon.png";
import amad from "../img/medalha/amad-icon.png";
import profi from "../img/medalha/profi-icon.png";
import rock from "../img/medalha/rock-icon.png";

class Medalhas extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      regMedalhas: {
        regDataInicio: null,
        regTodasInfos: null,
        regNumPesquisas: null,
        regNumPublicacoes: null,
        regNumConvites: null,
      },
      nome: ''
    };
  }

  render() {
    let id = this.props.match.params.id;
    
    let nome = this.state.nome;

    const { regDataInicio, regTodasInfos, regNumPesquisas, regNumPublicacoes, regNumConvites } = this.state.regMedalhas;

    const medalhas = (
      <div>
        {/* <h2>Medalha de tempo</h2> */}
        {
          regDataInicio === 0 ?
            <Medal titulo='Engajado' texto='Comece a usar a plataforma' emblema='Iniciante' /> : regDataInicio === 1 ?
              <Medal titulo='Engajado' texto='Permaneceu por mais de 24h na plataforma' emblema='Amador' /> : regDataInicio === 2 ?
                <Medal titulo='Engajado' texto='Use ainda mais a plataforma' emblema='Profissional' /> :
                <Medal titulo='Engajado' texto='Três anos passaram rápido!' emblema='Rockstar' />
        }
        {/* <h2>Medalha de infos</h2> */}
        {
          regTodasInfos === false ?
            <Medal titulo='Harmonia' texto='Cadastre todas as informações' emblema='Iniciante' /> :
            <Medal titulo='Harmonia' texto='Cadastrou todas as informações' emblema='Rockstar' />
        }
        {/* <h2>Medalha de Pesquisas</h2> */}
        {
          regNumPesquisas === 0 ?
            <Medal titulo='Procurando' texto='Comece a fazer pesquisas' emblema='Iniciante' /> : regNumPesquisas === 1 ?
              <Medal titulo='Procurando' texto='Continue pesquisando' emblema='Amador' /> : regNumPesquisas === 2 ?
                <Medal titulo='Procurando' texto='Continue pesquisando' emblema='Profissional' /> :
                <Medal titulo='Procurando' texto='Pesquisou bastante?' emblema='Rockstar' />

        }
        {/* <h2>Medalha de Publicacoes</h2> */}
        {
          regNumPublicacoes === 0 ?
            <Medal titulo='Musicista' texto='Faça publicações para desbloquear essa conquista' emblema='Iniciante' /> : regNumPublicacoes === 1 ?
              <Medal titulo='Musicista' texto='Faça ainda mais publicações' emblema='Amador' /> : regNumPublicacoes === 2 ?
                <Medal titulo='Musicista' texto='Faça ainda mais publicações' emblema='Profissional' /> :
                <Medal titulo='Musicista' texto='Fez diversas publicações na plataforma' emblema='Rockstar' />

        }
        {/* <h2>Medalha de Convites</h2>     */}
        {
          regNumConvites === 0 ?
            <Medal titulo='Chama' texto='Envie convites para outros usuários' emblema='Iniciante' /> : regNumConvites === 1 ?
              <Medal titulo='Chama' texto='Continue convidando' emblema='Amador' /> : regNumConvites === 2 ?
                <Medal titulo='Chama' texto='Continue convidando' emblema='Profissional' /> :
                <Medal titulo='Chama' texto='Chamou geral!' emblema='Rockstar' />

        }
      </div>
    )
    return (
      <div className="geral">
        <div className="sidebar">
          <Side id={id} nome={nome}/>
        </div>
        <div className="descricao-header">
          <img className="icon-descricao" src={trofeu}></img>
          <div className="descricao">Medalhas MusicAll
          
          <div className="descricao-texto">Conquiste medalhas e alcance o nível Rockstar!
          <br></br> Isso pode te ajudar a ganhar a confiança e entrar no ritmo de novos usuários ;)
          </div><div className="descricao-niveis">
          <br></br> Nível de medalhas:
          <br></br>
          <img className="descricao-icons" src={ini}></img> Iniciante&nbsp;&nbsp;&nbsp;&nbsp;
          <img className="descricao-icons" src={amad}></img> Amador&nbsp;&nbsp;&nbsp;&nbsp;
          <img className="descricao-icons" src={profi}></img>  Profissional&nbsp;&nbsp;&nbsp;&nbsp;
          <img className="descricao-icons" src={rock}></img>  Rockstar
          </div>

          </div>

        </div>
        <div className="medalhas-content">

          <div className="titulo">Conquistas adquiridas</div>

          {medalhas}
        </div>
      </div>
    );
  }

  async componentDidMount() {
    
    let userData2 = await api.get("/usuarios");
    this.setState({ nome: userData2.data.nome}
    );
    
    let userData = await api.get("/usuarios/medalhas");
    this.setState({
      regMedalhas: {
        regDataInicio: userData.data[0].regDataInicio,
        regTodasInfos: userData.data[0].regTodasInfos,
        regNumPesquisas: userData.data[0].regNumPesquisas,
        regNumPublicacoes: userData.data[0].regNumPublicacoes,
        regNumConvites: userData.data[0].regNumConvites
      }
    }
    );

  }
}

export default Medalhas;