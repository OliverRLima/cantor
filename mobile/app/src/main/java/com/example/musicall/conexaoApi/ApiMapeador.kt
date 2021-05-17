package com.example.musicall.conexaoApi

import com.example.musicall.conexaoApi.modelos.*
import retrofit2.Call
import retrofit2.http.*

interface ApiMapeador {

    @POST("auth")
    fun logar(@Body usuario: Usuario): Call<UsuarioApi>
    // 200 - login realizado
    // 400 - campos do objeto enviado estão inválidos
    // 404 - usuário e senha não encontrados

    @POST("cadastrar")
    fun cadastrar(@Body usuario: UsuarioCadastro): Call<UsuarioCadastradoApi>
    // 201 - cadastro realizado
    // 400 - usuario já cadastrado ou campos inválidos

    @POST("cadastrar/dados/{idUsuario}")
    fun cadastrarInfo(@Body dados: Dados, @Path("idUsuario") idUsuario:Int): Call<Dados>
    // 201 - cadastro realizado
    // 400 - campos inválidos

    @POST("publicacoes/{idUsuario}")
    fun fazerPublicacao(@Body publicacao: Publicacao, @Header ("Authorization") token: String, @Path("idUsuario") idUsuario:Int): Call<Void>
    // 200 - publicado
    // 400 - idUsuario não corresponde a nenhum usuário
    // 403 - Token inválido

    @POST("convites/{idUsuario}/{idConvidado}")
    fun enviarConvite(@Path ("idUsuario") idUsuario: Int, @Path("idConvidado") idConvidado: Int, @Header ("Authorization") token: String): Call<Void>
    // 200 - convite enviado
    // 400 - convite já enviado
    // 403 - Token inválido

    @GET("auth")
    fun verificaToken(@Header ("Authorization") token: String): Call<Void>
    // 200 - token válido
    // 403 - Token inválido

    @GET("dados/{idUsuario}")
    fun getDadosUsuario(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<DadosApi>
    // 200 - retorna os dados do usuário
    // 403 - Token inválido
    // 404 - idUsuario não encontrado

    @GET("publicacoes/{idUsuario}")
    fun getPublicacoesUsuario(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<List<PublicacaoUsuarioApi>>
    // 200 - devolve a lista de publicacoes
    // 403 - Token inválido
    // 404 - idUsuario não corresponde a nenhum usuário

    @GET("medalhas/{idUsuario}")
    fun getMedalhasUsuario(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<MedalhaApi>

    @GET("convites/recebidos/{idUsuario}")
    fun getConvitesRecebidos(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<List<ConviteRecebidoApi>>

    @GET("convites/enviados/{idUsuario}")
    fun getConvitesEnviados(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<List<ConviteEnviadoApi>>

    @GET("publicacoes/pesquisar/{idUsuario}/{valor}")
    fun getPublicacoesFeed(@Path("idUsuario") idUsuario: Int, @Path("valor") valor: String, @Header ("Authorization") token: String): Call<List<PublicacaoApi>>
    // 200 - buscou e encontrou publicacoes
    // 403 - Token inválido
    // 404 - buscou e não encontrou publicacoes

    @PUT("dados/alterar/{idUsuario}")
    fun alterarDadosUsuario(@Path("idUsuario") idUsuario: Int, @Body dados: Dados, @Header ("Authorization") token: String): Call<Void>
    // 200 - dados alterados
    // 400 - campos inválidos
    // 403 - Token inválido
    // 404 - usuario não encontrado

    @PUT("dados/usuario/{idUsuario}")
    fun alterarSenha(@Path("idUsuario") idUsuario: Int, @Body usuarioSenha: UsuarioSenha, @Header ("Authorization") token: String): Call<Void>
    // 200 - senha alterada
    // 400 - idUsuario não corresponde a nenhum usuário
    // 403 - token inválido

    @PUT("convites/{idConvite}")
    fun alterarVisibilidadeConvite(@Path("idConvite") idConvite: Int, @Header ("Authorization") token: String): Call<Void>

    @DELETE("publicacoes/{idPublicacao}")
    fun deletarPublicacao(@Path("idPublicacao") idPublicacao: Int, @Header ("Authorization") token: String): Call<Void>
    // 200 - publicacao deletada
    // 400 - idPublicacao não corresponde a nenhuma publicação
    // 403 - token inválido

    @DELETE("dados/usuario/{idUsuario}")
    fun deletarUsuario(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<Void>
    // 200 - conta excluida
    // 403 - token inválido
    // 404 - idUsuario não encontrado

}