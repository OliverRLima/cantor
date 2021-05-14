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
    //
    @POST("cadastrar/dados/{idUsuario}")
    fun cadastrarInfo(@Body dados: Dados, @Path("id") id:Int): Call<Dados>

    @POST("publicacoes")
    fun fazerPublicacao(@Body publicacao: Publicacao, @Header ("Authorization") token: String): Call<Void>

    @POST("convites/{idUsuario}/{idConvidado}")
    fun enviarConvite(@Path ("idUsuario") idUsuario: Int, @Path("idConvidado") idConvidado: Int, @Header ("Authorization") token: String): Call<Void>

    @GET("dados/{idUsuario}")
    fun getDadosUsuario(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<Dados>

    @GET("dados/{idUsuario}")
    fun getPublicacoesUsuario(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<List<PublicacaoApi>>

    @GET("medalhas/{idUsuario}")
    fun getMedalhasUsuario(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<MedalhaApi>

    @GET("convites/recebidos/{idUsuario}")
    fun getConvitesRecebidos(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<List<ConviteRecebidoApi>>

    @GET("convites/enviados/{idUsuario}")
    fun getConvitesEnviados(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<List<ConviteEnviadoApi>>

    @GET("publicacoes/pesquisar/{idUsuario}/{chave}/{valor}")
    fun getPublicacoesCidade(@Path("idUsuario") idUsuario: Int, @Path("chave") chave: String, @Path("valor") valor: String, @Header ("Authorization") token: String): Call<List<PublicacaoApi>>

    @PUT("dados/alterar/{idUsuario}")
    fun alterarDadosUsuario(@Path("idUsuario") idUsuario: Int, @Body dados: Dados, @Header ("Authorization") token: String): Call<Void>

    @PUT("dados/usuario/{idUsuario}")
    fun alterarSenha(@Path("idUsuario") idUsuario: Int, @Body usuarioSenha: UsuarioSenha, @Header ("Authorization") token: String): Call<Void>

    @PUT("convites/{idConvite}")
    fun alterarVisibilidadeConvite(@Path("idConvite") idConvite: Int, @Header ("Authorization") token: String): Call<Void>

    @DELETE("publicacoes/{idPublicacao}")
    fun deletarPublicacao(@Path("idPublicacao") idPublicacao: Int, @Header ("Authorization") token: String): Call<Void>

    @DELETE("dados/usuario/{idUsuario}")
    fun deletarUsuario(@Path("idUsuario") idUsuario: Int, @Header ("Authorization") token: String): Call<Void>

}