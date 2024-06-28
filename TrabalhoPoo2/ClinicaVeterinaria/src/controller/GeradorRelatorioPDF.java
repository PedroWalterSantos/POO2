package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GeradorRelatorioPDF {

    public void gerarRelatorioSimplesPDF(String texto) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Relatorio.pdf"));
            document.open();
            document.add(new Paragraph(texto));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioPorDataPDF(String data) {
        // Exemplo simples, adaptar conforme sua lógica e estrutura de banco de dados
        String texto = "Relatório por data: " + data;

        // Chamar método do DAO para buscar os dados por data
        ServicoDAO servicoDAO = new ServicoDAOImpl(ConexaoBD.conectar());
        // Exemplo: List<ServicoModel> servicos = servicoDAO.listarServicosPorData(data);

        // Construir texto do relatório
        StringBuilder builder = new StringBuilder();
        builder.append("Relatório de Serviços por Data: ").append(data).append("\n\n");
        for (ServicoModel servico : servicos) {
            builder.append("ID: ").append(servico.getIdServico()).append("\n");
            builder.append("Nome: ").append(servico.getNomeServico()).append("\n");
            builder.append("Descrição: ").append(servico.getDescricaoServico()).append("\n");
            builder.append("Preço: ").append(servico.getPreco()).append("\n\n");
        }

        gerarRelatorioSimplesPDF(builder.toString());
    }

    public void gerarRelatorioPorAnimalPDF(String animal) {
        // Implementar lógica para gerar relatório por animal
    }

    public void gerarRelatorioPorServicoPDF(String servico) {
        // Implementar lógica para gerar relatório por serviço
    }
}
