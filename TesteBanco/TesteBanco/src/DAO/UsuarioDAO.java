package DAO;

import DTO.UsuarioDTO;
import VIEW.TelaPrincipal;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar(UsuarioDTO objusuarioDTO) {
        String sql = "select * from tb_usuarios where login = ? and senha = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objusuarioDTO.getLogin_usuario());
            pst.setString(2, objusuarioDTO.getSenha_usuario());

            rs = pst.executeQuery();
            if (rs.next()) {
                String perfil = rs.getString(5);
                System.out.println(perfil);

                if (perfil.equals("admin")) {
                    TelaPrincipal principal = new TelaPrincipal();;
                    principal.setVisible(true);
                  //  TelaPrincipal.MenuRel.setEnabled(true);
                   // TelaPrincipal.subMenuUsuarios.setEnabled(true);
                   // TelaPrincipal.lblUsuarioPrincipal.setText(rs.getString(2));
                  //  TelaPrincipal.lblUsuarioPrincipal.setForeground(Color.RED);
                    conexao.close();

                } else {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                   // principal.lblUsuarioPrincipal.setText(rs.getString(2));
                   // TelaPrincipal.lblUsuarioPrincipal.setForeground(Color.BLUE);
                    conexao.close();

                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha invalidos");
            }

        } catch (Exception e) {      
            JOptionPane.showMessageDialog(null, "** tela Login ***" + e);

        }
    }
}