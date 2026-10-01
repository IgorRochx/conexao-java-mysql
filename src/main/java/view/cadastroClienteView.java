/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.JOptionPane;
import dao.CadastroDao;
import modelo.Cadastro;


/**
 *
 * @author guil
 */
public class cadastroClienteView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(cadastroClienteView.class.getName());

    /**
     * Creates new form cadastroClienteView
     */
    public cadastroClienteView() {
        initComponents();
        configurarMascaras();
        jbSalvar4.addActionListener(this::jbSalvar4ActionPerformed);
        btnAlterar.addActionListener(this::btnAlterarActionPerformed);
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        btnPesquisar.addActionListener(this::btnPesquisarActionPerformed);
        btnNovo.addActionListener(this::btnNovoActionPerformed);
        
    }
         
     private void configurarMascaras() {
         jftCpf.setFormatterFactory(criarFabricaMascara("###.###.###-##")); // Mudar para Formatted Field nome: jftCpf
         jftDataNascimento.setFormatterFactory(criarFabricaMascara("##/##/####")); // Mudar para Formatted Field nome: jftDataNascimento
         jftTelefone.setFormatterFactory(criarFabricaMascara("(##) #####-####")); // Mudar para Formatted Field nome: jftTelefone
         jftCep.setFormatterFactory(criarFabricaMascara("#####-###"));
     }
    
     private DefaultFormatterFactory criarFabricaMascara(String mascara) {
         try {
             MaskFormatter mf = new MaskFormatter(mascara);
             mf.setPlaceholderCharacter('_');
             return new DefaultFormatterFactory(mf);
         } catch (ParseException e) {
             e.printStackTrace();
             return new DefaultFormatterFactory();
         }
     }
    private void buscarCep() {
        
        // Pega o texto digitado no campo CEP e remove tudo que NÃO for número.
        // A expressão "[^0-9]" significa "qualquer caractere que não seja de 0 a 9".
        // Assim, se o usuário digitar "85900-000", vira "85900000".
        String cep = jftCep.getText().replaceAll("[^0-9]", "");
        
        // Todo CEP brasileiro tem exatamente 8 dígitos.
        // Se não tiver, avisa o usuário e para a execução do método com "return".
        if(cep.length() != 8){
            JOptionPane.showMessageDialog(this, "Digite um CEP válido com 8 número.",
                    "CEP inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Cria o "cliente HTTP", que é quem sabe conversar com sites da internet.
            HttpClient cliente = HttpClient.newHttpClient();
            
            // Monta a requisição (o "pedido") que será enviado ao site.
            // A URL do ViaCEP segue sempre o padrão:
            // https://viacep.com.br/ws/SEUCEP/json/
            // O .GET() indica que queremos BUSCAR dados (e não enviar).
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                    .GET()
                    .build(); //Finaliza a montagem da requisição 
            
            // Envia a requisição e espera a resposta do site.
            // BodyHandlers.ofString() significa: "quero a resposta em formato de texto".
            HttpResponse<String> resposta = cliente.send(requisicao,
                    HttpResponse.BodyHandlers.ofString());
            
            // Pega só o "corpo" da resposta, que é o texto JSON com os dados.
            String json = resposta.body();
            
            // Quando o CEP não existe, o ViaCEP responde assim: {"erro": true}
            if (json.contains("\"erro\"")) {
                JOptionPane.showMessageDialog(this, "CEP não encontrado.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                limparEndereco();
                return;
            }
            
            // Agora extraímos cada informação do JSON
            jftRua.setText(pegarValor(json, "logradouro"));
            jftBairro.setText(pegarValor(json, "bairro"));
            jftCidade.setText(pegarValor(json, "localidade"));
            jftUf.setText(pegarValor(json, "uf"));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível buscar o CEP. Verifique sua conexão com a internet.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Extrai um valor de dentro do texto JSON
    private String pegarValor(String json, String campo) {
        // Monta o texto que vamos procurar. Para o campo "bairro",
        String procura = "\"" + campo + "\":";
        // indexOf devolve a POSIÇÃO onde o texto foi encontrado.
        // Se devolver -1, significa que não encontrou nada.
        int inicio = json.indexOf(procura);
        
        // Se o campo não existir no JSON, devolve texto vazio para não dar erro.
        if (inicio == -1) {
            return "";
        }
        
        inicio = json.indexOf("\"", inicio + procura.length()) + 1;
        int fim = json.indexOf("\"", inicio);
        
        return json.substring(inicio, fim);
    }
    
    // Usado quando o CEP não é encontrado, para apagar dados de uma
    // busca anterior que ficaram na tela.
    private void limparEndereco(){
        jftRua.setText("");
        jftBairro.setText("");
        jftCidade.setText("");
        jftUf.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jlTitudoCadastroCliente = new javax.swing.JLabel();
        jlIdCodigo = new javax.swing.JLabel();
        jtIdCodigo = new javax.swing.JTextField();
        jlNomeCompleto = new javax.swing.JLabel();
        jtNomeCompleto = new javax.swing.JTextField();
        jlCpf = new javax.swing.JLabel();
        jlDataNascimento = new javax.swing.JLabel();
        jlEmail = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jlTelefone = new javax.swing.JLabel();
        jlGenero = new javax.swing.JLabel();
        jcGenero = new javax.swing.JComboBox<>();
        jlEstadoCivil = new javax.swing.JLabel();
        jlRuaLogradouro = new javax.swing.JLabel();
        jlNumero = new javax.swing.JLabel();
        jcEstadoCivil = new javax.swing.JComboBox<>();
        jlBairro = new javax.swing.JLabel();
        jlCidade = new javax.swing.JLabel();
        jlUfEstado = new javax.swing.JLabel();
        jlCep = new javax.swing.JLabel();
        jlLimiteCredito = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        jftLimiteCredito = new javax.swing.JFormattedTextField();
        jftCep = new javax.swing.JFormattedTextField();
        jftCidade = new javax.swing.JFormattedTextField();
        jftUf = new javax.swing.JFormattedTextField();
        jftBairro = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jftRua = new javax.swing.JFormattedTextField();
        jftCpf = new javax.swing.JFormattedTextField();
        jftDataNascimento = new javax.swing.JFormattedTextField();
        jftTelefone = new javax.swing.JFormattedTextField();
        btnPesquisar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jbSalvar4 = new javax.swing.JButton();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlTitudoCadastroCliente.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jlTitudoCadastroCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitudoCadastroCliente.setText("Cadastro Cliente");

        jlIdCodigo.setText("ID/Código:");

        jtIdCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtIdCodigoKeyPressed(evt);
            }
        });

        jlNomeCompleto.setText("Nome Completo:");

        jtNomeCompleto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtNomeCompletoKeyPressed(evt);
            }
        });

        jlCpf.setText("CPF:");

        jlDataNascimento.setText("Data Nascimento:");

        jlEmail.setText("E-mail:");

        jtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtEmailKeyPressed(evt);
            }
        });

        jlTelefone.setText("Telefone:");

        jlGenero.setText("Gênero:");

        jcGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino", "Não Informado" }));
        jcGenero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcGeneroKeyPressed(evt);
            }
        });

        jlEstadoCivil.setText("Estado Civil:");

        jlRuaLogradouro.setText("Rua/Logradouro:");

        jlNumero.setText("Número:");

        jcEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solteiro(a)", "Casado(a)", "Viuvo(a)", "União Estável" }));
        jcEstadoCivil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcEstadoCivilKeyPressed(evt);
            }
        });

        jlBairro.setText("Bairro:");

        jlCidade.setText("Cidade:");

        jlUfEstado.setText("UF/Estado:");

        jlCep.setText("CEP:");

        jlLimiteCredito.setText("Limite de Crédito:");

        btnExcluir.setText("Excluir");

        jftLimiteCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jftLimiteCreditoKeyPressed(evt);
            }
        });

        jftCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jftCepFocusLost(evt);
            }
        });

        jftBairro.addActionListener(this::jftBairroActionPerformed);

        btnPesquisar.setText("Pesquisar");

        btnAlterar.setText("Alterar");

        btnNovo.setText("Limpar");

        jbSalvar4.setText("Salvar");
        jbSalvar4.addActionListener(this::jbSalvar4ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTitudoCadastroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlIdCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtIdCodigo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlNomeCompleto)
                            .addComponent(jtNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jftCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlCpf)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jftDataNascimento, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlEstadoCivil)
                            .addComponent(jcEstadoCivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlBairro)
                            .addComponent(jftBairro))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlEmail)
                                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlTelefone)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jftTelefone))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlGenero)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jftCep)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlCep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlNumero)
                                .addGap(93, 93, 93))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlRuaLogradouro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlUfEstado))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnNovo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPesquisar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnExcluir)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAlterar))
                                    .addComponent(jftRua))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbSalvar4)
                                    .addComponent(jftUf, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jftCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlCidade))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlLimiteCredito)
                            .addComponent(jftLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitudoCadastroCliente)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlIdCodigo)
                    .addComponent(jlNomeCompleto)
                    .addComponent(jlCpf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtIdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDataNascimento)
                    .addComponent(jlEmail)
                    .addComponent(jlTelefone)
                    .addComponent(jlGenero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEstadoCivil)
                    .addComponent(jlNumero)
                    .addComponent(jlCep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlBairro)
                    .addComponent(jlUfEstado)
                    .addComponent(jlRuaLogradouro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jftUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCidade)
                    .addComponent(jlLimiteCredito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jftCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnPesquisar)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovo)
                    .addComponent(jbSalvar4))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtIdCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtIdCodigoKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            jtNomeCompleto.requestFocus();
        }
    }//GEN-LAST:event_jtIdCodigoKeyPressed

    private void jcGeneroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcGeneroKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            jcEstadoCivil.requestFocus();
        }
    }//GEN-LAST:event_jcGeneroKeyPressed

    private void jcEstadoCivilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcEstadoCivilKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            jftCep.requestFocus();
        }
    }//GEN-LAST:event_jcEstadoCivilKeyPressed

    private void jftLimiteCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jftLimiteCreditoKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            btnExcluir.requestFocus();
        }
    }//GEN-LAST:event_jftLimiteCreditoKeyPressed

    private void jftCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jftCepFocusLost
         // trim() remove espaços em branco do início e fim.
        // isEmpty() verifica se sobrou algo escrito.
        // Só busca se o usuário realmente digitou alguma coisa.
        if (!jftCep.getText().trim().isEmpty()) {
            buscarCep();
        }
    }//GEN-LAST:event_jftCepFocusLost

    private void jtNomeCompletoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNomeCompletoKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER){
            jftCpf.requestFocus();
        }
    }//GEN-LAST:event_jtNomeCompletoKeyPressed

    private void jftBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jftBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jftBairroActionPerformed

    private void jtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtEmailKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            jftTelefone.requestFocus();
        }
    }//GEN-LAST:event_jtEmailKeyPressed

    private void jbSalvar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvar4ActionPerformed
        try {
        Cadastro cadastro = new Cadastro();
        cadastro.setNomeCad(jtNomeCompleto.getText().trim());
        cadastro.setCpf(jftCpf.getText().trim());
        cadastro.SetSexo(jcGenero.getSelectedItem().toString());
        cadastro.setEmail(jtEmail.getText().trim());

        CadastroDao cadastroDao = new CadastroDao();
        cadastroDao.inserir(cadastro);

        JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
        limparCampos();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        if (jtIdCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o ID/Código para alterar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cadastro cadastro = new Cadastro();
        cadastro.setIdCad(Integer.parseInt(jtIdCodigo.getText().trim()));
        cadastro.setNomeCad(jtNomeCompleto.getText().trim());
        cadastro.setCpf(jftCpf.getText().trim());
        cadastro.SetSexo(jcGenero.getSelectedItem().toString());
        cadastro.setEmail(jtEmail.getText().trim());

        CadastroDao cadastroDao = new CadastroDao();
        cadastroDao.alterar(cadastro);

        JOptionPane.showMessageDialog(this, "Cadastro alterado com sucesso!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao alterar cadastro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        if (jtIdCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o ID/Código para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(jtIdCodigo.getText().trim());
        int confirmacao = JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja excluir o cliente ID " + id + "?", 
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            CadastroDao cadastroDao = new CadastroDao();
            cadastroDao.excluirID(id);
            JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
            limparCampos();
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao excluir cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        String busca = jtNomeCompleto.getText().trim();
        CadastroDao cadastroDao = new CadastroDao();
        java.util.ArrayList<Cadastro> lista = cadastroDao.consultar(busca);

        if (!lista.isEmpty()) {
            Cadastro c = lista.get(0); // Exibe o primeiro encontrado
            jtIdCodigo.setText(String.valueOf(c.getIdCad()));
            jtNomeCompleto.setText(c.getNomeCad());
            jftCpf.setText(c.getCpf());
            jtEmail.setText(c.getEmail());
            jcGenero.setSelectedItem(c.getSexo());
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum cadastro encontrado com este nome.", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao buscar cadastro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
    limparCampos();
}

private void limparCampos() {
    jtIdCodigo.setText("");
    jtNomeCompleto.setText("");
    jftCpf.setText("");
    jftDataNascimento.setText("");
    jtEmail.setText("");
    jftTelefone.setText("");
    jcGenero.setSelectedIndex(0);
    jcEstadoCivil.setSelectedIndex(0);
    jftCep.setText("");
    jftRua.setText("");
    jftBairro.setText("");
    jftCidade.setText("");
    jftUf.setText("");
    jftLimiteCredito.setText("");
    jtNomeCompleto.requestFocus();
}
}
}
}








    }//GEN-LAST:event_jbSalvar4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new cadastroClienteView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JButton jbSalvar4;
    private javax.swing.JComboBox<String> jcEstadoCivil;
    private javax.swing.JComboBox<String> jcGenero;
    private javax.swing.JFormattedTextField jftBairro;
    private javax.swing.JFormattedTextField jftCep;
    private javax.swing.JFormattedTextField jftCidade;
    private javax.swing.JFormattedTextField jftCpf;
    private javax.swing.JFormattedTextField jftDataNascimento;
    private javax.swing.JFormattedTextField jftLimiteCredito;
    private javax.swing.JFormattedTextField jftRua;
    private javax.swing.JFormattedTextField jftTelefone;
    private javax.swing.JFormattedTextField jftUf;
    private javax.swing.JLabel jlBairro;
    private javax.swing.JLabel jlCep;
    private javax.swing.JLabel jlCidade;
    private javax.swing.JLabel jlCpf;
    private javax.swing.JLabel jlDataNascimento;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEstadoCivil;
    private javax.swing.JLabel jlGenero;
    private javax.swing.JLabel jlIdCodigo;
    private javax.swing.JLabel jlLimiteCredito;
    private javax.swing.JLabel jlNomeCompleto;
    private javax.swing.JLabel jlNumero;
    private javax.swing.JLabel jlRuaLogradouro;
    private javax.swing.JLabel jlTelefone;
    private javax.swing.JLabel jlTitudoCadastroCliente;
    private javax.swing.JLabel jlUfEstado;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtIdCodigo;
    private javax.swing.JTextField jtNomeCompleto;
    // End of variables declaration//GEN-END:variables
}
