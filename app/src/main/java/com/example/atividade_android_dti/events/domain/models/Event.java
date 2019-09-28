package com.example.atividade_android_dti.events.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    @SerializedName("data")
    @Expose
    private Long data;

    @SerializedName("rotaImagem")
    @Expose
    private String rotaImagem;

    public Event(String id, String nome, String descricao, long data, String rotaImagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.rotaImagem = rotaImagem;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getDate() {
        return data;
    }

    public String getRotaImagem() {
        return rotaImagem;
    }
}
