package br.com.musicall.api.aplicacao;

import br.com.musicall.api.aplicacao.respostas.LoginEnum;
import br.com.musicall.api.dominios.Usuario;
import br.com.musicall.api.visoes.UsuarioAutenticado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoFunction extends Funcao{

    @Autowired
    private RegistroMedalhaFunction registroMedalhaFunction;

    @Autowired
    private MedalhaFunction medalhaFunction;

    public UsuarioAutenticado logar(Usuario usuario){
        Usuario usuarioEmVerificacao = this.usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).orElse(new Usuario());
        Integer idUsuario = usuarioEmVerificacao.getIdUsuario();

        if (idUsuario == null){
            return new UsuarioAutenticado(0, "", LoginEnum.USUARIO_SENHA_INCORRETO);
        }

        if (usuarioEmVerificacao.getInfoUsuario() == null || usuarioEmVerificacao.getRedeSocial() == null){
            return new UsuarioAutenticado(usuarioEmVerificacao.getIdUsuario(), "", LoginEnum.USUARIO_INCOMPLETO);
        }

        medalhaFunction.construtor(idUsuario);
        medalhaFunction.atualizaMedalha("infos");
        return new UsuarioAutenticado(idUsuario, "@musicall-token",LoginEnum.USUARIO_COMPLETO);

    }
}
