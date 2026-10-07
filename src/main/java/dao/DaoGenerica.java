package dao;


//funciona como um manual de padronizacao, garante que cada DAO fale a mesma lingua dentro do projeto, cada DAO vai implementar a mesma interface, e cada DAO vai ter os mesmos metodos, mas cada um vai ter sua propria implementacao


import java.util.ArrayList;

public interface DaoGenerica<ObjetoGenerico> {
    public void inserir(ObjetoGenerico objt);
    public void alterar(ObjetoGenerico objt);
    public void excluir();
    public ArrayList<ObjetoGenerico> consultar();
    public ArrayList<ObjetoGenerico> dashboard();
}