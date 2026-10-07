package modelo;

public class Cadastro {
    //criamos o molde, uma ficha de dados na memoria
    private int idcad;
    private String nomecad;
    private String cpf;
    private int idsexo;
    private String email;
    private String sexo;
    private int numCadastros;
    private int somaCodigos;
    private int numSexualidade;
    private String foto;
    
    public Cadastro() { //construtor vazio para ir preenchendo os dados um por um
    }
    
    public Cadastro(String nomecad, String cpf, String sexo, String email) {
        this.nomecad = nomecad;  //esse construtor é para quando já temos todos os dados e queremos criar um objeto de uma vez só
                                // ele pede todos os dados necessários para criar um objeto Cadastro, ao inves de um por um
        this.cpf = cpf;
        this.sexo = sexo; //this distingue a variavel do parametro, pois tem o mesmo nome
        this.email = email;        
    }

    // Getters e Setters
    public int getIdCad() { return idcad; }
    public void setIdCad(int idcad) { this.idcad = idcad; }

    public String getNomeCad() { return nomecad; }
    public void setNomeCad(String nomecad) { this.nomecad = nomecad; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public int getIdSexo() { return idsexo; }
    public void setIdSexo(int idsexo) { this.idsexo = idsexo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSexo() { return sexo; }
    public void SetSexo(String sexo) { this.sexo = sexo; }

    public int getTotalCadastros() { return numCadastros; }
    public void setTotalCadastros(int numCadastros) { this.numCadastros = numCadastros; }

    public int getSomaCodigos() { return somaCodigos; }
    public void setSomaCodigos(int somaCodigos) { this.somaCodigos = somaCodigos; }

    public int getNumSexualidade() { return numSexualidade; }
    public void setNumSexualidade(int numSexualidade) { this.numSexualidade = numSexualidade; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}

