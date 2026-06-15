package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    if (produto.getId() == null) {
        Long proximoId = 1L;
        java.util.List<Produto> todos = produtoDAO.findAll();
        
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
}