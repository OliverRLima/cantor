import React from "react";
import PropTypes from "prop-types";
import api from '../api';
import MyToast from '../componentes/MyToast';
import "../css/PublicacaoPerfil.css";
import Lixeira from "../img/lixeira.svg";

class PublicPerfil extends React.Component {
  
  constructor(props) {
    super(props);
    this.state = {
      mensagem: '',
      titulo: '',
      show: false
    };
  }

  deletarPublicacao = (idPublicacao) =>{
    api.delete("/publicacoes/" + idPublicacao)
         .then(response => {
            if(response.data != null){
              this.setState({
                  show: true,
                  mensagem: 'Postagem sendo deletada!',
                  titulo: 'Aceito'
              });
              setTimeout(() => this.setState({show: false}), 5000);
            }
         });
  }

  render() {
    const { id, nome, texto, isLoading } = this.props;

    let mensagem = this.state.mensagem;
    let titulo = this.state.titulo;

    const publicacaoDetails = (
        <div>
          <div id={id}>
            <div  className="content-perfil">
              <div className="feed">
                <div className="feed-item blog">
                  <div className="icon-holder">
                    <div className="icon">
                      
                    </div>
                    <div id={texto} style={{"display": this.state.show ? "block" : "none"}}>
                      <MyToast titulo = {titulo} mensagem = {mensagem} children = {{show:this.state.show}}/>  
                    </div>

                  </div>
                  <div className="text-holder col-3-5">
                    <div className="feed-title">{nome}</div>
                    <div className="feed-description">{texto}</div>
                  </div>
                  <div className="icon-lixeira">
                    <i className="fa fa-ellipsis-v" id="lixeira">
                      <img onClick={this.deletarPublicacao.bind(this, id)} src={Lixeira} />
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

PublicPerfil.propTypes = {
  id: PropTypes.number,
  nome: PropTypes.string,
  texto: PropTypes.string,
  isLoading: PropTypes.bool
};

export default PublicPerfil;