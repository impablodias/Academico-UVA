package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class WebControle {

    @Autowired
    private ProdutoDAO produtoDAO;

    @GetMapping("/web/produtos")
    public String exibirTela(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("listaP", produtoDAO.findAll());
        return "lista_produto";
    }

    @PostMapping("/web/produtos/salvar")
    public String salvarVeiculo(Produto produto, RedirectAttributes attributes) {
        if (produto.getModelo() == null || produto.getModelo().trim().isEmpty()) {
            attributes.addFlashAttribute("erro", "O campo Modelo é obrigatório.");
            return "redirect:/web/produtos";
        }
        String modelo = produto.getModelo().trim();
        if (modelo.length() < 2 || modelo.length() > 50) {
            attributes.addFlashAttribute("erro", "O Modelo deve ter entre 2 e 50 caracteres.");
            return "redirect:/web/produtos";
        }
        if (!modelo.matches("^[A-Za-z0-9À-ÿ\\s\\-\\.]+$")) {
            attributes.addFlashAttribute("erro", "O Modelo contém caracteres inválidos.");
            return "redirect:/web/produtos";
        }

        if (produto.getMarca() == null || produto.getMarca().trim().isEmpty()) {
            attributes.addFlashAttribute("erro", "O campo Marca é obrigatório.");
            return "redirect:/web/produtos";
        }
        String marca = produto.getMarca().trim();
        if (!marca.matches("^[A-Za-zÀ-ÿ\\s]{2,50}$")) {
            attributes.addFlashAttribute("erro", "A Marca deve conter apenas letras (2 a 50 caracteres).");
            return "redirect:/web/produtos";
        }

        if (produto.getAno() == null) {
            attributes.addFlashAttribute("erro", "O campo Ano é obrigatório.");
            return "redirect:/web/produtos";
        }
        if (produto.getAno() < 1900 || produto.getAno() > 2028) {
            attributes.addFlashAttribute("erro", "O Ano deve estar entre 1900 e 2028.");
            return "redirect:/web/produtos";
        }

        if (produto.getCor() == null || produto.getCor().trim().isEmpty()) {
            attributes.addFlashAttribute("erro", "O campo Cor é obrigatório.");
            return "redirect:/web/produtos";
        }
        String cor = produto.getCor().trim();
        if (!cor.matches("^[A-Za-zÀ-ÿ\\s]{2,30}$")) {
            attributes.addFlashAttribute("erro", "A Cor deve conter apenas letras (2 a 30 caracteres).");
            return "redirect:/web/produtos";
        }

        produto.setModelo(modelo);
        produto.setMarca(marca);
        produto.setCor(cor);

        if (produto.getId() == null) {
            Long proximoId = 1L;
            List<Produto> todos = produtoDAO.findAll();

            if (!todos.isEmpty()) {
                Long maxId = todos.stream()
                    .mapToLong(Produto::getId)
                    .max()
                    .getAsLong();
                proximoId = maxId + 1;
            }
            produto.setId(proximoId);
        }

        produtoDAO.save(produto);
        attributes.addFlashAttribute("mensagem", "Veículo salvo com sucesso!");
        return "redirect:/web/produtos";
    }

    @GetMapping("/web/produtos/excluir/{id}")
    public String excluirVeiculo(@PathVariable Long id, RedirectAttributes attributes) {
        produtoDAO.deleteById(id);
        attributes.addFlashAttribute("mensagem", "Veículo excluído com sucesso!");
        return "redirect:/web/produtos";
    }

    @GetMapping("/web/produtos/editar/{id}")
    public String editarVeiculo(@PathVariable Long id, Model model) {
        Optional<Produto> veiculo = produtoDAO.findById(id);
        if (veiculo.isPresent()) {
            model.addAttribute("produto", veiculo.get());
            model.addAttribute("listaP", produtoDAO.findAll());
            return "lista_produto";
        }
        return "redirect:/web/produtos";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(@RequestParam(name = "termo", defaultValue = "") String termo, Model model) {
        String termoLimpo = termo.trim();
        List<Produto> resultados = produtoDAO.buscarPorNomeOuCategoria(termoLimpo);
        model.addAttribute("produto", new Produto());
        model.addAttribute("listaP", resultados);
        model.addAttribute("termoPesquisado", termoLimpo);
        return "lista_produto";
    }
}