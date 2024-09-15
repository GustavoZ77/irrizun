package zuun.tech.budget.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zuun.tech.budget.domain.Quotation;
import zuun.tech.budget.domain.QuotationDetail;
import zuun.tech.budget.service.CustomerService;
import zuun.tech.budget.service.ProductService;
import zuun.tech.budget.service.QuotationService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/quotations")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listQuotations(Model model) {
        model.addAttribute("quotations", quotationService.findAll());
        return "quotation/list";
    }

    @GetMapping("/new")
    public String newQuotationForm(Model model) {

        Quotation quotation = new Quotation();

        // Asegúrate de inicializar la lista de QuotationDetails si está vacía
        if (quotation.getQuotationDetails() == null || quotation.getQuotationDetails().isEmpty()) {
            System.out.println("---------------------------------------------------------------------------------");
            List<QuotationDetail> quotationDetailsSet = new ArrayList<>();
//            quotationDetailsSet.add(QuotationDetail.builder().product(Product.builder().salesPrice(2.5).basePrice(1.1).code("sadfas").description("fgsfdgsfd").id(2L).build()).quantity(2).build());
            quotation.setQuotationDetails(quotationDetailsSet);
        }

        model.addAttribute("quotation", quotation);
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("products", productService.findAll());   
        return "quotation/form";
    }

    @PostMapping("/save")
    public String saveQuotation(@ModelAttribute("quotation") Quotation quotation,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("products", productService.findAll());
            return "quotation-form";
        }

        // Verifica si la lista de detalles está vacía e inicializa si es necesario
        if (quotation.getQuotationDetails() == null) {
            quotation.setQuotationDetails(new ArrayList<>());
        }

        List<QuotationDetail> list = quotation.getQuotationDetails().stream().map(d -> {
            d.product = productService.findById(d.getProduct().getId());
            return d;
        }).toList();

        quotation.setQuotationDetails(list);

        // Calcular el monto total de la cotización basado en los detalles
        double totalAmount = quotation.getQuotationDetails().stream()
                .mapToDouble(detail -> detail.getProduct().getSalesPrice() * detail.getQuantity())
                .sum();
        quotation.setAmount(totalAmount);

        // Guardar la cotización
        quotationService.save(quotation);

        return "redirect:/quotations";
    }

    @GetMapping("/view/{id}")
    public String viewQuotation(@PathVariable Long id, Model model) {
        Optional<Quotation> quotation = quotationService.findById(id);
        if (quotation.isPresent()) {
            model.addAttribute("quotation", quotation.get());
            return "quotation/view";
        }
        return "redirect:/quotations";
    }

    @GetMapping("/edit/{id}")
    public String editQuotationForm(@PathVariable Long id, Model model) {
        Optional<Quotation> quotation = quotationService.findById(id);
        if (quotation.isPresent()) {
            model.addAttribute("quotation", quotation.get());
            return "quotation/form";
        }
        return "redirect:/quotations";
    }

    @PostMapping("/delete/{id}")
    public String deleteQuotation(@PathVariable Long id) {
        quotationService.deleteById(id);
        return "redirect:/quotations";
    }

    @PostMapping("/exportToPdf")
    public ResponseEntity<ByteArrayResource> exportToPdf(@RequestParam Long id, HttpServletResponse response) throws IOException, DocumentException {
        // Obtener la cotización por ID (reemplaza esto con tu lógica real)
        Optional<Quotation> quotation = quotationService.findById(id);

        // Crear documento PDF
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.add(new Paragraph("Detalles de la Cotización"));
        document.add(new Paragraph("ID: " + quotation.get().getId()));
        document.add(new Paragraph("Fecha: " + quotation.get().getDate()));
        document.add(new Paragraph("Cliente: " + quotation.get().getCustomer().getName()));
        document.add(new Paragraph("Descripción: " + quotation.get().getDescription()));
        quotation.get().getQuotationDetails().forEach( d -> {
            try {
                document.add(new Paragraph("Producto: " + d.getProduct().getDescription()));
                document.add(new Paragraph("Precio: " + d.getProduct().getSalesPrice()));
                document.add(new Paragraph("Unidad: " + d.getProduct().getUnity().getDescription()));
                document.add(new Paragraph("Cantidad: " + d.getQuantity()));
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }

        });
        document.add(new Paragraph("Monto Total: " + quotation.get().getAmount()));
        // Agrega más detalles según sea necesario
        document.close();

        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=quotation.pdf");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}

