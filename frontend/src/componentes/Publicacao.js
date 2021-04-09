import React from "react";
import PropTypes from "prop-types";
import api from "../api";
import MyToast from '../componentes/MyToast';
import "../css/Publicacao.css";
import Invite from "../img/Publicacao/Invite.svg";



class Public extends React.Component {
  
  constructor(props) {
    super(props);
    this.state = {
      mensagem: '',
      titulo: '',
      show: false
    };
  }
  
  curtirPublicacao = (idPublicacao) =>{
    api({
      method: 'post',
      url: "/home/convites",
      headers: {}, 
      data: {
        "idConvidado": idPublicacao,
      }}).then(response => {
        console.log(response);
          if(response.data != null){
            this.setState({
              show: true,
              mensagem: 'Convite enviado!',
              titulo: 'Aceito'
            });
            setTimeout(() => this.setState({show: false}), 5000);  
          }
        }).catch((error) => {
          if (error.response.status === 400) {
            this.setState({
              show: true,
              mensagem: 'Convite já enviado. Verifique sua aba de convites',
              titulo: 'Negado'
            });
            setTimeout(() => this.setState({show: false}), 5000);
          } else if (error.response.status === 404) {
            this.setState({
              show: true,
              mensagem: 'Não é possível convidar a si mesmo',
              titulo: 'Negado'
            });
            setTimeout(() => this.setState({show: false}), 5000);
          }else {
            this.setState({
              show: true,
              mensagem: 'Erro desconhecido',
              titulo: 'Erro'
            });
            setTimeout(() => this.setState({show: false}), 5000);
          }
        });
    
  }

  render() {
    const { id, usuario, nome, texto, isLoading } = this.props;

    let mensagem = this.state.mensagem;
    let titulo = this.state.titulo;

    const publicacaoDetails = (
      <div>
        <div id={id}>
          <div id="content">
            <div className="feed">
              <div className="feed-item blog">
                <div className="icon-holder">
                  <div className="icon">
                    
                  </div>
                </div>
                <div style={{"display": this.state.show ? "block" : "none"}}>
                  <MyToast titulo = {titulo} mensagem = {mensagem} children = {{show:this.state.show}}/>  
                </div>
                <div className="text-holder col-3-5">
                  <div className="feed-title">{nome}</div>
                  <div className="feed-description">{texto}</div>
                </div>
                <div className="icon-invite">
                  <i className="fa fa-ellipsis-v" id="invite">
                    <img onClick={this.curtirPublicacao.bind(this, usuario)} src={Invite} />
                  </i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    const loadingMessage = <span>Carregando...</span>;

    return (
      <div>
        { isLoading ? loadingMessage : publicacaoDetails}
      </div>
    );
  }
}

Public.propTypes = {
  id: PropTypes.number,
  id: PropTypes.number,
  nome: PropTypes.string,
  texto: PropTypes.string,
  isLoading: PropTypes.bool
};

export default Public;