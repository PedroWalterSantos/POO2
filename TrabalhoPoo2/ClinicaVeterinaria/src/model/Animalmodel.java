package model;

public class Animalmodel {
    private int idAnimal;
    private String nome;
    private int idade;
    private String sexo;
    private String raca;
    private String especie;
    private double peso;
    private int idProprietario;

    public Animalmodel(int idAnimal,String nome, int idade, String sexo, String raca, String especie, double peso, int idProprietario) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.raca = raca;
        this.especie = especie;
        this.peso = peso;
        this.idProprietario = idProprietario;
    }
    
    public Animalmodel(String nome, int idade, String sexo, String raca, String especie, double peso, int idProprietario) {
        
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.raca = raca;
        this.especie = especie;
        this.peso = peso;
        this.idProprietario = idProprietario;
    }
    
    

  



	// Getters and Setters
    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }
}
