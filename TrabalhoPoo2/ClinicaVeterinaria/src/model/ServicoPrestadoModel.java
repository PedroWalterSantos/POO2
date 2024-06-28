package model;



public class ServicoPrestadoModel {
    private int idServicoPrestado;
    private String observacoes;
    private double valor;
    private String dataServicoPrestado;
    private int idServico;
    private int idAnimal;

    // Construtor
    public ServicoPrestadoModel(int idServicoPrestado, String observacoes, double valor, String dataServicoPrestado, int idServico, int idAnimal) {
        this.idServicoPrestado = idServicoPrestado;
        this.observacoes = observacoes;
        this.valor = valor;
        this.dataServicoPrestado = dataServicoPrestado;
        this.idServico = idServico;
        this.idAnimal = idAnimal;
    }

    // Getters e setters
    public int getIdServicoPrestado() {
        return idServicoPrestado;
    }

    public void setIdServicoPrestado(int idServicoPrestado) {
        this.idServicoPrestado = idServicoPrestado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataServicoPrestado() {
        return dataServicoPrestado;
    }

    public void setDataServicoPrestado(String dataServicoPrestado) {
        this.dataServicoPrestado = dataServicoPrestado;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
}
